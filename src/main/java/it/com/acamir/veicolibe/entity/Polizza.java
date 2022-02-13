package it.com.acamir.veicolibe.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Table(name = "polizza")
@Data
public class Polizza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 20)
	private String codPolizza;

	@OneToMany
	@JoinTable(name = "polizza_veicoli", joinColumns = { @JoinColumn(name = "polizza_id") }, inverseJoinColumns = { @JoinColumn(name = "veicolo_id") })
	private List<Veicolo> veicoli;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy mm:hh:ss")
	private Date dataScadPolizzaGaranziaBase;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy mm:hh:ss")
	private Date dataScadPolizzaGaranziaEstesa;

}
