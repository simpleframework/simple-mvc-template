package net.simpleframework.mvc.template.t1;

import net.simpleframework.mvc.PageParameter;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public abstract class T1FixedTemplatePage extends T1TemplatePage {

	@Override
	protected void onForward(final PageParameter pp) {
		super.onForward(pp);

		addHtmlViewVariable(T1FixedTemplatePage.class, "body");
		addHtmlViewVariable(getClass(), "content");
	}

	@Override
	protected void addImportCSS(final PageParameter pp) {
		super.addImportCSS(pp);

		pp.addImportCSS(T1TemplatePage.class, "/t1_fixed.css");
	}
}
