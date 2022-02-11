package it.com.acamir.veicolibe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "gara")
@Data
public class Gara {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 15)
	private String codGara;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id", nullable = true)
	private Dizionario finanziamentoAcquisto;
	
	
	@Column(length = 15)
	private String codUnicProg;
	
	@Column(length = 10)
	private String codIdeGara;
	
	@Column(length = 20)
	private String respUnicProc;
	
	@Column(length = 20)
	private String dirEsecCont;

}
