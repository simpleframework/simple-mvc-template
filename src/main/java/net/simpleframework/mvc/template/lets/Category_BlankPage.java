package net.simpleframework.mvc.template.lets;

import java.io.IOException;
import java.util.Map;

import net.simpleframework.mvc.AbstractMVCPage;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.template.AbstractTemplatePage;
import net.simpleframework.mvc.template.struct.CategoryItems;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         https://github.com/simpleframework
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

	protected int getCategoryWidth(final PageParameter pp) {
		return 160;
	}

	protected String toCategoryHTML(final PageParameter pp) {
		final CategoryItems categories = getCategoryList(pp);
		if (categories != null) {
			return categories.toString();
		}
		return "";
	}

	@Override
	protected String toHtml(final PageParameter pp, final Class<? extends AbstractMVCPage> pageClass,
			final Map<String, Object> variables, final String currentVariable) throws IOException {
		if (Category_BlankPage.class.equals(pageClass)) {
			final StringBuilder sb = new StringBuilder();
			sb.append("<div class='Category_BlankPage'>");
			sb.append(" <div style='width: ").append(getCategoryWidth(pp))
					.append("px;' class='category'>");
			sb.append("  <div class='col1'>").append(toCategoryHTML(pp)).append("</div>");
			sb.append(" </div>");
			sb.append(" <div class='col2'>").append(variables.get("col2")).append("</div>");
			sb.append("</div>");
			return sb.toString();
		}
		return super.toHtml(pp, pageClass, variables, currentVariable);
	}
}
