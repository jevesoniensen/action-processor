package jeveson.actionprocessor.engine.action;

import java.util.Map;

public interface Action {

	boolean execute(Map<String, Object> context);
	void setName(String name);
	String getName();
	Map<String, Object> getConf();
	void setConf(Map<String, Object> conf);

}
