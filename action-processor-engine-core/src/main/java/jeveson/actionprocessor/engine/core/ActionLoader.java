package jeveson.actionprocessor.engine.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


@PropertySources(@PropertySource(name="actionList",value="classpath:action-list.properties"))
@Component
public class ActionLoader implements Constants {
		
	@Autowired
	private Environment env;
		
	/**
	 * getActionList - This method going to return the list actions to be executed
	 * @actionSetName - name of an action set  
	 * 
	 * @return action set
	 */
	@SuppressWarnings("unchecked")
	public List<ActionInfo> getActionList(Map<String, Object> context) throws Exception{
		
		Map<String,String> requestParams = (Map<String,String>)context.get(KEY_CTX_REQUEST_PARAMS);
		String actionSetName = requestParams.get(KEY_CTX_ACTION_SET);
		
		/*
		 * Access some kind of repository to find an action set
		 * 
		 * Possible repositories: File, MongoDB, Oracle, ...
		 */
		String actionSet = env.getProperty(actionSetName);
		StringTokenizer st = new StringTokenizer(actionSet,",");
		ArrayList<ActionInfo> list = new ArrayList<ActionInfo>();
		String actionName = null;
		String actionType = null;
		ActionInfo actionInfo = null;
		while (st.hasMoreElements()) {
			actionName = (String) st.nextElement();
			actionInfo = new ActionInfo();
			actionType = (String)env.getProperty(actionName+".type");
			if(actionType != null && actionType.equals("HTTP-REST")){
				Map<String,Object> conf= new HashMap<String, Object>();
				conf.put(KEY_CTX_WS_URL, env.getProperty(actionName+".url"));
				actionInfo.setConf(conf);
				actionInfo.setName("restWsAction");
			}else{
				actionInfo.setName(actionName);
			}

			list.add(actionInfo);			
		}		
		
		return list;
	}
	
	public Environment getEnv() {
		return env;
	}
	
	public void setEnv(Environment env) {
		this.env = env;
	}
	
	/*
	public String getProperty(String key) throws Exception{
		InputStream is = getClass().getClassLoader().getResourceAsStream("action-list.properties");
		Properties p = new Properties();
		if(is!=null){
			p.load(is);
		}
		return p.getProperty(key);
	} */
}
