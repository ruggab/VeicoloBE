package it.com.acamir.veicolibe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name = "dizionario")
@Data
public class Dizionario {

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(length = 30)
	private String codice;
	
	@Column(length = 30)
	private String contesto;
	
	@Column(name = "descrizione", length = 200)
	private String descrizione;
}
