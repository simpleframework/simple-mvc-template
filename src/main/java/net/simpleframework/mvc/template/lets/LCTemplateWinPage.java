package net.simpleframework.mvc.template.lets;

import java.util.Map;

import net.simpleframework.common.StringUtils;
import net.simpleframework.common.coll.KVMap;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.template.AbstractTemplatePage;
import net.simpleframework.mvc.template.IPageToolbarAware;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public abstract class LCTemplateWinPage extends AbstractTemplatePage implements IPageToolbarAware {

	@Override
	protected void onForward(final PageParameter pp) {
		super.onForward(pp);
		final Class<?> pageClass = getClass();

		addHtmlViewVariable(pageClass, "content_left", getClassName(pageClass) + "_Left.html");
		addHtmlViewVariable(pageClass, "content_center", getClassName(pageClass) + "_Center.html");
	}

	@Override
	public Map<String, Object> createVariables(final PageParameter pp) {
		return ((KVMap) super.createVariables(pp)).add("winAct", getWindowAction(pp)).add(
				"toolbar_center", StringUtils.blank(toToolbarHTML(pp)));
	}

	protected abstract String getWindowAction(PageParameter pp);
}
