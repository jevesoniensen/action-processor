/**
 * WsController_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package jeveson.actionprocessor.engine.ws;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.apache.axis.client.Service;

import jeveson.actionprocessor.engine.core.Constants;
import jeveson.actionprocessor.engine.ws.WsController_Service;

import javax.xml.namespace.QName;

@SuppressWarnings("serial")
public class WsController_ServiceLocator extends Service implements WsController_Service,Constants {

    // Use to get a proxy class for WsControllerPort
    private String wsControllerPort_address = null; //"http://localhost:8080/action-processor-engine-ws/ws";
    private String wsControllerPort = null; // "http://ws.engine.actionprocessor.jeveson/";
    private HashSet<QName> ports = null;
    private Map<String,Object> conf= null;
	
    public WsController_ServiceLocator(Map<String,Object> conf) {
    	this.conf = conf;
    	this.wsControllerPort_address = (String)getConf().get(KEY_CTX_WS_URL);
    	this.wsControllerPort = (String)getConf().get(KEY_CTX_WS_CONTROLLER_PORT);
    	
    }

    /*
    public WsController_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WsController_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }*/
    
    public Map<String, Object> getConf() {
		return conf;
	}
    
    public void setConf(Map<String, Object> conf) {
		this.conf = conf;
	}

    public String getWsControllerPortAddress() {
        return wsControllerPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WsControllerPortWSDDServiceName = "WsControllerPort";

    public java.lang.String getWsControllerPortWSDDServiceName() {
        return WsControllerPortWSDDServiceName;
    }

    public void setWsControllerPortWSDDServiceName(java.lang.String name) {
        WsControllerPortWSDDServiceName = name;
    }

    public jeveson.actionprocessor.engine.ws.WsController_PortType getWsControllerPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(wsControllerPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWsControllerPort(endpoint);
    }

    public jeveson.actionprocessor.engine.ws.WsController_PortType getWsControllerPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            jeveson.actionprocessor.engine.ws.WsControllerPortBindingStub _stub = new jeveson.actionprocessor.engine.ws.WsControllerPortBindingStub(getConf(),portAddress, this);
            _stub.setPortName(getWsControllerPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWsControllerPortEndpointAddress(java.lang.String address) {
        wsControllerPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    @SuppressWarnings("rawtypes")
	public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (jeveson.actionprocessor.engine.ws.WsController_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                jeveson.actionprocessor.engine.ws.WsControllerPortBindingStub _stub = new jeveson.actionprocessor.engine.ws.WsControllerPortBindingStub(getConf(),new java.net.URL(wsControllerPort_address), this);
                _stub.setPortName(getWsControllerPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
	/*public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("WsControllerPort".equals(inputPortName)) {
            return getWsControllerPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }*/

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName(wsControllerPort, "WsController");
    }

    public Iterator<QName> getPorts() {
        if (ports == null) {
            ports = new HashSet<QName>();
            ports.add(new javax.xml.namespace.QName(wsControllerPort, "WsControllerPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
    	if ("WsControllerPort".equals(portName)) {
            setWsControllerPortEndpointAddress(address);
        }
        else { // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }
}
