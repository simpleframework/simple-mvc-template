package net.simpleframework.mvc.template.struct;

import java.util.Random;

import net.simpleframework.common.coll.AbstractArrayListEx;
import net.simpleframework.common.web.JavascriptUtils;
import net.simpleframework.mvc.common.element.SpanElement;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class CategoryPopItems extends AbstractArrayListEx<CategoryPopItems, CategoryItem> {

	public static CategoryPopItems of(final CategoryItem... item) {
		return new CategoryPopItems().append(item);
	}

	private int width = 180, subWidth = 640;

	public int getWidth() {
		return width;
	}

	public CategoryPopItems setWidth(final int width) {
		this.width = width;
		return this;
	}

	public int getSubWidth() {
		return subWidth;
	}

	public CategoryPopItems setSubWidth(final int subWidth) {
		this.subWidth = subWidth;
		return this;
	}

	protected String toTitleHTML(final CategoryItem item) {
		return item.toString();
	}

	protected String toChildrenHTML(final CategoryItem item) {
		return toChildrenHTML_level3(item);
	}

	protected String toChildrenHTML_level3(final CategoryItem item) {
		final StringBuilder sb = new StringBuilder();
		int i = 0;
		for (final CategoryItem c : item.getChildren()) {
			c.setIndex(i++);
			if (i > 1) {
				sb.append("<div class='p_sub_sep'></div>");
			}
			sb.append("<div class='p_sub_item clearfix'>");
			sb.append(" <div class='p_il'>").append(c.toTitleElement()).append("</div>");
			sb.append(" <div class='p_ir'>").append(toChildrenHTML_level2(c)).append(" </div>");
			sb.append("</div>");
		}
		return sb.toString();
	}

	protected String toChildrenHTML_level2(final CategoryItem item) {
		final StringBuilder sb = new StringBuilder();
		int j = 0;
		for (final CategoryItem c : item.getChildren()) {
			if ("-".equals(c.getTitle())) {
				sb.append("<br>");
				j = 0;
			} else {
				c.setIndex(j++);
				if (j > 1) {
					sb.append(SpanElement.SEP());
				}
				sb.append(c.toTitleElement().setClassName("a2"));
			}
		}
		return sb.toString();
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		final int width = getWidth();
		final int subWidth = getSubWidth();
		sb.append("<div class='category_pop' style='");
		if (width > 0) {
			sb.append("width: ").append(width).append("px;");
		}
		sb.append("display: none;'>");
		sb.append(" <div class='p_cc'>");
		int i = 0;
		for (final CategoryItem item : this) {
			if (i++ > 0) {
				sb.append("<div class='p_sep'></div>");
			}
			item.setIndex(i);
			sb.append("<div class='p_item'>");
			sb.append(" <span class='p_txt'>").append(toTitleHTML(item));
			sb.append("   <span class='p_bg' style='display: none;'></span>");
			sb.append(" </span>");
			sb.append(" <div class='p_sub' style='");
			if (subWidth > 0) {
				sb.append("width: ").append(subWidth).append("px;");
			}
			sb.append("display: none;'>").append(toChildrenHTML(item)).append("</div>");
			sb.append("</div>");
		}
		sb.append(" </div>");
		sb.append("</div>");
		return sb.toString();
	}

	public String bindElement(final String eleId) {
		return bindElement(eleId, 0, 0);
	}

	public String bindElement(final String eleId, final int left, final int top) {
		final StringBuilder js = new StringBuilder();
		js.append("(function() {");
		js.append("var btn = $('").append(eleId).append("');");
		js.append("var ele = \"").append(JavascriptUtils.escape(toString()))
				.append("\".makeElement();");
		js.append("$UI.doCategoryPopItems(btn, ele, [").append(left).append(", ").append(top)
				.append("]);");
		js.append("})();");
		return js.toString();
	}

	public static CategoryPopItems test() {
		final CategoryPopItems items = CategoryPopItems.of();
		final Random r = new Random();
		for (int i = 0; i < 12; i++) {
			final CategoryItem item = new CategoryItem("Test-" + i).setHref("#");
			for (int j = 0; j < Math.max(r.nextInt(6), 2); j++) {
				final CategoryItem item2 = new CategoryItem("Children-" + i + "-" + j).setHref("#");
				item.getChildren().add(item2);
				for (int k = 0; k < 15; k++) {
					item2.getChildren().add(new CategoryItem("Children-" + j + "-" + k).setHref("#"));
				}
			}
			items.add(item);
		}
		return items;
	}

	private static final long serialVersionUID = -256420042212326956L;
}
