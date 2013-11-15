package net.simpleframework.mvc.template;

import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.common.element.ElementList;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public interface IPageToolbarAware {

	/**
	 * 定义工具条的输出内容
	 * 
	 * @param pp
	 * @return
	 */
	String toToolbarHTML(PageParameter pp);

	/**
	 * 定义工具条左边的输出html元素
	 * 
	 * @param pp
	 * @return
	 */
	ElementList getLeftElements(PageParameter pp);

	/**
	 * 定义工具条右边的输出html元素
	 * 
	 * @param pp
	 * @return
	 */
	ElementList getRightElements(PageParameter pp);
}
