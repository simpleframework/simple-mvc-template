package net.simpleframework.mvc.template.struct;

import static net.simpleframework.common.I18n.$m;
import net.simpleframework.common.StringUtils;
import net.simpleframework.mvc.common.element.AbstractTagElement;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class FilterButton extends AbstractTagElement<FilterButton> {
	private String ondelete;

	private String label;

	public FilterButton() {
	}

	public FilterButton(final Object text) {
		super(text);
	}

	public String getOndelete() {
		return ondelete;
	}

	public FilterButton setOndelete(final String ondelete) {
		this.ondelete = ondelete;
		return this;
	}

	public String getLabel() {
		return label;
	}

	public FilterButton setLabel(final String label) {
		this.label = label;
		return this;
	}

	@Override
	public String getText() {
		final StringBuilder sb = new StringBuilder();
		final String lbl = getLabel();
		if (StringUtils.hasText(lbl)) {
			sb.append("<span class='lbl'>").append(lbl).append("</span>");
			sb.append("<span class='lbl2'>").append(lbl).append("</span>");
		}
		sb.append(super.getText());
		sb.append("<span class='del' title='").append($m("FilterButton.0")).append("'");
		final String ondelete = getOndelete();
		if (StringUtils.hasText(ondelete)) {
			sb.append(" onclick=\"").append(ondelete).append("\"");
		}
		sb.append("></span>");
		return sb.toString();
	}

	@Override
	protected String tag() {
		return "span";
	}

	@Override
	public String toString() {
		addClassName("filter_item");
		if (!StringUtils.hasText(getLabel())) {
			addClassName("bg");
		}
		return super.toString();
	}
}
