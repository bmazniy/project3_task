package com.mazniy.project3.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mazniy.project3.models.Sensor;
import com.mazniy.project3.services.SensorsService;

@Component
public class SensorValidator implements Validator {

	private final SensorsService sensorsService;
	
	@Autowired
	public SensorValidator(SensorsService sensorsService) {
		super();
		this.sensorsService = sensorsService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Sensor.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Sensor sensor = (Sensor) target;
		
		if(sensorsService.findByName(sensor.getName()) != null) {
		
			errors.rejectValue("name", "Sensor with this name is already exsist");
		}
		
	}

}
