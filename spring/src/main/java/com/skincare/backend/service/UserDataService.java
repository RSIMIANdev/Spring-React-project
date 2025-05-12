package com.skincare.backend.service;

import com.skincare.backend.domain.dto.auth.AuthResponse;
import com.skincare.backend.domain.dto.user.UserCreateDto;
import com.skincare.backend.domain.dto.user.UserLogInDto;

public interface UserDataService {
    AuthResponse registerUser(UserCreateDto userCreateDto);
    AuthResponse loginUser(UserLogInDto userLogInDto);
}
