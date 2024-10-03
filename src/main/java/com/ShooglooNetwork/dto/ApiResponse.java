package com.ShooglooNetwork.dto;

public class ApiResponse {
	
	private String status;
	private Object payload;
	private String error;
	private int statusCode;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getPayload() {
		return payload;
	}
	public void setPayload(Object payload) {
		this.payload = payload;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	@Override
	public String toString() {
		return "ApiResponse [status=" + status + ", payload=" + payload + ", error=" + error + ", statusCode="
				+ statusCode + "]";
	}
	
	

}
