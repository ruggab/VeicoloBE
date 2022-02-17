package it.com.acamir.veicolibe.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import it.com.acamir.veicolibe.entity.Dizionario;

@Repository
public interface DizionarioRepository extends JpaRepository<Dizionario, Integer>, JpaSpecificationExecutor<Dizionario> {
	
	
	    List<Dizionario> findByContesto(String contesto);

	 
}
