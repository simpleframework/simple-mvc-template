package net.simpleframework.mvc.template;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.simpleframework.mvc.AbstractMVCPage;
import net.simpleframework.mvc.PageMapping;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.common.element.ImageElement;
import net.simpleframework.mvc.common.element.LinkButton;
import net.simpleframework.mvc.common.element.SpanElement;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
@PageMapping(url = "/404")
public class Err404Page extends AbstractTemplatePage {

	@Override
	protected void onForward(final PageParameter pp) throws Exception {
		super.onForward(pp);

		pp.addImportCSS(Err404Page.class, "/template.css");
	}

	@Override
	protected String toHtml(final PageParameter pp, final Map<String, Object> variables,
			final String currentVariable) throws IOException {
		final StringBuilder sb = new StringBuilder();
		final String src = pp.getCssResourceHomePath(Err404Page.class) + "/images/404.jpg";
		sb.append("<div class='err404'>");
		sb.append(" <div>");
		sb.append(new ImageElement(src));
		sb.append("  <div class='txt1'>#(Err404Page.0)</div>");
		sb.append("  <div class='txt2'>#(Err404Page.1)</div>");
		sb.append("  <div class='txt2'>#(Err404Page.2)</div>");
		sb.append("  <div class='btns'>")
				.append(LinkButton.corner("#(Err404Page.3)").setOnclick("history.back();"))
				.append(SpanElement.SPACE10).append(LinkButton.corner("#(Err404Page.4)").setHref("/"))
				.append("</div>");
		sb.append(" </div>");
		sb.append("</div>");
		sb.append("<style type='text/css'>body {background-color: #f1f1f1;}</style>");
		return sb.toString();
	}

	public static String url(final HttpServletRequest request) {
		return request.getContextPath() + AbstractMVCPage.url(Err404Page.class);
	}
}
