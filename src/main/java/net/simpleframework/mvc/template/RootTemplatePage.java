package net.simpleframework.mvc.template;

import net.simpleframework.mvc.AbstractMVCPage;
import net.simpleframework.mvc.ITemplateHandler;
import net.simpleframework.mvc.PageParameter;
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
	protected void addImportCSS(final PageParameter pp) {
		super.addImportCSS(pp);

		pp.addImportCSS(AbstractTemplatePage.class, "/template.css");
	}

	@Override
	protected void addImportJavascript(final PageParameter pp) {
		super.addImportJavascript(pp);
		pp.addImportJavascript(AbstractTemplatePage.class, "/js/template.js");
	}

	@Override
	protected NamedTemplate createNamedTemplates(final PageParameter pp) {
		Class<? extends AbstractMVCPage> header = null, footer = null;
		final ITemplateHandler t = ctx.getTemplate(pp);
		if (t instanceof ITemplateHandler) {
			final ITemplateHandler t1 = t;
			header = t1.getHeaderPage();
			footer = t1.getFooterPage();
		}
		return new NamedTemplate(pp).add("header", header == null ? HeaderPage.class : header).add(
				"footer", footer == null ? FooterPage.class : footer);
	}

	protected MenuItems getMainMenuItems(final ComponentParameter cp, final MenuItem menuItem) {
		final ITemplateHandler t = ctx.getTemplate(cp);
		if (t instanceof AbstractTemplateHandler) {
			return ((AbstractTemplateHandler) t).getMainMenuItems(cp, menuItem);
		}
		return null;
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
}
