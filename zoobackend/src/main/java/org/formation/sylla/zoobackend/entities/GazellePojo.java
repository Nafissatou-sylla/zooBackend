/**
 * 
 */
package org.formation.sylla.zoobackend.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor

@Entity
@Table(name = "gazelle")
public class GazellePojo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8018537466656302756L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int idAnimal;
	private int lgCornes;
	
	public int getLgCornes() {
		return lgCornes;
	}
	public void setLgCornes(int lgCornes) {
		this.lgCornes = lgCornes;
	}
	public int getIdAnimal() {
		return idAnimal;
	}
	public void setIdAnimal(int idAnimal) {
		this.idAnimal = idAnimal;
	}

}
