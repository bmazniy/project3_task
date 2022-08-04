package com.mazniy.project3.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mazniy.project3.models.Sensor;
import com.mazniy.project3.repositories.SensorsRepository;

@Service
@Transactional(readOnly = true)
public class SensorsService {

	private final SensorsRepository sensorsRepository;

	@Autowired
	public SensorsService(SensorsRepository sensorsRepository) {
		this.sensorsRepository = sensorsRepository;
	}
	
	// ????
	public List<Sensor> findAll() {
		return sensorsRepository.findAll();
	}
	
	// ????
	public Sensor findById(int id) {
		return sensorsRepository.findById(id).orElse(null);
	}
	
	public Sensor findByName(String name) {
		return sensorsRepository.findByName(name).orElse(null);
	}
	
	@Transactional
	public void save(Sensor sensor) {
		sensorsRepository.save(sensor);
	}
}
