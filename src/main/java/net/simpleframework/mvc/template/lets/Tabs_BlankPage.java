package net.simpleframework.mvc.template.lets;

import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.common.element.ElementList;
import net.simpleframework.mvc.common.element.TabButtons;
import net.simpleframework.mvc.template.AbstractTemplatePage;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public abstract class Tabs_BlankPage extends AbstractTemplatePage {

	@Override
	protected void onForward(final PageParameter pp) {
		super.onForward(pp);
		pp.addImportCSS(AbstractTemplatePage.class, "/tabs.css");

		addHtmlViewVariable(pp, getClass(), "cc");
	}

	public TabButtons getTabButtons(final PageParameter pp) {
		return null;
	}

	public String toTabsbarHTML(final PageParameter pp) {
		final StringBuilder sb = new StringBuilder();
		final ElementList el = getLeftElements(pp);
		if (el != null && el.size() > 0) {
			sb.append("<div class='le'>").append(el).append("</div>");
		}
		final TabButtons tabs = getTabButtons(pp);
		if (tabs != null && tabs.size() > 0) {
			sb.append("<div class='re'>").append(tabs.toString(pp)).append("</div>");
		}
		return sb.toString();
	}
}