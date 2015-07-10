package net.simpleframework.mvc.template.lets;

import java.io.IOException;
import java.util.Map;

import net.simpleframework.mvc.AbstractMVCPage;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.common.element.ButtonElement;
import net.simpleframework.mvc.common.element.ElementList;
import net.simpleframework.mvc.common.element.SpanElement;
import net.simpleframework.mvc.component.ui.propeditor.PropEditorBean;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public abstract class FormPropEditorTemplatePage extends FormExTemplatePage {

	@Override
	protected void onForward(final PageParameter pp) throws Exception {
		super.onForward(pp);
		addHtmlViewVariable(pp, FormPropEditorTemplatePage.class, "_form");

		final PropEditorBean propEditor = (PropEditorBean) addComponentBean(pp,
				"FormPropEditorTemplatePage_propeditor", PropEditorBean.class).setContainerId(
				"propeditor_" + hashId);
		initPropEditor(pp, propEditor);
	}

	@Override
	public ElementList getRightElements(final PageParameter pp) {
		return ElementList.of(SAVE_BTN(), SpanElement.SPACE, ButtonElement.closeBtn());
	}

	@Override
	protected String toHtml(final PageParameter pp,
			final Class<? extends AbstractMVCPage> pageClass, final Map<String, Object> variables,
			final String currentVariable) throws IOException {
		if (FormPropEditorTemplatePage.class.equals(pageClass)) {
			final StringBuilder sb = new StringBuilder();
			sb.append("<div class='FormPropEditorTemplatePage simple_window_tcb'>");
			sb.append(" <div class='c'>");
			sb.append("  <form id='propeditor_").append(hashId).append("'></form>");
			sb.append(" </div>");
			sb.append(" <div class='b'>").append(getRightElements(pp)).append("</div>");
			sb.append("</div>");
			return sb.toString();
		}
		return super.toHtml(pp, pageClass, variables, currentVariable);
	}

	protected abstract void initPropEditor(PageParameter pp, PropEditorBean propEditor);
}
