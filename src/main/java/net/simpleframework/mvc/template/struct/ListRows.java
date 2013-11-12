package net.simpleframework.mvc.template.struct;

import net.simpleframework.common.StringUtils;
import net.simpleframework.common.coll.AbstractArrayListEx;
import net.simpleframework.common.object.ObjectUtils;
import net.simpleframework.common.web.html.HtmlConst;
import net.simpleframework.mvc.common.element.BlockElement;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public class ListRows extends AbstractArrayListEx<ListRows, ListRow> {
	public static ListRows of(final ListRow... rows) {
		return new ListRows().append(rows);
	}

	private EImageDot dotIcon;

	public EImageDot getDotIcon() {
		return dotIcon == null ? EImageDot.dot1 : dotIcon;
	}

	public ListRows setDotIcon(final EImageDot dotIcon) {
		this.dotIcon = dotIcon;
		return this;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		final String id = ObjectUtils.hashStr(this);
		boolean isShowTip = false;
		sb.append("<div class='pagelet_list_rows' id='").append(id).append("'>");
		for (final ListRow item : this) {
			sb.append("<div class=\"ti ").append(getDotIcon().name()).append("\">");
			final String[] shortDesc = item.getShortDesc();
			if (shortDesc != null && shortDesc.length > 0) {
				if (StringUtils.hasText(shortDesc[0])) {
					sb.append("<span class='sdesc'");
					if (shortDesc.length > 1) {
						sb.append(" title=\"").append(shortDesc[1]).append("\"");
					}
					sb.append(">").append(shortDesc[0]).append("</span>");
				}
			}
			sb.append(item.toString());
			final String tooltip = item.getTooltip();
			if (StringUtils.hasText(tooltip)) {
				sb.append(BlockElement.tip(tooltip).setClassName("tt"));
				isShowTip = true;
			}
			sb.append("</div>");
		}
		sb.append("</div>");
		if (isShowTip) {
			sb.append(HtmlConst.TAG_SCRIPT_START);
			sb.append("$UI.doListRowsTooltip('#").append(id).append(" .ti');");
			sb.append(HtmlConst.TAG_SCRIPT_END);
		}
		return sb.toString();
	}

	private static final long serialVersionUID = -3115982190346893038L;
}
