package net.simpleframework.mvc.template.t1;

import net.simpleframework.ctx.ApplicationContextFactory;
import net.simpleframework.ctx.IApplicationContext;
import net.simpleframework.mvc.AbstractMVCPage;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.common.element.LinkElement;
import net.simpleframework.mvc.template.AbstractTemplateHandler;
import net.simpleframework.mvc.template.AbstractTemplatePage;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public abstract class AbstractTemplateHandlerT1 extends AbstractTemplateHandler
		implements ITemplateHandlerT1 {

	public static ITemplateHandlerT1 get() {
		final String t1 = ((IApplicationContext) ApplicationContextFactory.ctx()).getContextSettings()
				.getProperty("Template.T1");
		return (ITemplateHandlerT1) (t1 != null ? singleton(t1) : null);
	}

	@Override
	public LinkElement getNavigationHome(final PageParameter pp,
			final AbstractMVCPage templatePage) {
		return AbstractTemplatePage.HOME;
	}
}