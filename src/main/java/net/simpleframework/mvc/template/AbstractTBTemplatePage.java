package net.simpleframework.mvc.template;

import java.io.IOException;
import java.util.Map;

import net.simpleframework.mvc.PageParameter;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public abstract class AbstractTBTemplatePage extends AbstractTemplatePage implements
		IPageToolbarAware {

	protected abstract String toContentHTML(PageParameter pp);

	@Override
	protected String toHtml(final PageParameter pp, final Map<String, Object> variables,
			final String currentVariable) throws IOException {
		final StringBuilder sb = new StringBuilder();
		sb.append("<div class='").append(getOriginalClass().getSimpleName()).append("'>");
		sb.append(toToolbarHTML(pp));
		final String cc = toContentHTML(pp);
		if (cc != null) {
			sb.append(cc);
		}
		sb.append("</div>");
		return sb.toString();
	}
}
