package proeza.test.unit.persistence;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncriptarPassword {

	private static final String	PASSWORD	= "user_admin";

	@Test
	public void encodePass () {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode(PASSWORD));
	}
}