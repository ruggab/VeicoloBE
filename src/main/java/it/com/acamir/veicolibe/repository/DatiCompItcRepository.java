package it.com.acamir.veicolibe.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import it.com.acamir.veicolibe.entity.DatiCompItc;

@Repository
public interface DatiCompItcRepository extends JpaRepository<DatiCompItc, Integer>, JpaSpecificationExecutor<DatiCompItc> {
}
