package jeveson.actionprocessor.engine.core;

import java.util.HashMap;
import java.util.Map;

public class ResultToView {

	private HashMap<String, Object> results;
	private String status;

	public HashMap<String, Object> getResults() {
		return results;
	}

	@SuppressWarnings("unchecked")
	public void setResultsToShow(String key, String resultName, Object result) {
        if (this.results == null) {
            this.results = new HashMap<String, Object>();
        }

        Map<String,Object> res = null;
        if (this.results.containsKey(key)) {
            res = ((Map<String,Object>) this.results.get(key));
        } else {
            res = new HashMap<String,Object>();
        }
        
        if (result instanceof Result) {
        	Result resMsg = (Result) result;
        	res.put((resMsg.getSource().hashCode())+"_"+resMsg.getCode(), resMsg.getMessage());
		}
        
        this.results.put(key, res); 
		
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}

}
