package it.com.acamir.veicolibe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "azienda")
@Data
public class Azienda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true)
	private String nome;
	
	@Column(unique=true)
	private String matricola;
	
	private String nominativoRef;
	
	
	private String mailRef;
	
	
	private String telRef;

}
