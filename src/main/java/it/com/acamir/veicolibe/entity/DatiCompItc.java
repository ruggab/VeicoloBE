package it.com.acamir.veicolibe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "componente_itc")
@Data
public class DatiCompItc {

	@Id
	private Integer id;
	
	@Column(length = 100)
	private String seriale;
	
	@Column(length = 100)
	private String modello;
	
	@Column(length = 100)
	private String marca;

}
