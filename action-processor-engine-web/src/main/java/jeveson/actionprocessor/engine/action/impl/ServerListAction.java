package jeveson.actionprocessor.engine.action.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import jeveson.actionprocessor.engine.action.AbstractAction;
import jeveson.actionprocessor.engine.action.Action;
import jeveson.actionprocessor.engine.core.Constants;
/***
 * 
 * Sample Class
 * 
 * 
 * @author Jeveson
 *
 */
@Component
public class ServerListAction extends AbstractAction implements Action,Constants {
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean execute(Map<String, Object> context) {
		/*
		 * This action should access some services
		 * 
		 */
					
		getLogger().logDebug(this, METHOD_EXECUTE, "loading servers");
		
		
		Map<String,String> requestParams = (Map<String,String>)context.get(KEY_CTX_REQUEST_PARAMS);
		String filter = requestParams.get("filter");
		
		ArrayList<String> servers = new ArrayList<String>();
		
		if(filter != null && filter.trim().length() > 0){
			servers.add(filter);
		}else{
			servers.add("Server1");
			servers.add("Server2");
			servers.add("Server3");
		}
		
		HashMap<String, Object> resultData = new HashMap<String, Object>();
		resultData.put("servers", servers);
		
		addResult(context,NO_ERROR, "Success !!!", resultData);
						
		return true;
	}




	

}
