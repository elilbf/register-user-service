package com.gft.registeruserservice.service.impl;

import com.gft.registeruserservice.dto.UserDTO;
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

    @BeforeEach
    void setUp() {
        List<String> domains = new ArrayList<>();
        domains.add("gmail.com");
        domains.add("hotmail.com");
        domains.add("outlook.com");
        domains.add("yahoo.com");

        when(emailDomainProperty.getDomains()).thenReturn(domains);
    }

    @DisplayName("Create an user using all fields.")
    @Test
    void givenPayloadWhenRegisterThenCreateUser() {
        List<String> abilities = new ArrayList<>();
        abilities.add("Play Guitar");
        abilities.add("Java Developer");
        abilities.add("Good communication");

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
        List<String> abilities = new ArrayList<>();
        abilities.add("Play Guitar");
        abilities.add("Java Developer");
        abilities.add("Good communication");

        var user = UserDTO.builder()
                .name("Isaac Fábio Jesus")
                .email("isaacfabiojesus@outlook.com")
                .birthDate(LocalDate.of(1990, Month.JANUARY, 7))
                .address("Rua Bom Jesus 213")
                .abilities(abilities)
                .build();

        when(userRepository.findUserByNameAndEmail(any(), any())).thenReturn(new User());

        UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class,
                () -> userService.createUser(user));

        assertEquals("The user: Isaac Fábio Jesus already exists. Try register a new user.", exception.getBody().getDetail());
    }


}
