package net.simpleframework.mvc.template;

import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.template.struct.FilterButtons;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public interface IPageFilterbarAware {

	/**
	 * 
	 * @param pp
	 * @return
	 */
	String toFilterBarHTML(PageParameter pp);

	/**
	 * 
	 * @param pp
	 * @return
	 */
	FilterButtons getFilterButtons(PageParameter pp);
}
