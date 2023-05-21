package com.gft.registeruserservice.service.impl;

import com.gft.registeruserservice.dto.UserDTO;
import com.gft.registeruserservice.exception.InvalidFieldException;
import com.gft.registeruserservice.exception.UserAlreadyExistsException;
import com.gft.registeruserservice.exception.UserNotFoundException;
import com.gft.registeruserservice.model.User;
import com.gft.registeruserservice.repository.UserRepository;
import com.gft.registeruserservice.service.UserService;
import com.gft.registeruserservice.util.EmailDomainProperty;
import com.gft.registeruserservice.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailDomainProperty emailDomainProperty;

    public void createUser(UserDTO userDTO) {
        UserUtil.validatePayloadFields(userDTO);
        validateDomainEmail(userDTO);

        var user = User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .birthDate(userDTO.getBirthDate())
                .address(userDTO.getAddress())
                .abilities(User.parseAbilities(userDTO.getAbilities())).build();

        if (Objects.isNull(userRepository.findUserByNameAndEmail(userDTO.getName(), userDTO.getEmail()))) {
            userRepository.save(user);
        } else {
            throw new UserAlreadyExistsException(HttpStatus.BAD_REQUEST,
                    "The user: " + user.getName() + " already exists. Try register a new user.");
        }
    }

    @Override
    public void updateUser(Long id, UserDTO userDTO) {
        findUserIfExistsById(id);


    }

    public List<UserDTO> findAllUsers() {
        return userRepository.findAll().stream().map(UserDTO::parseUser).toList();
    }

    public UserDTO findUserIfExistsById(Long id) {
        return UserDTO.parseUser(findUserById(id).get());
    }

    private Optional<User> findUserById(Long id) {
        return Optional.of(userRepository.findById(id)).orElseThrow(() -> new UserNotFoundException(HttpStatus.NOT_FOUND, "User with ID: " + id + " not found!"));
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    private void validateDomainEmail(UserDTO userDTO) {
        boolean isValidDomain = false;

        for (String domain : emailDomainProperty.getDomains()) {
            Pattern patternDomain = Pattern.compile("@" + domain + "$");
            isValidDomain = patternDomain.matcher(userDTO.getEmail()).find();
            if (isValidDomain) {
                break;
            }
        }

        if (!isValidDomain) {
            throw new InvalidFieldException(HttpStatus.BAD_REQUEST, "Your email domain is not valid.");
        }
    }

}
