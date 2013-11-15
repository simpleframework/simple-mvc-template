package net.simpleframework.mvc.template.struct;

import net.simpleframework.common.StringUtils;
import net.simpleframework.common.coll.AbstractArrayListEx;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
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
			sb.append(let.toTabsHTML("tr"));
			sb.append("</div>");
			sb.append("<div class='lc'>").append(StringUtils.blank(let.getContent())).append("</div>");
			sb.append("</div>");
		}
		return sb.toString();
	}

	private static final long serialVersionUID = -8582013991587709448L;
}
