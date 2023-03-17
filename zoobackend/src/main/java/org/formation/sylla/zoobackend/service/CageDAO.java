package org.formation.sylla.zoobackend.service;

import java.util.List;

import org.formation.sylla.zoobackend.entities.CagePojo;

public interface CageDAO {
	public List<CagePojo> lireTous();
	public CagePojo lire(int cle);
	public void modifier(CagePojo cp);
}
