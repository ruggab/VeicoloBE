package it.com.acamir.veicolibe.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import it.com.acamir.veicolibe.entity.Files;
import it.com.acamir.veicolibe.entity.Veicolo;
import it.com.acamir.veicolibe.payload.response.MessageResponse;
import it.com.acamir.veicolibe.repository.VeicoloRepository;
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
	public ResponseEntity<?> deleteVeicolo(@PathVariable(value = "idVeicolo") String idVeicolo) {
		
		veicoloRepository.deleteById(new Long(idVeicolo));
		return ResponseEntity.ok(new MessageResponse("Veicolo Aggiornato Correttamente"));
	}

	@GetMapping("/getListVeicolo")
	public List<Veicolo> getListVeicolo() {
		return veicoloRepository.findAll();
	}

	@PostMapping("/getListVeicoloByFilter")
	public List<Veicolo> getListVeicoloByFilter(@RequestBody Veicolo veicolo) {
		
		Long idAss = null;
		if (veicolo.getAssegnatario() != null) {
			idAss = veicolo.getAssegnatario().getId();
			logger.info(veicolo.getMatricola() + " " +  veicolo.getTelaio() + " " +   idAss);
		}
		logger.info(">>>>" + veicolo.getMatricola() + " " +  veicolo.getTelaio() + " " +   idAss);
		List<Veicolo> listaVeicoli = veicoloRepository.getListVeicoloByFilter(veicolo.getMatricola(), veicolo.getTelaio(), idAss);
		return listaVeicoli;
	}
	

	@ResponseBody
	@PostMapping("/generateVeicolo")
	public ResponseEntity<?> generateVeicolo(@RequestPart(name="fileCC", required = false) MultipartFile fileCC, 
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
			logger.error(e.toString() + " - " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		

		return ResponseEntity.status(HttpStatus.CREATED).body(veicoloNew);
	}
	
	 @GetMapping("/files")
	 public ResponseEntity<byte[]> getFile(@RequestParam String idVeicolo, @RequestParam String nomeFile) {
	    Files fileDB = veicoloService.getFile(new Long(idVeicolo), nomeFile);
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getNomeFile() + "\"")
	        .body(fileDB.getData());
	 }

}
