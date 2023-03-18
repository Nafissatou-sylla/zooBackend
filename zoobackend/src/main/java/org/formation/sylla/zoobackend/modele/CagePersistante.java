package org.formation.sylla.zoobackend.modele;

import org.formation.sylla.zoodto.model.Cage;
import org.formation.sylla.zoobackend.entities.CagePojo;
import org.formation.sylla.zoobackend.entities.GazellePojo;
import org.formation.sylla.zoobackend.service.CageDAO;
import org.formation.sylla.zoobackend.service.CageService;
import org.formation.sylla.zoobackend.utilitaires.Conversion;
import org.formation.sylla.zoodto.model.Animal;
import org.formation.sylla.zoodto.model.Gazelle;
import org.formation.sylla.zoodto.model.Mangeable;
import org.formation.sylla.zoodto.model.technique.BeurkException;
import org.formation.sylla.zoodto.model.technique.CagePleineException;
import org.formation.sylla.zoodto.model.technique.PorteException;


public class CagePersistante {
	private Cage modele;
	private CagePojo pojo;
	private CageDAO stockage;
	private int cle;
	
	public CagePersistante( int id, int x,int y) {
		cle = id;
		modele = new Cage(x,y);
	}
	public CagePersistante(int id, CageDAO stockage)
	{
		this.stockage=stockage;
		pojo = stockage.lire(id);
		cle = id;
		modele = Conversion.pojoToCage(pojo);
	}
	public void entrer(Animal a) throws PorteException, CagePleineException
	{
		modele.entrer(a);
		sauver();
	}
	public Animal sortir() throws PorteException
	{
		Animal tmp = null;
		tmp = modele.sortir();
		sauver();
		return tmp;
	}
	public boolean estOccupee()
	{
		return modele.getOccupant() != null;
	}
	/**
	 * uniquement pour les lambdas.
	 * NE PAS UTILISER pour manipuler l'animal de la cage.
	 * @return Animal qui est dans la cage.
	 */
	public Animal getOccupant()
	{
		return modele.getOccupant();
	}
	public int getX()
	{
		return modele.getX();
	}
	public int getY()
	{
		return modele.getY();
	}
	public void ouvrir()
	{
		modele.ouvrir();
	}
	public void fermer()
	{
		modele.fermer();
	}
	public boolean estOuverte()
	{
		return modele.isOuvert();
	}
	public void nourrirOccupant()
	{
		if(modele.getOccupant() != null)
		{
			modele.getOccupant().manger();
			sauver();
		}
	}
	public void devorer(Mangeable autre) throws BeurkException 
	{
		if(estOccupee())
		{
			modele.getOccupant().manger(autre);
			sauver();
		}
	}
	@Override
	public String toString() {
		return "Cage n° "+cle+" " + modele.toString();
	}
	public int getCle() {
		return cle;
	}
	/**
	 * méthode privée qui assure la liaison avec le stockage
	 * en remplissant le pojo et en donnant le pojo au dao.
	 */
	private void sauver()
	{
		GazellePojo tmp = null;
		String codeAnimal = null;
		if(modele.getOccupant() != null)
		{
			codeAnimal = modele.getOccupant().getClass().getSimpleName();
			pojo.setCodeAnimal(codeAnimal);
			pojo.setNom(modele.getOccupant().getNom());
			pojo.setAge(modele.getOccupant().getAge());
			pojo.setPoids(modele.getOccupant().getPoids());
			if(codeAnimal.equals("Gazelle"))
			{
				if(pojo.getGazelle() == null)
				{
					tmp = new GazellePojo();
					pojo.setGazelle(tmp);
				}
				pojo.getGazelle().setIdAnimal(cle);
				pojo.getGazelle().setLgCornes(((Gazelle)modele.getOccupant()).getLgCornes());
			}
		}
		else
		{
			pojo.setCodeAnimal(null);
			pojo.setNom(null);
			pojo.setAge(0);
			pojo.setPoids(0);
			pojo.setGazelle(tmp);
			
		}
		stockage.modifier(pojo);
	}
	public CagePojo getPojo() {
		// TODO Auto-generated method stub
		return pojo;
	}

}
