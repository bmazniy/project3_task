package com.mazniy.project3.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mazniy.project3.models.Mesurment;
import com.mazniy.project3.models.Sensor;

@Repository
public interface MesurmentsRepository extends JpaRepository<Mesurment, Integer> {

	public List<Mesurment> findBySensor(Sensor sensor);
	
	public List<Mesurment> findByRaining(boolean raining);
	
}
