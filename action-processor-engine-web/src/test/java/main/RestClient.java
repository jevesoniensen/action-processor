package main;

import java.io.IOException;
import java.util.HashMap;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

public class RestClient {

	public static void main(String[] args) {
		
		try {
			HashMap map = new HashMap();
			
			map.put("actionSet", "findSomeData");
			
			
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			
			String aux =  ow.writeValueAsString(map);
			System.out.println(aux);
			//aux = "{\"actionSet\" : \"findSomeData\"}";
			//System.out.println(aux);
			
			Client client0 = Client.create();
			WebResource webResource0 = client0.resource("http://localhost:8080/action-processor-engine-rest/app/json/jeveson");

			ClientResponse response0 = webResource0.header("Content-Type", "application/json").accept("application/json").post(ClientResponse.class,aux);
			
			if (response0.getStatus() != 200) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response0.getStatus());
			}

			String output0 = response0.getEntity(String.class);

			System.out.println("Output from Server .... \n");
			System.out.println(output0);
			
			Client client = Client.create();
			WebResource webResource = client.resource("http://localhost:8080/action-processor-engine-rest/app/json");

			ClientResponse response = webResource.header("Content-Type", "application/json").accept("application/json").post(ClientResponse.class,aux);
			
			if (response.getStatus() != 200) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
			}

			String output = response.getEntity(String.class);

			System.out.println("Output from Server .... \n");
			System.out.println(output);			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
