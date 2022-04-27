package it.com.acamir.veicolibe.controller;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
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

import it.com.acamir.veicolibe.entity.Gara;
import it.com.acamir.veicolibe.payload.response.MessageResponse;
import it.com.acamir.veicolibe.repository.GaraRepository;
import it.com.acamir.veicolibe.utils.ApiError;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class GaraController {

	@Autowired
	GaraRepository garaRepository;

	private static final Logger logger = LoggerFactory.getLogger(GaraController.class);
	
	
//	@GetMapping("/deleteGara/{idGara}")
//	public List<Gara> deleteGara(@PathVariable(value = "idGara") String idGara) {
//		garaRepository.deleteById(new Long(idGara));
//		return garaRepository.findAll();
//	}
	
	@GetMapping("/deleteGara/{idGara}")
	public ResponseEntity<?> deleteGara(@PathVariable(value = "idGara") String idGara) {
		try {
			garaRepository.deleteById(new Long(idGara));
		} catch (Exception e) {
			logger.error(e.toString() + " - " + e.getMessage());
            String errorMsg = e.getMessage();
            if (e instanceof DataIntegrityViolationException) {
            	errorMsg = "Non è possibile eliminare questa Gara perché esistono Veicoli ad essa Associata";
            }
            
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMsg);
		}
		
		return ResponseEntity.ok(new MessageResponse("Gara Aggiornata Correttamente"));
	}
	
	
	
	@GetMapping("/getListGara")
	public List<Gara> getListGara() {
		return garaRepository.findAll();
	}
	
	@PostMapping("/getListGaraByFilter")
	public List<Gara> getListAziendaByFilter(@RequestBody Gara gara) {
		List<Gara> listaGara = garaRepository.getListGaraByFilter(gara.getCodGara(), gara.getCig(), gara.getCup(), gara.getRup(), gara.getDrec());
		return listaGara;
	}

	
	@PostMapping("/generateGara")
	@ResponseBody
	public ResponseEntity<Gara> generateGara(@RequestBody Gara gara) {
		Gara garaNew = garaRepository.save(gara);
		return new ResponseEntity<Gara>(garaNew, HttpStatus.OK);
	}

	
}
