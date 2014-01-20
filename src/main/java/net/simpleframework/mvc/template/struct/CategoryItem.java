package net.simpleframework.mvc.template.struct;

import java.util.ArrayList;
import java.util.List;

import net.simpleframework.common.StringUtils;
import net.simpleframework.common.object.DescriptionObject;
import net.simpleframework.mvc.common.element.AbstractElement;
import net.simpleframework.mvc.common.element.BlockElement;
import net.simpleframework.mvc.common.element.LinkElement;
import net.simpleframework.mvc.common.element.SpanElement;
import net.simpleframework.mvc.common.element.SupElement;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class CategoryItem extends DescriptionObject<CategoryItem> {

	private String title;

	private String iconClass;

	private SupElement num;

	/* 链接 */
	private String href;

	private boolean selected;

	// 仅支持2级
	private List<CategoryItem> children;

	private int index;

	public CategoryItem(final String title, final String iconClass) {
		this.title = title;
		this.iconClass = iconClass;
	}

	public CategoryItem(final String title) {
		this(title, null);
	}

	public String getTitle() {
		return title;
	}

	public CategoryItem setTitle(final String title) {
		this.title = title;
		return this;
	}

	public String getHref() {
		return href;
	}

	public CategoryItem setHref(final String href) {
		this.href = href;
		return this;
	}

	public String getIconClass() {
		return iconClass;
	}

	public CategoryItem setIconClass(final String iconClass) {
		this.iconClass = iconClass;
		return this;
	}

	public SupElement getNum() {
		return num;
	}

	public CategoryItem setNum(final SupElement num) {
		this.num = num;
		return this;
	}

	public List<CategoryItem> getChildren() {
		if (children == null) {
			children = new ArrayList<CategoryItem>();
		}
		return children;
	}

	public CategoryItem setChildren(final List<CategoryItem> children) {
		this.children = children;
		return this;
	}

	public boolean isSelected() {
		return selected;
	}

	public CategoryItem setSelected(final boolean selected) {
		this.selected = selected;
		return this;
	}

	public int getIndex() {
		return index;
	}

	public CategoryItem setIndex(final int index) {
		this.index = index;
		return this;
	}

	public AbstractElement<?> toTitleElement() {
		AbstractElement<?> titleEle;
		final String href = getHref();
		if (StringUtils.hasText(href)) {
			titleEle = new LinkElement(getTitle()).setHref(href);
		} else {
			titleEle = new SpanElement(getTitle());
		}
		return titleEle;
	}

	public BlockElement toItemElement(final String itemClass) {
		final BlockElement ele = new BlockElement().setClassName(itemClass);
		final String href = getHref();
		if (StringUtils.hasText(href)) {
			ele.setOnclick("$Actions.loc('" + href + "');");
		}
		if (isSelected()) {
			ele.addClassName("selected");
		}
		final AbstractElement<?> titleEle = toTitleElement();
		final String iconClass = getIconClass();
		final SpanElement span = new SpanElement().setClassName("icon");
		if (StringUtils.hasText(iconClass)) {
			span.addClassName(iconClass);
		}
		ele.addElements(span.addElements(titleEle));
		final SupElement num = getNum();
		if (num != null) {
			ele.addElements(num.setStyle("margin-left: 4px;"));
		}
		return ele;
	}

	@Override
	public String toString() {
		return toItemElement(null).toString();
	}
}
