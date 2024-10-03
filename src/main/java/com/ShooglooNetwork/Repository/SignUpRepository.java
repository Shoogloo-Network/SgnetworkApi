package com.ShooglooNetwork.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ShooglooNetwork.dto.UserDto;
import com.ShooglooNetwork.model.SignUp;

public interface SignUpRepository extends JpaRepository<SignUp, Long> {

	SignUp findByEmail(String email);

	SignUp findByEmailAndPassword(String email,String password);

	SignUp findByToken(String token);

}
