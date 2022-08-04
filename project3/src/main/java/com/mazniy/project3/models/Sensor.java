package com.mazniy.project3.models;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Sensors")
public class Sensor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	@NotEmpty(message = "Наименование не должно быть пустым")
	@Size(min = 2,max = 30, message = "Наименование должно быть от 2 до 30 символов")
	private String name;
	
	@OneToMany(mappedBy = "sensor", fetch = FetchType.LAZY)
	private List<Mesurment> mesurments;

	public Sensor() {
		
	}

	public Sensor(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Mesurment> getMesurments() {
		return mesurments;
	}

	public void setMesurments(List<Mesurment> mesurments) {
		this.mesurments = mesurments;
	}

	@Override
	public String toString() {
		return "Sensors [id=" + id + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sensor other = (Sensor) obj;
		return Objects.equals(name, other.name);
	}
	
}
