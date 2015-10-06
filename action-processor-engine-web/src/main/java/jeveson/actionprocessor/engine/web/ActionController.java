package jeveson.actionprocessor.engine.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jeveson.actionprocessor.engine.core.AbstractActionProcessor;
import jeveson.actionprocessor.engine.core.BeanFactory;
import jeveson.actionprocessor.engine.core.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ComponentScan("jeveson.test,jeveson.actionprocessor.engine.logger.impl,"
		     + "jeveson.actionprocessor.engine.core,"
		     + "jeveson.actionprocessor.engine.action.impl,"
		     + "jeveson.actionprocessor.engine.io.impl")
@EnableWebMvc
@Controller
public class ActionController extends AbstractActionProcessor implements BeanFactory,Constants {
	
	@Autowired
	private ApplicationContext applicationContext;
		
	@RequestMapping("/json")
	public @ResponseBody HashMap<String, Object> getProcessAction(@RequestBody Map<String, String> requestParams) {
		
		HashMap<String, Object> context = new HashMap<String, Object>();
		context.put(KEY_CTX_BEAN_FACTORY, this);
		context.put(KEY_APP_CONTEXT, applicationContext);
		context.put(KEY_CTX_REQUEST_PARAMS, requestParams);
		
		initIOInterfaces(context);
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			process(context);			
			result.put(KEY_CTX_RESULT_TO_VIEW, context.get(KEY_CTX_RESULT_TO_VIEW));
			result.put(KEY_RESULTS_DATA, context.get(KEY_RESULTS_DATA));
		} catch (Exception e) {
			result.put("ERROR", "...");
		}
		
		return result;
		

	}
	
	public void initIOInterfaces(HashMap<String, Object> context){
				
		ArrayList<String> ioInInterfaces = new ArrayList<String>();
		ioInInterfaces.add(KEY_BEAN_REQUEST_ATTRIBUTES_GETTER);
		ioInInterfaces.add(KEY_BEAN_SESSION_ATTRIBUTES_GETTER);
		ioInInterfaces.add(KEY_BEAN_COOKIE_GETTER);
		
		context.put(KEY_CTX_IO_IN_INTERFACES, ioInInterfaces);
		
		ArrayList<String> ioOutInterfaces = new ArrayList<String>();
		ioOutInterfaces.add(KEY_BEAN_SESSION_ATTRIBUTE_REMOVER);
		ioOutInterfaces.add(KEY_BEAN_SESSION_ATTRIBUTES_SETTER);
		ioOutInterfaces.add(KEY_BEAN_COOKIE_SETTER);
		
		context.put(KEY_CTX_IO_OUT_INTERFACES, ioOutInterfaces);
	}
	
	@Override
	public Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}
	
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
}
