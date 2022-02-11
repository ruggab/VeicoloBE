package it.com.acamir.veicolibe.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.com.acamir.veicolibe.entity.Veicolo;

@Repository
public interface VeicoloRepository extends JpaRepository<Veicolo, Integer>, JpaSpecificationExecutor<Veicolo> {
	
	
	
//	public List<Veicolo> findDatiVeicoloByIdAziendaAndIdUtente(Azienda azienda, String idUtente);
//	
//	public List<Veicolo> findDatiVeicoloByIdAzienda(Azienda azienda);
//	
//	@Query(value = "SELECT b.* FROM veicoli.dati_veicolo b where b.idAzienda = ?1 and b.idUtente = ?2 and b.telaio = ?3"
//			+ " and b.targa = ?4 and  b.matricola = ?5 and  b.modello = ?6 and  b.targa = ?7"
//			+ " order by b.dataAggiornamento desc", nativeQuery=true )
//	public List<Veicolo> findVeicoliBy(String idAzienda, String utente, String telaio, String targa, String matricola, String modello, String marca);
//	
//	@Query(value = "SELECT a.* FROM veicoli.dati_veicolo a, (SELECT  max(b.dataAggiornamento) as dataAgg, b.id  FROM dati_veicolo b GROUP BY telaio) c where  a.id = c.id  order by a.dataAggiornamento desc" , nativeQuery=true )
//	public List<Veicolo> findAllActive();
//	
//	@Query(value = "SELECT a.* FROM veicoli.dati_veicolo a, (SELECT  max(b.dataAggiornamento) as dataAgg, b.id  FROM dati_veicolo b where b.idAzienda = 1 and idUtente = ?2 GROUP BY telaio) c where  a.id = c.id order by a.dataAggiornamento desc", nativeQuery=true )
//	public List<Veicolo> findActiveDatiVeicoloByIdAziendaAndIdUtente(Azienda azienda, String idUtente);
//	
//	@Query(value = "SELECT a.* FROM veicoli.dati_veicolo a, (SELECT  max(b.dataAggiornamento) as dataAgg, b.id  FROM dati_veicolo b where b.idAzienda = ?1 GROUP BY telaio) c where  a.id = c.id order by a.dataAggiornamento desc", nativeQuery=true )
//	public List<Veicolo> findActiveDatiVeicoloByIdAzienda(Azienda azienda);
//	
//	@Query(value = "SELECT a.* FROM veicoli.dati_veicolo a, (SELECT  max(b.id) as maxid  FROM dati_veicolo b "
//			+ "where 1 = 1 "
//			+ " and (:idAzienda is null or b.idAzienda like %:idAzienda% )" 
//			+ " and (:utente is null or b.idUtente like %:utente% )"
//			+ " and (:telaio = '' or b.telaio like %:telaio% )"
//			+ " and (:targa = '' or b.targa like %:targa% )" 
//			+ " and (:matricola = '' or b.matricola like %:matricola% )"
//			+ " and (:modello = '' or b.modello like %:modello% )"
//			+ " and (:marca = '' or b.marca like %:marca% )"
//			+ " GROUP BY telaio) c where a.id = c.maxid order by a.dataAggiornamento desc", nativeQuery=true )
//	public List<Veicolo> findDatiVeicoloBy(@Param("idAzienda") Integer idAzienda, @Param("utente") String utente, @Param("telaio") String telaio, @Param("targa") String targa, @Param("matricola") String matricola, @Param("modello") String modello, @Param("marca") String marca);
	

	@Query(value = "SELECT b.* FROM veicoli.dati_veicolo b "
			+ " where b.stato = 1 "
			+ " and (:idAzienda is null or b.idAzienda like %:idAzienda% )" 
			+ " and (:utente is null or b.idUtente like %:utente% )"
			+ " and (:telaio = '' or b.telaio like %:telaio% )"
			+ " and (:targa = '' or b.targa like %:targa% )" 
			+ " and (:matricola = '' or b.matricola like %:matricola% )"
			+ " and (:modello = '' or b.modello like %:modello% )"
			+ " and (:marca = '' or b.marca like %:marca% )"
			+ " order by b.dataAggiornamento desc, b.stato desc, b.telaio desc", nativeQuery=true )
	public List<Veicolo> findDatiVeicoloBy(@Param("idAzienda") Integer idAzienda, @Param("utente") String utente, @Param("telaio") String telaio, @Param("targa") String targa, @Param("matricola") String matricola, @Param("modello") String modello, @Param("marca") String marca);
	
	
	
	
	
	@Query(value = "SELECT b.* FROM veicoli.dati_veicolo b "
			+ " where 1 = 1 "
			+ " and (:idAzienda is null or b.idAzienda like %:idAzienda% )" 
			+ " and (:utente is null or b.idUtente like %:utente% )"
			+ " and (:telaio = '' or b.telaio like %:telaio% )"
			+ " and (:targa = '' or b.targa like %:targa% )" 
			+ " and (:matricola = '' or b.matricola like %:matricola% )"
			+ " and (:modello = '' or b.modello like %:modello% )"
			+ " and (:marca = '' or b.marca like %:marca% )"
			+ " order by b.dataAggiornamento desc, b.stato desc, b.telaio desc", nativeQuery=true )
	public List<Veicolo> findAllDatiVeicoloBy(@Param("idAzienda") Integer idAzienda, @Param("utente") String utente, @Param("telaio") String telaio, @Param("targa") String targa, @Param("matricola") String matricola, @Param("modello") String modello, @Param("marca") String marca);
	
	
	public List<Veicolo> findDatiVeicoloByTelaio(String telaio);
}
