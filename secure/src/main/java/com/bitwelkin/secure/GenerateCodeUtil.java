package com.bitwelkin.secure;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class GenerateCodeUtil {

	public static String generateCode() {
		// TODO Auto-generated method stub
		String code;
		try {
			SecureRandom random = SecureRandom.getInstanceStrong();
			int c = random.nextInt(9000) + 1000;
			code = String.valueOf(c);
		}catch(NoSuchAlgorithmException e) {
			throw new RuntimeException("proble when generating random code");
		}
		return code;
	}

}
