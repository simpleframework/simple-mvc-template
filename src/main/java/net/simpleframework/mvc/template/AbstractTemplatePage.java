package net.simpleframework.mvc.template;

import static net.simpleframework.common.I18n.$m;

import java.util.ArrayList;
import java.util.Map;

import net.simpleframework.common.ClassUtils;
import net.simpleframework.common.ID;
import net.simpleframework.common.StringUtils;
import net.simpleframework.common.coll.KVMap;
import net.simpleframework.common.object.ObjectUtils;
import net.simpleframework.ctx.permission.PermissionUser;
import net.simpleframework.ctx.service.ado.db.IDbBeanService;
import net.simpleframework.mvc.AbstractBasePage;
import net.simpleframework.mvc.IForward;
import net.simpleframework.mvc.JavascriptForward;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.PageParameter.IVal;
import net.simpleframework.mvc.TextForward;
import net.simpleframework.mvc.UrlForward;
import net.simpleframework.mvc.common.element.BlockElement;
import net.simpleframework.mvc.common.element.ElementList;
import net.simpleframework.mvc.common.element.InputElement;
import net.simpleframework.mvc.common.element.Option;
import net.simpleframework.mvc.common.element.SpanElement;
import net.simpleframework.mvc.common.element.TabButtons;
import net.simpleframework.mvc.component.ComponentParameter;
import net.simpleframework.mvc.component.base.ajaxrequest.AjaxRequestBean;
import net.simpleframework.mvc.component.ext.ckeditor.HtmlEditorBean;
import net.simpleframework.mvc.component.ext.userselect.UserSelectBean;
import net.simpleframework.mvc.component.ui.autocomplete.AutocompleteBean;
import net.simpleframework.mvc.component.ui.pager.IGroupTablePagerHandler;
import net.simpleframework.mvc.component.ui.pager.TablePagerColumn;
import net.simpleframework.mvc.template.struct.FilterButtons;
import net.simpleframework.mvc.template.struct.NavigationButtons;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public abstract class AbstractTemplatePage extends AbstractBasePage {

	protected String hashId = ObjectUtils.hashStr(this);

	@Override
	public Map<String, Object> createVariables(final PageParameter pp) {
		return ((KVMap) super.createVariables(pp)).add("hashId", hashId);
	}

	protected String getPageCSS(final PageParameter pp) {
		return null;
	}

	protected boolean isPage404(final PageParameter pp) {
		return false;
	}

	public static UrlForward PAGE404 = new UrlForward(url(Err404Page.class));

	@Override
	public IForward forward(final PageParameter pp) {
		if (isPage404(pp)) {
			return PAGE404;
		}
		final IForward forward = super.forward(pp);
		String pageCss;
		if (forward != null && TextForward.class.equals(forward.getClass())
				&& StringUtils.hasText(pageCss = getPageCSS(pp))) {
			final TextForward tf = (TextForward) forward;
			tf.insert(0, "<div class='" + pageCss + "'>");
			tf.append("</div>");
		}
		return forward;
	}

	public static <T> T getCacheBean(final PageParameter pp, final IDbBeanService<T> beanService,
			final String key) {
		return pp.getCache(key, new IVal<T>() {
			@Override
			public T get() {
				return beanService.getBean(pp.getParameter(key));
			}
		});
	}

	/**
	 * 定义导航条
	 * 
	 * @param pParameter
	 * @return
	 */
	public NavigationButtons getNavigationBar(final PageParameter pp) {
		return null;
	}

	/*-------------------IPageToolbarAware--------------------*/

	public ElementList getLeftElements(final PageParameter pp) {
		return null;
	}

	public ElementList getRightElements(final PageParameter pp) {
		return null;
	}

	public String toToolbarHTML(final PageParameter pp) {
		final StringBuilder sb = new StringBuilder();
		if (this instanceof IPageToolbarAware) {
			final ElementList le = ((IPageToolbarAware) this).getLeftElements(pp);
			final ElementList re = ((IPageToolbarAware) this).getRightElements(pp);
			final boolean bLe = le != null && le.size() > 0;
			final boolean bRe = re != null && re.size() > 0;
			if (bLe || bRe) {
				sb.append("<div class='tool_bar'>");
				if (bLe) {
					sb.append(" <div class='le'>").append(le).append("</div>");
				}
				if (bRe) {
					sb.append(" <div class='re'>").append(re).append("</div>");
				}
				sb.append(BlockElement.CLEAR);
				sb.append("</div>");
			}
		}
		return sb.toString();
	}

	/*-------------------IPageFilterbarAware--------------------*/
	public FilterButtons getFilterButtons(final PageParameter pp) {
		return null;
	}

	public String toFilterBarHTML(final PageParameter pp) {
		final FilterButtons items = getFilterButtons(pp);
		if (items != null) {
			return items.toString();
		}
		return null;
	}

	/*-------------------Components--------------------*/

	/* HtmlEditorBean */
	protected HtmlEditorBean addHtmlEditorBean(final PageParameter pp, final String name) {
		return addComponentBean(pp, name, HtmlEditorBean.class);
	}

	protected HtmlEditorBean addHtmlEditorBean(final PageParameter pp, final String name,
			final boolean codeEnabled) {
		return addComponentBean(pp, new KVMap().add("name", name).add("codeEnabled", codeEnabled),
				HtmlEditorBean.class);
	}

	/* AutocompleteBean */
	protected AutocompleteBean addUserAutocompleteBean(final PageParameter pp, final String name) {
		return addAutocompleteBean(pp, name,
				"net.simpleframework.organization.web.component.autocomplete.UserAutocompleteHandler");
	}

	protected AutocompleteBean addUserRoleAutocompleteBean(final PageParameter pp, final String name) {
		return addAutocompleteBean(pp, name,
				"net.simpleframework.organization.web.component.autocomplete.UserRoleAutocompleteHandler");
	}

	protected AutocompleteBean addAutocompleteBean(final PageParameter pp, final String name,
			final String handlerClass) {
		final AutocompleteBean autocomplete = addComponentBean(pp, name, AutocompleteBean.class);
		try {
			autocomplete.setHandlerClass(ClassUtils.forName(handlerClass));
		} catch (final ClassNotFoundException e) {
			getLog().warn(e);
		}
		return autocomplete;
	}

	protected void addComponent_logout(final PageParameter pp) {
		addAjaxRequest(pp, "AbstractTemplatePage_logout").setHandlerMethod("logout");
	}

	/**
	 * AjaxRequest组件的调用方法
	 * 
	 * @param cParameter
	 * @return
	 */
	public IForward logout(final ComponentParameter cp) {
		cp.getPermission().logout(cp);
		return new JavascriptForward("$Actions.loc('").append(settings.getLoginPath(cp))
				.append("');");
	}

	// 表格过滤用到的用户选择
	protected UserSelectBean addUserSelectForTbl(final PageParameter pp,
			final String tblComponentName, String columnName) {
		if (!StringUtils.hasText(columnName)) {
			columnName = "userId";
		}
		return (UserSelectBean) addComponentBean(pp, tblComponentName + "_userselect_" + columnName,
				UserSelectBean.class).setMultiple(false).setJsSelectCallback(
				"$Actions['" + tblComponentName + "']('filter_cur_col=" + columnName
						+ "&filter=%3D;' + selects[0].id);return true;");
	}

	protected UserSelectBean addUserSelectForTbl(final PageParameter pp,
			final String tblComponentName) {
		return addUserSelectForTbl(pp, tblComponentName, null);
	}

	protected TablePagerColumn createUserColumn(final PageParameter pp, final String columnName,
			final String columnText, final String tblComponentName) {
		return new TablePagerColumn(columnName, columnText, 120) {
			@Override
			public String getFilterVal(final String val) {
				if (val == null) {
					return null;
				}
				final PermissionUser user = pp.getUser(ID.of(val));
				return user.exists() ? user.getText() : val;
			};
		}.setFilterAdvClick("$Actions['" + tblComponentName + "_userselect_" + columnName + "']();");
	}

	protected AjaxRequestBean addPageletTabAjaxRequest(final PageParameter pp) {
		return addAjaxRequest(pp, "PageletTab_ajax").setHandlerMethod("doPageletTab");
	}

	/*-------------------Element utils--------------------*/

	public static final String G = IGroupTablePagerHandler.G;
	public static Option OPTION_NO_GROUP = new Option("none", $m("AbstractTemplatePage.0"));

	protected InputElement createGroupElement(final PageParameter pp, final String tblAction,
			final Option... opts) {
		final ArrayList<Option> list = new ArrayList<Option>();
		list.add(OPTION_NO_GROUP);
		if (opts != null) {
			for (final Option opt : opts) {
				list.add(opt);
			}
		}
		return _createGroupElement(pp, tblAction, list.toArray(new Option[list.size()]));
	}

	private InputElement _createGroupElement(final PageParameter pp, final String tblAction,
			final Option... opts) {
		final String g = pp.getParameter(G);

		final StringBuilder sb = new StringBuilder();
		sb.append("var val = $F(this); $Actions['").append(tblAction)
				.append("']('g=' + val); document.setCookie('group_")
				.append(getClass().getSimpleName()).append("', val);");
		final InputElement select = InputElement.select("InputElement_group").setOnchange(
				sb.toString());
		if (opts != null) {
			for (final Option opt : opts) {
				final String name = opt.getName();
				if (name == null) {
					continue;
				}
				select.addElements(opt.setSelected(name.equals(g)));
			}
		}
		return select;
	}

	protected void setDefaultGroupVal(final PageParameter pp, final String defaultGroupVal) {
		String groupVal = pp.getCookie("group_" + getClass().getSimpleName());
		if (!StringUtils.hasText(groupVal)) {
			groupVal = defaultGroupVal;
		}
		pp.putParameter(IGroupTablePagerHandler.G, groupVal);
	}

	protected SpanElement createTabsElement(final PageParameter pp, final TabButtons tabs) {
		return new SpanElement().setClassName("tabbtns").addHtml(tabs.toString(pp));
	}
}
