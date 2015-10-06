package jeveson.actionprocessor.engine.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

@WebService(serviceName="WsController")
@SOAPBinding(style = Style.RPC, use = Use.LITERAL)
public class WsController {
	
	@WebMethod(operationName = "processAction" )
	public String processAction(String requestParams) {
		return "WsController::params received["+requestParams+"]";
	}
}
