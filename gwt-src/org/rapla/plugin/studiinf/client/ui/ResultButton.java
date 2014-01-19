package org.rapla.plugin.studiinf.client.ui;

import java.util.LinkedList;
import java.util.List;

import org.rapla.plugin.studiinf.client.pages.AbstractPage;

import com.google.gwt.user.client.ui.Widget;

public class ResultButton extends NavButton implements ResultObject {
	
	private NavButton bottomPictureButton;
	private List<Widget> cellList;
	


	
	@Override
	public void setNumber(int number) {
		super.setNumber(number);
		getFooterButton().setNumber(number);
	}

	public ResultButton(String title, AbstractPage targetPage, String targetId, FontIcon icon) {

		super(0, icon, title, targetPage, targetId);
		setNumber(0);
		this.setWidth("100%");	
	}
		
	@Override
	public void setIcon(FontIcon fontIcon) {
		super.setIcon(fontIcon);
		bottomPictureButton.setIcon(fontIcon);
	}

	@Override
	public List<Widget> getCellObjects() {
		if(cellList == null){
			cellList = new LinkedList<Widget>();
			cellList.add(this);
		}
		return cellList;
	}

	@Override
	public NavButton getFooterButton() {
		if(bottomPictureButton == null){
			bottomPictureButton = new NavButton(0, getIcon(),getText(),targetPage,targetId);
		}
		return bottomPictureButton;
	}
	
	@Override
	public void setTargetId(String targetId) {
		super.setTargetId(targetId);
		getFooterButton().setTargetId(targetId);
	}
	
	@Override
	public void setTargetPage(AbstractPage targetPage) {
		super.setTargetPage(targetPage);
		getFooterButton().setTargetPage(targetPage);
	}

	
}
