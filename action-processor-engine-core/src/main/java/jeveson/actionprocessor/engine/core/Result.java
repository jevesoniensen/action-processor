package jeveson.actionprocessor.engine.core;

public class Result {

	private String source = null;
	private int code;
	private String message; 
	
	public Result(String source, int code, String message) {
		this.source = source;
		this.code = code;
		this.message = message;
	}	
		
	public String getMessage() {
		return message;
	}
	
	public String getSource() {
		return source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
}
