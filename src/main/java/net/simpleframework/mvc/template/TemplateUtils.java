package net.simpleframework.mvc.template;

import net.simpleframework.common.ID;
import net.simpleframework.common.StringUtils;
import net.simpleframework.ctx.permission.PermissionUser;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.common.element.PhotoImage;

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

	public static String toIconUser(final PageParameter pp, final ID userId, final String userText) {
		final StringBuilder sb = new StringBuilder();
		sb.append(PhotoImage.icon16(pp.getPhotoUrl(userId)).setStyle("margin-right: 5px;"));
		if (StringUtils.hasText(userText)) {
			sb.append(userText);
		} else {
			final PermissionUser _user = pp.getUser(userId);
			sb.append(_user.getId() != null ? _user : "?");
		}
		return sb.toString();
	}
}
