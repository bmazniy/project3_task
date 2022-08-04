package com.mazniy.project3.dto;

import java.util.List;

public class MesurementsResponse {
	
	private List<MesurmentDTO> mesurmentDTO;

	public MesurementsResponse(List<MesurmentDTO> mesurmentDTO) {
		super();
		this.mesurmentDTO = mesurmentDTO;
	}

	public List<MesurmentDTO> getMesurmentDTO() {
		return mesurmentDTO;
	}

	public void setMesurmentDTO(List<MesurmentDTO> mesurmentDTO) {
		this.mesurmentDTO = mesurmentDTO;
	}
	
	

}
