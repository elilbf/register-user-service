package com.gft.registeruserservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
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
    private Date birthDate;
    private String address;
    private List<String> abilities = new ArrayList<>();

}
