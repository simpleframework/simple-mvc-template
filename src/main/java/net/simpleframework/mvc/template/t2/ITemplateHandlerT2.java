package net.simpleframework.mvc.template.t2;

import net.simpleframework.mvc.AbstractMVCPage;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.common.element.LinkElement;
import net.simpleframework.mvc.template.ITemplateHandler;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public interface ITemplateHandlerT2 extends ITemplateHandler {

	LinkElement getNavigationHome(PageParameter pp, AbstractMVCPage templatePage);
}
