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
    private static final int MIN_AGE = 18;
    private static final String FIELD_NAME = "name";
    private static final String FIELD_ADDRESS = "address";

    public static void validatePayloadFields(UserDTO userDTO) {
        nameValidate(userDTO.getName());
        emailValidate(userDTO.getEmail());
        ageOlderValidate(userDTO.getBirthDate());
        addressValidate(userDTO.getAddress());
    }

    private static void nameValidate(String name) {
        if(Objects.isNull(name) || name.isBlank()){
            throw new InvalidFieldException(HttpStatus.BAD_REQUEST,
                    "To register an user you must to fill in the 'name' field.");
        }

        validateField(name, true, true, FIELD_NAME);
    }

    private static void addressValidate(String address) {
        validateField(address, true, false, FIELD_ADDRESS);
    }

    private static void emailValidate(String email) {
        if(Objects.isNull(email) || email.isBlank()){
            throw new InvalidFieldException(HttpStatus.BAD_REQUEST,
                    "To register an user you must to fill in the 'email' field.");
        } else if(!email.matches(VALID_EMAIL_REGEX)){
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

    private static void validateField(String field, boolean containSpecialCharacters, boolean containNumbers, String fieldName) {
        boolean constainsSymbols = false;
        boolean containsNumber = false;

        String nameWithoutAccent = Normalizer.normalize(field, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        if (containSpecialCharacters) {
            Pattern specialCharacters = Pattern.compile(SPECIAL_CHARACTERS_REGEX, Pattern.CASE_INSENSITIVE);
            Matcher specialCharactersMatcher = specialCharacters.matcher(nameWithoutAccent);
            constainsSymbols = specialCharactersMatcher.find();
        }

        if (containNumbers) {
            Pattern number = Pattern.compile(NUMBER_REGEX, Pattern.CASE_INSENSITIVE);
            Matcher matcherNumber = number.matcher(nameWithoutAccent);
            containsNumber = matcherNumber.find();
        }

        if (constainsSymbols || containsNumber) {
            throw new InvalidFieldException(HttpStatus.BAD_REQUEST,
                    "The field '" + fieldName + "' was not correctly informed. Please do not use special characters.");
        }
    }

}
