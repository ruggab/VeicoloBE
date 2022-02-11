package it.com.acamir.veicolibe.security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.com.acamir.veicolibe.entity.User;
import it.com.acamir.veicolibe.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUsername(username);
		
		user.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		UserDetailsImpl userdet= null;
		try {
			userdet= UserDetailsImpl.build(user.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userdet;
	}

}