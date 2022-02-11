package it.com.acamir.veicolibe.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.com.acamir.veicolibe.entity.DatiCompItc;
import it.com.acamir.veicolibe.repository.DatiCompItcRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")
public class DatiCompItcController {

	@Autowired
	DatiCompItcRepository datiCompItcRepository;

	public long countAllComponenteitcs() {
		return datiCompItcRepository.count();
	}

	public void deleteComponenteitc(DatiCompItc componenteitc) {
		datiCompItcRepository.delete(componenteitc);
	}

	public DatiCompItc findComponenteitc(Integer id) {
		Optional<DatiCompItc> aa = datiCompItcRepository.findById(id);
		return aa.get();
	}

	public List<DatiCompItc> findAllComponenteitcs() {
		return datiCompItcRepository.findAll();
	}

	public List<DatiCompItc> findComponenteitcEntries(int firstResult, int maxResults) {
		return datiCompItcRepository.findAll();
	}

	public void saveComponenteitc(DatiCompItc componenteitc) {
		datiCompItcRepository.save(componenteitc);
	}

	public DatiCompItc updateComponenteitc(DatiCompItc componenteitc) {
		return datiCompItcRepository.save(componenteitc);
	}
}
