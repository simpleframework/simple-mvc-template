package net.simpleframework.mvc.template;

import java.io.IOException;
import java.util.Map;

import net.simpleframework.mvc.AbstractMVCPage;
import net.simpleframework.mvc.PageParameter;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class FooterPage extends AbstractTemplatePage {

	@Override
	protected String toHtml(final PageParameter pp,
			final Class<? extends AbstractMVCPage> pageClass, final Map<String, Object> variables,
			final String variable) throws IOException {
		if (FooterPage.class.equals(pageClass)) {
			return "<div>FooterPage</div>";
		}
		return super.toHtml(pp, pageClass, variables, variable);
	}
}
