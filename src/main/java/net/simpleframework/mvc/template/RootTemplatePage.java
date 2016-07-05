package net.simpleframework.mvc.template;

import java.util.Collection;

import net.simpleframework.mvc.AbstractMVCPage;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.common.element.LinkElement;
import net.simpleframework.mvc.common.element.Meta;
import net.simpleframework.mvc.component.ComponentParameter;
import net.simpleframework.mvc.component.ui.menu.AbstractMenuHandler;
import net.simpleframework.mvc.component.ui.menu.MenuItem;
import net.simpleframework.mvc.component.ui.menu.MenuItems;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public abstract class RootTemplatePage extends AbstractTemplatePage {

	@Override
	protected void onForward(final PageParameter pp) throws Exception {
		super.onForward(pp);

		pp.addImportCSS(AbstractTemplatePage.class, "/template.css");

		pp.addImportJavascript(AbstractTemplatePage.class, "/js/template.js");
	}

	@Override
	public void onHttpRequestMeta(final PageParameter pp, final Collection<Meta> coll) {
		super.onHttpRequestMeta(pp, coll);
		if (pp.isMobile()) {
			coll.add(Meta.DEFAULT_VIEWPORT);
		}
	}

	/**
	 * 创建mvel预定义好的模板
	 * 
	 * html写法：$includeNamed{'key'}
	 * 
	 * @param pp
	 */
	protected void addHeaderFooterNamedTemplates(final PageParameter pp) {
		/* 创建头尾模板 */
		final ITemplateHandler t = getTemplate(pp);
		if (t != null) {
			final Class<? extends AbstractMVCPage> header = t.getHeaderPage();
			if (header != null) {
				addMVELNamedTemplate(pp, "header", header);
			}
			Class<? extends AbstractMVCPage> footer;
			if (t.isShowFooter(pp) && (footer = t.getFooterPage()) != null) {
				addMVELNamedTemplate(pp, "footer", footer);
			}
		}
	}

	public ITemplateHandler getTemplate(final PageParameter pp) {
		return null;
	}

	@Override
	public String getFavicon(final PageParameter pp) {
		final ITemplateHandler tmp = getTemplate(pp);
		return tmp != null ? tmp.getFavicon(pp) : super.getFavicon(pp);
	}

	protected MenuItems getMainMenuItems(final ComponentParameter cp, final MenuItem menuItem) {
		final ITemplateHandler t = getTemplate(cp);
		return t != null ? t.getMainMenuItems(cp, menuItem) : null;
	}

	public static class MainMenuHandler extends AbstractMenuHandler {
		@Override
		public MenuItems getMenuItems(final ComponentParameter cp, final MenuItem menuItem) {
			if (menuItem == null) {
				return ((RootTemplatePage) AbstractMVCPage.get(cp)).getMainMenuItems(cp, menuItem);
			}
			return null;
		}
	}

	public static LinkElement CHROME_LINK = LinkElement.BLANK("Chrome").setHref(
			"http://www.google.cn/chrome/browser/");
	public static LinkElement FIREFOX_LINK = LinkElement.BLANK("Firefox").setHref(
			"http://firefox.com.cn/");

}
