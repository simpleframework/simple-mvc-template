package net.simpleframework.mvc.template;

import java.io.IOException;
import java.util.Map;

import net.simpleframework.mvc.AbstractMVCPage;
import net.simpleframework.mvc.PageParameter;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public class HeaderPage extends AbstractTemplatePage {

	@Override
	protected String toHtml(final PageParameter pp,
			final Class<? extends AbstractMVCPage> pageClass, final Map<String, Object> variables,
			final String variable) throws IOException {
		if (HeaderPage.class.equals(pageClass)) {
			return "<div>HeaderPage</div>";
		}
		return super.toHtml(pp, pageClass, variables, variable);
	}
}
