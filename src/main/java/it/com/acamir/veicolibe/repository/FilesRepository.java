package it.com.acamir.veicolibe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.com.acamir.veicolibe.entity.Files;


@Repository
public interface FilesRepository extends JpaRepository<Files, Long> {
	
	
	public void deleteByIdVeicolo(Long idVeicolo);
	
	public Files findByIdVeicoloAndNomeFile(Long idVeicolo, String nomeFile);
	
}
