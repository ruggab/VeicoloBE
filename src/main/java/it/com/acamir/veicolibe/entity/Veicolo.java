package it.com.acamir.veicolibe.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Table(name = "veicolo")
@Data
public class Veicolo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id", nullable = false)
	private Azienda assegnatario;

	@Column(length = 5)
	private String matricola;

	@Column(length = 15)
	private String telaio;

	@Column(precision = 10, scale = 2)
	private Long costoAcquistoNettoIva;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id", nullable = false)
	private Dizionario modello;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id", nullable = false)
	private Dizionario categoria;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id", nullable = false)
	private Dizionario tipoAllestimento;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id", nullable = false)
	private Dizionario tipoAlimentazione;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id", nullable = false)
	private Dizionario classe;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id", nullable = false)
	private Dizionario fornitore;

	@ManyToOne
	@JoinColumn(name = "id_dispCopiaCartaCirc")
	private Dizionario dispCopiaCartaCirc;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id", nullable = false)
	private Dizionario regimeProprieta;

	@Column(length = 7)
	private String targa1Imm;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy mm:hh:ss")
	private Date dataPrimaImm;

	@Column(precision = 4, scale = 2)
	private BigDecimal lunghezza;

	@Temporal(TemporalType.DATE)
	private Date dataContrattoAziendaTpl;

	@Temporal(TemporalType.DATE)
	private Date dataConsegnaAdAziendaTpl;

	@Temporal(TemporalType.DATE)
	private Date dataUltimaRevisione;

	@Temporal(TemporalType.DATE)
	private Date dataScadGaranziaBase;

	@Temporal(TemporalType.DATE)
	private Date dataScadGaranziaEstesa;

	

	@Temporal(TemporalType.DATE)
	private Date dataScadUsufrutto;

	@Temporal(TemporalType.DATE)
	private Date dataScadVincolo;

	@Column(precision = 6, scale = 3)
	private Long kmDataRevisione;

	@Column(length = 250)
	private String note;

	private Integer numPorte;

	private Integer NumSimSerialNumber;

	private Integer NumSimTelefonico;

	@Temporal(TemporalType.DATE)
	private Date dataAttivazioneavm;

	@Column(length = 50)
	private String utimaVerIspettiva;

	@Column(length = 50)
	private String indirizzoDepositoRicovero;

	@Column(length = 15)
	private String depositoRicoveroProtComunicazione;

	@Column(length = 45)
	private String username;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInserimento;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAggiornamento;
}
