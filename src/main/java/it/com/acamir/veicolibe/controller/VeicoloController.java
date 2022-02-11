package it.com.acamir.veicolibe.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.com.acamir.veicolibe.entity.Veicolo;
import it.com.acamir.veicolibe.repository.VeicoloRepository;

@Service
@Transactional
public class VeicoloController {

	@Autowired
	VeicoloRepository datiVeicoloRepository;


	public long countAllDatiVeicoloes() {
		return datiVeicoloRepository.count();
	}

	

	public Veicolo findDatiVeicolo(Integer id) {
		Optional<Veicolo> aa = datiVeicoloRepository.findById(id);
		return aa.get();
	}

	public List<Veicolo> findAllDatiVeicoloes() {
		return datiVeicoloRepository.findAll();
	}

	public List<Veicolo> findDatiVeicoloEntries(int firstResult, int maxResults) {
		return datiVeicoloRepository.findAll();
	}

	
	public void saveDatiVeicolo(Veicolo datiVeicolo) {
		datiVeicoloRepository.save(datiVeicolo);
	}

	public List<Veicolo> findDatiVeicoliBy(Integer idAzienda, String utente, String telaio, String targa, String matricola, String modello, String marca) {
		return datiVeicoloRepository.findDatiVeicoloBy(idAzienda, utente, telaio, targa, matricola, modello, marca);
	}

	public List<Veicolo> findAllDatiVeicoliBy(Integer idAzienda, String utente, String telaio, String targa, String matricola, String modello, String marca) {
		return datiVeicoloRepository.findAllDatiVeicoloBy(idAzienda, utente, telaio, targa, matricola, modello, marca);
	}

	public Veicolo findDatiVeicoloByTelaio(String telaio) throws Exception {
		// TODO Auto-generated method stub
		List<Veicolo> listDatiVeicolo = datiVeicoloRepository.findDatiVeicoloByTelaio(telaio);
		if (listDatiVeicolo.size() > 1) {
			throw new Exception("Trovati più Veicoli con lo stesso telaio");
		} else if (listDatiVeicolo.size() == 1) {
			return listDatiVeicolo.get(0);
		} else {
			return null;
		}

	}

}
