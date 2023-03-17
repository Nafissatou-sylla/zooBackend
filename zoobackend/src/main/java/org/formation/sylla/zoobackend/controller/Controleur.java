package org.formation.sylla.zoobackend.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.formation.sylla.zoobackend.entities.CagePojo;
import org.formation.sylla.zoobackend.modele.CagePersistante;
import org.formation.sylla.zoobackend.service.CageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controleur {
	@Autowired
	private CageDAO dao;
	private List<CagePersistante> lesCages;
	private void init() {
		lesCages = dao.lireTous().stream().map(cp->new CagePersistante(cp.getIdAnimal(), dao)).collect(Collectors.toList());
	}
	
	@GetMapping("/tous")
	public List<CagePojo> lireTous(){
		List<CagePojo> ret = null;
		init();
		ret = lesCages.stream().map(cp->cp.getPojo()).toList();
		return ret;
		
	}
}

