package net.simpleframework.mvc.template.lets;

import static net.simpleframework.common.I18n.$m;

import java.io.IOException;
import java.util.Map;

import net.simpleframework.common.StringUtils;
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
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public abstract class FormTableRowTemplatePage extends FormExTemplatePage implements
		IPageToolbarAware {

	@Override
	protected void onForward(final PageParameter pp) {
		super.onForward(pp);
		addHtmlViewVariable(FormTableRowTemplatePage.class, "_form");
	}

	/**
	 * 定义label的宽度
	 * 
	 * @param pParameter
	 * @return
	 */
	public int getLabelWidth(final PageParameter pp) {
		return 100;
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
		return tableRows != null ? tableRows.toString() : "";
	}

	protected boolean show_opt_next(final PageParameter pp) {
		return false;
	}

	protected static final String OPT_NEXT = "opt_next";

	@Override
	public ElementList getLeftElements(final PageParameter pp) {
		final ElementList el = ElementList.of();
		if (show_opt_next(pp)) {
			el.append(new Checkbox(OPT_NEXT + hashId, $m("FormExTemplatePage.0")).setName(OPT_NEXT));
		}
		return el;
	}

	@Override
	public ElementList getRightElements(final PageParameter pp) {
		return ElementList.of(SAVE_BTN(), SpanElement.SPACE, ButtonElement.WINDOW_CLOSE);
	}

	@Override
	protected String toHtml(final PageParameter pp,
			final Class<? extends AbstractMVCPage> pageClass, final Map<String, Object> variables,
			final String currentVariable) throws IOException {
		if (FormTableRowTemplatePage.class.equals(pageClass)) {
			final StringBuilder sb = new StringBuilder();
			sb.append("<div class='FormTableRowTemplatePage'>");
			final boolean top = isButtonsOnTop(pp);
			if (top) {
				sb.append(toToolbarHTML(pp));
			}
			sb.append("<form>").append(toTableRowsString(pp)).append("</form>");
			final String bstr = _toFormBottomHTML(pp);
			if (StringUtils.hasText(bstr)) {
				sb.append(bstr);
			}
			if (!top) {
				sb.append(toToolbarHTML(pp));
			}
			sb.append("</div>");
			sb.append("<style type='text/css'>");
			sb.append("#").append(getBlockId()).append(" .form_tbl .l {");
			sb.append("width: ").append(getLabelWidth(pp)).append("px;");
			sb.append("}");
			sb.append("</style>");
			return sb.toString();
		}
		return super.toHtml(pp, pageClass, variables, currentVariable);
	}

	protected String _toFormBottomHTML(final PageParameter pp) {
		return null;
	}
}
