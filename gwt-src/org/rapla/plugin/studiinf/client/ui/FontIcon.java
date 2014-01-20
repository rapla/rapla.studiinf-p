package org.rapla.plugin.studiinf.client.ui;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

public class FontIcon extends Widget {
	
	
	
	public FontIcon() {
		setElement(DOM.createSpan());
	}
	public FontIcon(String url) {
		this();
		setUrl(url);
	}
	@Override
	public void setPixelSize(int width, int height) {
		this.getElement().getStyle().setFontSize(Math.max(width, height), Unit.PX);
		super.setPixelSize(width, height);
	}

	public void setUrl(SafeUri url) {
		setUrl(url.asString());
	}
	

	public void setUrl(String url) {
		if(url.startsWith("icon-")){
			getElement().setClassName(url);
		}
	}
	public String getUrl() {
		return getElement().getClassName();	
	}
	
}