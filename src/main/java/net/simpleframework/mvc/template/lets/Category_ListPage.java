package net.simpleframework.mvc.template.lets;

import java.io.IOException;
import java.util.Map;

import net.simpleframework.common.StringUtils;
import net.simpleframework.mvc.AbstractMVCPage;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.template.IPageToolbarAware;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class Category_ListPage extends Category_BlankPage implements IPageToolbarAware {
	@Override
	protected void onForward(final PageParameter pp) {
		super.onForward(pp);
		addHtmlViewVariable(Category_ListPage.class, "col2");
	}

	@Override
	protected String toHtml(final PageParameter pp,
			final Class<? extends AbstractMVCPage> pageClass, final Map<String, Object> variables,
			final String currentVariable) throws IOException {
		if (Category_ListPage.class.equals(pageClass)) {
			final StringBuilder sb = new StringBuilder();
			sb.append("<div class='Category_ListPage'>");
			sb.append(StringUtils.blank(toToolbarHTML(pp)));
			sb.append(" <div id='list_").append(hashId).append("'></div>");
			sb.append("</div>");
			return sb.toString();
		}
		return super.toHtml(pp, pageClass, variables, currentVariable);
	}
}
