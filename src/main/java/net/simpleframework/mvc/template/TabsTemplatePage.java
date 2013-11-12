package net.simpleframework.mvc.template;

import java.io.IOException;
import java.util.Map;

import net.simpleframework.common.object.ObjectUtils;
import net.simpleframework.mvc.AbstractMVCPage;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.component.ComponentParameter;
import net.simpleframework.mvc.component.ui.tabs.DefaultTabsHandler;
import net.simpleframework.mvc.component.ui.tabs.TabItems;
import net.simpleframework.mvc.component.ui.tabs.TabsBean;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public abstract class TabsTemplatePage extends AbstractTemplatePage {

	@Override
	protected void addComponents(final PageParameter pp) {
		super.addComponents(pp);

		final Class<?> beanClass = getClass();
		if (!TabsTemplatePage.class.equals(beanClass)) {
			addComponentBean(pp, TabsBean.class, ContentTabsHandler.class).setContainerId(
					"tabs_" + ObjectUtils.hashStr(beanClass));
		}
	}

	@Override
	protected String toHtml(final PageParameter pp,
			final Class<? extends AbstractMVCPage> pageClass, final Map<String, Object> variables,
			final String variable) throws IOException {
		if (TabsTemplatePage.class.equals(pageClass)) {
			return "<div class='TabsTemplatePage' id='tabs_" + ObjectUtils.hashStr(getClass())
					+ "'></div>";
		}
		return super.toHtml(pp, pageClass, variables, variable);
	}

	protected TabItems tabItems(final PageParameter pp) {
		return null;
	}

	public static class ContentTabsHandler extends DefaultTabsHandler {
		@Override
		public TabItems tabItems(final ComponentParameter cp) {
			return ((TabsTemplatePage) get(cp)).tabItems(cp);
		}
	}
}
