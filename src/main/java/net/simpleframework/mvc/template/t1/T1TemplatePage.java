package net.simpleframework.mvc.template.t1;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import net.simpleframework.common.coll.KVMap;
import net.simpleframework.ctx.permission.IPermissionConst;
import net.simpleframework.mvc.AbstractMVCPage;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.common.element.LinkElement;
import net.simpleframework.mvc.common.element.Meta;
import net.simpleframework.mvc.common.element.TabButtons;
import net.simpleframework.mvc.component.ui.menu.MenuBean;
import net.simpleframework.mvc.template.RootTemplatePage;
import net.simpleframework.mvc.template.struct.NavigationButtons;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public abstract class T1TemplatePage extends RootTemplatePage {

	@Override
	protected void onForward(final PageParameter pp) {
		super.onForward(pp);

		addComponentBean(pp, MenuBean.class, MainMenuHandler.class)
				.setContainerId("resized_menu_bar");
	}

	@Override
	public String getRole(final PageParameter pp) {
		return IPermissionConst.ROLE_ALL_ACCOUNT;
	}

	@Override
	public Map<String, Object> createVariables(final PageParameter pp) {
		return ((KVMap) super.createVariables(pp)).add("showMenubar", isShowMenubar(pp));
	}

	protected LinkElement getNavigationHome(final PageParameter pp) {
		final ITemplateHandlerT1 t1 = (ITemplateHandlerT1) ctx.getTemplate(pp);
		return t1 != null ? t1.getNavigationHome(pp) : null;
	}

	@Override
	public NavigationButtons getNavigationBar(final PageParameter pp) {
		return NavigationButtons.of(getNavigationHome(pp));
	}

	/**
	 * 是否显示菜单栏
	 * 
	 * @param pp
	 * @return
	 */
	protected boolean isShowMenubar(final PageParameter pp) {
		final ITemplateHandlerT1 t1 = (ITemplateHandlerT1) ctx.getTemplate(pp);
		return t1 != null ? t1.isShowMenubar(pp) : true;
	}

	public TabButtons getTabButtons(final PageParameter pp) {
		return null;
	}

	@Override
	protected String toHtml(final PageParameter pp,
			final Class<? extends AbstractMVCPage> pageClass, final Map<String, Object> variables,
			final String currentVariable) throws IOException {
		if (T1TemplatePage.class.equals(pageClass)) {
			final StringBuilder sb = new StringBuilder();
			sb.append("<html>");
			sb.append("<head></head>");
			sb.append("<body>").append(variables.get("body")).append("</body>");
			sb.append("</html>");
			return sb.toString();
		}
		return super.toHtml(pp, pageClass, variables, currentVariable);
	}

	@Override
	public Collection<Meta> meta(final PageParameter pp) {
		final Collection<Meta> coll = super.meta(pp);
		coll.add(Meta.ROBOTS_NONE);
		return coll;
	}
}
