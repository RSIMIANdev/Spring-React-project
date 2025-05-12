package com.skincare.backend.mapper;

import com.skincare.backend.domain.dto.user.UserCreateDto;
import com.skincare.backend.domain.dto.user.UserGetDto;
import com.skincare.backend.domain.entity.userdata.UserData;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-12T22:27:04+0300",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.2.jar, environment: Java 21.0.7 (Microsoft)"
)
public class UserDataMapperImpl implements UserDataMapper {

    @Override
    public UserGetDto userToUserGetDto(UserData user) {
        if ( user == null ) {
            return null;
        }

        UserGetDto.UserGetDtoBuilder userGetDto = UserGetDto.builder();

        userGetDto.id( user.getId() );
        userGetDto.firstName( user.getFirstName() );
        userGetDto.lastName( user.getLastName() );
        userGetDto.gender( user.getGender() );
        userGetDto.age( user.getAge() );
        userGetDto.email( user.getEmail() );

        return userGetDto.build();
    }

    @Override
    public UserData userCreateDtoToUserData(UserCreateDto userCreateDto) {
        if ( userCreateDto == null ) {
            return null;
        }

        UserData.UserDataBuilder userData = UserData.builder();

        userData.firstName( userCreateDto.getFirstName() );
        userData.lastName( userCreateDto.getLastName() );
        userData.age( userCreateDto.getAge() );
        userData.gender( userCreateDto.getGender() );
        userData.email( userCreateDto.getEmail() );
        userData.password( userCreateDto.getPassword() );

        return userData.build();
    }
}
