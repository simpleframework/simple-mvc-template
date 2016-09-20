package net.simpleframework.mvc.template.lets;

import static net.simpleframework.common.I18n.$m;

import java.io.IOException;
import java.util.Map;

import net.simpleframework.common.StringUtils;
import net.simpleframework.common.web.JavascriptUtils;
import net.simpleframework.mvc.AbstractMVCPage;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.common.element.ButtonElement;
import net.simpleframework.mvc.common.element.Checkbox;
import net.simpleframework.mvc.common.element.ElementList;
import net.simpleframework.mvc.common.element.SpanElement;
import net.simpleframework.mvc.common.element.TableRows;
import net.simpleframework.mvc.template.IPageToolbarAware;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public abstract class FormTableRowTemplatePage extends FormExTemplatePage
		implements IPageToolbarAware {

	@Override
	protected void onForward(final PageParameter pp) throws Exception {
		super.onForward(pp);
		addHtmlViewVariable(pp, FormTableRowTemplatePage.class, "_form");
	}

	/**
	 * 定义label的宽度
	 * 
	 * @param pParameter
	 * @return
	 */
	public String getLabelWidth(final PageParameter pp) {
		return "100px";
	}

	public boolean isButtonsOnTop(final PageParameter pp) {
		return false;
	}

	/**
	 * 
	 * @return
	 */
	protected TableRows getTableRows(final PageParameter pp) {
		return null;
	}

	public String toTableRowsString(final PageParameter pp) {
		final TableRows tableRows = getTableRows(pp);
		return tableRows != null ? tableRows.toString() : null;
	}

	protected boolean isShowOptNext(final PageParameter pp) {
		return false;
	}

	protected static final String OPT_NEXT = "opt_next";

	@Override
	public ElementList getLeftElements(final PageParameter pp) {
		final ElementList el = ElementList.of();
		if (isShowOptNext(pp)) {
			el.append(new Checkbox(OPT_NEXT + hashId, $m("FormExTemplatePage.0")).setName(OPT_NEXT));
		}
		return el;
	}

	@Override
	public ElementList getRightElements(final PageParameter pp) {
		return ElementList.of(SAVE_BTN(), SpanElement.SPACE, ButtonElement.closeBtn());
	}

	protected ElementList getFormElements(final PageParameter pp) {
		return null;
	}

	@Override
	protected String toHtml(final PageParameter pp, final Class<? extends AbstractMVCPage> pageClass,
			final Map<String, Object> variables, final String currentVariable) throws IOException {
		if (FormTableRowTemplatePage.class.equals(pageClass)) {
			final StringBuilder sb = new StringBuilder();
			sb.append("<div class='FormTableRowTemplatePage'>");
			final boolean top = isButtonsOnTop(pp);
			if (top) {
				sb.append(toToolbarHTML(pp));
			}
			sb.append("<form>");
			final ElementList el = getFormElements(pp);
			if (el != null && el.size() > 0) {
				sb.append(el);
			}
			final String tableRows = toTableRowsString(pp);
			if (tableRows != null) {
				sb.append(tableRows);
			}
			sb.append("</form>");
			final String bstr = _toFormBottomHTML(pp);
			if (StringUtils.hasText(bstr)) {
				sb.append(bstr);
			}
			if (!top) {
				sb.append(toToolbarHTML(pp));
			}
			sb.append("</div>");
			return sb.append(toFormHTML(pp)).toString();
		}
		return super.toHtml(pp, pageClass, variables, currentVariable);
	}

	protected String toFormHTML(final PageParameter pp) {
		final StringBuilder sb = new StringBuilder();
		String lwidth = getLabelWidth(pp);
		if (StringUtils.hasText(lwidth)) {
			lwidth = lwidth.trim();
			sb.append("<style type='text/css'>");
			sb.append("#").append(getBlockId()).append(" .form_tbl .l {");
			sb.append("width: ");
			try {
				sb.append(Integer.parseInt(lwidth)).append("px;");
			} catch (final NumberFormatException e) {
				sb.append(lwidth);
				if (!lwidth.endsWith(";")) {
					sb.append(";");
				}
			}
			sb.append("}");
			sb.append("</style>");
		}

		final StringBuilder js = new StringBuilder();
		js.append(
				"var topb = $('.form_content .FormTableRowTemplatePage>.tool_bar'); if (!topb) return;");
		js.append("var w = parseInt(topb.getStyle('width'));");
		js.append("Event.observe(window, 'scroll', function() {");
		js.append(
				" var scrollTop = document.documentElement.scrollTop || document.body.scrollTop || 0;");
		js.append(" if (scrollTop > 0) {");
		js.append("   topb.addClassName('scroll');");
		js.append("   topb.up().addClassName('scroll');");
		js.append(
				"   if (!topb._scroll) { topb.setStyle('width: ' + w + 'px;'); topb._scroll = true; }");
		js.append(" } else {");
		js.append("   topb.removeClassName('scroll');");
		js.append("   topb.up().removeClassName('scroll');");
		js.append("   topb._scroll = null;");
		js.append(" }");
		js.append("});");
		sb.append(JavascriptUtils.wrapScriptTag(js.toString(), true));

		return sb.toString();
	}

	protected String _toFormBottomHTML(final PageParameter pp) {
		return null;
	}
}
