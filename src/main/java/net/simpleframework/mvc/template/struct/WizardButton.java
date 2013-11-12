package net.simpleframework.mvc.template.struct;

import net.simpleframework.common.StringUtils;
import net.simpleframework.mvc.common.element.AbstractElement;
import net.simpleframework.mvc.common.element.AbstractTagElement;
import net.simpleframework.mvc.common.element.SpanElement;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public class WizardButton extends AbstractTagElement<WizardButton> {
	public WizardButton() {
	}

	private EWizardStatus status;

	private boolean active;

	/* 是否显示下拉菜单 */
	private boolean menuIcon;

	public WizardButton(final Object text) {
		setText(text);
	}

	public EWizardStatus getStatus() {
		return status == null ? EWizardStatus.ready : status;
	}

	public WizardButton setStatus(final EWizardStatus status) {
		this.status = status;
		return this;
	}

	public boolean isActive() {
		return active;
	}

	public WizardButton setActive(final boolean active) {
		this.active = active;
		return this;
	}

	public boolean isMenuIcon() {
		return menuIcon;
	}

	public WizardButton setMenuIcon(final boolean menuIcon) {
		this.menuIcon = menuIcon;
		return this;
	}

	@Override
	public String getText() {
		final StringBuilder sb = new StringBuilder();
		sb.append(super.getText());
		if (isActive()) {
			sb.append(new SpanElement().setClassName(getStatus().name()));
		}
		if (isMenuIcon()) {
			sb.append(getDownMenu());
		}
		return sb.toString();
	}

	protected AbstractElement<?> getDownMenu() {
		return new SpanElement().setClassName("right_down_menu");
	}

	@Override
	protected String tag() {
		return "span";
	}

	@Override
	public String toString() {
		addClassName("wizard_btn " + getStatus().name());
		if (StringUtils.hasText(getOnclick())) {
			addClassName("click");
		}
		return super.toString();
	}

	@Override
	public String getOnclick() {
		return isActive() ? null : super.getOnclick();
	}

	public static enum EWizardStatus {

		ready,

		running,

		complete
	}
}
