package net.simpleframework.mvc.template.struct;

import net.simpleframework.common.coll.AbstractArrayListEx;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class WizardButtons extends AbstractArrayListEx<WizardButtons, WizardButton> {

	public static WizardButtons of(final WizardButton... btns) {
		return new WizardButtons().append(btns);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("<span class='wizard_btns'>");
		int i = 0;
		final int size = size();
		for (final WizardButton btn : this) {
			sb.append(btn);
			if (i++ < size - 1) {
				sb.append("<span class='arrow'></span>");
			}
		}
		sb.append("</span>");
		return sb.toString();
	}

	private static final long serialVersionUID = -6361967931545055266L;
}
