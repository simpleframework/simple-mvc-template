package net.simpleframework.mvc.template.struct;

import net.simpleframework.mvc.common.element.AbstractLinkElement;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class ListRow extends AbstractLinkElement<ListRow> {
	{
		setTarget("_blank");
	}

	public ListRow() {
	}

	public ListRow(final String text) {
		setText(text);
	}

	private String[] shortDesc;

	private String tooltip;

	public String[] getShortDesc() {
		return shortDesc;
	}

	public ListRow setShortDesc(final String[] shortDesc) {
		this.shortDesc = shortDesc;
		return this;
	}

	public String getTooltip() {
		return tooltip;
	}

	public ListRow setTooltip(final String tooltip) {
		this.tooltip = tooltip;
		return this;
	}
}
