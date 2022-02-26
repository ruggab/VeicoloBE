package it.com.acamir.veicolibe.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.com.acamir.veicolibe.entity.Veicolo;

@Repository
public interface VeicoloRepository extends JpaRepository<Veicolo, Integer>, JpaSpecificationExecutor<Veicolo> {
	
	
 @Query(value = "select * from veicolo a"
    		+ " where (?1 = '' or a.matricola = ?1) and (?2 = '' or a.telaio = ?2) "
    		+ " and (?3 = '' or a.assegnatario_id = ?3) order by a.matricola" , nativeQuery = true)
 List<Veicolo> getListVeicoloByFilter(String matricola, String telaio, Integer idAssegnatazio);
	
}
