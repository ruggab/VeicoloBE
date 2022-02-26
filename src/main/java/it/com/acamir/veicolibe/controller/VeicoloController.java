package it.com.acamir.veicolibe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.com.acamir.veicolibe.entity.Veicolo;
import it.com.acamir.veicolibe.repository.VeicoloRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class VeicoloController {

	@Autowired
	VeicoloRepository veicoloRepository;


	@GetMapping("/deleteVeicolo/{idVeicolo}")
	public List<Veicolo> deleteGara(@PathVariable(value = "idVeicolo") String idVeicolo) {
		veicoloRepository.deleteById(new Integer(idVeicolo));
		return veicoloRepository.findAll();
	}
	
	@GetMapping("/getListVeicolo")
	public List<Veicolo> getListVeicolo() {
		return veicoloRepository.findAll();
	}
	
	@PostMapping("/getListVeicoloByFilter")
	public List<Veicolo> getListVeicoloByFilter(@RequestBody Veicolo veicolo) {
		List<Veicolo> listaVeicoli = veicoloRepository.getListVeicoloByFilter(veicolo.getMatricola(), veicolo.getTelaio(), veicolo.getAssegnatario().getId());
		return listaVeicoli;
	}

	
	@PostMapping("/generateVeicolo")
	@ResponseBody
	public ResponseEntity<Veicolo> generateVeicolo(@RequestBody Veicolo veicolo) {
		Veicolo veicoloNew = veicoloRepository.save(veicolo);
		return new ResponseEntity<Veicolo>(veicoloNew, HttpStatus.OK);
	}

}
