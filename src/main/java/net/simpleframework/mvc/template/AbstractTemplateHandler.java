package net.simpleframework.mvc.template;

import net.simpleframework.common.StringUtils;
import net.simpleframework.common.object.ObjectEx;
import net.simpleframework.ctx.IModuleContext;
import net.simpleframework.ctx.Module;
import net.simpleframework.ctx.ModuleContextFactory;
import net.simpleframework.ctx.ModuleFunction;
import net.simpleframework.ctx.ModuleFunctions;
import net.simpleframework.mvc.AbstractMVCPage;
import net.simpleframework.mvc.IMVCConst;
import net.simpleframework.mvc.IMVCContextVar;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.common.element.LinkElement;
import net.simpleframework.mvc.component.ComponentParameter;
import net.simpleframework.mvc.component.ui.menu.MenuItem;
import net.simpleframework.mvc.component.ui.menu.MenuItems;
import net.simpleframework.mvc.ctx.WebModuleFunction;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public abstract class AbstractTemplateHandler extends ObjectEx implements ITemplateHandler,
		IMVCContextVar, IMVCConst {

	@Override
	public Class<? extends AbstractMVCPage> getHeaderPage() {
		return HeaderPage.class;
	}

	@Override
	public Class<? extends AbstractMVCPage> getFooterPage() {
		return FooterPage.class;
	}

	protected AbstractMVCPage getParentPage(final PageParameter pp) {
		final String pageClass = pp.getParameter(PARAM_PARENT_PAGE);
		return pageClass != null ? (AbstractMVCPage) singleton(pageClass) : null;
	}

	@Override
	public String getFavicon(final PageParameter pp) {
		return null;
	}

	public LinkElement getNavigationHome(final PageParameter pp) {
		return new LinkElement("#(AbstractTemplateHandler.0)").setHref("/");
	}

	@Override
	public boolean isShowMenubar(final PageParameter pp) {
		return true;
	}

	@Override
	public boolean isShowFooter(final PageParameter pp) {
		return true;
	}

	@Override
	public MenuItems getMainMenuItems(final ComponentParameter cp, final MenuItem menuItem) {
		final MenuItems al = MenuItems.of();
		for (final IModuleContext ctx : ModuleContextFactory.allModules()) {
			final Module module = ctx.getModule();
			MenuItem moduleItem = null;
			final ModuleFunction defaultFunction = ModuleFunctions.getFunctionByName(module
					.getDefaultFunction());
			if (defaultFunction instanceof WebModuleFunction) {
				if (!defaultFunction.isDisabled()) {
					moduleItem = MenuItem.of(defaultFunction.getText()).setUrl(
							((WebModuleFunction) defaultFunction).getUrl());
				}
			} else {
				moduleItem = MenuItem.of(module.getText());
			}
			if (moduleItem != null) {
				final MenuItems children = moduleItem.children();
				doSubItems(ctx, children, null, defaultFunction);
				if (StringUtils.hasText(moduleItem.getUrl()) || children.size() > 0) {
					al.add(moduleItem);
				}
			}
		}
		if (isShowMainMenuSeparator(cp)) {
			int i = 0, j = 0;
			final int c = al.size();
			while (++i < c) {
				al.add(i + j++, MenuItem.sep());
			}
		}
		return al;
	}

	protected boolean isShowMainMenuSeparator(final PageParameter pp) {
		return false;
	}

	private void doSubItems(final IModuleContext ctx, final MenuItems children,
			final ModuleFunction parent, final ModuleFunction defaultFunction) {
		final ModuleFunctions functions = ctx.getFunctions(parent);
		if (functions != null) {
			for (final ModuleFunction function : functions) {
				if (function.isDisabled()) {
					continue;
				}
				if (!(function instanceof WebModuleFunction)) {
					continue;
				}
				if (defaultFunction != null && defaultFunction.equals(function)) {
					continue;
				}
				final MenuItem item = MenuItem.of(function.getText()).setUrl(
						((WebModuleFunction) function).getUrl());
				children.add(item);
				doSubItems(ctx, item.children(), function, defaultFunction);
			}
		}
	}
}
