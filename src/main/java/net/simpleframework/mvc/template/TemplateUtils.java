package net.simpleframework.mvc.template;

import net.simpleframework.common.ID;
import net.simpleframework.common.StringUtils;
import net.simpleframework.ctx.permission.PermissionUser;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.common.element.PhotoImage;
import net.simpleframework.mvc.common.element.SpanElement;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public abstract class TemplateUtils {

	public static String toIconUser(final PageParameter pp, final ID user) {
		return toIconUser(pp, user, null);
	}

	public static String toIconUser(final PageParameter pp, final ID userId, String userText) {
		final StringBuilder sb = new StringBuilder();
		sb.append(PhotoImage.icon16(pp.getPhotoUrl(userId)).addStyle("margin-right: 5px;"));
		if (!StringUtils.hasText(userText)) {
			final PermissionUser _user = pp.getUser(userId);
			userText = _user.getId() != null ? _user.toString() : "?";
		}
		sb.append(new SpanElement(userText).setClassName("icon_txt"));
		return sb.toString();
	}
}
