package com.mazniy.project3.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Mesurments")
public class Mesurment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "temperature")
	private float value;
	
	@Column(name = "raining")
	private boolean raining;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sensor_id")
	private Sensor sensor;
	
	public Mesurment() {
	
	}

	public Mesurment(float value, boolean raining, Sensor sensor) {
		this.value = value;
		this.raining = raining;
		this.sensor = sensor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	@Override
	public String toString() {
		return "Mesurments [id=" + id + ", temperature=" + value + ", raining=" + raining + ", sensor=" + sensor
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, raining, sensor, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mesurment other = (Mesurment) obj;
		return id == other.id && raining == other.raining && Objects.equals(sensor, other.sensor)
				&& Double.doubleToLongBits(value) == Double.doubleToLongBits(other.value);
	}

	
		
}
