package it.com.acamir.veicolibe.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "files")
@Data
public class Files {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long idVeicolo;

	private String nomeFile;

	private String tipoFile;

	@Lob
	private byte[] data;

	public Files() {
	}

	public Files(Long idVeicolo, String name, String type, byte[] data) {
		this.idVeicolo = idVeicolo;
		this.nomeFile = name;
		this.tipoFile = type;
		this.data = data;
	}

}
