package com.movement.controller.dto;

import com.movement.dto.Operation;

/**
 * Base Response class for controllers
 * @author DN
 *
 */
public class SimpleResponse {
	
	private String value;
	private Operation op;
	
	public SimpleResponse(String value, Operation op){
		this.op = op;
		this.value = value;
	}
	
	public SimpleResponse(Operation op){
		this.op = op;
	}
	
	public void setValue(String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
	
	public Operation getOperation(){
		return op;
	}
	
	public void setOperation(Operation op){
		this.op = op;
	}
	
	
}
