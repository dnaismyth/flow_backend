package com.movement.controller.dto;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;

/**
 * Response object for returning
 * A Page or Collection of data.
 * @author DN
 *
 * @param <T>
 */
public class ResponseList<T> {

	private List<T> dataList;
	private Page<T> dataPage;
	private int count;
	
	public ResponseList(List<T> dataList){
		this.dataList = dataList;
		this.count = dataList.size();
	}
	
	public ResponseList(Page<T> dataPage){
		this.dataPage = dataPage;
		this.count = dataPage.getNumberOfElements();
	}
	
	
	public ResponseList(){}
	
	public List<T> getData(){
		return dataList;
	}
	
	public void setData(List<T> dataList){
		this.dataList = dataList;
	}
	
	public void setPage(Page<T> dataPage){
		this.dataPage = dataPage;
	}
	
	public Page<T> getPage(){
		return dataPage;
	}
	
	public int getCount(){
		return count;
	}
	
	public void setCount(int count){
		this.count = count;
	}
}
