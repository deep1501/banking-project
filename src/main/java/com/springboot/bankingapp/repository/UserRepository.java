package com.springboot.bankingapp.repository;

import com.springboot.bankingapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
