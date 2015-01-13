package proeza.test.unit.persistence;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncriptarPassword {

	private static final String	PASSWORD	= "proezasgs1234";

	@Test
	public void encodePass () {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode(PASSWORD));
	}
}