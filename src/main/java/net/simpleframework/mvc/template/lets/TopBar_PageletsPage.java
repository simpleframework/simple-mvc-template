package net.simpleframework.mvc.template.lets;

import java.io.IOException;
import java.util.Map;

import net.simpleframework.common.StringUtils;
import net.simpleframework.mvc.AbstractMVCPage;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.common.element.BlockElement;
import net.simpleframework.mvc.common.element.ElementList;
import net.simpleframework.mvc.common.element.TabButtons;
import net.simpleframework.mvc.template.IPageFilterbarAware;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public abstract class TopBar_PageletsPage extends Blank_PageletsPage implements IPageFilterbarAware {
	@Override
	protected void onForward(final PageParameter pp) {
		super.onForward(pp);

		addHtmlViewVariable(TopBar_PageletsPage.class, "col1");
		addHtmlViewVariable(getClass(), "cc");
	}

	/**
	 * 定义列表的类目
	 * 
	 * @param pParameter
	 * @return
	 */
	protected TabButtons getCategoryTabs(final PageParameter pp) {
		return null;
	}

	/**
	 * 定义右边搜索区域
	 * 
	 * @param pParameter
	 * @return
	 */
	@Override
	public ElementList getRightElements(final PageParameter pp) {
		return null;
	}

	@Override
	public String toToolbarHTML(final PageParameter pp) {
		final StringBuilder sb = new StringBuilder();
		final ElementList le = getLeftElements(pp);
		final ElementList re = getRightElements(pp);
		if (le != null) {
			sb.append("<div class='le'>").append(le).append("</div>");
		}
		if (re != null) {
			sb.append("<div class='re'>").append(re).append("</div>");
		}
		if (sb.length() > 0) {
			sb.append(BlockElement.CLEAR);
		}
		final TabButtons tabs = getCategoryTabs(pp);
		if (tabs != null) {
			sb.append(tabs.toString(pp));
		}
		return sb.toString();
	}

	@Override
	protected String toHtml(final PageParameter pp,
			final Class<? extends AbstractMVCPage> pageClass, final Map<String, Object> variables,
			final String currentVariable) throws IOException {
		if (TopBar_PageletsPage.class.equals(pageClass)) {
			final StringBuilder sb = new StringBuilder();
			sb.append("<div class='TopBar_PageletsPage'>");
			String html = toToolbarHTML(pp);
			if (StringUtils.hasText(html)) {
				sb.append("<div class='tool_bar'>").append(html).append("</div>");
			}
			html = toFilterBarHTML(pp);
			if (StringUtils.hasText(html)) {
				sb.append("<div class='filter_bar'>").append(html).append("</div>");
			}
			sb.append(variables.get("cc"));
			sb.append("</div>");
			return sb.toString();
		}
		return super.toHtml(pp, pageClass, variables, currentVariable);
	}
}
