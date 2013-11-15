package net.simpleframework.mvc.template.t1.ext;

import java.util.Map;

import net.simpleframework.common.StringUtils;
import net.simpleframework.common.coll.KVMap;
import net.simpleframework.common.web.html.HtmlUtils;
import net.simpleframework.mvc.AbstractMVCPage;
import net.simpleframework.mvc.component.ComponentParameter;
import net.simpleframework.mvc.component.ui.pager.db.GroupDbTablePagerHandler;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public abstract class LCTemplateTablePagerHandler extends GroupDbTablePagerHandler {

	@Override
	public Map<String, Object> toJSON(final ComponentParameter cp) {
		final KVMap json = (KVMap) super.toJSON(cp);
		AbstractMVCPage p;
		if (cp.isAjaxRequest()
				&& (p = AbstractMVCPage.get(cp)) instanceof CategoryTableLCTemplatePage) {
			final String bar = (String) ((CategoryTableLCTemplatePage) p).getVariables(cp).get(
					"toolbar_center");
			if (StringUtils.hasText(bar)) {
				json.add("bar", HtmlUtils.wrapContextPath(cp.request, bar));
			}
		}
		return json;
	}
}
