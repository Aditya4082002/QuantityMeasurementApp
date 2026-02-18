package com.quantitymeasurementapp;

public class QuantityMeasurementApp {
	//inner class
	public static class Feet{
		
		private final double value;
		
		//constructor
		public Feet(double value){
			this.value = value;
		}
		
		//getter
		public double getValue() {
			return value;
		}
		
		@Override
		public boolean equals(Object obj) {
			//same reference
			if(this==obj) {
				return true;
			}
			//null check
			if(obj==null) {
				return false;
			}
			
			//type check
			if(this.getClass()!=obj.getClass()) {
				return false;
			}
			
			//cast safety
			Feet other = (Feet)obj;
			
			//compare
			return Double.compare(this.value, other.value)==0;
			
		}
	}

	public static void main(String[] args) {
		
		Feet firstValue = new Feet(1.0);
		Feet secondValue = new Feet(1.0);
		
		boolean result = firstValue.equals(secondValue);
		System.out.println("Are Values equal "+result);
		
	}

}
