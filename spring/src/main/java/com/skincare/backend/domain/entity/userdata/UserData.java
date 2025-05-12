package com.skincare.backend.domain.entity.userdata;

import com.skincare.backend.domain.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String email;

    private String password;

    private String role;
}
