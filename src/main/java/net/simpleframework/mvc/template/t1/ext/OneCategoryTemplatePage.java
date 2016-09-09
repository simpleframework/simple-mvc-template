package net.simpleframework.mvc.template.t1.ext;

import static net.simpleframework.common.I18n.$m;

import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.component.ext.category.CategoryBean;
import net.simpleframework.mvc.component.ext.category.ICategoryHandler;
import net.simpleframework.mvc.template.lets.OneTreeTemplatePage;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public abstract class OneCategoryTemplatePage extends OneTreeTemplatePage {

	@Override
	protected CategoryBean addCategoryBean(final PageParameter pp, final String name,
			final Class<? extends ICategoryHandler> handlerClass) {
		return (CategoryBean) addComponentBean(pp, name, CategoryBean.class)
				.setContainerId("tree_" + hashId).setHandlerClass(handlerClass);
	}

	@Override
	public String toTopicHTML(final PageParameter pp) {
		return $m("OneCategoryTemplatePage.0");
	}
}
