package jeveson.actionprocessor.engine.action.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jeveson.actionprocessor.engine.action.AbstractAction;
import jeveson.actionprocessor.engine.action.Action;
import jeveson.actionprocessor.engine.core.Constants;

import org.springframework.stereotype.Component;

@Component
public class PieAction extends AbstractAction implements Action,Constants {

	@Override
	public boolean execute(Map<String, Object> context) {
						
		ArrayList<Map<String,String>> itens = new ArrayList<Map<String,String>>();
		itens.add(getItem("2000" , "#F7464A", "#FF5A5E", "Service 1"));
		itens.add(getItem("55555", "#46BFBD", "#5AD3D1", "Service 2"));		
		itens.add(getItem("9000"  , "#FDB45C", "#FFC870", "Service 3"));
		itens.add(getItem("99999", "#949FB1", "#A8B3C5", "Service 4"));
		
		HashMap<String, Object> resultData = new HashMap<String, Object>();
		resultData.put("pieData", itens);
		
		addResult(context,NO_ERROR, "Success !!!", resultData);
		
		return true;
	}
	
	public Map<String,String> getItem(String value,String color,String highlight, String label){
		HashMap<String,String> item = new  HashMap<String, String>();
		
		item.put("value", value);
		item.put("color", color);
		item.put("highlight", highlight);
		item.put("label", label);
		
		return item;
	}

}
