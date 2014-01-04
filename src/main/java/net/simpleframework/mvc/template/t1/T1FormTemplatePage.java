package net.simpleframework.mvc.template.t1;

import static net.simpleframework.common.I18n.$m;

import java.io.IOException;
import java.util.Map;

import net.simpleframework.mvc.AbstractMVCPage;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.common.element.ElementList;
import net.simpleframework.mvc.common.element.Icon;
import net.simpleframework.mvc.common.element.LinkButton;
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

	protected LinkButton backBtn() {
		return new LinkButton($m("T1FormTemplatePage.0")).setIconClass(Icon.share_alt);
	}

	@Override
	protected TabButtons getTabButtons(final PageParameter pp) {
		return null;
	}

	@Override
	protected String toHtml(final PageParameter pp,
			final Class<? extends AbstractMVCPage> pageClass, final Map<String, Object> variables,
			final String currentVariable) throws IOException {
		if (T1FormTemplatePage.class.equals(pageClass)) {
			final StringBuilder sb = new StringBuilder();
			final ElementList eles = getLeftElements(pp);
			final boolean b1 = eles != null && eles.size() > 0;
			final TabButtons tabs = getTabButtons(pp);
			final boolean b2 = tabs != null && tabs.size() > 0;
			sb.append("<div align='center'>");
			if (b1 || b2) {
				sb.append("<div class='form_tabs_bar'>");
				if (b1) {
					sb.append("<div class='eles'>").append(eles.toString()).append("</div>");
				}
				if (b2) {
					sb.append("<div class='tab'>").append(tabs.toString(pp)).append("</div>");
				}
				sb.append("</div>");
				sb.append("<div class='form_tabs_bar2'></div>");
			}
			sb.append(" <div class='form_content'>").append(variables.get("content")).append("</div>");
			sb.append("</div>");
			return sb.toString();
		}
		return super.toHtml(pp, pageClass, variables, currentVariable);
	}
}
