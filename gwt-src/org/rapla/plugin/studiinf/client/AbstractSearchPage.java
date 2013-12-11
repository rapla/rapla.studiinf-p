package org.rapla.plugin.studiinf.client;

import com.google.gwt.user.client.ui.*;

public abstract class AbstractSearchPage extends AbstractPage{

public AbstractSearchPage(){
	super();
	Widget searchField = new TextBox();
	Widget resultLabel = new Label(i18n.resultLabel());
	Widget frequentLabel = new Label(i18n.frequentResultsLabel());
	Grid results = new Grid(3, 2);
	Grid frequent = new Grid(2, 2);
	Widget organigramBtn = new Button(i18n.organigram());
	
	searchField.setStyleName("searchField");
	resultLabel.setStyleName("resultLabel");
	frequentLabel.setStyleName("frequentLabel");
	results.setStyleName("results");
	frequent.setStyleName("frequent");
	organigramBtn.setStyleName("organigramBtn");
	
	this.add(searchField);
	this.add(resultLabel);
	this.add(frequentLabel);
	this.add(results);
	this.add(frequent);
	this.add(organigramBtn);
}

}
