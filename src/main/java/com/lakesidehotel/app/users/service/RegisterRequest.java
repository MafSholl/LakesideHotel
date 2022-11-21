package com.lakesidehotel.app.users.service;

import com.lakesidehotel.app.users.model.Gender;
import com.lakesidehotel.app.users.model.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private Gender gender;
    private String confirmPassword;
    private UserType staffType;
}
