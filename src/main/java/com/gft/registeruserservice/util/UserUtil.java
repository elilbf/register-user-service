package com.gft.registeruserservice.util;

import com.gft.registeruserservice.dto.UserDTO;
import com.gft.registeruserservice.exception.InvalidFieldException;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class UserUtil {

    //    private static final String VALID_EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private static final String VALID_EMAIL_REGEX = "'[a-z0-9]+@[a-z]+\\.[a-z]{2,3}'";
    private static int MIN_AGE = 18;

    public static void validatePayloadFields(UserDTO userDTO) {
        nameValidate(userDTO.getName());
        emailValidate(userDTO.getEmail());
        ageOlderValidate(userDTO.getBirthDate());

    }

    private static void nameValidate(String name) {
        if (!name.matches("^[a-zA-Z]+$")) {
            throw new InvalidFieldException(HttpStatus.BAD_REQUEST,
                    "The field 'name' was not correctly infomed. Please do not use special characters.");
        }
    }

    private static void emailValidate(String email) {
        if (!email.matches(VALID_EMAIL_REGEX)) {
            throw new InvalidFieldException(HttpStatus.BAD_REQUEST,
                    "The field 'email' was not correctly informed.");
        }
    }

    private static void ageOlderValidate(LocalDate birthDate) {
        if (Objects.nonNull(birthDate) && Period.between(birthDate, LocalDate.now()).getYears() < MIN_AGE) {
            throw new InvalidFieldException(HttpStatus.BAD_REQUEST,
                    "To register, you must be over 18 years old.");
        }
    }

}
