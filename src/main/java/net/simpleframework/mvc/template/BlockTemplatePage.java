package net.simpleframework.mvc.template;

import java.io.IOException;
import java.util.Map;

import net.simpleframework.common.StringUtils;
import net.simpleframework.common.web.JavascriptUtils;
import net.simpleframework.common.web.html.HtmlConst;
import net.simpleframework.mvc.AbstractMVCPage;
import net.simpleframework.mvc.PageParameter;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public abstract class BlockTemplatePage extends AbstractTemplatePage {

	@Override
	protected void onForward(final PageParameter pp) throws Exception {
		super.onForward(pp);

		addHtmlViewVariable(pp, getClass(), "_block");
	}

	protected String getBlockId() {
		return "block_" + hashId;
	}

	@Override
	protected String toHtml(final PageParameter pp, final Class<? extends AbstractMVCPage> pageClass,
			final Map<String, Object> variables, final String currentVariable) throws IOException {
		if (BlockTemplatePage.class.equals(pageClass)) {
			final StringBuilder sb = new StringBuilder();
			final String blockId = getBlockId();
			sb.append("<div id='").append(blockId).append("'>");
			sb.append(variables.get("_block"));
			sb.append("</div>");
			final String title = getTitle(pp);
			if (StringUtils.hasText(title)) {
				sb.append(HtmlConst.TAG_SCRIPT_START);
				sb.append("(function(o) {");
				sb.append(" if (w = $(o).up('.ui-window'))");
				sb.append("	 w.window.setHeader(\"").append(JavascriptUtils.escape(title).trim())
						.append("\");");
				sb.append("})('").append(blockId).append("');");
				sb.append(HtmlConst.TAG_SCRIPT_END);
			}
			return sb.toString();
		}
		return super.toHtml(pp, pageClass, variables, currentVariable);
	}
}
