package jeveson.actionprocessor.engine.core;

import java.util.Map;

public class ActionInfo {
	
	private String name = null;
	
	private Map<String,Object> conf= null;
	
	public ActionInfo(){
		super();
	}
	
	public ActionInfo(String name){
		super();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	
	public Map<String, Object> getConf() {
		return conf;
	}
	
	public void setConf(Map<String, Object> conf) {
		this.conf = conf;
	}
}
