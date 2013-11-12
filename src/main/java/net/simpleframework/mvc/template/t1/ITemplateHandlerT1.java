package net.simpleframework.mvc.template.t1;

import net.simpleframework.mvc.ITemplateHandler;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.common.element.LinkElement;
import net.simpleframework.mvc.component.ComponentParameter;
import net.simpleframework.mvc.component.ui.menu.MenuItem;
import net.simpleframework.mvc.component.ui.menu.MenuItems;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public interface ITemplateHandlerT1 extends ITemplateHandler {

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
