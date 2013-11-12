package net.simpleframework.mvc.template.struct;

import net.simpleframework.mvc.common.element.TabButtons;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
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
}
