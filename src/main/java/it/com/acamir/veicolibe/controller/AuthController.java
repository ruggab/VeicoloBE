package it.com.acamir.veicolibe.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.com.acamir.veicolibe.entity.Azienda;
import it.com.acamir.veicolibe.entity.ERole;
import it.com.acamir.veicolibe.entity.Role;
import it.com.acamir.veicolibe.entity.User;
import it.com.acamir.veicolibe.payload.request.AddUserRequest;
import it.com.acamir.veicolibe.payload.request.ChangePasswordRequest;
import it.com.acamir.veicolibe.payload.request.LoginRequest;
import it.com.acamir.veicolibe.payload.request.SignupRequest;
import it.com.acamir.veicolibe.payload.response.JwtResponse;
import it.com.acamir.veicolibe.payload.response.MessageResponse;
import it.com.acamir.veicolibe.repository.RoleRepository;
import it.com.acamir.veicolibe.repository.UserRepository;
import it.com.acamir.veicolibe.security.services.UserDetailsImpl;

import it.com.acamir.veicolibe.security.jwt.JwtUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles, userDetails.getAziendas()));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		List<Role> roles = new ArrayList<Role>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				// case "mod":
				// Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR).orElseThrow(() -> new
				// RuntimeException("Error: Role is not found."));
				// roles.add(modRole);
				//
				// break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@PostMapping("/changePassword")
	public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordRequest cpRequest) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		User user = userRepository.findByUsername(cpRequest.getUsername()).get();
		if (user == null) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Attenzione username non esistente"));
		}
		boolean matchPasw = passwordEncoder.matches(cpRequest.getOldPassword(), user.getPassword());
		if (!matchPasw) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Attenzione la vecchia password non è corretta"));
		}
		//
		user.setPassword(passwordEncoder.encode(cpRequest.getPassword()));
		userRepository.save(user);
		//
		return ResponseEntity.ok(new MessageResponse("Password Modificata Correttamente"));
	}

	@PostMapping("/addUser")
	public ResponseEntity<?> addUser(@Valid @RequestBody AddUserRequest addUserRequest) {
		//
		User user = null;
		if (addUserRequest.getId() == null) {
			if (userRepository.existsByUsername(addUserRequest.getUsername())) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: Username già in uso!"));
			}
			//
			if (userRepository.existsByEmail(addUserRequest.getEmail())) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: Email già in uso!"));
			}
			user = new User(addUserRequest.getUsername(), addUserRequest.getEmail(), encoder.encode(addUserRequest.getPassword()));
		} else {
			user = userRepository.findById(addUserRequest.getId()).get();
			user.setEmail(addUserRequest.getEmail());
			user.setPassword(encoder.encode(addUserRequest.getPassword()));
		}

		// Aggiungi ruolo user
		List<Role> roles = new ArrayList<Role>();
		Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(userRole);
		user.setRoles(roles);
		// Aggiungi Aziende
		List<Azienda> aziendas = new ArrayList<Azienda>();
		aziendas.add(addUserRequest.getAzienda());
		user.setAziendas(aziendas);
		//
		userRepository.save(user);
		//
		return ResponseEntity.ok(new MessageResponse("Utente Aggiornato Correttamente"));
	}

	@PostMapping("/getListUtenteByFilter")
	public List<User> getListUtenteByFilter(@RequestBody User user) {

		Integer idAz = null;
		if (user.getAziendas() != null && user.getAziendas().size() > 0 && user.getAziendas().get(0) != null) {
			idAz = user.getAziendas().get(0).getId();
		}

		List<User> listaUtente = userRepository.getListUtenteByFilter(user.getUsername(), idAz);
		return listaUtente;
	}

}
