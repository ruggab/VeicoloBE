package it.com.acamir.veicolibe.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.com.acamir.veicolibe.entity.Azienda;
import it.com.acamir.veicolibe.repository.AziendaRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class AziendaController {

	@Autowired
	AziendaRepository aziendaRepository;

	public long countAllAziendas() {
		return aziendaRepository.count();
	}

	@GetMapping("/deleteAzienda/{idAzienda}")
	public List<Azienda> deleteAzienda(@PathVariable(value = "idAzienda") String idAzienda) {
		aziendaRepository.deleteById(new Integer(idAzienda));
		return aziendaRepository.findAll();
	}

	public Azienda findAzienda(Integer id) {
		Optional<Azienda> aa = aziendaRepository.findById(id);
		return aa.get();
	}

	
	@GetMapping("/getListAzienda")
	public List<Azienda> getListAzienda() {
		return aziendaRepository.findAll();
	}
	
	@PostMapping("/getListAziendaByFilter")
	public List<Azienda> getListAziendaByFilter(@RequestBody Azienda azienda) {
		List<Azienda> listaAzienda = aziendaRepository.getListAziendaByFilter(azienda.getMatricola(), azienda.getNominativoRef(), azienda.getMailRef(), azienda.getTelRef());
		return listaAzienda;
	}

	
	@GetMapping("/getListAziendaPag")
	public List<Azienda> getListAzienda(int firstResult, int maxResults) {
		return aziendaRepository.findAll();
	}

	@PostMapping("/generateAzienda")
	@ResponseBody
	public ResponseEntity<Azienda> generateAzienda(@RequestBody Azienda azienda) {
		Azienda aziendaNew = aziendaRepository.save(azienda);
		return new ResponseEntity<Azienda>(aziendaNew, HttpStatus.OK);
	}

	public Azienda updateAzienda(Azienda azienda) {
		return aziendaRepository.save(azienda);
	}
}
