package org.formation.sylla.zoobackend.service;

import java.util.List;
import java.util.Optional;

import org.formation.sylla.zoobackend.entities.CagePojo;
import org.formation.sylla.zoobackend.repo.CageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CageService implements CageDAO {
	@Autowired
	private CageRepository repo;

	@Override
	public List<CagePojo> lireTous() {
		return (List<CagePojo>) repo.findAll();
	}

	@Override
	public CagePojo lire(int cle) {
		Optional<CagePojo> tmp =repo.findById(cle);
		if(tmp.isPresent()) {
			return tmp.get();
		}
		return null;
	}

	@Override
	public void modifier(CagePojo cp) {
		repo.save(cp);
	}

}
