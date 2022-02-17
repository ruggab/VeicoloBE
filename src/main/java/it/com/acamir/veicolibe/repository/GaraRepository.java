package it.com.acamir.veicolibe.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import it.com.acamir.veicolibe.entity.Gara;

@Repository
public interface GaraRepository extends JpaRepository<Gara, Integer>, JpaSpecificationExecutor<Gara> {
	
	
 @Query(value = "select * from gara a"
    		+ " where (?1 = '' or a.cod_gara = ?1) and (?2 = '' or a.cig = ?2) "
    		+ " and (?3 = '' or a.sup = ?3) and (?4 = '' or a.rup = ?4) and (?5 = '' or a.dec = ?5) order by a.cod_gara" , nativeQuery = true)
 List<Gara> getListGaraByFilter(String cod_gara, String cig, String cup, String rup,String dec);
	 

	 
}
