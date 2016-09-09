package net.simpleframework.mvc.template.t1;

import java.util.Map;

import net.simpleframework.common.Convert;
import net.simpleframework.common.StringUtils;
import net.simpleframework.common.coll.KVMap;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.common.element.ETextAlign;
import net.simpleframework.mvc.common.element.ElementList;
import net.simpleframework.mvc.common.element.TabButtons;
import net.simpleframework.mvc.template.IPageFilterbarAware;
import net.simpleframework.mvc.template.IPageToolbarAware;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public abstract class T1ResizedTemplatePage extends T1TemplatePage
		implements IPageToolbarAware, IPageFilterbarAware {

	@Override
	protected void onForward(final PageParameter pp) throws Exception {
		super.onForward(pp);

		pp.addImportCSS(T1TemplatePage.class, "/t1_resized.css");
		getTemplate(pp).addPageResource(pp, this);

		addHtmlViewVariable(pp, T1ResizedTemplatePage.class, "body");
		addHtmlViewVariable(pp, getClass(), "content");
	}

	private static final String toggleCookie = "t1_toggleHide";

	@Override
	public Map<String, Object> createVariables(final PageParameter pp) {
		final KVMap kv = ((KVMap) super.createVariables(pp)).add("toggleCookie", toggleCookie)
				.add("headerHidden", isHeaderHidden(pp));
		return kv;
	}

	/**
	 * 是否隐藏header
	 * 
	 * @param pp
	 * @return
	 */
	protected boolean isHeaderHidden(final PageParameter pp) {
		return Convert.toBool(pp.getCookie(toggleCookie));
	}

	protected boolean isTabsVertical(final PageParameter pp) {
		return true;
	}

	protected int getTabsrMarginTop(final PageParameter pp) {
		return 45;
	}

	protected String hackVerticalTabs(final PageParameter pp) {
		final StringBuilder sb = new StringBuilder();
		int top;
		if (isTabsVertical(pp) && (top = getTabsrMarginTop(pp)) > 0) {
			sb.append("<style type='text/css'>");
			sb.append("#resized_tabbar2 .simple_tabs.vertical { margin-top: ").append(top)
					.append("px !important; }");
			sb.append("</style>");
		}
		return sb.toString();
	}

	public String _toContentBarHTML(final PageParameter pp) {
		final StringBuilder sb = new StringBuilder();
		String html = toToolbarHTML(pp);
		if (StringUtils.hasText(html)) {
			sb.append(html);
			sb.append(hackVerticalTabs(pp));
		}
		html = toFilterBarHTML(pp);
		if (StringUtils.hasText(html)) {
			sb.append("<div class='filter_bar'>").append(html).append("</div>");
		}
		if (sb.length() > 0) {
			sb.insert(0, "<div class='tb_container'>");
			sb.append("</div>");
		}
		return sb.toString();
	}

	public String _toHeaderHTML(final PageParameter pp) {
		final StringBuilder sb = new StringBuilder();
		sb.append("<div id='resized_header'");
		if (Convert.toBool(getVariables(pp).get("headerHidden"))) {
			sb.append(" style='display:none;'");
		}
		sb.append(">").append(getMVELNamedTemplate(pp, "header")).append("</div>");
		return sb.toString();
	}

	public String _toToggleHTML(final PageParameter pp) {
		final StringBuilder sb = new StringBuilder();
		sb.append("<div id='resized_toggle_bar'").append(
				Convert.toBool(getVariables(pp).get("headerHidden")) ? " class='t2'" : " class='t1'");
		sb.append("></div>");
		return sb.toString();
	}

	public String _toTabsHTML(final PageParameter pp) {
		final StringBuilder sb = new StringBuilder();
		final TabButtons tabs = getTabButtons(pp);
		if (tabs != null && tabs.size() > 0) {
			final boolean vertical = isTabsVertical(pp);
			sb.append("<div id='").append(vertical ? "resized_tabbar2" : "resized_tabbar")
					.append("'>");
			final ElementList el = tabs.setVertical(isTabsVertical(pp)).getTopElements();
			if (el != null) {
				sb.append("<div class='tb_top'>").append(el).append("</div>");
			}
			sb.append("<div class='tabs_bar");
			if (tabs.getTextAlign() == ETextAlign.right) {
				sb.append(" tab_right");
			}
			sb.append("'>").append(tabs.toString(pp)).append("</div>");
			sb.append("</div>");
		}
		return sb.toString();
	}
}
