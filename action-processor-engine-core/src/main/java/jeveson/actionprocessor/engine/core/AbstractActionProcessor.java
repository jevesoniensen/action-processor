package jeveson.actionprocessor.engine.core;

import java.util.List;
import java.util.Map;

import jeveson.actionprocessor.engine.action.Action;
import jeveson.actionprocessor.engine.io.IOInterface;
import jeveson.actionprocessor.engine.logger.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("jeveson.actionprocessor.engine.action")
public abstract class AbstractActionProcessor implements Constants{

	@Autowired
    private Logger logger = null;
	
	public void process(Map<String, Object> context) throws Exception {
		
		final String METHOD = "process";
		BeanFactory beanFactory = (BeanFactory) context.get(KEY_CTX_BEAN_FACTORY);

        try {
            this.logger.logDebug(this, METHOD, "iniciando");

            ActionLoader actionLoader = (ActionLoader)beanFactory.getBean(KEY_BEAN_ACTION_LOADER);
            Action action = null;
            boolean goToNext = false;
    		            
            List<ActionInfo> listActionInfo = actionLoader.getActionList(context);
            
            for (ActionInfo actionInfo : listActionInfo) {
            	this.logger.logDebug(this, METHOD, "Begin-Action[" + actionInfo.getName() + "]");
            	
            	action = (Action) beanFactory.getBean(actionInfo.getName());
                action.setName(actionInfo.getName());
                action.setConf(actionInfo.getConf());
                
                context.put(KEY_CTX_IN_EXECUTION_ACTION, action);
                
                /*
                 * Loading parameters to execute the action  
                 */
                
                @SuppressWarnings("unchecked")
				List<String> ioInInterfaces = (List<String>)context.get(KEY_CTX_IO_IN_INTERFACES);
        		
                if(ioInInterfaces!=null){
	        		for (String ioInterface : ioInInterfaces) {
	        			IOInterface requestAttributesGetter = (IOInterface)beanFactory.getBean(ioInterface);
	                    requestAttributesGetter.execute(context);
					}
                }
                
                goToNext = action.execute(context);
                
                @SuppressWarnings("unchecked")
				List<String> ioOutInterfaces = (List<String>)context.get(KEY_CTX_IO_OUT_INTERFACES);
                
                if(ioOutInterfaces!=null){
	        		for (String ioInterface : ioOutInterfaces) {
	        			IOInterface requestAttributesGetter = (IOInterface)beanFactory.getBean(ioInterface);
	                    requestAttributesGetter.execute(context);
					}
                }
                
                if (!goToNext) {
                	
                	if (context.containsKey(KEY_CTX_RESULT_TO_VIEW)) {
                		break;
                	}

                    @SuppressWarnings("unchecked")
					List<Result> results = (List<Result>) context.get(KEY_RESULTS);
                    if (results != null) {                        
                        for (Result result : results) {
                            if (result.getSource().equals(action.getName())) {
                            	setResultViewInContext(context, KEY_STATUS_NOK, result);   
                            }
						}
                    }

                    this.logger.logDebug(this, "doAction", "execution stoped !!!");
                    break;
                }
                this.logger.logDebug(this, METHOD, "End-Action[" + actionInfo.getName() + "]");
			}

            this.logger.logDebug(this, METHOD, "Generating return");
        } catch (Exception e) {

            /*
             * All not treated exception going to stop at this point.
             *  
             */

        	this.logger.logErro(this, METHOD, e);        	
            Result auxResult = new Result(this.getClass().getName(), ERROR_DEFAULT," Flow stoped Error ...");
            setResultViewInContext(context, KEY_STATUS_NOK, auxResult);

        }

        if (!context.containsKey(KEY_CTX_RESULT_TO_VIEW)) {
            Result auxResult1 = new Result(this.getClass().getName(),NO_ERROR,"All actions executed");
            setResultViewInContext(context, KEY_STATUS_OK, auxResult1);
        } 

	}
	
	public void setResultViewInContext(Map<String, Object> context,String status,Result result){
		
		ResultToView resultToView = (ResultToView)context.get(KEY_CTX_RESULT_TO_VIEW);
		
		if(resultToView == null){
			resultToView = new ResultToView();
		}
		
        resultToView.setStatus(status);
        resultToView.setResultsToShow(status,result.getSource(), result);
        context.put(KEY_CTX_RESULT_TO_VIEW, resultToView);
	}
	
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	
	public Logger getLogger() {
		return logger;
	}
}
