package com.skincare.backend.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.skincare.backend.domain.dto.auth.AuthResponse;
import com.skincare.backend.domain.dto.user.UserCreateDto;
import com.skincare.backend.domain.dto.user.UserGetDto;
import com.skincare.backend.domain.dto.user.UserLogInDto;
import com.skincare.backend.domain.entity.userdata.UserData;
import com.skincare.backend.exception.UserNotFoundException;
import com.skincare.backend.exception.UserPasswordIncorrectException;
import com.skincare.backend.mapper.UserDataMapper;
import com.skincare.backend.repository.UserDataRepository;
import com.skincare.backend.service.UserDataService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserDataServiceImpl implements UserDataService {

    private static final String SECRET_KEY = "MY_SUPER_SECRET_KEY_123456";
    private final UserDataRepository userDataRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDataServiceImpl(UserDataRepository userDataRepository,
                               PasswordEncoder passwordEncoder) {
        this.userDataRepository = userDataRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponse registerUser(UserCreateDto userCreateDto) {
        UserData user = UserDataMapper.INSTANCE.userCreateDtoToUserData(userCreateDto);

        String hashedPassword = passwordEncoder.encode(userCreateDto.getPassword());
        user.setPassword(hashedPassword);
        user.setRole("ROLE_USER");

        UserData savedUser = userDataRepository.save(user);

        String token = generateJwtToken(savedUser);

        UserGetDto userGetDto = UserDataMapper.INSTANCE.userToUserGetDto(savedUser);

        return new AuthResponse(token, userGetDto);
    }

    @Override
    public AuthResponse loginUser(UserLogInDto userLogInDto) {
        UserData user = userDataRepository.findUserDataByEmail(userLogInDto.getEmail())
                .orElseThrow(() -> new UserNotFoundException(
                        "Cannot find user with email " + userLogInDto.getEmail()
                ));

        if (!passwordEncoder.matches(userLogInDto.getPassword(), user.getPassword())) {
            throw new UserPasswordIncorrectException("Incorrect password for " + userLogInDto.getEmail());
        }

        String token = generateJwtToken(user);

        UserGetDto userGetDto = UserDataMapper.INSTANCE.userToUserGetDto(user);

        return new AuthResponse(token, userGetDto);
    }

    private String generateJwtToken(UserData user) {
        return JWT.create()
                .withSubject(String.valueOf(user.getId()))
                .withClaim("email", user.getEmail())
                .withClaim("role", user.getRole())
                .withExpiresAt(new Date(System.currentTimeMillis() + 86_400_000))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }
}
