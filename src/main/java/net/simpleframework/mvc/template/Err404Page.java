package net.simpleframework.mvc.template;

import java.io.IOException;
import java.util.Map;

import net.simpleframework.mvc.PageParameter;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public class Err404Page extends AbstractTemplatePage {

	@Override
	protected void addImportCSS(final PageParameter pp) {
		super.addImportCSS(pp);

		pp.addImportCSS(Err404Page.class, "/template.css");
	}

	@Override
	protected String toHtml(final PageParameter pp, final Map<String, Object> variables,
			final String currentVariable) throws IOException {
		final StringBuilder sb = new StringBuilder();
		sb.append("<div align='center'>");
		sb.append("<div class='err404'>");
		sb.append("  <div class='img'></div>");
		sb.append("  <div class='txt'>#(Err404Page.0)</div>");
		sb.append("  <div><a class='simple_btn simple_btn_all' onclick=\"$Actions.loc('/');\">#(Err404Page.1)</a></div>");
		sb.append("</div>");
		sb.append("</div>");
		return sb.toString();
	}
}
