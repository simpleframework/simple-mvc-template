package net.simpleframework.mvc.template.t1.ext;

import java.util.Map;

import net.simpleframework.ado.bean.IIdBeanAware;
import net.simpleframework.common.StringUtils;
import net.simpleframework.common.coll.KVMap;
import net.simpleframework.common.web.html.HtmlUtils;
import net.simpleframework.mvc.AbstractMVCPage;
import net.simpleframework.mvc.component.ComponentParameter;
import net.simpleframework.mvc.component.ext.category.ctx.CategoryBeanAwareHandler;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public abstract class LCTemplateCategoryHandler<T extends IIdBeanAware> extends
		CategoryBeanAwareHandler<T> {

	@Override
	public Map<String, Object> toJSON(final ComponentParameter cp) {
		final KVMap json = (KVMap) super.toJSON(cp);
		AbstractMVCPage p;
		if (cp.isAjaxRequest() && (p = AbstractMVCPage.get(cp)) instanceof CategoryLCTemplatePage) {
			final String bar = (String) ((CategoryLCTemplatePage) p).getVariables(cp).get(
					"toolbar_left");
			if (StringUtils.hasText(bar)) {
				json.add("bar", HtmlUtils.wrapContextPath(cp.request, bar));
			}
		}
		return json;
	}
}
