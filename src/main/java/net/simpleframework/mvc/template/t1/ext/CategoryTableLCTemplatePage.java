package net.simpleframework.mvc.template.t1.ext;

import static net.simpleframework.common.I18n.$m;

import java.io.IOException;
import java.util.Map;

import net.simpleframework.common.StringUtils;
import net.simpleframework.common.web.html.HtmlConst;
import net.simpleframework.mvc.AbstractMVCPage;
import net.simpleframework.mvc.JavascriptForward;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.common.element.Icon;
import net.simpleframework.mvc.common.element.LinkButton;
import net.simpleframework.mvc.component.ComponentParameter;
import net.simpleframework.mvc.component.ui.pager.EPagerBarLayout;
import net.simpleframework.mvc.component.ui.pager.ITablePagerHandler;
import net.simpleframework.mvc.component.ui.pager.TablePagerBean;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public abstract class CategoryTableLCTemplatePage extends CategoryLCTemplatePage {

	public static final String COMPONENT_TABLE = "TP_ComponentTable";

	@Override
	protected void onForward(final PageParameter pp) {
		super.onForward(pp);
		addHtmlViewVariable(pp, CategoryTableLCTemplatePage.class, "content_center");
	}

	protected TablePagerBean addTablePagerBean(final PageParameter pp,
			final Class<? extends ITablePagerHandler> handlerClass) {
		return (TablePagerBean) addComponentBean(pp, COMPONENT_TABLE, TablePagerBean.class)
				.setShowLineNo(true).setPagerBarLayout(EPagerBarLayout.top)
				.setContainerId("table_" + hashId).setHandlerClass(handlerClass);
	}

	public static JavascriptForward createTableRefresh() {
		return createTableRefresh(null);
	}

	public static JavascriptForward createTableRefresh(final String params) {
		final JavascriptForward js = new JavascriptForward();
		js.append("$Actions['").append(COMPONENT_TABLE).append("'](");
		if (StringUtils.hasText(params)) {
			js.append("'").append(params).append("'");
		}
		js.append(");");
		return js;
	}

	@Override
	protected String toHtml(final PageParameter pp,
			final Class<? extends AbstractMVCPage> pageClass, final Map<String, Object> variables,
			final String currentVariable) throws IOException {
		if ("content_center".equals(currentVariable)) {
			final StringBuilder sb = new StringBuilder();
			sb.append("<div class='CategoryTableLCTemplatePage' id='table_").append(hashId)
					.append("'>");
			sb.append("</div>");
			sb.append(HtmlConst.TAG_SCRIPT_START);
			sb.append("$ready(function() {");
			sb.append(" var tbl = $Actions['").append(COMPONENT_TABLE).append("'];");
			sb.append(" tbl.jsLoadedCallback = function() {");
			final TablePagerBean tablePager = (TablePagerBean) pp
					.getComponentBeanByName(COMPONENT_TABLE);
			if (tablePager != null) {
				final String jsLoadedCallback = (String) ComponentParameter.get(pp, tablePager)
						.getBeanProperty("jsLoadedCallback");
				if (StringUtils.hasText(jsLoadedCallback)) {
					sb.append(jsLoadedCallback);
				}
			}
			sb.append("  var bar = tbl.json['bar'];");
			sb.append("  var tb;");
			sb.append("  if (bar && (tb = $('table_").append(hashId)
					.append("').previous('.tb_container'))) {");
			sb.append("    tb.replace(bar);");
			sb.append("  }");
			sb.append("  $win.closeAll(true);");
			sb.append(" };");
			sb.append("});");
			sb.append(HtmlConst.TAG_SCRIPT_END);
			return sb.toString();
		}
		return super.toHtml(pp, pageClass, variables, currentVariable);
	}

	protected LinkButton delete_btn(final String act) {
		return act_btn(act, $m("Delete")).setIconClass(Icon.trash);
	}

	protected LinkButton delete_btn(final String act, final String idKey, final String params) {
		return act_btn(act, $m("Delete"), idKey, params).setIconClass(Icon.trash);
	}

	protected LinkButton act_btn(final String act, final String title) {
		return act_btn(act, title, null, null);
	}

	protected LinkButton act_btn(final String act, final String title, final String idKey,
			final String params) {
		final StringBuilder click = new StringBuilder();
		click.append("$Actions['").append(COMPONENT_TABLE).append("'].doAct('").append(act)
				.append("', ");
		if (StringUtils.hasText(idKey)) {
			click.append("'").append(idKey).append("'");
		} else {
			click.append("undefined");
		}
		if (StringUtils.hasText(params)) {
			click.append(", '").append(params).append("'");
		}
		click.append(");");
		return new LinkButton(title).setOnclick(click.toString());
	}
}
