package net.simpleframework.mvc.template;

import net.simpleframework.mvc.AbstractMVCPage;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.component.ComponentParameter;
import net.simpleframework.mvc.component.ui.menu.MenuItem;
import net.simpleframework.mvc.component.ui.menu.MenuItems;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public interface ITemplateHandler {

	/**
	 * @param pp
	 * @param templatePage
	 * @throws Exception
	 */
	void addPageResource(PageParameter pp, AbstractMVCPage templatePage) throws Exception;

	/**
	 * 获取模板头部页面
	 * 
	 * @param templatePage
	 * @return
	 */
	Class<? extends AbstractMVCPage> getHeaderPage(AbstractMVCPage templatePage);

	/**
	 * 获取模板尾部页面
	 * 
	 * @param templatePage
	 * @return
	 */
	Class<? extends AbstractMVCPage> getFooterPage(AbstractMVCPage templatePage);

	String getFavicon(PageParameter pp, AbstractMVCPage templatePage);

	/**
	 * 是否显示菜单栏
	 * 
	 * @param pp
	 * @param templatePage
	 * @return
	 */
	boolean isShowMenubar(PageParameter pp, AbstractMVCPage templatePage);

	boolean isShowFooter(PageParameter pp, AbstractMVCPage templatePage);

	MenuItems getMainMenuItems(final ComponentParameter cp, final MenuItem menuItem,
			AbstractMVCPage templatePage);
}
