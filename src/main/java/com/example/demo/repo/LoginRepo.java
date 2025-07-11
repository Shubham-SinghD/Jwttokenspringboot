package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.LoginData;

public interface LoginRepo extends JpaRepository<LoginData, String>{

	Optional<LoginData> findByEmailIgnoreCase(String email);

	LoginData findByMobile(long mobile);

}
