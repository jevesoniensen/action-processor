package jeveson.actionprocessor.engine.ws;

import java.util.HashMap;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import jeveson.actionprocessor.engine.core.AbstractActionProcessor;
import jeveson.actionprocessor.engine.core.BeanFactory;
import jeveson.actionprocessor.engine.core.Constants;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("jeveson.actionprocessor.engine.ws" 
			 + "jeveson.test,jeveson.actionprocessor.engine.logger.impl,"
		     + "jeveson.actionprocessor.engine.core,"
		     + "jeveson.actionprocessor.engine.action.impl,"
		     + "jeveson.actionprocessor.engine.io.impl")
@WebService(serviceName="WsController")
@SOAPBinding(style = Style.RPC, use = Use.LITERAL)
public class WsController extends AbstractActionProcessor implements BeanFactory,Constants,ApplicationContextAware {
		
	@SuppressWarnings("rawtypes")
	@WebMethod(operationName = "processAction" )
	public String processAction(String strRequestParams) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String data = null;
		try {		
			getLogger().logDebug(this, "processAction", "Srv strRequestParams["+ strRequestParams +"]");
			
			ObjectMapper om = new ObjectMapper();
			HashMap requestParams = (HashMap)om.readValue(strRequestParams, HashMap.class);
					
			HashMap<String, Object> context = new HashMap<String, Object>();
			context.put(KEY_CTX_BEAN_FACTORY, this);
			context.put(KEY_APP_CONTEXT, applicationContext);
			context.put(KEY_CTX_REQUEST_PARAMS, requestParams);
			
			process(context);			
			result.put(KEY_CTX_RESULT_TO_VIEW, context.get(KEY_CTX_RESULT_TO_VIEW));
			result.put(KEY_RESULTS_DATA, context.get(KEY_RESULTS_DATA));
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			
			data =  ow.writeValueAsString(result);
		} catch (Exception e) {
			result.put("ERROR", "...");
		}
		
		return data;
	}
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Override
	@WebMethod(exclude = true)
	public Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}
	
	@WebMethod(exclude = true)
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
	@WebMethod(exclude = true)
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
}
