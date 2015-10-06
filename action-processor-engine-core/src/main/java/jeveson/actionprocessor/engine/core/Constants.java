package jeveson.actionprocessor.engine.core;

public interface Constants {

	//Context constants
    String KEY_CTX_RESULT_TO_VIEW = "resultToView";
	String KEY_CTX_BEAN_FACTORY = "BeanFactory";
	String KEY_CTX_ACTION_NAME ="actionName";
	String KEY_CTX_ACTION_SET = "actionSet";
    String KEY_CTX_IN_EXECUTION_ACTION = "KEY_CTX_IN_EXECUTION_ACTION";
    String KEY_CTX_IO_IN_INTERFACES = "io-in-interfaces";
    String KEY_CTX_IO_OUT_INTERFACES = "io-out-interfaces";
    String KEY_CTX_REQUEST_PARAMS = "requestParams";
    String KEY_CTX_WS_URL = "wsURL";
	
	String KEY_BEAN_REQUEST_ATTRIBUTES_GETTER = "requestAttributesGetter";
	String KEY_BEAN_SESSION_ATTRIBUTES_GETTER = "sessionAttributesGetter";	
	String KEY_BEAN_COOKIE_GETTER = "cookieGetter";
	String KEY_BEAN_SESSION_ATTRIBUTE_REMOVER = "sessionAttributeRemover";
	String KEY_BEAN_SESSION_ATTRIBUTES_SETTER = "sessionAttributesSetter";	
	String KEY_BEAN_COOKIE_SETTER = "cookieSetter";		
	String KEY_BEAN_ACTION_LOADER = "actionLoader";
	
	
	String KEY_APP_CONTEXT = "ApplicationContext";
	String KEY_RESULTS = "results";
    String KEY_RESULTS_DATA = "resultData";
    
    String KEY_STATUS_OK = "OK";
	String KEY_STATUS_NOK = "NOK";
    String KEY_STATUS_SYS = "_SYS";
	
	int ERROR_DEFAULT = 99;
	int NO_ERROR = 0;
	
}
