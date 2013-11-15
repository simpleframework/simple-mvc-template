package net.simpleframework.mvc.template.struct;

import net.simpleframework.common.coll.AbstractArrayListEx;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class CategoryItems extends AbstractArrayListEx<CategoryItems, CategoryItem> {

	public static CategoryItems of(final CategoryItem... item) {
		return new CategoryItems().append(item);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		int i = 0;
		for (final CategoryItem item : this) {
			item.setIndex(i++);
			sb.append(item.toItemElement("c_item"));
			for (final CategoryItem child : item.getChildren()) {
				sb.append(child.toItemElement("c_child_item"));
			}
		}
		return sb.toString();
	}

	private static final long serialVersionUID = 1334102930442200267L;
}
