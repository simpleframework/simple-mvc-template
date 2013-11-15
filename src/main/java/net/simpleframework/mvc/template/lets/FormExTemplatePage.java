package net.simpleframework.mvc.template.lets;

import net.simpleframework.common.StringUtils;
import net.simpleframework.mvc.JavascriptForward;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.common.element.ButtonElement;
import net.simpleframework.mvc.component.ComponentParameter;
import net.simpleframework.mvc.component.base.validation.EWarnType;
import net.simpleframework.mvc.component.base.validation.ValidationBean;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public abstract class FormExTemplatePage extends FormTemplatePage {

	protected final String AJAX_SAVE = "ajax_" + hashId;

	@Override
	protected void onForward(final PageParameter pp) {
		super.onForward(pp);

		// 添加一个ajaxRequest组件
		addAjaxRequest(pp, AJAX_SAVE).setHandleMethod("onSave").setSelector(getFormSelector());
	}

	protected String getFormSelector() {
		return "#" + getBlockId();
	}

	protected ValidationBean addFormValidationBean(final PageParameter pp) {
		return addComponentBean(pp, "validation_" + hashId, ValidationBean.class).setWarnType(
				EWarnType.insertAfter).setTriggerSelector("#" + getBlockId() + " .validation");
	}

	/**
	 * 确认按钮的action函数
	 * 
	 * @param compParameter
	 * @return 返回浏览器端需要执行的javascript，缺省是关闭Dialog窗口，子类super得到后，然后重组自己的逻辑
	 */
	public JavascriptForward onSave(final ComponentParameter cp) {
		return new JavascriptForward().append("$win($Actions['").append(cp.getComponentName())
				.append("'].trigger).close();");
	}

	protected ButtonElement SAVE_BTN() {
		return SAVE_BTN(null);
	}

	protected ButtonElement SAVE_BTN(final String params) {
		final StringBuilder sb = new StringBuilder();
		sb.append("$Actions['").append(AJAX_SAVE).append("'](");
		if (StringUtils.hasText(params)) {
			sb.append("'").append(params).append("'");
		}
		sb.append(");");
		return ButtonElement.saveBtn().setClassName("validation").setOnclick(sb.toString());
	}

	protected ButtonElement VALIDATION_BTN() {
		return new ButtonElement().setClassName("validation");
	}
}
