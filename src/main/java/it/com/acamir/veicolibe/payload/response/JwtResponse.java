package it.com.acamir.veicolibe.payload.response;

import java.util.List;

import it.com.acamir.veicolibe.entity.Azienda;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	private String email;
	private List<String> roles;
	private List<Azienda> aziendas;

	public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles, List<Azienda> aziendas) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
		this.aziendas = aziendas;
		
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}

	public List<Azienda> getAziendas() {
		return aziendas;
	}

	public void setAziendas(List<Azienda> aziendas) {
		this.aziendas = aziendas;
	}
	
	
}