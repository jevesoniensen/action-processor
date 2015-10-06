package jeveson.actionprocessor.engine.action.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import jeveson.actionprocessor.engine.action.AbstractAction;
import jeveson.actionprocessor.engine.action.Action;
import jeveson.actionprocessor.engine.core.Constants;

@Component
public class FindSomeDataAction extends AbstractAction implements Action,Constants{

	
	@Override
	public boolean execute(Map<String, Object> context) {
		
		ArrayList<String> somethings = new ArrayList<String>();
		somethings.add("Laranja");
		somethings.add("Carro");
		somethings.add("Casa");
		
		HashMap<String, Object> resultData = new HashMap<String, Object>();
		resultData.put("someData", somethings);
		
		addResult(context,NO_ERROR, "Success !!!", resultData);
		
		return true;
	}
	
	
}