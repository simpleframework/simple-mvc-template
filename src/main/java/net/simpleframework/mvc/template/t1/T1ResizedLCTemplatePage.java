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
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public abstract class T1ResizedLCTemplatePage extends T1ResizedTemplatePage {

	@Override
	protected void onForward(final PageParameter pp) throws Exception {
		super.onForward(pp);
		final Class<?> pageClass = getClass();
		addHtmlViewVariable(pp, pageClass, "content_left", getClassName(pageClass) + "_Left.html");
		addHtmlViewVariable(pp, pageClass, "content_center",
				getClassName(pageClass) + "_Center.html");
		addHtmlViewVariable(pp, T1ResizedLCTemplatePage.class, "content");
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
			sb.append(super.hackVerticalTabs(pp));
		}
		kv.add("toolbar_left", sb.toString()).add("toolbar_center", super._toContentBarHTML(pp));
		return kv;
	}

	@Override
	protected String hackVerticalTabs(final PageParameter pp) {
		return "";
	}

	@Override
	public String _toContentBarHTML(final PageParameter pp) {
		return "";
	}
}
