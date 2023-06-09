/**
 * 
 */
package org.formation.sylla.zoobackend.entities;

import java.io.Serializable;
import java.util.StringJoiner;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
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
@NamedQuery(name = "tous", query = "SELECT cp FROM CagePojo AS  cp")
@NamedQuery(name="un", query = "SELECT cp FROM CagePojo AS cp WHERE cp.idAnimal=:bestiole")
@NamedQuery(name = "derniere", query ="SELECT cp.idAnimal FROM CagePojo cp ORDER BY idAnimal DESC LIMIT 1")
public class CagePojo implements Serializable {

	@Transient
	private String image;
	@Transient
	private String pancarte;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAnimal;
	/**
	 * Lion, Singe, Gazelle ==> nom de la classe !!!sans package!!!
	 */
	private String codeAnimal;
	private String nom;
	private int age;
	private double poids;
	private int x;
	private int y;
	private GazellePojo gazelle;
	
	// relation 0 à 1
	@ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE})
	@JoinColumn(name = "idAnimal", referencedColumnName = "idAnimal",updatable = false,insertable = false)
	
	public String getPancarte() {
		StringJoiner temp = new StringJoiner(" ");
		if (codeAnimal == null) {
			temp.add("cage vide");
		}else {
			temp.add(nom).add(Integer.toString(age)).add("an(s)");
			temp.add(Double.toString(poids)).add("kg");
			if (codeAnimal.equals("Gazelle")) {
				temp.add(", cornes =").add(Integer.toString(this.getGazelle().getLgCornes())).add("cm");
			}
		}
		pancarte =  temp.toString();
		return pancarte;
	}
	public String getImage() {
		StringJoiner ret = new StringJoiner("");
		if (codeAnimal == null) {
			ret.add("images/cage.jpg");
			
		}else {
			ret.add("images/").add(codeAnimal.toLowerCase()).add(".gif");
		}
		image = ret.toString();
		return image;
	}
	
	public GazellePojo getGazelle() {
		return gazelle;
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
	public void setImage(String image) {
		this.image = image;
	}
	public void setPancarte(String pancarte) {
		this.pancarte = pancarte;
	}
	public void setGazelle(GazellePojo gazelle) {
		this.gazelle = gazelle;
	}

	
}