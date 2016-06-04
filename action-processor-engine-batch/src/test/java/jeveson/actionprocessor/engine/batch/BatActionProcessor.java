package jeveson.actionprocessor.engine.batch;

import jeveson.actionprocessor.engine.core.AbstractActionProcessor;
import jeveson.actionprocessor.engine.core.BeanFactory;
import jeveson.actionprocessor.engine.core.Constants;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class BatActionProcessor extends AbstractActionProcessor implements BeanFactory,Constants{
	
	private ApplicationContext ctx;
	
	
	
	public void setCtx(ApplicationContext ctx) {
		this.ctx = ctx;
	}
	
	public ApplicationContext getCtx() {
		return ctx;
	}
	
	@Override
	public Object getBean(String beanName) {
		return ctx.getBean(beanName);
	}
}
