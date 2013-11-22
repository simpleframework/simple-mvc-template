package net.simpleframework.mvc.template.lets;

import static net.simpleframework.common.I18n.$m;

import java.util.Map;

import net.simpleframework.common.ETimePeriod;
import net.simpleframework.common.StringUtils;
import net.simpleframework.common.TimePeriod;
import net.simpleframework.common.coll.KVMap;
import net.simpleframework.common.web.HttpUtils;
import net.simpleframework.ctx.script.MVEL2Template;
import net.simpleframework.mvc.IMVCConst;
import net.simpleframework.mvc.JavascriptForward;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.common.element.AbstractElement;
import net.simpleframework.mvc.component.ComponentParameter;
import net.simpleframework.mvc.component.ui.calendar.CalendarBean;
import net.simpleframework.mvc.template.struct.FilterButton;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public abstract class AdvSearchPage extends FormTemplatePage {

	@Override
	protected void onForward(final PageParameter pp) {
		super.onForward(pp);
		addHtmlViewVariable(pp, AdvSearchPage.class, "_form");

		addAjaxRequest(pp, "ajaxAdvSearchSave").setHandleMethod("doSearch").setSelector(
				".AdvSearchPage");
	}

	protected String[] getFilterParams() {
		return null;
	}

	public JavascriptForward doSearch(final ComponentParameter cp) throws Exception {
		final JavascriptForward js = new JavascriptForward();
		return js.append("$Actions.loc('").append(toQueryParams(cp, getFilterParams())).append("');");
	}

	public static String encodeRefererUrl(final String url) {
		return "_referer=" + StringUtils.encodeHex(url.getBytes());
	}

	protected String getRefererUrl(final PageParameter pp) {
		return StringUtils.decodeHexString(pp.getParameter(IMVCConst.PARAM_REFERER));
	}

	protected String getParameterValue(final PageParameter pp, final String key) {
		return pp.getParameter(key);
	}

	protected String toQueryParams(final PageParameter pp, final String... params) {
		return HttpUtils.addParameters(getRefererUrl(pp), pp.toQueryParams(params));
	}

	protected String addSearchItem(final PageParameter pp, final String lbl,
			final AbstractElement<?> val) {
		final StringBuilder sb = new StringBuilder();
		sb.append("<table class='it'>");
		sb.append(" <tr>");
		sb.append("  <td class='lbl'>").append(lbl).append("</td>");
		final String txt = getParameterValue(pp, val.getName());
		if (StringUtils.hasText(txt)) {
			val.setText(txt);
		}
		sb.append("  <td>").append(val).append("</td>");
		sb.append(" </tr>");
		sb.append("</table>");
		return sb.toString();
	}

	protected String addSearchDateItem(final PageParameter pp, final String name) {
		return addSearchDateItem(pp, $m("AdvSearchPage.0"), name);
	}

	protected String addSearchDateItem(final PageParameter pp, final String lbl, final String name) {
		addComponentBean(pp, "advSearchCalendar", CalendarBean.class);
		return MVEL2Template.replace(
				new KVMap().add("lbl", lbl).add("name", name).add("ETimePeriod", ETimePeriod.class)
						.add("time", StringUtils.blank(getParameterValue(pp, name))),
				AdvSearchPage.class, "AdvSearchPage_date.html");
	}

	public String toItemsHTML(final PageParameter pp) {
		return "";
	}

	public FilterButton createFilterButton(final PageParameter pp, final String url,
			final String rParam) {
		final String val = pp.getLocaleParameter(rParam);
		if (StringUtils.hasText(val)) {
			return _createFilterButton(pp, val, url, rParam);
		}
		return null;
	}

	public FilterButton createFilterDateButton(final PageParameter pp, final String url,
			final String rParam) {
		final String val = pp.getParameter(rParam);
		TimePeriod timePeriod;
		if (StringUtils.hasText(val) && !(timePeriod = new TimePeriod(val)).isAll()) {
			return _createFilterButton(pp, timePeriod, url, rParam);
		}
		return null;
	}

	private FilterButton _createFilterButton(final PageParameter pp, final Object val,
			final String url, final String rParam) {
		final Map<String, Object> params = pp.toQueryParams(getFilterParams());
		params.remove(rParam);
		return new FilterButton(val).setOndelete("$Actions.loc('"
				+ HttpUtils.addParameters(url, params) + "');");
	}
}
