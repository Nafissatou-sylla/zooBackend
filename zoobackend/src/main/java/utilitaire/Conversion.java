/**
 * 
 */
package org.formation.sylla.zoo23.utilitaire;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.formation.sylla.zoo23.model.Animal;
import org.formation.sylla.zoo23.model.Cage;
import org.formation.sylla.zoo23.model.technique.CagePleineException;
import org.formation.sylla.zoo23.model.technique.PorteException;
import org.formation.sylla.zoo23.service.CagePojo;

/**
 * @author finas
 * pas de constructeurs, car class outils (helper)
 */

public class Conversion {
	public static final String PACKAGE = "org.formation.sylla.zoo23.model.";
	
	public static Cage pojoToCage(CagePojo cagepojo) {
		Cage ret = null;
		Class<?> lesTypes[]; //? c'est à dire un tableau de lecture seule
		Object lesValeurs[];
		Animal laBete;
		Constructor<?> construct;
		Class<?> laClasseAnimal;
		
		ret = new Cage(cagepojo.getX(), cagepojo.getY());
		//on a un animal dedans
		if (cagepojo.getCodeAnimal() != null) {
			//on récupére le nom de la classe sans le package
			if ( ! cagepojo.getCodeAnimal().equals("Gazelle")) {
				lesTypes = new Class<?>[3];
				lesValeurs = new Object[3];
			}
			else {
				lesTypes = new Class<?>[4];
				lesValeurs = new Object[4];
				lesTypes[3] = int.class;
				lesValeurs[3] = cagepojo.getGazelle().getLgCornes();
			}
			
			lesTypes[0] = String.class;
			lesTypes[1] = int.class;
			lesTypes[2] = double.class;
			
			lesValeurs[0] = cagepojo.getNom();
			lesValeurs[1] = cagepojo.getAge();
			lesValeurs[2] = cagepojo.getPoids();
			
			//forname
			try {
				laClasseAnimal = Class.forName(PACKAGE + cagepojo.getCodeAnimal());
				//on recupere le constructeur 
				construct = laClasseAnimal.getConstructor(lesTypes);
				// instancié l'animal
				laBete = (Animal) construct.newInstance(lesValeurs);
				// le faire entrer dans la cage(ret)
				ret.entrer(laBete);
				//fermer la cage (ret)
				ret.fermer();
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | PorteException | CagePleineException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ret;
		}
	
//	public static CagePojo cageToPojo(Cage cage) {
//		
//	}
}
