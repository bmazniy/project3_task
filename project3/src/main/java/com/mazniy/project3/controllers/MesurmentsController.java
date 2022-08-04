package com.mazniy.project3.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mazniy.project3.dto.MesurementsResponse;
import com.mazniy.project3.dto.MesurmentDTO;
import com.mazniy.project3.models.Mesurment;
import com.mazniy.project3.models.Sensor;
import com.mazniy.project3.services.MesurmentService;
import com.mazniy.project3.services.SensorsService;
import com.mazniy.project3.util.ErrorResponse;
import com.mazniy.project3.util.MesurmentNotCreatedException;

@Controller
@RequestMapping("/mesurments")
public class MesurmentsController {

	private final MesurmentService mesurmentService;
	private final SensorsService sensorsService;

	@Autowired
	public MesurmentsController(MesurmentService mesurmentService
			,SensorsService sensorsService) {
		this.mesurmentService = mesurmentService;
		this.sensorsService = sensorsService;
	}
	
	@PostMapping("/add")
	@ResponseBody
	public ResponseEntity<HttpStatus> add(@RequestBody @Valid MesurmentDTO mesurmentDTO
			, BindingResult bindingResult) {
		
		Mesurment mesurment = convertToMesurment(mesurmentDTO, bindingResult);
		
		if(bindingResult.hasErrors()) {
		
			StringBuilder sBuilder = new StringBuilder();
			
			List<FieldError> errors = bindingResult.getFieldErrors();
			
			for (FieldError fieldError : errors) {
				sBuilder.append(fieldError.getField())
							.append(" - ")
							.append(fieldError.getDefaultMessage())
							.append("; ");
			}
			
			List<ObjectError> objErrors = bindingResult.getAllErrors();
			
			for (ObjectError fieldError : objErrors) {
				sBuilder.append(fieldError.getObjectName())
							.append(" - ")
							.append(fieldError.getDefaultMessage())
							.append("; ");
			}
			
			throw new MesurmentNotCreatedException(sBuilder.toString());
			
		}
		
		mesurmentService.save(mesurment);
		
		return ResponseEntity.ok(HttpStatus.OK);
		
	}
	
	@GetMapping()
	@ResponseBody
	public MesurementsResponse findAll(){
		return new MesurementsResponse(mesurmentService.findAll().stream()
			.map(this::convertToMesurmentDTO)
			.collect(Collectors.toList()));
	}
	
	@GetMapping("/rainyDaysCount")
	@ResponseBody
	public int rainyDaysCount() {
		return mesurmentService.findRainingDays();
	}
	
	@ExceptionHandler
	private ResponseEntity<ErrorResponse> handle(MesurmentNotCreatedException e){
		ErrorResponse response = new ErrorResponse(e.getMessage(), System.currentTimeMillis());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	private Mesurment convertToMesurment(MesurmentDTO mesurmentDTO, BindingResult bindingResult) {
		ModelMapper modelMapper = new ModelMapper();
		
		Sensor sensor = sensorsService.findByName(mesurmentDTO.getSensor().getName());
		
		if(sensor == null) {
			ObjectError error = new ObjectError("Sensor name","Sensor with this name has not found");
			bindingResult.addError(error);
			return null;
		}
		
		mesurmentDTO.setSensor(sensor);
		
		return modelMapper.map(mesurmentDTO, Mesurment.class);
	}
		
	public MesurmentDTO convertToMesurmentDTO(Mesurment mesurment) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(mesurment, MesurmentDTO.class);
	}
	
}
