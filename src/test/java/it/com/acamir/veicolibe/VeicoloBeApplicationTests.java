package it.com.acamir.veicolibe;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import it.com.acamir.veicolibe.entity.ERole;
import it.com.acamir.veicolibe.entity.Role;
import it.com.acamir.veicolibe.entity.User;

@SpringBootTest
class VeicoloBeApplicationTests {

	@Autowired
	PasswordEncoder encoder;
	
	@Test
	void contextLoads() {
		// Create new user's account
		System.out.println(encoder.encode("test1234"));
		
		
		
	}

}
