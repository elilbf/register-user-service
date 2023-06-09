package com.gft.registeruserservice.model;

import com.gft.registeruserservice.dto.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String name;
    private String email;
    private LocalDate birthDate;
    private String address;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Ability> abilities;

    public static List<Ability> parseAbilities(List<String> abilities) {
        return abilities.stream().map(e -> Ability.builder()
                                            .description(e)
                                            .build())
                                            .toList();
    }

    public static User parseUser(UserDTO userDTO){
        return User.builder()
                .userId(userDTO.getId())
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .birthDate(userDTO.getBirthDate())
                .address(userDTO.getAddress())
                .abilities(parseAbilities(userDTO.getAbilities()))
                .build();
    }

}
