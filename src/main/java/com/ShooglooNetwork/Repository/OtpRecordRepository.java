package com.ShooglooNetwork.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ShooglooNetwork.model.OtpRecord;

public interface OtpRecordRepository extends JpaRepository<OtpRecord, Long> {

	Optional<OtpRecord> findByEmail(String email);

}
