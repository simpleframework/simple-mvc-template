package net.simpleframework.mvc.template.lets;

import java.util.Map;

import net.simpleframework.common.coll.KVMap;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.common.element.LinkButton;
import net.simpleframework.mvc.component.ext.category.CategoryBean;
import net.simpleframework.mvc.component.ext.category.ICategoryHandler;
import net.simpleframework.mvc.component.ui.tree.ITreeHandler;
import net.simpleframework.mvc.component.ui.tree.TreeBean;
import net.simpleframework.mvc.template.BlockTemplatePage;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public abstract class OneTreeTemplatePage extends BlockTemplatePage {

	@Override
	protected void onForward(final PageParameter pp) {
		super.onForward(pp);

		addHtmlViewVariable(OneTreeTemplatePage.class, "_block");
	}

	@Override
	public Map<String, Object> getVariables(final PageParameter pp) {
		return ((KVMap) super.getVariables(pp)).add("treeId", "tree_" + hashId);
	}

	protected TreeBean addTreeBean(final PageParameter pp, final String name,
			final Class<? extends ITreeHandler> handleClass) {
		return (TreeBean) addComponentBean(pp, name, TreeBean.class).setContainerId("tree_" + hashId)
				.setHandleClass(handleClass);
	}

	protected CategoryBean addCategoryBean(final PageParameter pp, final String name,
			final Class<? extends ICategoryHandler> handleClass) {
		return (CategoryBean) addComponentBean(pp, name, CategoryBean.class).setContainerId(
				"tree_" + hashId).setHandleClass(handleClass);
	}

	public String toTopicHTML(final PageParameter pp) {
		return "";
	}

	public String toBottomHTML(final PageParameter pp) {
		final StringBuilder sb = new StringBuilder();
		sb.append(LinkButton.closeBtn());
		return sb.toString();
	}
}
