package com.mazniy.project3.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mazniy.project3.models.Mesurment;
import com.mazniy.project3.repositories.MesurmentsRepository;

@Service
@Transactional(readOnly = true)
public class MesurmentService {

	private final MesurmentsRepository mesurmentsRepository;

	@Autowired
	public MesurmentService(MesurmentsRepository mesurmentsRepository) {
		this.mesurmentsRepository = mesurmentsRepository;
	}
	
	public List<Mesurment> findAll(){
		return mesurmentsRepository.findAll();
	}
	
	public int findRainingDays() {
		return mesurmentsRepository.findByRaining(true).size();
	}
	
	@Transactional
	public void save(Mesurment mesurment) {
		mesurmentsRepository.save(mesurment);
	}
	
}
