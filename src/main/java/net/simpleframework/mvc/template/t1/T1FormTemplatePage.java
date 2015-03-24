package net.simpleframework.mvc.template.t1;

import java.io.IOException;
import java.util.Map;

import net.simpleframework.ctx.permission.PermissionUser;
import net.simpleframework.mvc.AbstractMVCPage;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.common.element.ElementList;
import net.simpleframework.mvc.common.element.SpanElement;
import net.simpleframework.mvc.common.element.TabButtons;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class T1FormTemplatePage extends T1TemplatePage {

	@Override
	protected void onForward(final PageParameter pp) {
		super.onForward(pp);

		pp.addImportCSS(T1TemplatePage.class, "/t1_form.css");

		addHtmlViewVariable(pp, T1FormTemplatePage.class, "body");
		addHtmlViewVariable(pp, getClass(), "content");
	}

	protected SpanElement titleElement(final Object title) {
		return new SpanElement(title).setClassName("lbl");
	}

	@Override
	public TabButtons getTabButtons(final PageParameter pp) {
		return null;
	}

	@Override
	public ElementList getRightElements(final PageParameter pp) {
		final ElementList el = ElementList.of();
		final TabButtons tabs = getTabButtons(pp);
		if (tabs != null && tabs.size() > 0) {
			el.append(new SpanElement().addHtml(tabs.toString(pp)));
		}
		final PermissionUser login = pp.getLogin();
		if (login.getId() != null) {
			el.append(new SpanElement(login).setClassName("login"));
		}
		return el;
	}

	@Override
	protected String toHtml(final PageParameter pp,
			final Class<? extends AbstractMVCPage> pageClass, final Map<String, Object> variables,
			final String currentVariable) throws IOException {
		if (T1FormTemplatePage.class.equals(pageClass)) {
			final StringBuilder sb = new StringBuilder();
			final ElementList le = getLeftElements(pp);
			final boolean b1 = le != null && le.size() > 0;
			final ElementList re = getRightElements(pp);
			final boolean b2 = re != null && re.size() > 0;
			sb.append("<div align='center'>");
			if (b1 || b2) {
				sb.append("<div class='form_tb_bar'>");
				if (b1) {
					sb.append("<div class='le'>").append(le.toString()).append("</div>");
				}
				if (b2) {
					sb.append("<div class='re'>").append(re.toString()).append("</div>");
				}
				sb.append("</div>");
				sb.append("<div class='form_top_gap'></div>");
			}
			sb.append(" <div class='form_content'>").append(variables.get("content")).append("</div>");
			sb.append("</div>");
			return sb.toString();
		}
		return super.toHtml(pp, pageClass, variables, currentVariable);
	}
}
