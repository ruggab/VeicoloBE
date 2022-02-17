package it.com.acamir.veicolibe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.com.acamir.veicolibe.entity.Dizionario;
import it.com.acamir.veicolibe.repository.DizionarioRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class DizionarioController {

	@Autowired
	DizionarioRepository dizionarioRepository;

	@GetMapping("/getListDizionario/{contesto}")
	public List<Dizionario> getListDizionario(@PathVariable(value = "contesto") String contesto) {
		return dizionarioRepository.findByContesto(contesto);
	}

}
