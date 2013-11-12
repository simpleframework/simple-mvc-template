package net.simpleframework.mvc.template.t1;

import java.util.Map;

import net.simpleframework.common.StringUtils;
import net.simpleframework.common.coll.KVMap;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.common.element.BlockElement;
import net.simpleframework.mvc.common.element.ElementList;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public abstract class T1ResizedLCTemplatePage extends T1ResizedTemplatePage {

	@Override
	protected void onForward(final PageParameter pp) {
		super.onForward(pp);
		final Class<?> pageClass = getClass();
		addHtmlViewVariable(pageClass, "content_left", getClassName(pageClass) + "_Left.html");
		addHtmlViewVariable(pageClass, "content_center", getClassName(pageClass) + "_Center.html");
		addHtmlViewVariable(T1ResizedLCTemplatePage.class, "content");
	}

	protected ElementList getLeftToolbar_LeftElements(final PageParameter pp) {
		return null;
	}

	protected ElementList getLeftToolbar_RightElements(final PageParameter pp) {
		return null;
	}

	protected String toLeftToolbarHTML(final PageParameter pp) {
		final ElementList le = getLeftToolbar_LeftElements(pp);
		final ElementList re = getLeftToolbar_RightElements(pp);
		final StringBuilder sb = new StringBuilder();
		if (le != null) {
			sb.append("<div class='le'>").append(le).append("</div>");
		}
		if (re != null) {
			sb.append("<div class='re'>").append(re).append("</div>");
		}
		if (sb.length() > 0) {
			sb.append(BlockElement.CLEAR);
		}
		return sb.toString();
	}

	@Override
	public Map<String, Object> createVariables(final PageParameter pp) {
		final KVMap kv = (KVMap) super.createVariables(pp);
		final StringBuilder sb = new StringBuilder();
		final String html = toLeftToolbarHTML(pp);
		if (StringUtils.hasText(html)) {
			sb.append("<div class='tool_bar'>").append(html).append("</div>");
			sb.append("<style type='text/css'>");
			sb.append("#resized_tabbar2 .simple_tabs { margin-top: 45px; }");
			sb.append("</style>");
		}
		kv.add("toolbar_left", sb.toString()).add("toolbar_center", _toContentBarHTML(pp, false));
		return kv;
	}

	@Override
	public String _toContentBarHTML(final PageParameter pp) {
		return "";
	}
}
