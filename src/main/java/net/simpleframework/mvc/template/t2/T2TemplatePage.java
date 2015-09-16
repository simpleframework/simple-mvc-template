package net.simpleframework.mvc.template.t2;

import java.util.Collection;

import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.common.element.LinkElement;
import net.simpleframework.mvc.common.element.Meta;
import net.simpleframework.mvc.common.element.SpanElement;
import net.simpleframework.mvc.component.ui.menu.MenuBean;
import net.simpleframework.mvc.template.RootTemplatePage;
import net.simpleframework.mvc.template.struct.NavigationButtons;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public abstract class T2TemplatePage extends RootTemplatePage {

	@Override
	protected void onForward(final PageParameter pp) throws Exception {
		super.onForward(pp);

		pp.addImportCSS(T2TemplatePage.class, "/t2.css");

		addHtmlViewVariable(pp, getClass(), "content");

		addComponentBean(pp, MenuBean.class, MainMenuHandler.class).setContainerId("site_menu_bar");

		addHeaderFooterNamedTemplates(pp);
	}

	@Override
	public ITemplateHandlerT2 getTemplate(final PageParameter pp) {
		return AbstractTemplateHandlerT2.get();
	}

	protected LinkElement getNavigationHome(final PageParameter pp) {
		final ITemplateHandlerT2 t2 = getTemplate(pp);
		return t2 != null ? t2.getNavigationHome(pp) : null;
	}

	@Override
	public NavigationButtons getNavigationBar(final PageParameter pp) {
		return NavigationButtons.of(getNavigationHome(pp)).setSeparator(SpanElement.NAV());
	}

	public String toInfoHTML(final PageParameter pp) {
		final StringBuilder sb = new StringBuilder();
		final NavigationButtons nav = getNavigationBar(pp);
		if (nav != null) {
			sb.append(nav);
		}
		return sb.toString();
	}

	@Override
	public void onHttpRequestMeta(final PageParameter pp, final Collection<Meta> coll) {
		super.onHttpRequestMeta(pp, coll);
		coll.add(Meta.ROBOTS_ALL);
	}
}
