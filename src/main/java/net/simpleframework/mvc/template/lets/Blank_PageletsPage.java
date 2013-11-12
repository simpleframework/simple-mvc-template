package net.simpleframework.mvc.template.lets;

import java.util.Map;

import net.simpleframework.common.coll.KVMap;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.component.base.ajaxrequest.AjaxRequestBean;
import net.simpleframework.mvc.component.ui.tooltip.TooltipRegistry;
import net.simpleframework.mvc.template.AbstractTemplatePage;
import net.simpleframework.mvc.template.struct.Pagelets;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public abstract class Blank_PageletsPage extends AbstractTwoColPage {
	@Override
	protected void onForward(final PageParameter pp) {
		super.onForward(pp);
		addHtmlViewVariable(getClass(), "col1");
	}

	@Override
	protected void addImportCSS(final PageParameter pp) {
		super.addImportCSS(pp);

		pp.addImportCSS(AbstractTemplatePage.class, "/pagelets.css");
	}

	@Override
	public Map<String, Object> createVariables(final PageParameter pp) {
		return ((KVMap) super.createVariables(pp)).add("showPagelets", isShowPagelets(pp)).add(
				"pageletsScroll", isPageletsScroll(pp));
	}

	protected AjaxRequestBean addPageletTabAjaxRequest(final PageParameter pp) {
		return addAjaxRequest(pp, "PageletTab_ajax").setHandleMethod("doPageletTab");
	}

	protected Pagelets getPagelets(final PageParameter pp) {
		return null;
	}

	protected boolean isPageletsScroll(final PageParameter pp) {
		return true;
	}

	public String toPageletHTML(final PageParameter pp) {
		final Pagelets pagelets = getPagelets(pp);
		return pagelets != null ? pagelets.toString() : "";
	}

	protected boolean isShowPagelets(final PageParameter pp) {
		return true;
	}

	@Override
	public String[] getDependentComponents(final PageParameter pp) {
		return new String[] { TooltipRegistry.TOOLTIP };
	}
}
