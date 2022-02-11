package it.com.acamir.veicolibe.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.com.acamir.veicolibe.entity.Azienda;
import it.com.acamir.veicolibe.repository.AziendaRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")
public class AziendaController  {

	@Autowired
	AziendaRepository aziendaRepository;

	public long countAllAziendas() {
		return aziendaRepository.count();
	}

	public void deleteAzienda(Azienda azienda) {
		aziendaRepository.delete(azienda);
	}

	public Azienda findAzienda(Integer id) {
		Optional<Azienda> aa = aziendaRepository.findById(id);
		return aa.get();
	}

	public List<Azienda> findAllAziendas() {
		return aziendaRepository.findAll();
	}

	public List<Azienda> findAziendaEntries(int firstResult, int maxResults) {
		return aziendaRepository.findAll();
	}

	public void saveAzienda(Azienda azienda) {
		aziendaRepository.save(azienda);
	}

	public Azienda updateAzienda(Azienda azienda) {
		return aziendaRepository.save(azienda);
	}
}
