package net.simpleframework.mvc.template;

import java.io.IOException;
import java.util.Map;

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
			sb.append(js_windowTitle(pp, blockId));
			return sb.toString();
		}
		return super.toHtml(pp, pageClass, variables, currentVariable);
	}
}
