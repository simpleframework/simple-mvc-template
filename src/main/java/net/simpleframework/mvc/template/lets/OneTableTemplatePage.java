package net.simpleframework.mvc.template.lets;

import java.io.IOException;
import java.util.Map;

import net.simpleframework.mvc.AbstractMVCPage;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.component.ui.pager.EPagerBarLayout;
import net.simpleframework.mvc.component.ui.pager.ITablePagerHandler;
import net.simpleframework.mvc.component.ui.pager.TablePagerBean;
import net.simpleframework.mvc.template.BlockTemplatePage;
import net.simpleframework.mvc.template.IPageToolbarAware;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public abstract class OneTableTemplatePage extends BlockTemplatePage implements IPageToolbarAware {

	@Override
	protected void onForward(final PageParameter pp) throws Exception {
		super.onForward(pp);
		addHtmlViewVariable(pp, OneTableTemplatePage.class, "_block");
	}

	@Override
	protected TablePagerBean addTablePagerBean(final PageParameter pp, final String name,
			final Class<? extends ITablePagerHandler> tHdl) {
		return (TablePagerBean) super.addTablePagerBean(pp, name, tHdl).setShowCheckbox(true)
				.setPagerBarLayout(EPagerBarLayout.bottom).setContainerId("table_" + hashId)
				.setHandlerClass(tHdl);
	}

	@Override
	protected String toHtml(final PageParameter pp, final Class<? extends AbstractMVCPage> pageClass,
			final Map<String, Object> variables, final String currentVariable) throws IOException {
		if (OneTableTemplatePage.class.equals(pageClass)) {
			final StringBuilder sb = new StringBuilder();
			sb.append("<div class='OneTableTemplatePage'>");
			sb.append(toToolbarHTML(pp));
			sb.append(" <div id='table_").append(hashId).append("'></div>");
			sb.append("</div>");
			return sb.toString();
		}
		return super.toHtml(pp, pageClass, variables, currentVariable);
	}
}
