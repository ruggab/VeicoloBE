package it.com.acamir.veicolibe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.com.acamir.veicolibe.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	Optional<User> findByUsernameAndPassword(String username, String password);
	
	
	 @Query(value = "select a.* from users a inner join users_azienda b on a.id = b.user_id"
	    		+ " where (?1 = '' or a.username = ?1) and (?2 is null or b.azienda_id = ?2) " , nativeQuery = true)
	 List<User> getListUtenteByFilter(String username,  Long idAzienda);
}