package jeveson.actionprocessor.engine.batch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import jeveson.actionprocessor.engine.core.Constants;
import jeveson.actionprocessor.engine.core.ResultToView;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("jeveson.actionprocessor.engine.batch,"
		     + "jeveson.actionprocessor.engine.logger,"
		     + "jeveson.actionprocessor.engine.core,"
		     + "jeveson.actionprocessor.engine.action.impl,"
		     + "jeveson.actionprocessor.engine.io.impl")
public class Main implements Constants{

	public static void main(String[] args){
		
		try {
			
			Map<String, String> requestParams = new HashMap<String, String>();
			requestParams.put(KEY_CTX_ACTION_SET, "showServer");
			
			ApplicationContext ctx = new AnnotationConfigApplicationContext(Main.class);
			BatActionProcessor bap = (BatActionProcessor)ctx.getBean(BatActionProcessor.class);
			bap.setCtx(ctx);
			
			HashMap<String, Object> context = new HashMap<String, Object>();
			context.put(KEY_CTX_BEAN_FACTORY, bap);
			context.put(KEY_APP_CONTEXT, ctx);
			context.put(KEY_CTX_REQUEST_PARAMS, requestParams);
			
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
			
			bap.process(context);
			
			ResultToView resultToView = (ResultToView)context.get(KEY_CTX_RESULT_TO_VIEW);
			
			System.out.println("ResultToView::status::" + resultToView.getStatus());
			
			Map<String, Object> results = resultToView.getResults();
			
			Set<String> setKeys = results.keySet();
			
			for (String key : setKeys) {
				System.out.println("key["+key+"] value["+results.get(key)+"]");
			}
			
			@SuppressWarnings("unchecked")
			Map<String, Object> resultData = (Map<String, Object>)context.get(KEY_RESULTS_DATA);
			
			setKeys = resultData.keySet();
			
			for (String key : setKeys) {
				System.out.println("key["+key+"] value["+resultData.get(key)+"]");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
