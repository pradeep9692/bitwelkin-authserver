package com.bitwelkin.secure;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/user/add")
	public void addUser(@RequestBody Employee user) {
		userService.adddUser(user);
	}
	
	@PostMapping("/user/auth")
	public void auth(@RequestBody Employee user) {
		userService.auth(user);
	}
	
	@PostMapping("/otp/check")
	public void check(@RequestBody Otp otp, HttpServletResponse response) {
		if (userService.check(otp)) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}
	}
}
