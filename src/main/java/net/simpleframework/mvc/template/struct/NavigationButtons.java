package net.simpleframework.mvc.template.struct;

import net.simpleframework.common.StringUtils;
import net.simpleframework.common.coll.AbstractArrayListEx;
import net.simpleframework.mvc.common.element.AbstractElement;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class NavigationButtons extends AbstractArrayListEx<NavigationButtons, AbstractElement<?>> {

	public static NavigationButtons of(final AbstractElement<?>... btns) {
		return new NavigationButtons().append(btns);
	}

	private String separatorHtml;

	public String getSeparatorHtml() {
		return StringUtils.text(separatorHtml, "<span class='nav_img'></span>");
	}

	public NavigationButtons setSeparatorHtml(final String separatorHtml) {
		this.separatorHtml = separatorHtml;
		return this;
	}

	public NavigationButtons setSeparator(final AbstractElement<?> element) {
		return setSeparatorHtml(element.toString());
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		int i = 0;
		for (final AbstractElement<?> btn : this) {
			if (i++ > 0) {
				sb.append(getSeparatorHtml());
			}
			sb.append(btn.setClassName("nav_item"));
		}
		return sb.toString();
	}

	private static final long serialVersionUID = 5679380307264466992L;
}
