package jeveson.actionprocessor.engine.action;

import java.util.HashMap;
import java.util.Map;

import jeveson.actionprocessor.engine.core.Constants;
import jeveson.actionprocessor.engine.logger.Logger;
import jeveson.actionprocessor.engine.ws.WsControllerProxy;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.stereotype.Component;

@Component
public class SoapWsAction extends AbstractAction implements Action,Constants {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean execute(Map<String, Object> context) {
		try {			
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			
			String data =  ow.writeValueAsString((Map<String,String>)context.get(KEY_CTX_REQUEST_PARAMS));
			
			WsControllerProxy wcp = new WsControllerProxy(getConf());
			
			String output = wcp.processAction(data);
			
			getLogger().logDebug(this, METHOD_EXECUTE, "Srv output["+ output +"]");
			
			ObjectMapper om = new ObjectMapper();
			
			HashMap result = (HashMap)om.readValue(output, HashMap.class);
			
			addResult(context,NO_ERROR, "Success !!!", (Map)result.get(KEY_RESULTS_DATA));	
						
			return true;
		} catch (Exception e) {
			getLogger().logErro(this, METHOD_EXECUTE, e);			
			addResult(context, ERROR_DEFAULT, "Erro ao acessar serviço");
			return false;
		}
	}
	
	@Override
	public void setLogger(Logger logger) {
		// TODO Auto-generated method stub
		super.setLogger(logger);
	}
}
