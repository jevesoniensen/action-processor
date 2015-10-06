/**
 * WsController_Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package jeveson.actionprocessor.engine.ws;

public interface WsController_Service extends javax.xml.rpc.Service {
    public java.lang.String getWsControllerPortAddress();

    public jeveson.actionprocessor.engine.ws.WsController_PortType getWsControllerPort() throws javax.xml.rpc.ServiceException;

    public jeveson.actionprocessor.engine.ws.WsController_PortType getWsControllerPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
