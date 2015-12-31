package net.simpleframework.mvc.template.lets;

import static net.simpleframework.common.I18n.$m;

import java.util.LinkedHashMap;
import java.util.Map;

import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.PageRequestResponse;
import net.simpleframework.mvc.common.element.ElementList;
import net.simpleframework.mvc.common.element.LinkButton;
import net.simpleframework.mvc.component.ui.pager.EPagerBarLayout;
import net.simpleframework.mvc.component.ui.pager.ITablePagerHandler;
import net.simpleframework.mvc.component.ui.pager.TablePagerBean;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public abstract class FormTableRow_ListTemplatePage extends FormTableRowTemplatePage {

	@Override
	protected TablePagerBean addTablePagerBean(final PageParameter pp, final String name,
			final Class<? extends ITablePagerHandler> handlerClass) {
		clearTableCache(pp);
		return (TablePagerBean) addComponentBean(pp, name, TablePagerBean.class).setFilter(false)
				.setShowLineNo(true).setPagerBarLayout(EPagerBarLayout.bottom).setNoResultDesc(null)
				.setContainerId("tbl_" + hashId).setHandlerClass(handlerClass);
	}

	protected ElementList getTableButtons(final PageParameter pp) {
		return ElementList.of(new LinkButton($m("Add")));
	}

	@Override
	public boolean isButtonsOnTop(final PageParameter pp) {
		return isShowTable(pp);
	}

	protected boolean isShowTable(final PageParameter pp) {
		return true;
	}

	@Override
	protected String _toFormBottomHTML(final PageParameter pp) {
		if (isShowTable(pp)) {
			final StringBuilder sb = new StringBuilder();
			final ElementList el = getTableButtons(pp);
			if (el != null && el.size() > 0) {
				sb.append("<div class='tbl_bar'>").append(el).append("</div>");
			}
			sb.append("<div class='tbl_c' id='tbl_").append(hashId).append("'></div>");
			return sb.toString();
		}
		return null;
	}

	private static final String INSERTROWS = "@insert_rows", UPDATEROWS = "@update_rows",
			DELETEROWS = "@delete_rows";

	private void clearTableCache(final PageParameter pp) {
		pp.removeSessionAttr(INSERTROWS);
		pp.removeSessionAttr(UPDATEROWS);
		pp.removeSessionAttr(DELETEROWS);
	}

	@SuppressWarnings("unchecked")
	private static Map<String, Map<String, Object>> _getRows(final PageRequestResponse rRequest,
			final String key) {
		Map<String, Map<String, Object>> rows = (Map<String, Map<String, Object>>) rRequest
				.getSessionAttr(key);
		if (rows == null) {
			rRequest.setSessionAttr(key, rows = new LinkedHashMap<String, Map<String, Object>>());
		}
		return rows;
	}

	protected static Map<String, Map<String, Object>> getUpdateRows(
			final PageRequestResponse rRequest) {
		return _getRows(rRequest, UPDATEROWS);
	}

	protected static Map<String, Map<String, Object>> getInsertRows(
			final PageRequestResponse rRequest) {
		return _getRows(rRequest, INSERTROWS);
	}

	protected static Map<String, Map<String, Object>> getDeleteRows(
			final PageRequestResponse rRequest) {
		return _getRows(rRequest, DELETEROWS);
	}
}
