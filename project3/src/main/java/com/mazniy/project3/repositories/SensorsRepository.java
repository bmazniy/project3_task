package com.mazniy.project3.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mazniy.project3.models.Sensor;

@Repository
public interface SensorsRepository extends JpaRepository<Sensor, Integer> {

	public Optional<Sensor> findByName(String name);
	
}
