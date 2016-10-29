package com.movement.controller.dto;

import java.io.Serializable;

import com.movement.dto.Operation;

/**
 * General Rest Response
 * @author DN
 *
 * @param <T>
 */
public class RestResponse<T> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4558461599630366305L;
	private T data;
	private Operation op;
	private Long id;
	private FlowResponseCode code;
	
	public RestResponse(Operation op, T data){
		this.op = op;
		this.data = data;
	}
	
	public RestResponse(FlowResponseCode code, T data){
		this.code = code;
		this.data = data;
	}
	
	public RestResponse(Operation op, Long id){
		this.id = id;
		this.op = op;
	}
	
	public RestResponse(T data){
		this.data = data;
	}
	
	public RestResponse(FlowResponseCode code){
		this.code = code;
	}
	
	public Operation getOp() {
		return op;
	}

	public void setOp(Operation op) {
		this.op = op;
	}

	public FlowResponseCode getCode() {
		return code;
	}

	public void setCode(FlowResponseCode code) {
		this.code = code;
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
