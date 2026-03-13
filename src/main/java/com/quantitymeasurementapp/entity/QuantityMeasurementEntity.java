package com.quantitymeasurementapp.entity;

import java.io.Serializable;

public class QuantityMeasurementEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String operand1;
	private String operand2;
	private String operation;
	private String result;
	private boolean error;
	private String errorMessage;

	public QuantityMeasurementEntity(String operand1, String operand2, String operation, String result) {

		this.operand1 = operand1;
		this.operand2 = operand2;
		this.operation = operation;
		this.result = result;
		this.error = false;
	}

	public QuantityMeasurementEntity(String errorMessage) {
		this.error = true;
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {

		if (error)
			return "Error : " + errorMessage;

		return operand1 + " " + operation + " " + operand2 + " = " + result;
	}
}