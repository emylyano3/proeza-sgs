package proeza.test.unit.persistence;

import java.sql.Timestamp;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.proeza.core.util.date.DateUtil;

public class EncriptarPassword {

    private static final String PASSWORD = "prod_admin";

    @Test
    public void encodePass () {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode(PASSWORD));
    }

    @Test
    public void aux () {
        System.out.println(new Timestamp(DateUtil.createNow().getTime()));
    }
}