package net.simpleframework.mvc.template.lets;

import java.io.IOException;
import java.util.Map;

import net.simpleframework.common.StringUtils;
import net.simpleframework.common.web.JavascriptUtils;
import net.simpleframework.mvc.AbstractMVCPage;
import net.simpleframework.mvc.IPageHandler.PageSelector;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.template.BlockTemplatePage;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public abstract class FormTemplatePage extends BlockTemplatePage {

	@Override
	protected void onForward(final PageParameter pp) throws Exception {
		super.onForward(pp);
		addHtmlViewVariable(pp, FormTemplatePage.class, "_block");
		addHtmlViewVariable(pp, getClass(), "_form");

		getPageBean().setHandlerClass(getClass().getName()).setHandlerMethod("onLoad");
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
			sb.append(JavascriptUtils.wrapScriptTag(toFormJS(pp), true));
			return sb.toString();
		}
		return super.toHtml(pp, pageClass, variables, currentVariable);
	}

	private String toFormJS(final PageParameter pp) {
		final StringBuilder sb = new StringBuilder();
		sb.append("var _block = $('#").append(getBlockId()).append("');");
		// 设置autorows
		sb.append("_block.select('textarea').each(function(ele) {");
		sb.append(" if (ele.getAttribute('autorows') == 'true') {");
		sb.append("  ele.style.height = '0px';");
		sb.append("  ele.style.height = ele.scrollHeight + 'px';");
		sb.append(" }");
		sb.append("});");

		sb.append("var _focus;");
		final String focusElement = getFocusElement(pp);
		if (StringUtils.hasText(focusElement)) {
			sb.append("_focus = $('").append(focusElement).append("');");
		}
		sb.append("if (!_focus) { var _form = _block.down('form'); if (_form) _focus = _form.findFirstElement(); }");
		sb.append("if (_focus) { _focus.focus(); }");

		sb.append("$UI.disableBackspace();");
		return sb.toString();
	}
}
