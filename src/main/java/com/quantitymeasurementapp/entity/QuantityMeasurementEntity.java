package com.quantitymeasurementapp.entity;

public class QuantityMeasurementEntity {

	public String operation;
	public String operand1;
	public String operand2;
	public String result;

	public QuantityMeasurementEntity(String operation, String operand1, String operand2, String result) {

		this.operation = operation;
		this.operand1 = operand1;
		this.operand2 = operand2;
		this.result = result;
	}
}