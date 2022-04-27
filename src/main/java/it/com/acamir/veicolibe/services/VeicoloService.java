package it.com.acamir.veicolibe.services;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import it.com.acamir.veicolibe.entity.Files;
import it.com.acamir.veicolibe.entity.Veicolo;
import it.com.acamir.veicolibe.repository.FilesRepository;
import it.com.acamir.veicolibe.repository.VeicoloRepository;

@Service
public class VeicoloService {
	private static final Logger logger = LoggerFactory.getLogger(VeicoloService.class);
	
	@Autowired
	VeicoloRepository veicoloRepository;
	
	@Autowired
	private FilesRepository fileDBRepository;

	
    @Transactional
	public Veicolo storeListFile(Veicolo veicolo, List<MultipartFile> filesMultipart) throws IOException {
    	if (veicolo.getId()!=null) {
    		fileDBRepository.deleteByIdVeicolo(veicolo.getId());
    	}
		Veicolo veicoloNew = veicoloRepository.save(veicolo);
		//
		for (MultipartFile file : filesMultipart) {
			if (file!=null) {
				String fileName = StringUtils.cleanPath(file.getOriginalFilename());
				Files files = new Files(veicoloNew.getId(), fileName, file.getContentType(), file.getBytes());
				fileDBRepository.save(files);
			}
		}
		//
		return veicoloNew;
	}

	public Files getFile(Long idVeicolo, String nomeFile) {
		return fileDBRepository.findByIdVeicoloAndNomeFile(idVeicolo, nomeFile);
	}

	public Stream<Files> getAllFiles() {
		return fileDBRepository.findAll().stream();
	}

}
