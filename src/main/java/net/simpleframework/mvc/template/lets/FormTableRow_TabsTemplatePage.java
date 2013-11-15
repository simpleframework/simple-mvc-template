package net.simpleframework.mvc.template.lets;

import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.component.ui.tabs.TabItems;
import net.simpleframework.mvc.component.ui.tabs.TabsBean;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public abstract class FormTableRow_TabsTemplatePage extends FormTableRowTemplatePage {

	protected TabItems getTabItems(final PageParameter pp) {
		return null;
	}

	@Override
	protected String _toFormBottomHTML(final PageParameter pp) {
		final TabItems items = getTabItems(pp);
		if (items != null && items.size() > 0) {
			final StringBuilder sb = new StringBuilder();
			final String containerId = "tabs_" + hashId;
			final TabsBean tabs = (TabsBean) addComponentBean(pp,
					"FormTableRow_TabsTemplatePage_tabs", TabsBean.class).setContainerId(containerId);
			tabs.getTabItems().addAll(items);
			sb.append("<div id='").append(containerId).append("'></div>");
			return sb.toString();
		}
		return null;
	}
}
