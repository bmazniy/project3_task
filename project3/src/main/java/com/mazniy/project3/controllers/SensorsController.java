package com.mazniy.project3.controllers;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mazniy.project3.dto.SensorDTO;
import com.mazniy.project3.models.Sensor;
import com.mazniy.project3.services.SensorsService;
import com.mazniy.project3.util.ErrorResponse;
import com.mazniy.project3.util.SensorNotCreatedException;
import com.mazniy.project3.util.SensorValidator;

@Controller
@RequestMapping("/sensors")
public class SensorsController {

	private final SensorsService sensorsService;
	private final SensorValidator sensorValidator;
	
	@Autowired
	public SensorsController(SensorsService sensorsService
			,SensorValidator sensorValidator) {
		this.sensorsService = sensorsService;
		this.sensorValidator = sensorValidator;
	}

	@PostMapping("/registration")
	@ResponseBody
	public ResponseEntity<HttpStatus> registrate(@RequestBody @Valid SensorDTO sensorDTO
			, BindingResult bindingResult) {
		
		sensorValidator.validate(convertToSensor(sensorDTO), bindingResult);
		
		if(bindingResult.hasErrors()) {
		
			StringBuilder sBuilder = new StringBuilder();
			
			List<FieldError> errors = bindingResult.getFieldErrors();
			
			for (FieldError fieldError : errors) {
				sBuilder.append(fieldError.getField())
							.append(" - ")
							.append(fieldError.getDefaultMessage())
							.append("; ");
				
			}
			
			throw new SensorNotCreatedException(errors.toString());
			
		}
		
		sensorsService.save(convertToSensor(sensorDTO));
		
		return ResponseEntity.ok(HttpStatus.OK);
		
	}
	
	@ExceptionHandler
	private ResponseEntity<ErrorResponse> handle(SensorNotCreatedException e){
		ErrorResponse response = new ErrorResponse(e.getMessage(), System.currentTimeMillis());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	private Sensor convertToSensor(SensorDTO sensorDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(sensorDTO, Sensor.class);
	}

}
