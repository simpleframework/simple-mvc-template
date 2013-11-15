package net.simpleframework.mvc.template.struct;

import net.simpleframework.common.coll.AbstractArrayListEx;
import net.simpleframework.mvc.common.element.SpanElement;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class FilterButtons extends AbstractArrayListEx<FilterButtons, FilterButton> {

	public static FilterButtons of(final FilterButton... item) {
		return new FilterButtons().append(item);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		int i = 0;
		for (final FilterButton item : this) {
			if (i++ > 0) {
				sb.append(new SpanElement().setStyle("display: inline-block; width: 8px;"));
			}
			sb.append(item);
		}
		return sb.toString();
	}

	private static final long serialVersionUID = 5885583406084231852L;
}
