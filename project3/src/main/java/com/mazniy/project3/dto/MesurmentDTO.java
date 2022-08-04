package com.mazniy.project3.dto;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.mazniy.project3.models.Sensor;

public class MesurmentDTO {
	
	@Column(name = "temperature")
	private float value;
	
	@Column(name = "raining")
	private boolean raining;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sensor_id")
	private Sensor sensor;

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public boolean isRaining() {
		return raining;
	}

	public void setRaining(boolean raining) {
		this.raining = raining;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}
	
	

}
