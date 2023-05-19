package com.gft.registeruserservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class User {

    private String name;
    private String email;
    private Date birthDate;
    private String Address;
    private List<String> habilities;

}
