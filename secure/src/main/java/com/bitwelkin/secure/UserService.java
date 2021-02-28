package com.bitwelkin.secure;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmployeeRepository userRepository;
	
	@Autowired
	private OtpRepository otpRepository;
	
	public void adddUser(Employee user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}
	
	public void auth(Employee user) {
		Optional<Employee> o = userRepository.findEmployeeByUsername(user.getUsername());
		
		if(o.isPresent()) {
			Employee u = o.get();
			if(passwordEncoder.matches(user.getPassword(), u.getPassword())) {
				renewOtp(u);
			}else {
				throw new BadCredentialsException("Bad Credentials.");
			}
		}else {
			throw new BadCredentialsException("Bad Credentials.");
		}
		
	}
	
	public boolean check(Otp otpToValidate) {
		Optional<Otp> userOtp = otpRepository.findOtpByUsername(otpToValidate.getUsername());
		if(userOtp.isPresent()) {
			Otp otp = userOtp.get();
			if(otpToValidate.getCode().equals(otp.getCode())) {
				return true;
			}
		}
		return false;
	}

	private void renewOtp(Employee u) {
		// TODO Auto-generated method stub
		String code = GenerateCodeUtil.generateCode();
		Optional<Otp> userOtp = otpRepository.findOtpByUsername(u.getUsername());
		if(userOtp.isPresent()) {
			Otp otp = userOtp.get();
			otp.setCode(code);
		}else {
			Otp otp = new Otp();
			otp.setUsername(u.getUsername());
			otp.setCode(code);
			otpRepository.save(otp);
		}
	}
	
}
