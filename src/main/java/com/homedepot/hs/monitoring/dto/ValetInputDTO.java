package com.homedepot.hs.monitoring.dto;

import java.util.List;

public class ValetInputDTO {

	private List<ValetParameterDTO> valetParameters;

	public List<ValetParameterDTO> getValetParameters() {
		return valetParameters;
	}

	public void setValetParameters(List<ValetParameterDTO> valetParameters) {
		this.valetParameters = valetParameters;
	}
}
