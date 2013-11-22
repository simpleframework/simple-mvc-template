package net.simpleframework.mvc.template.lets;

import java.io.IOException;
import java.util.Map;

import net.simpleframework.common.StringUtils;
import net.simpleframework.common.web.html.HtmlConst;
import net.simpleframework.mvc.AbstractMVCPage;
import net.simpleframework.mvc.IPageHandler.PageSelector;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.template.BlockTemplatePage;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public abstract class FormTemplatePage extends BlockTemplatePage {

	@Override
	protected void onForward(final PageParameter pp) {
		super.onForward(pp);
		addHtmlViewVariable(pp, FormTemplatePage.class, "_block");
		addHtmlViewVariable(pp, getClass(), "_form");

		getPageBean().setHandleClass(getClass().getName()).setHandleMethod("onLoad");
	}

	/**
	 * Form页面的初始化函数，子类覆盖
	 * 
	 * @param pageParameter
	 * @param dataBinding
	 * @param selector
	 */
	public void onLoad(final PageParameter pp, final Map<String, Object> dataBinding,
			final PageSelector selector) {
	}

	public String getFocusElement(final PageParameter pp) {
		// $if{StringUtils.hasText(elementId = page.getFocusElementId(parameter))}
		// $endif{}
		return null;
	}

	@Override
	protected String toHtml(final PageParameter pp,
			final Class<? extends AbstractMVCPage> pageClass, final Map<String, Object> variables,
			final String currentVariable) throws IOException {
		if (FormTemplatePage.class.equals(pageClass)) {
			final StringBuilder sb = new StringBuilder();
			sb.append("<div class='FormTemplatePage'>");
			sb.append(variables.get("_form"));
			sb.append("</div>");
			final String focusElement = getFocusElement(pp);
			if (StringUtils.hasText(focusElement)) {
				sb.append(HtmlConst.TAG_SCRIPT_START);
				sb.append("(function() { var f = $('").append(focusElement)
						.append("'); if (f) f.focus(); }).defer();");
				sb.append(HtmlConst.TAG_SCRIPT_END);
			}
			return sb.toString();
		}
		return super.toHtml(pp, pageClass, variables, currentVariable);
	}
}
