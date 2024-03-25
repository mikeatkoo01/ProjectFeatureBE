package com.LBG.legacy.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LBG.legacy.domain.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}