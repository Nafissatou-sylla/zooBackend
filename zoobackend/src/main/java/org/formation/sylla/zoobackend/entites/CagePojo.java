/**
 * 
 */
package org.formation.sylla.zoobackend.entites;

import java.io.Serializable;

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
	

	
}
