package com.victorze.secondhandmarket.repositories;

import com.victorze.secondhandmarket.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstByEmail(String email);

}
