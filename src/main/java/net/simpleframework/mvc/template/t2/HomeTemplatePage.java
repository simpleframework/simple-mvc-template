package net.simpleframework.mvc.template.t2;

import java.util.Map;

import net.simpleframework.common.coll.KVMap;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.component.ComponentParameter;
import net.simpleframework.mvc.component.ui.imageslide.AbstractImageSlideHandler;
import net.simpleframework.mvc.component.ui.imageslide.ImageItems;
import net.simpleframework.mvc.component.ui.imageslide.ImageSlideBean;
import net.simpleframework.mvc.component.ui.tooltip.TooltipRegistry;
import net.simpleframework.mvc.template.struct.ListRows;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public class HomeTemplatePage extends T2TemplatePage {

	@Override
	protected void addComponents(final PageParameter pp) {
		super.addComponents(pp);

		addComponentBean(pp, ImageSlideBean.class, HomeImageSlideHandler.class).setContainerId(
				"imageSlide_" + hashId);
	}

	@Override
	public Map<String, Object> createVariables(final PageParameter pp) {
		return ((KVMap) super.createVariables(pp)).add("imageSlideId", "imageSlide_" + hashId);
	}

	@Override
	public String[] getDependentComponents(final PageParameter pp) {
		return new String[] { TooltipRegistry.TOOLTIP };
	}

	protected ListRows getListRows(final PageParameter pp, final int row, final int col) {
		return null;
	}

	public String toPageletHTML(final PageParameter pp, final int row, final int col) {
		final ListRows lr = getListRows(pp, row, col);
		return lr != null ? lr.toString() : null;
	}

	protected ImageItems getImageItems(final ComponentParameter cp) {
		return null;
	}

	public static class HomeImageSlideHandler extends AbstractImageSlideHandler {
		@Override
		public ImageItems getImageItems(final ComponentParameter cp) {
			return ((HomeTemplatePage) get(cp)).getImageItems(cp);
		}
	}
}
