package it.com.acamir.veicolibe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import it.com.acamir.veicolibe.repository.GaraRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class GaraController {

	@Autowired
	GaraRepository garaRepository;


	@GetMapping("/deleteGara/{idGara}")
	public List<Gara> deleteGara(@PathVariable(value = "idGara") String idGara) {
		garaRepository.deleteById(new Integer(idGara));
		return garaRepository.findAll();
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
