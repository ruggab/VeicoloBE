package it.com.acamir.veicolibe.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.com.acamir.veicolibe.entity.Azienda;

@Repository
public interface AziendaRepository extends JpaRepository<Azienda, Integer>, JpaSpecificationExecutor<Azienda> {
	
	
	 @Query(value = "select * from azienda a"
	    		+ " where (?1 = '' or a.matricola = ?1) and (?2 = '' or a.nominativo_ref = ?2) "
	    		+ " and (?3 = '' or a.mail_ref = ?3) and (?4 = '' or a.tel_ref = ?4) order by a.nominativo_ref" , nativeQuery = true)
	    List<Azienda> getListAziendaByFilter(String matricola, String nominativo, String mail, String tel);
}
