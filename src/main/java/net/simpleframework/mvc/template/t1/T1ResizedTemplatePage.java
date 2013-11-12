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
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public abstract class T1ResizedTemplatePage extends T1TemplatePage implements IPageToolbarAware,
		IPageFilterbarAware {

	@Override
	protected void onForward(final PageParameter pp) {
		super.onForward(pp);

		addHtmlViewVariable(T1ResizedTemplatePage.class, "body");
		addHtmlViewVariable(getClass(), "content");
	}

	@Override
	protected void addImportCSS(final PageParameter pp) {
		super.addImportCSS(pp);

		pp.addImportCSS(T1TemplatePage.class, "/t1_resized.css");
	}

	private static final String toggleCookie = "t1_toggleHide";

	@Override
	public Map<String, Object> createVariables(final PageParameter pp) {
		final boolean hide = isHeaderHidden(pp);
		final KVMap kv = ((KVMap) super.createVariables(pp)).add("toggleCookie", toggleCookie)
				.add("headerAttr", hide ? " style='display:none;'" : "")
				.add("toggleAttr", hide ? " class='t2'" : " class='t1'");
		final StringBuilder sb = new StringBuilder();
		final boolean vertical = isTabbarVertical(pp);
		final String tabs = toTabsHTML(pp);
		if (StringUtils.hasText(tabs)) {
			sb.append("<div id='").append(vertical ? "resized_tabbar2" : "resized_tabbar")
					.append("'>").append(tabs).append("</div>");
		}
		kv.add("tabsHTML", sb.toString()).add("vertical", vertical);
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

	protected boolean isTabbarVertical(final PageParameter pp) {
		return true;
	}

	public String toTabsHTML(final PageParameter pp) {
		final StringBuilder sb = new StringBuilder();
		final TabButtons tabs = getTabButtons(pp);
		if (tabs != null && tabs.size() > 0) {
			final ElementList el = tabs.getTopElements();
			if (el != null) {
				sb.append("<div class='tb_top'>").append(el).append("</div>");
			}
			sb.append("<div class='tabs_bar'><div class='tab");
			if (tabs.getTextAlign() == ETextAlign.right) {
				sb.append(" tab_right");
			}
			sb.append("'>").append(tabs.toString(pp));
			sb.append("</div></div>");
		}
		return sb.toString();
	}

	public String _toContentBarHTML(final PageParameter pp) {
		return _toContentBarHTML(pp, true);
	}

	protected String _toContentBarHTML(final PageParameter pp, final boolean cssHack) {
		final StringBuilder sb = new StringBuilder();
		String html = toToolbarHTML(pp);
		if (StringUtils.hasText(html)) {
			sb.append(html);
		}
		html = toFilterBarHTML(pp);
		if (StringUtils.hasText(html)) {
			sb.append("<div class='filter_bar'>").append(html).append("</div>");
		}
		if (sb.length() > 0) {
			sb.insert(0, "<div class='tb_container'>");
			sb.append("</div>");
			if (cssHack) {
				sb.append("<style type='text/css'>");
				sb.append("#resized_tabbar2 .simple_tabs { margin-top: 45px; }");
				sb.append("</style>");
			}
		}
		return sb.toString();
	}
}
