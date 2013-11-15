package net.simpleframework.mvc.template.t1.ext;

import java.io.IOException;
import java.util.Map;

import net.simpleframework.mvc.AbstractMVCPage;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.component.AbstractComponentBean;
import net.simpleframework.mvc.component.ext.category.CategoryBean;
import net.simpleframework.mvc.component.ext.category.ICategoryHandler;
import net.simpleframework.mvc.component.ui.tree.ITreeHandler;
import net.simpleframework.mvc.component.ui.tree.TreeBean;
import net.simpleframework.mvc.template.t1.T1ResizedLCTemplatePage;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public abstract class CategoryLCTemplatePage extends T1ResizedLCTemplatePage {

	public static final String COMPONENT_CATEGORY = "TP_ComponentCategory";

	private AbstractComponentBean categoryBean;

	public AbstractComponentBean getCategoryBean() {
		return categoryBean;
	}

	@Override
	protected void onForward(final PageParameter pp) {
		super.onForward(pp);
		addHtmlViewVariable(CategoryLCTemplatePage.class, "content_left");
		addHtmlViewVariable(getClass(), "content_center");
	}

	protected CategoryBean addCategoryBean(final PageParameter pp,
			final Class<? extends ICategoryHandler> handleClass) {
		return addCategoryBean(pp, COMPONENT_CATEGORY, handleClass);
	}

	protected CategoryBean addCategoryBean(final PageParameter pp, final String name,
			final Class<? extends ICategoryHandler> handleClass) {
		return (CategoryBean) (categoryBean = addComponentBean(pp, name, CategoryBean.class)
				.setContainerId("category_" + hashId).setHandleClass(handleClass));
	}

	protected TreeBean addTreeBean(final PageParameter pp,
			final Class<? extends ITreeHandler> handleClass) {
		return addTreeBean(pp, COMPONENT_CATEGORY, handleClass);
	}

	protected TreeBean addTreeBean(final PageParameter pp, final String name,
			final Class<? extends ITreeHandler> handleClass) {
		return (TreeBean) (categoryBean = addComponentBean(pp, name, TreeBean.class).setContainerId(
				"category_" + hashId).setHandleClass(handleClass));
	}

	@Override
	protected String toHtml(final PageParameter pp,
			final Class<? extends AbstractMVCPage> pageClass, final Map<String, Object> variables,
			final String currentVariable) throws IOException {
		if ("content_left".equals(currentVariable)) {
			final StringBuilder sb = new StringBuilder();
			sb.append("<div class='CategoryLCTemplatePage' id='category_").append(hashId)
					.append("'></div>");
			sb.append(TAG_SCRIPT_START);
			sb.append("$ready(function() {");
			sb.append(" var tree = $Actions['").append(COMPONENT_CATEGORY).append("'];");
			sb.append(" var category = $('category_").append(hashId).append("');");
			sb.append(" tree.jsLoadedCallback = function() {");
			sb.append("  var bar = tree.json['bar'];");
			sb.append("  var tb;");
			sb.append("  if (bar && (tb = category.previous('.tool_bar')))");
			sb.append("    tb.replace(bar);");
			sb.append(" }");
			sb.append("});");
			sb.append(TAG_SCRIPT_END);
			return sb.toString();
		}
		return super.toHtml(pp, pageClass, variables, currentVariable);
	}
}
