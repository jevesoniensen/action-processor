package jeveson.actionprocessor.engine.ws;

import java.util.Map;
import jeveson.actionprocessor.engine.ws.WsController_PortType;

public class WsControllerProxy implements jeveson.actionprocessor.engine.ws.WsController_PortType {
	
  private String _endpoint = null;
  private WsController_PortType wsController_PortType = null;
  private Map<String,Object> conf= null;
  
  public WsControllerProxy(Map<String,Object> conf) {
	  this.conf = conf;
    _initWsControllerProxy();
  }
  
  /*
  public WsControllerProxy(Map<String,Object> conf,String endpoint) {
	  this.conf = conf;
	  _endpoint = endpoint;
	  _initWsControllerProxy();
  }*/
  
  private void _initWsControllerProxy() {
    try {
      wsController_PortType = (new jeveson.actionprocessor.engine.ws.WsController_ServiceLocator(getConf())).getWsControllerPort();
      if (wsController_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wsController_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wsController_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wsController_PortType != null)
      ((javax.xml.rpc.Stub)wsController_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public jeveson.actionprocessor.engine.ws.WsController_PortType getWsController_PortType() {
    if (wsController_PortType == null)
      _initWsControllerProxy();
    return wsController_PortType;
  }
  
  public java.lang.String processAction(java.lang.String arg0) throws java.rmi.RemoteException{
    if (wsController_PortType == null)
      _initWsControllerProxy();
    return wsController_PortType.processAction(arg0);
  }
  
  public Map<String, Object> getConf() {
	return conf;
  }
  
  public void setConf(Map<String, Object> conf) {
	this.conf = conf;
  }
  
}