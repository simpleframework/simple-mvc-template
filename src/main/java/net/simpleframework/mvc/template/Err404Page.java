package net.simpleframework.mvc.template;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.simpleframework.mvc.AbstractMVCPage;
import net.simpleframework.mvc.PageMapping;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.common.element.ImageElement;
import net.simpleframework.mvc.common.element.LinkButton;
import net.simpleframework.mvc.common.element.Meta;
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

	private final Meta MOBILE_VIEWPORT = new Meta(
			"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0")
					.setName("viewport");

	@Override
	public void onHttpRequestMeta(final PageParameter pp, final Collection<Meta> coll) {
		super.onHttpRequestMeta(pp, coll);
		if (pp.isMobile(true)) {
			coll.add(MOBILE_VIEWPORT);
		} else {
			coll.add(Meta.DEFAULT_VIEWPORT);
		}
	}

	protected String toBtnsHTML(final PageParameter pp) {
		final StringBuilder sb = new StringBuilder();
		sb.append(LinkButton.corner("#(Err404Page.3)").setOnclick("history.back();"))
				.append(SpanElement.SPACE10).append(LinkButton.corner("#(Err404Page.4)").setHref("/"));
		return sb.toString();
	}

	protected String toTxt2HTML(final PageParameter pp) {
		final StringBuilder sb = new StringBuilder();
		sb.append("#(Err404Page.1)<br>#(Err404Page.2)");
		return sb.toString();
	}

	@Override
	protected String toHtml(final PageParameter pp, final Map<String, Object> variables,
			final String currentVariable) throws IOException {
		final StringBuilder sb = new StringBuilder();
		final String src = pp.getCssResourceHomePath(Err404Page.class) + "/images/404.jpg";
		sb.append("<div class='err404'>");
		sb.append(" <div>");
		sb.append(new ImageElement(src));
		sb.append(" </div>");
		sb.append(" <div class='txt1'>#(Err404Page.0)</div>");
		sb.append(" <div class='txt2'>").append(toTxt2HTML(pp)).append("</div>");
		sb.append(" <div class='btns'>").append(toBtnsHTML(pp)).append("</div>");
		sb.append("</div>");
		sb.append("<style type='text/css'>body {background-color: #f1f1f1;}</style>");
		return sb.toString();
	}

	public static String url(final HttpServletRequest request) {
		return request.getContextPath() + AbstractMVCPage.url(Err404Page.class);
	}
}
