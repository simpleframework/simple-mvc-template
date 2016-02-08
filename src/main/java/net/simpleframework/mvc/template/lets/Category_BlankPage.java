package net.simpleframework.mvc.template.lets;

import java.util.Map;

import net.simpleframework.common.coll.KVMap;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.template.AbstractTemplatePage;
import net.simpleframework.mvc.template.struct.CategoryItems;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public abstract class Category_BlankPage extends AbstractTwoColPage {

	@Override
	protected void onForward(final PageParameter pp) throws Exception {
		super.onForward(pp);

		pp.addImportCSS(AbstractTemplatePage.class, "/category.css");

		addHtmlViewVariable(pp, getClass(), "col2");
	}

	protected CategoryItems getCategoryList(final PageParameter pp) {
		return null;
	}

	public String toCategoryHTML(final PageParameter pp) {
		final CategoryItems categories = getCategoryList(pp);
		if (categories != null) {
			return categories.toString();
		}
		return "";
	}

	protected int getCategoryWidth(final PageParameter pp) {
		return 160;
	}

	@Override
	public Map<String, Object> createVariables(final PageParameter pp) {
		return ((KVMap) super.createVariables(pp)).add("w", getCategoryWidth(pp)).add("categoryHTML",
				toCategoryHTML(pp));
	}
}
