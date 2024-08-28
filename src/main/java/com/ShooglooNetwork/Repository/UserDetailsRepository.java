package com.ShooglooNetwork.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ShooglooNetwork.model.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
	
	Optional<UserDetails> findByEmail(String mail);

}
