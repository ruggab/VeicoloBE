package it.com.acamir.veicolibe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.com.acamir.veicolibe.entity.Azienda;

@Repository
public interface AziendaRepository extends JpaRepository<Azienda, Long>, JpaSpecificationExecutor<Azienda> {

	@Query(value = "select * from azienda a" + " where (?1 = '' or a.matricola like %?1%) and (?2 = '' or a.nome like %?2%) and (?3 = '' or a.nominativo_ref like %?3%) " + " and (?4 = '' or a.mail_ref like %?4%) order by a.nome", nativeQuery = true)
	List<Azienda> getListAziendaByFilter(String matricola, String nome, String nominativo, String mail);

	@Query(value = "select * from azienda a where id <> 1", nativeQuery = true)
	List<Azienda> getListAziendaNoAcamir();
	
	
	List<Azienda> findAllByOrderByMatricola();
}
