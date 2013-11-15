package net.simpleframework.mvc.template.lets;

import static net.simpleframework.common.I18n.$m;

import java.util.Date;

import net.simpleframework.common.coll.KVMap;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.common.element.SpanElement;
import net.simpleframework.mvc.common.element.TableRows;
import net.simpleframework.mvc.component.ext.attachments.AttachmentBean;
import net.simpleframework.mvc.component.ext.attachments.IAttachmentHandler;
import net.simpleframework.mvc.component.ext.comments.CommentBean;
import net.simpleframework.mvc.component.ext.comments.ICommentHandler;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public abstract class View_PageletsPage extends Blank_PageletsPage {

	@Override
	protected void onForward(final PageParameter pp) {
		super.onForward(pp);

		addHtmlViewVariable(View_PageletsPage.class, "col1");
	}

	@Override
	public KVMap createVariables(final PageParameter pp) {
		final KVMap kv = (KVMap) super.createVariables(pp);
		final Date date = (Date) getDataProperty(pp, OP_DATE);
		kv.add("cd_year", String.format("%tY", date)).add("cd_month", String.format("%tb", date))
				.add("cd_day", String.format("%te", date));

		// topic, content
		kv.add("vp_topic", getDataProperty(pp, OP_TOPIC)).add("vp_topic2", getTopic2(pp))
				.add("vp_content", getDataProperty(pp, OP_CONTENT));

		final StringBuilder sb = new StringBuilder();
		final TableRows tableRows = getFormData(pp);
		if (tableRows != null) {
			sb.append("<div class='fd'>");
			sb.append(tableRows);
			sb.append("</div>");
		}
		kv.add("vp_form", sb.toString());

		sb.setLength(0);
		if (pp.hasComponentType(AttachmentBean.class)) {
			sb.append("<div class='attach'>");
			sb.append("  <div id='attachment_").append(hashId).append("'></div>");
			sb.append("  <span class='attach_img'></span>");
			sb.append("</div>");
		}
		if (pp.hasComponentType(CommentBean.class)) {
			sb.append("<div class='comment'>");
			sb.append("  <div id='comment_").append(hashId).append("'></div>");
			sb.append("</div>");
		}
		kv.add("vp_ext", sb.toString());
		return kv;
	}

	protected CommentBean addCommentBean(final PageParameter pp,
			final Class<? extends ICommentHandler> handleClass) {
		return (CommentBean) addComponentBean(pp, CommentBean.class, handleClass).setContainerId(
				"comment_" + hashId);
	}

	protected AttachmentBean addAttachmentBean(final PageParameter pp,
			final Class<? extends IAttachmentHandler> handleClass) {
		return (AttachmentBean) addComponentBean(pp, AttachmentBean.class, handleClass)
				.setContainerId("attachment_" + hashId);
	}

	protected TableRows getFormData(final PageParameter pp) {
		return null;
	}

	/**
	 * 获取数据bean的指定属性值，提供该页面模板展示
	 * 
	 * @param pParameter
	 * @param key
	 *           参考OP_开头的属性
	 * @return
	 */
	protected abstract Object getDataProperty(PageParameter pp, String key);

	protected String getTopic2(final PageParameter pp) {
		final StringBuilder sb = new StringBuilder();
		sb.append(SpanElement.num(getDataProperty(pp, "views"))).append(NBSP)
				.append($m("AbstractTwoColPage.2"));
		return sb.toString();
	}
}
