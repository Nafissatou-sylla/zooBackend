/**
 * 
 */
package org.formation.sylla.zoobackend.entites;

import java.io.Serializable;
import java.util.StringJoiner;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author finas
 *
 */


@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor

@Entity
@Table(name = "animal")
@NamedQuery(name = "tous", query = "SELECT cp FROM CagePojo cp")
@NamedQuery(name  = "un", query = "SELECT cp FROM CagePojo cp WHERE cp.idAnimal= :bestiole")
@NamedQuery(name = "dernier", query = "SELECT cp.idAnimal FROM CagePojo cp ORDER BY cp.idAnimal DESC  LIMIT 1")
public class CagePojo implements Serializable {
	
	@Transient
	private String image;
	
	@Transient
	private String pancarte;
	

	/**
	 *  Lion, Singe Gazelle ==> nom de la classe  package
	 */  
	private String codeAnimal; 
	private String nom;
	private int age;
	private double poids;
	private int x, y;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAnimal;
	
	//pour faire la jointure 
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name = "idAnimal", referencedColumnName = "idAnimal", updatable = false, insertable = false)
	private GazellePojo gazelle;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9209993839196192672L;
	

	/**
	 * 
	 */
	public CagePojo() {
		// TODO Auto-generated constructor stub
	}


	public int getIdAnimal() {
		return idAnimal;
	}

	public void setIdAnimal(int idAnimal) {
		this.idAnimal = idAnimal;
	}

	public String getCodeAnimal() {
		return codeAnimal;
	}

	public void setCodeAnimal(String codeAnimal) {
		this.codeAnimal = codeAnimal;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getPoids() {
		return poids;
	}

	public void setPoids(double poids) {
		this.poids = poids;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	

	/**
	 * @return the gazelle
	 */
	public GazellePojo getGazelle() {
		return gazelle;
	}

	/**
	 * @param gazelle the gazelle to set
	 */
	public void setGazelle(GazellePojo gazelle) {
		this.gazelle = gazelle;
	}

	public String getImage() {
		StringJoiner ret = new StringJoiner("");
		if(codeAnimal == null) {
			ret.add("images/cage.jpg");
		}
		else {
			ret.add("images/").add(codeAnimal.toLowerCase()).add(".gif");
		}
		return ret.toString();
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getPancarte() {
		StringJoiner tmp = new StringJoiner("");
		if(this.codeAnimal == null) {
			tmp.add("cage vide");
		}
		else {
			tmp.add(nom).add(Integer.toString(age)).add("an(s)");
			tmp.add(Double.toString(poids)).add("kg");
			if(this.codeAnimal.equals("Gazelle")  ) {
				tmp.add(", cornes = ").add(Integer.toString(getGazelle().getLgCornes())).add("cm");
			}
		}
			
		return tmp.toString();
	}


	public void setPancarte(String pancarte) {
		this.pancarte = pancarte;
	}

	@Override
	public String toString() {
		return "CagePojo [idAnimal= " + idAnimal + ", codeAnimal=" + codeAnimal + ", nom=" + nom + ", age=" + age + ", poids=" + poids + ", x=" + x
				+ ", y=" + y + ", gazelle=" + gazelle +  " pancarte= " + getPancarte() + " images= " + getImage() + "]";
	}

	
}
