package it.com.acamir.veicolibe.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import it.com.acamir.veicolibe.entity.Files;
import it.com.acamir.veicolibe.entity.Veicolo;
import it.com.acamir.veicolibe.repository.VeicoloRepository;
import it.com.acamir.veicolibe.security.jwt.AuthEntryPointJwt;
import it.com.acamir.veicolibe.services.VeicoloService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class VeicoloController {
	private static final Logger logger = LoggerFactory.getLogger(VeicoloController.class);
	@Autowired
	VeicoloRepository veicoloRepository;
	
	@Autowired
	VeicoloService veicoloService;

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

		Integer idAss = null;
		if (veicolo.getAssegnatario() != null) {
			idAss = veicolo.getAssegnatario().getId();
		}
		List<Veicolo> listaVeicoli = veicoloRepository.getListVeicoloByFilter(veicolo.getMatricola(), veicolo.getTelaio(), idAss);
		return listaVeicoli;
	}
	

	@ResponseBody
	@PostMapping("/generateVeicolo")
	public ResponseEntity<Veicolo> generateVeicolo(@RequestPart(name="fileCC", required = false) MultipartFile fileCC, 
												   @RequestPart(name="fileELA", required = false) MultipartFile fileELA, 
												   @RequestPart(name="fileVC", required = false) MultipartFile fileVC, 
												   @RequestPart(name="fileTitPropFR", required = false) MultipartFile fileTitPropFR,
												   @RequestPart(name="fileCCI", required = false) MultipartFile fileCCI,
												   @RequestPart("veicolo") Veicolo veicolo ) {
		
		logger.info("Store Veicolo");
		List<MultipartFile> listFile = new ArrayList<MultipartFile>();
		listFile.add(fileCCI);
		listFile.add(fileCC);
		listFile.add(fileELA);
		listFile.add(fileVC);
		listFile.add(fileTitPropFR);
		
		Date now = new Date(System.currentTimeMillis());
		veicolo.setDataAggiornamento(now);
		if (veicolo.getId() == null) {
			veicolo.setDataInserimento(now);
		} 
		Veicolo veicoloNew = null;
		try {
			 veicoloNew = veicoloService.storeListFile(veicolo, listFile);
		} catch (Exception e) {
			logger.info(e.toString() + " - " + e.getMessage());
			return new ResponseEntity<Veicolo>(veicoloNew, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		

		return new ResponseEntity<Veicolo>(veicoloNew, HttpStatus.OK);
	}

}
