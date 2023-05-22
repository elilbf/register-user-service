package com.gft.registeruserservice.service.impl;

import com.gft.registeruserservice.dto.UserDTO;
import com.gft.registeruserservice.exception.InvalidFieldException;
import com.gft.registeruserservice.exception.UserAlreadyExistsException;
import com.gft.registeruserservice.model.User;
import com.gft.registeruserservice.repository.UserRepository;
import com.gft.registeruserservice.util.EmailDomainProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EmailDomainProperty emailDomainProperty;

    @Mock
    private User user;

    private List<String> abilities;

    private List<String> domains;

    @BeforeEach
    void setUp() {
        abilities = new ArrayList<>();
        abilities.add("Play Guitar");
        abilities.add("Java Developer");
        abilities.add("Good communication");

        domains = new ArrayList<>();
        domains.add("gmail.com");
        domains.add("hotmail.com");
        domains.add("outlook.com");
        domains.add("yahoo.com");
    }

    @DisplayName("Create an user using all fields.")
    @Test
    void givenPayloadWhenRegisterThenCreateUser() {
        when(emailDomainProperty.getDomains()).thenReturn(domains);

        var user = UserDTO.builder()
                .name("Isaac Fábio Jesus")
                .email("isaacfabiojesus@outlook.com")
                .birthDate(LocalDate.of(1990, Month.JANUARY, 7))
                .address("Rua Bom Jesus 213")
                .abilities(abilities)
                .build();

        userService.createUser(user);

        verify(userRepository, times(1)).save(User.parseUser(user));
    }

    @DisplayName("Try to create an user that already exists.")
    @Test
    void givenPayloadWhenUserAlreadyExistsThenThrowException() {
        var user = UserDTO.builder()
                .name("Isaac Fábio Jesus")
                .email("isaacfabiojesus@gmail.com")
                .birthDate(LocalDate.of(1990, Month.JANUARY, 7))
                .address("Rua Bom Jesus 213")
                .abilities(abilities)
                .build();

        when(emailDomainProperty.getDomains()).thenReturn(domains);
        when(userRepository.findUserByNameAndEmail(any(), any())).thenReturn(new User());

        UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class,
                () -> userService.createUser(user));

        assertEquals("The user: Isaac Fábio Jesus already exists. Try register a new user.", exception.getBody().getDetail());
    }

    @DisplayName("Try to create an user with null user name field.")
    @Test
    void givenUserPayloadWhenNullFieldNameThenThrowException() {
        var user = UserDTO.builder()
                .email("isaacfabiojesus@yahoo.com")
                .birthDate(LocalDate.of(1990, Month.JANUARY, 7))
                .address("Rua Bom Jesus 213")
                .abilities(abilities)
                .build();

        InvalidFieldException exception = assertThrows(InvalidFieldException.class,
                () -> userService.createUser(user));

        assertEquals("To register an user you must to fill in the 'name' field.", exception.getBody().getDetail());
    }

    @DisplayName("Try to create an user with blank user name field.")
    @Test
    void givenUserPayloadWhenBlankUserNameThenThrowException() {
        var user = UserDTO.builder()
                .name("")
                .email("isaacfabiojesus@outlook.com")
                .birthDate(LocalDate.of(1990, Month.JANUARY, 7))
                .address("Rua Bom Jesus 213")
                .abilities(abilities)
                .build();

        InvalidFieldException exception = assertThrows(InvalidFieldException.class,
                () -> userService.createUser(user));

        assertEquals("To register an user you must to fill in the 'name' field.", exception.getBody().getDetail());
    }

    @DisplayName("Try to create an user with null user email field.")
    @Test
    void givenUserPayloadWhenNullUserEmailThenThrowException() {
        var user = UserDTO.builder()
                .name("Isaac Fábio Jesus")
                .birthDate(LocalDate.of(1990, Month.JANUARY, 7))
                .address("Rua Bom Jesus 213")
                .abilities(abilities)
                .build();

        InvalidFieldException exception = assertThrows(InvalidFieldException.class,
                () -> userService.createUser(user));

        assertEquals("To register an user you must to fill in the 'email' field.", exception.getBody().getDetail());
    }

    @DisplayName("Try to create an user with blank user email field.")
    @Test
    void givenUserPayloadWhenBlankEmailThenThrowException() {
        var user = UserDTO.builder()
                .name("Isaac Fábio Jesus")
                .email("")
                .birthDate(LocalDate.of(1990, Month.JANUARY, 7))
                .address("Rua Bom Jesus 213")
                .abilities(abilities)
                .build();

        InvalidFieldException exception = assertThrows(InvalidFieldException.class,
                () -> userService.createUser(user));

        assertEquals("To register an user you must to fill in the 'email' field.", exception.getBody().getDetail());
    }

    @DisplayName("Try to create an user with invalid name.")
    @Test
    void givenUserPayloadWhenInvalidNameThenThrowException() {
        var user = UserDTO.builder()
                .name("Isaac Fábio Jesus@")
                .email("isaacfabiojesus@outlook.com")
                .birthDate(LocalDate.of(1990, Month.JANUARY, 7))
                .address("Rua Bom Jesus 213")
                .abilities(abilities)
                .build();

        InvalidFieldException exception = assertThrows(InvalidFieldException.class,
                () -> userService.createUser(user));

        assertEquals("The field 'name' was not correctly informed. Please do not use special characters.", exception.getBody().getDetail());
    }

    @DisplayName("Try to create an user with invalid email")
    @Test
    void givenUserPayloadWhenInvalidEmailThenThrowException() {
        var user = UserDTO.builder()
                .name("Isaac Fábio Jesus")
                .email(".isaacfabiojesus@outlook.com")
                .birthDate(LocalDate.of(1990, Month.JANUARY, 7))
                .address("Rua Bom Jesus 213")
                .abilities(abilities)
                .build();

        InvalidFieldException exception = assertThrows(InvalidFieldException.class,
                () -> userService.createUser(user));

        assertEquals("The field 'email' was not correctly informed.", exception.getBody().getDetail());

    }

    @DisplayName("Try to create an user with invalid email domain.")
    @Test
    void givenUserPayloadWhenInvalidEmailDomainThenThrowException() {
        var user = UserDTO.builder()
                .name("Isaac Fábio Jesus")
                .email("isaacfabiojesus@tuta.io")
                .birthDate(LocalDate.of(1990, Month.JANUARY, 7))
                .address("Rua Bom Jesus 213")
                .abilities(abilities)
                .build();

        InvalidFieldException exception = assertThrows(InvalidFieldException.class,
                () -> userService.createUser(user));

        assertEquals("Your email domain is not valid.", exception.getBody().getDetail());

    }

    @DisplayName("Try to create an user with invalid address.")
    @Test
    void givenUserPayloadWhenInvalidAddressThenThrowException() {
        var user = UserDTO.builder()
                .name("Isaac Fábio Jesus")
                .email("isaacfabiojesus@outlook.com")
                .birthDate(LocalDate.of(1990, Month.JANUARY, 7))
                .address("Rua Bom Jesus, 213")
                .abilities(abilities)
                .build();

        InvalidFieldException exception = assertThrows(InvalidFieldException.class,
                () -> userService.createUser(user));

        assertEquals("The field 'address' was not correctly informed. Please do not use special characters.", exception.getBody().getDetail());

    }

    @DisplayName("Try to create an user with invalid age.")
    @Test
    void givenUserPayloadWhenInvalidAgeThenThrowException() {
        var user = UserDTO.builder()
                .name("Isaac Fábio Jesus")
                .email("isaacfabiojesus@outlook.com")
                .birthDate(LocalDate.of(2018, Month.JANUARY, 7))
                .address("Rua Bom Jesus 213")
                .abilities(abilities)
                .build();

        InvalidFieldException exception = assertThrows(InvalidFieldException.class,
                () -> userService.createUser(user));

        assertEquals("To register, you must be over 18 years old.", exception.getBody().getDetail());
    }

    @DisplayName("Update an user entity when send a new payload and existent user id.")
    @Test
    void givenUserPayloadWhenInformTheIdThenUpdateUser() {
//        when(emailDomainProperty.getDomains()).thenReturn(domains);
//
//        List<Ability> abilityList = new ArrayList<>();
//        abilityList.add(Ability.builder().description("Play Guitar").build());
//        abilityList.add(Ability.builder().description("Java Developer").build());
//        abilityList.add(Ability.builder().description("Good communication").build());
//
//        when(user.getAbilities()).thenReturn(abilityList);
//        when(userRepository.findById(any())).thenReturn(Optional.of(new User()));
//        when(userService.findUserIfExistsById(any())).thenReturn(null);
//
//        var user = UserDTO.builder()
//                .name("Isaac Fábio Jesus")
//                .email("isaacfabiojesus@outlook.com")
//                .birthDate(LocalDate.of(1990, Month.JANUARY, 7))
//                .address("Rua Bom Jesus 213")
//                .abilities(abilities)
//                .build();
//
//        userService.updateUser(1L,user);
//
//        verify(userRepository, times(1)).save(User.parseUser(user));
    }

    @DisplayName("Get all users.")
    @Test
    void givenUserWhenFindThenFindAllUsers() {
        userService.findAllUsers();

        verify(userRepository, times(1)).findAll();
    }

    @DisplayName("Delete an user when an id is informed.")
    @Test
    void givenUserWhenInformTheIdThenDeleteUser() {
        userService.deleteUserById(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }


}
