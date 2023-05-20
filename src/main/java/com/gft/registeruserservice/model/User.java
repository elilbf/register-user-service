package com.gft.registeruserservice.model;



import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private Date birthDate;


//    private String Address;
//    private List<String> habilities;

}
