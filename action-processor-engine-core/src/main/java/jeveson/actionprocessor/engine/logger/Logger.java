package jeveson.actionprocessor.engine.logger;

public interface Logger {

	void logDebug(Object cause,String method, String msg);

	void logErro(Object cause,String mETHOD, Exception e);

}
