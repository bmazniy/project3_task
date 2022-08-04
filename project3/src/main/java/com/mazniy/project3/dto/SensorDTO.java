package com.mazniy.project3.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.mazniy.project3.models.Mesurment;

public class SensorDTO {
	
	@Column(name = "name")
	@NotEmpty(message = "Наименование не должно быть пустым")
	@Size(min = 2,max = 30, message = "Наименование должно быть от 2 до 30 символов")
	private String name;
	
	@OneToMany(mappedBy = "sensor",fetch = FetchType.LAZY)
	private List<Mesurment> mesurments;

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
	
	

}
