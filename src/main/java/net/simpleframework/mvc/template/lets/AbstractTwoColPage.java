package net.simpleframework.mvc.template.lets;

import static net.simpleframework.common.I18n.$m;

import java.util.Date;
import java.util.Map;

import net.simpleframework.common.BeanUtils;
import net.simpleframework.common.StringUtils;
import net.simpleframework.common.coll.KVMap;
import net.simpleframework.common.web.html.HtmlUtils;
import net.simpleframework.lib.org.jsoup.nodes.Document;
import net.simpleframework.lib.org.jsoup.nodes.Element;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.common.ImageCache;
import net.simpleframework.mvc.common.element.ETextAlign;
import net.simpleframework.mvc.common.element.PhotoImage;
import net.simpleframework.mvc.common.element.SpanElement;
import net.simpleframework.mvc.component.ComponentParameter;
import net.simpleframework.mvc.component.ui.pager.EPagerBarLayout;
import net.simpleframework.mvc.component.ui.pager.ITablePagerHandler;
import net.simpleframework.mvc.component.ui.pager.TablePagerBean;
import net.simpleframework.mvc.component.ui.pager.TablePagerColumn;
import net.simpleframework.mvc.component.ui.pager.db.AbstractDbTablePagerHandler;
import net.simpleframework.mvc.template.AbstractTemplatePage;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class AbstractTwoColPage extends AbstractTemplatePage {
	protected static final String OP_CONTENT = "content";
	protected static final String OP_TOPIC = "topic";
	protected static final String OP_TOPIC_URL = "topic_url";
	protected static final String OP_DATE = "date";

	protected static final String OP_COMMENTS = "comments";
	protected static final String OP_VIEWS = "views";

	protected static final String OP_DESCRIPTION = "description";

	public static TablePagerColumn COLUMN_TOPIC = new TablePagerColumn("topic",
			$m("AbstractTwoColPage.0")).setTextAlign(ETextAlign.left).setSort(false).setNowrap(false);

	protected TablePagerBean addTablePagerBean(final PageParameter pp, final String name,
			final Class<? extends ITablePagerHandler> handleClass) {
		return addTablePagerBean(pp, name, handleClass, true);
	}

	protected TablePagerBean addTablePagerBean(final PageParameter pp, final String name,
			final Class<? extends ITablePagerHandler> handleClass, final boolean addTopicColumn) {
		final TablePagerBean tablePager = (TablePagerBean) pp
				.addComponentBean(name, TablePagerBean.class).setShowFilterBar(false)
				.setShowHead(false).setShowLineNo(true).setShowCheckbox(false).setExportAction("false")
				.setPagerBarLayout(EPagerBarLayout.bottom).setPageItems(30)
				.setContainerId("list_" + hashId).setHandleClass(handleClass);
		if (addTopicColumn) {
			tablePager.addColumn(COLUMN_TOPIC);
		}
		return tablePager;
	}

	protected static abstract class ListTemplatePagerHandler extends AbstractDbTablePagerHandler {

		@Override
		protected Map<String, Object> getRowData(final ComponentParameter cp, final Object dataObject) {
			return new KVMap().add("topic", toHTML(cp, dataObject));
		}

		protected Object getDataProperty(final ComponentParameter cp, final Object dataObject,
				final String key) {
			return BeanUtils.getProperty(dataObject, key);
		}

		protected String toHTML_topic(final ComponentParameter cp, final Object dataObject) {
			final StringBuilder sb = new StringBuilder();
			sb.append("<a class='f3 nt' target='_blank' href=\"")
					.append(getDataProperty(cp, dataObject, OP_TOPIC_URL)).append("\">")
					.append(getDataProperty(cp, dataObject, OP_TOPIC)).append("</a>");
			return sb.toString();
		}

		protected String toHTML_date(final ComponentParameter cp, final Object dataObject) {
			final StringBuilder sb = new StringBuilder();
			final Date d = (Date) getDataProperty(cp, dataObject, OP_DATE);
			sb.append("<div class='time'>");
			sb.append("<div class='month'>").append(String.format("%tb", d)).append("</div>");
			sb.append("<div class='day'>").append(String.format("%te", d)).append("</div>");
			sb.append("<div class='year'>").append(String.format("%tY", d)).append("</div>");
			sb.append("</div>");
			return sb.toString();
		}

		protected String toHTML_image(final ComponentParameter cp, final Object dataObject) {
			final StringBuilder sb = new StringBuilder();
			final Element img = doc(cp, dataObject).select("img").first();
			sb.append(new PhotoImage(new ImageCache(img == null ? null : img.attr("src")).getPath(cp)));
			return sb.toString();
		}

		protected String toHTML_desc(final ComponentParameter cp, final Object dataObject) {
			return toHTML_desc(cp, dataObject, HtmlUtils.DEFAULT_NEW_LINE, 240);
		}

		protected Document doc(final ComponentParameter cp, final Object dataObject) {
			Document doc = (Document) cp.getRequestAttr("_doc");
			if (doc == null) {
				cp.setRequestAttr(
						"_doc",
						doc = HtmlUtils.createHtmlDocument((String) getDataProperty(cp, dataObject,
								OP_CONTENT)));
			}
			return doc;
		}

		protected String toHTML_desc(final ComponentParameter cp, final Object dataObject,
				final String newLine, final int length) {
			String desc = (String) getDataProperty(cp, dataObject, OP_DESCRIPTION);
			if (StringUtils.hasText(desc)) {
				desc = StringUtils.substring(desc, length, true);
				desc = HtmlUtils.convertHtmlLines(desc);
			} else {
				desc = HtmlUtils.truncateHtml(doc(cp, dataObject), length, newLine, true, true);
			}
			return desc;
		}

		protected String toHTML_shortDesc(final ComponentParameter cp, final Object dataObject) {
			final StringBuilder sb = new StringBuilder();
			sb.append("<div class='nd'>");
			sb.append(SpanElement.num(getDataProperty(cp, dataObject, OP_COMMENTS))).append(NBSP)
					.append($m("AbstractTwoColPage.1"));
			sb.append(SpanElement.SEP);
			sb.append(SpanElement.num(getDataProperty(cp, dataObject, OP_VIEWS))).append(NBSP)
					.append($m("AbstractTwoColPage.2"));
			sb.append("</div>");
			return sb.toString();
		}

		protected String toHTML(final ComponentParameter cp, final Object dataObject) {
			final StringBuilder sb = new StringBuilder();

			sb.append(toHTML_topic(cp, dataObject));
			sb.append(toHTML_date(cp, dataObject));

			sb.append("<div class='nc'>");
			sb.append(toHTML_image(cp, dataObject));
			sb.append(toHTML_desc(cp, dataObject));
			sb.append(toHTML_shortDesc(cp, dataObject));
			sb.append("</div>");

			return sb.toString();
		}
	}
}
