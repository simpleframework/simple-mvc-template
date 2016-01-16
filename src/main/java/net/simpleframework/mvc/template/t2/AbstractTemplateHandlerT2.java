package net.simpleframework.mvc.template.t2;

import net.simpleframework.ctx.ApplicationContextFactory;
import net.simpleframework.ctx.IApplicationContext;
import net.simpleframework.mvc.template.AbstractTemplateHandler;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public abstract class AbstractTemplateHandlerT2 extends AbstractTemplateHandler implements
		ITemplateHandlerT2 {

	public static ITemplateHandlerT2 get() {
		final String t2 = ((IApplicationContext) ApplicationContextFactory.ctx())
				.getContextSettings().getProperty("Template.T2");
		return (ITemplateHandlerT2) (t2 != null ? singleton(t2) : null);
	}
}
