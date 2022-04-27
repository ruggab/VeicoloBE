package it.com.acamir.veicolibe.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.com.acamir.veicolibe.entity.Azienda;
import it.com.acamir.veicolibe.payload.response.MessageResponse;
import it.com.acamir.veicolibe.repository.AziendaRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class AziendaController {
	private static final Logger logger = LoggerFactory.getLogger(AziendaController.class);
	@Autowired
	AziendaRepository aziendaRepository;


//	@GetMapping("/deleteAzienda/{idAzienda}")
//	public List<Azienda> deleteAzienda(@PathVariable(value = "idAzienda") String idAzienda) {
//		aziendaRepository.deleteById(new Long(idAzienda));
//		return aziendaRepository.findAll();
//	}
	
	
	@GetMapping("/deleteAzienda/{idAzienda}")
	public ResponseEntity<?> deleteAzienda(@PathVariable(value = "idAzienda") String idAzienda) {
		try {
			aziendaRepository.deleteById(new Long(idAzienda));
		} catch (Exception e) {
			logger.error(e.toString() + " - " + e.getMessage());
            String errorMsg = e.getMessage();
            if (e instanceof DataIntegrityViolationException) {
            	errorMsg = "Non è possibile eliminare questa Azienda perché esistono Veicoli ad essa associata";
            }
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMsg);
		}
		
		return ResponseEntity.ok(new MessageResponse("Gara Aggiornata Correttamente"));
	}
	
	@GetMapping("/getListAzienda")
	public List<Azienda> getListAzienda() {
		return aziendaRepository.findAll();
	}
	
	@GetMapping("/getListAziendaNoAcamir")
	public List<Azienda> getListAziendaNoAcamir() {
		return aziendaRepository.getListAziendaNoAcamir();
	}
	
	@PostMapping("/getListAziendaByFilter")
	public List<Azienda> getListAziendaByFilter(@RequestBody Azienda azienda) {
		List<Azienda> listaAzienda = aziendaRepository.getListAziendaByFilter(azienda.getMatricola(), azienda.getNome(), azienda.getNominativoRef(), azienda.getMailRef());
		return listaAzienda;
	}

	
	@PostMapping("/generateAzienda")
	@ResponseBody
	public ResponseEntity<Azienda> generateAzienda(@RequestBody Azienda azienda) {
		Azienda aziendaNew = aziendaRepository.save(azienda);
		return new ResponseEntity<Azienda>(aziendaNew, HttpStatus.OK);
	}

	
}
