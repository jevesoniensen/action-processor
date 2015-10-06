package jeveson.actionprocessor.engine.action;

import java.util.HashMap;
import java.util.Map;

import jeveson.actionprocessor.engine.core.Constants;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.stereotype.Component;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Component
public class RestWsAction extends AbstractAction implements Action,Constants {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean execute(Map<String, Object> context) {
		try {
			String wsURL = (String)getConf().get(KEY_CTX_WS_URL);
			
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			
			String data =  ow.writeValueAsString((Map<String,String>)context.get(KEY_CTX_REQUEST_PARAMS));
			
			Client client = Client.create();
			WebResource webResource = client.resource(wsURL);	
			
			ClientResponse response = webResource.header("Content-Type", "application/json").accept("application/json").post(ClientResponse.class,data);
			getLogger().logDebug(this, METHOD_EXECUTE, "Response status["+ response.getStatus() +"]");
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			
			String output = response.getEntity(String.class);
			ObjectMapper om = new ObjectMapper();
			
			getLogger().logDebug(this, METHOD_EXECUTE, "Srv output["+ output +"]");
			
			HashMap result = (HashMap)om.readValue(output, HashMap.class);
			
			addResult(context,NO_ERROR, "Success !!!", (Map)result.get(KEY_RESULTS_DATA));		
			
			return true;
		} catch (Exception e) {
			getLogger().logErro(this, METHOD_EXECUTE, e);			
			addResult(context, ERROR_DEFAULT, "Erro ao acessar serviço");
			return false;
		}
	}	
}
