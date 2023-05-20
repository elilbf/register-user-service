package com.gft.registeruserservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;
    private String address;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Ability> abilities;

    public static List<Ability> parseAbilities(List<String> abilities){
        return abilities.stream().map(e -> Ability.builder()
                                            .description(e)
                                            .build())
                                            .toList();
    }

}
