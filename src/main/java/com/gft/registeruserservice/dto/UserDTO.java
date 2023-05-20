package com.gft.registeruserservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDTO {

    private String name;
    private String email;
    private Date birthDate;
    private String address;
    private List<String> abilities;

}
