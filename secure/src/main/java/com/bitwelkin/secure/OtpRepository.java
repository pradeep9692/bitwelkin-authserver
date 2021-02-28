package com.bitwelkin.secure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepository extends JpaRepository<Otp, String>{

	Optional<Otp> findOtpByUsername(String username);
}
