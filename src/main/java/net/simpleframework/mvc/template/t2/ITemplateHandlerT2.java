package net.simpleframework.mvc.template.t2;

import net.simpleframework.mvc.ITemplateHandler;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.common.element.LinkElement;
import net.simpleframework.mvc.component.ComponentParameter;
import net.simpleframework.mvc.component.ui.menu.MenuItem;
import net.simpleframework.mvc.component.ui.menu.MenuItems;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public interface ITemplateHandlerT2 extends ITemplateHandler {

	/**
	 * 获取模板的主菜单数据
	 * 
	 * @param cParameter
	 * @param menuItem
	 * @return
	 */
	MenuItems getMainMenuItems(ComponentParameter cp, MenuItem menuItem);

	LinkElement getNavigationHome(PageParameter pp);
}
