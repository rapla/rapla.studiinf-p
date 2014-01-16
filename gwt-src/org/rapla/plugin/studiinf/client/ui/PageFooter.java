package org.rapla.plugin.studiinf.client.ui;

import org.rapla.plugin.studiinf.client.IconProvider;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.i18n.I18n;
import org.rapla.plugin.studiinf.client.pages.AbstractPage;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;

public class PageFooter extends FlowPanel {
	
	private AbstractPage parent;
	
	public PageFooter(AbstractPage parent) {
		
		    this.parent = parent;
		    
	}
	
	public void init(){
		this.setStyleName("footer");
	    
	    NavButton homeBtn = new NavButton(IconProvider.Home,Studiinf.i18n.homeButtonText(),Navigation.homePage,null);
	    
	    homeBtn.getElement().getStyle().setProperty("float", "right");
	    
	    NavButton languageButton = new NavButton(IconProvider.World ,Studiinf.i18n.otherLanguage(),null,null);
	    languageButton.setClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				I18n i18n = GWT.create(I18n.class);
				UrlBuilder newUrl = Window.Location.createUrlBuilder();
				newUrl.setParameter("locale", i18n.otherLanguageURL());
				Window.Location.assign(newUrl.buildString());
				
			}
		});
	    languageButton.getElement().getStyle().setProperty("float", "left");
	    this.add(languageButton);
	    this.add(homeBtn);
	    
	}

	
	
	
	
	
}
