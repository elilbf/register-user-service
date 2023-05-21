package com.gft.registeruserservice.util;

import com.gft.registeruserservice.dto.UserDTO;
import com.gft.registeruserservice.exception.InvalidFieldException;
import org.springframework.http.HttpStatus;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UserUtil {

    private static final String VALID_EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private static final String SPECIAL_CHARACTERS_REGEX = "[^a-z0-9 ]";
    private static final String NUMBER_REGEX = "[0-9]";
    private static int MIN_AGE = 18;

    public static void validatePayloadFields(UserDTO userDTO) {
        nameValidate(userDTO.getName());
        emailValidate(userDTO.getEmail());
        ageOlderValidate(userDTO.getBirthDate());
    }

    private static void nameValidate(String name) {
        String nameWithoutAccent = Normalizer.normalize(name, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        Pattern specialCharacters = Pattern.compile(SPECIAL_CHARACTERS_REGEX, Pattern.CASE_INSENSITIVE);
        Pattern number = Pattern.compile(NUMBER_REGEX, Pattern.CASE_INSENSITIVE);

        Matcher matcher = specialCharacters.matcher(nameWithoutAccent);
        Matcher matcherNumber = number.matcher(nameWithoutAccent);

        boolean constainsSymbols = matcher.find();
        boolean containsNumber = matcherNumber.find();

        if (constainsSymbols || containsNumber) {
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
