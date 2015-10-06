package jeveson.actionprocessor.engine.action.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import jeveson.actionprocessor.engine.action.AbstractAction;
import jeveson.actionprocessor.engine.action.Action;
import jeveson.actionprocessor.engine.core.Constants;

@Component
public class ServicesListAction extends AbstractAction implements Action,Constants{

	
	@Override
	public boolean execute(Map<String, Object> context) {
		
		ArrayList<String> services = new ArrayList<String>();
		services.add("Report XXXX");
		services.add("Grafic YYYY");
		services.add("Customers List");
		services.add("..... ZZZZ");
		
		
		HashMap<String, Object> resultData = new HashMap<String, Object>();
		resultData.put("servicesList", services);
		
		addResult(context,NO_ERROR, "Success !!!", resultData);
		
		return true;
	}
	
	
}