package net.simpleframework.mvc.template.struct;

import net.simpleframework.mvc.common.element.SpanElement;
import net.simpleframework.mvc.common.element.TabButton;
import net.simpleframework.mvc.common.element.TabButtons;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class Pagelet {
	private CategoryItem title;

	private String content;

	private String className;

	private TabButtons tabs;

	public Pagelet(final CategoryItem title, final String content) {
		this.title = title;
		this.content = content;
	}

	public Pagelet(final CategoryItem title, final ListRows pageletItems) {
		this.title = title;
		this.content = pageletItems == null ? "" : pageletItems.toString();
	}

	public CategoryItem getTitle() {
		return title;
	}

	public Pagelet setTitle(final CategoryItem title) {
		this.title = title;
		return this;
	}

	public String getContent() {
		return content;
	}

	public Pagelet setContent(final String content) {
		this.content = content;
		return this;
	}

	public String getClassName() {
		return className;
	}

	public Pagelet setClassName(final String className) {
		this.className = className;
		return this;
	}

	public TabButtons getTabs() {
		return tabs;
	}

	public Pagelet setTabs(final TabButtons tabs) {
		this.tabs = tabs;
		return this;
	}

	public String toTabsHTML(final String className) {
		final StringBuilder sb = new StringBuilder();
		final TabButtons tabs = getTabs();
		int size;
		if (tabs != null && (size = tabs.size()) > 0) {
			sb.append("<div class='").append(className).append("'>");
			for (int i = 0; i < size; i++) {
				final TabButton tab = tabs.get(i);
				final SpanElement span = new SpanElement(tab.getText()).setClassName("tab").setOnclick(
						tab.getOnclick());
				if (i == tabs.getSelectedIndex()) {
					span.addClassName("active");
				}
				sb.append(span);
			}
			sb.append("</div>");
		}
		return sb.toString();
	}
}
