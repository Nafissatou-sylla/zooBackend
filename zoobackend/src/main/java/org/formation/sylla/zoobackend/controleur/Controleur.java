package org.formation.sylla.zoobackend.controleur;

import java.util.List;
import java.util.stream.Collectors;

import org.formation.sylla.zoobackend.entites.CagePojo;
import org.formation.sylla.zoobackend.modele.CagePersistante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api")
@RestController
public class Controleur {
	
	@Autowired
	
	private CageDAO dao;
	
	List<CagePersistante> lesCages;
	
	public void init() {
		lesCages = dao.lireTous().stream().map(cp->new CagePersistante(cp.getIdAnimal(), dao)).collect(Collectors.toList());
	}
	
	@GetMapping("/tous")
	public List<CagePojo> lireTous() {
		List<CagePojo> ret = null;
		init();
		ret = lesCages.stream().map(cpr -> cpr.getPojo()).toList();
	}
	

}
