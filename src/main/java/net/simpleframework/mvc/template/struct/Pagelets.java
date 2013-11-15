package net.simpleframework.mvc.template.struct;

import net.simpleframework.common.StringUtils;
import net.simpleframework.common.coll.AbstractArrayListEx;
import net.simpleframework.mvc.common.element.SpanElement;
import net.simpleframework.mvc.common.element.TabButton;
import net.simpleframework.mvc.common.element.TabButtons;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class Pagelets extends AbstractArrayListEx<Pagelets, Pagelet> {

	public static Pagelets of(final Pagelet... lets) {
		return new Pagelets().append(lets);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		for (final Pagelet let : this) {
			sb.append("<div class='").append(StringUtils.text(let.getClassName(), "let")).append("'>");
			sb.append("<div class='lh'>");
			final CategoryItem title = let.getTitle();
			if (title != null) {
				sb.append("<div class='tl'>").append(title.getTitle()).append("</div>");
			}
			final TabButtons tabs = let.getTabs();
			int size;
			if (tabs != null && (size = tabs.size()) > 0) {
				sb.append("<div class='tr'>");
				for (int i = 0; i < size; i++) {
					final TabButton tab = tabs.get(i);
					final SpanElement span = new SpanElement(tab.getText()).setClassName("tab")
							.setOnclick(tab.getOnclick());
					if (i == tabs.getSelectedIndex()) {
						span.addClassName("active");
					}
					sb.append(span);
				}
				sb.append("</div>");
			}
			sb.append("</div>");
			sb.append("<div class='lc'>").append(StringUtils.blank(let.getContent())).append("</div>");
			sb.append("</div>");
		}
		return sb.toString();
	}

	private static final long serialVersionUID = -8582013991587709448L;
}
