package com.gft.registeruserservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.gft.registeruserservice.model.Ability;
import com.gft.registeruserservice.model.User;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private String name;
    private String email;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;
    private String address;
    private List<String> abilities = new ArrayList<>();

    public static UserDTO parseUser(User user) {
        return UserDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .birthDate(user.getBirthDate())
                .abilities(user.getAbilities().stream().map(Ability::getDescription).toList())
                .build();
    }

}
