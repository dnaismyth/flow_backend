package com.movement.controller.dto;

import com.movement.dto.Operation;

/**
 * General Rest Response
 * @author DN
 *
 * @param <T>
 */
public class RestResponse<T> {
	
	private T data;
	private Operation op;
	private Long id;
	
	public RestResponse(Operation op, T data){
		this.op = op;
		this.data = data;
	}
	
	public RestResponse(Operation op, Long id){
		this.id = id;
		this.op = op;
	}
	
	public RestResponse(T data){
		this.data = data;
	}
	
	public Operation getOperation(){
		return op;
	}
	
	public void setOperation(Operation op){
		this.op = op;
	}
	
	public T getData(){
		return data;
	}
	
	public void setData(T data){
		this.data = data;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return id;
	}

}
