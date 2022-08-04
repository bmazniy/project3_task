package com.mazniy.project3client.dto;

import java.util.List;

public class MesurmentResponse {

	private List<MesurmentDTO> measurements;
	
    public List<MesurmentDTO> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<MesurmentDTO> measurements) {
        this.measurements = measurements;
    }
    
}
