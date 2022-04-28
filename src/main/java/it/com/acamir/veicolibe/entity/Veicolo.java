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
	private Long id;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id", nullable = false)
	private Gara gara;

	@Column(length = 5)
	private String matricola;

	@Column(length = 15)
	private String telaio;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id", nullable = false)
	private Azienda assegnatario;

	@Column(precision = 10, scale = 2)
	private Long costoAcquistoNettoIva;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Dizionario modello;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Dizionario categoria;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Dizionario tipoAllestimento;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Dizionario tipoAlimentazione;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Dizionario classe;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Dizionario classeAmbientale;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Dizionario fornitore;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Dizionario regimeProprieta;

	@Column(length = 7)
	private String targa1Imm;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy mm:hh:ss")
	private Date dataPrimaImm;

	@Column(precision = 4, scale = 2)
	private BigDecimal lunghezza;

	@Temporal(TemporalType.DATE)
	private Date dataScadUsufrutto;

	@Temporal(TemporalType.DATE)
	private Date dataScadVincolo;

	private Integer numPorte;

	private Integer NumSimSerialNumber;

	private Integer NumSimTelefonico;

	@Temporal(TemporalType.DATE)
	private Date dataAttivazioneAvm;

	@Column(length = 50)
	private String utimaVerIspettiva;

	@Column(length = 250)
	private String noteVerificaIsp;

	@Column(length = 15)
	private String numProtocolloRicovero;

	@Column(length = 45)
	private String username;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInserimento;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAggiornamento;

	@Column(length = 6)
	private String matricolaAz;

	@Column(length = 30)
	private String determAssegnazione;

	@Column(length = 50)
	private String estremiContrUsufrutto;

	@Temporal(TemporalType.DATE)
	private Date dataContrUsufrutto;

	private Double valAnnuoCanone;

	private Double valPrimoCanone;

	private Double valDa2a8Canone;

	private Double val9Canone;

	private Double val10Canone;

	private Double val11Canone;

	@Temporal(TemporalType.DATE)
	private Date dataConsegnaAziendaTpl;

	@Temporal(TemporalType.DATE)
	private Date dataContrattoApplAziendaTpl;

	@Temporal(TemporalType.DATE)
	private Date dataContrattoAssegnAziendaTpl;

	@Temporal(TemporalType.DATE)
	private Date dataScadTassaPossesso;

	@Temporal(TemporalType.DATE)
	private Date dataScadRca;

	@Column(length = 50)
	private String protComSituazApparati;

	@Column(length = 15)
	private String protArrivoAcamMessADispCons;

	// Dati Polizza
	@Column(length = 15)
	private String protFidGaranziaBase;

	@Column(length = 15)
	private String protFidGaranziaEstesa;

	private String numPolGaranziaBase;

	private String numPolGaranziaEstesa;

	@Temporal(TemporalType.DATE)
	private Date dataScadPolGaranziaBase;

	@Temporal(TemporalType.DATE)
	private Date dataScadPolGaranziaEstesa;

	@Temporal(TemporalType.DATE)
	private Date dataScadGaranziaBase;

	@Temporal(TemporalType.DATE)
	private Date dataScadGaranziaEstesa;

	@Column(length = 50)
	private String protComReferente;

	@Temporal(TemporalType.DATE)
	private Date dataUltimaVerificaIsp;

	@Column(length = 50)
	private String estremiProtRappVerIsp;

	// Dati Azienda

	@Column(length = 150)
	private String contrattoServizio;

	@Temporal(TemporalType.DATE)
	private Date dataUltimaRevisione;

	@Column(precision = 6, scale = 3)
	private Long kmDataRevisione;

	@Temporal(TemporalType.DATE)
	private Date dataCessMarcia;

	@Column(length = 150)
	private String motivoFermo;

	@Column(length = 50)
	private String indirizzoDepositoRicovero;
	
	
	//Files
	@Column(length = 150)
	private String nomefileCC;
	@Column(length = 150)
	private String nomefileCCI;
	@Column(length = 150)
	private String nomefileELA;
	@Column(length = 150)
	private String nomefileVC;
	@Column(length = 150)
	private String nomefileTitPropFR;
	
	

	
    

}
