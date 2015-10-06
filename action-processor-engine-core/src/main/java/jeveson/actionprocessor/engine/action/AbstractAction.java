package jeveson.actionprocessor.engine.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jeveson.actionprocessor.engine.core.Constants;
import jeveson.actionprocessor.engine.core.Result;
import jeveson.actionprocessor.engine.logger.Logger;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractAction implements Action, Constants{

	public static final String METHOD_EXECUTE = "execute";
	
	private Map<String,Object> conf= null;
	
	@Autowired
    private Logger logger = null;
	
	private String name;
	
	public void addResult(Map<String, Object> context, final int code, final String message) {
		addResult(context, code, message, null);
	}

	public void addResult(	Map<String, Object> context, 
							final int code,
							final String message, 
							final Map<String, Object> resultData) {
		
		if(resultData!=null){
			@SuppressWarnings("unchecked")
			Map<String, Object> mapResultsData = (Map<String, Object>) context.get(KEY_RESULTS_DATA);
	
			if (mapResultsData == null) {
				mapResultsData = new HashMap<String, Object>();
			}
						
			mapResultsData.putAll(resultData);			
			
			context.put(KEY_RESULTS_DATA,mapResultsData);
		}
		
		addResult(context, new Result(getName(), code, message));
	}
	
	@SuppressWarnings("unchecked")
	public void addResult(Map<String, Object> context, final Result result) {

		ArrayList<Result> results = (ArrayList<Result>) context.get(KEY_RESULTS);

		if (results == null) {
			results = new ArrayList<Result>();
		}

		results.add(result);

		context.put(KEY_RESULTS, results);
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	
	public Logger getLogger() {
		return logger;
	}
	
	public Map<String, Object> getConf() {
		return conf;
	}
	
	public void setConf(Map<String, Object> conf) {
		this.conf = conf;
	}
}
