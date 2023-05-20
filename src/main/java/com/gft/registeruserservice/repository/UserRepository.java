package com.gft.registeruserservice.repository;

import com.gft.registeruserservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ListCrudRepository<User, Long>, JpaRepository<User, Long> {

    User findUserByNameAndEmail(String name, String email);
}
