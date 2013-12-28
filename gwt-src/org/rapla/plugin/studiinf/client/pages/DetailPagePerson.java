package org.rapla.plugin.studiinf.client.pages;

import org.rapla.plugin.studiinf.client.AbstractDetailPage;
import org.rapla.plugin.studiinf.client.ui.IconButton;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class DetailPagePerson extends AbstractDetailPage {

	private FlowPanel personInfoPanel = new FlowPanel();
	private FlowPanel bottomPanel = new FlowPanel();
	private FlowPanel middlePanel = new FlowPanel();
	private Label personInfoLabel = new Label("Information");
	private Grid infos = new Grid(4, 1);
	private FlowPanel picture = new FlowPanel();
	private Label appointmentLabel = new Label("Anstehende Termine");
	private Label courseLabel = new Label("Kurse");
	
	
	
	@Override
	public void init(){
		super.init();
		
		personInfoPanel.setStyleName("personInfoPanel");
		personInfoLabel.setStyleName("personInfoLabel");
		bottomPanel.setStyleName("personBottomPanel");
		middlePanel.setStyleName("personMiddlePanel");
		infos.setStyleName("personInfos");
		picture.setStyleName("personDetailPicture");
		appointmentLabel.setStyleName("personAppointmentLabel");
		courseLabel.setStyleName("personCourseLabel");
		
		personInfoPanel.add(personInfoLabel);
		personInfoPanel.add(picture);
		personInfoPanel.add(infos);
		
		Image img1 = new Image("img/Kurse.svg");
		Image img2 = new Image("img/Raum.svg");
		Image img3 = new Image("img/Kurse.svg");
		Image img4 = new Image("img/Kurse.svg");
		
		Widget roomNrBtn = new IconButton("Raum 1234", img1);
		Widget mailBtn = new IconButton("test@mail.de", img2);
		Widget telephoneBtn = new IconButton("0122- 5675765", img3);
		Widget extraInfosBtn = new IconButton("Zusätzliche Infos", img4);
		
		infos.setWidget(0, 0, roomNrBtn);
		infos.setWidget(1, 0, mailBtn);
		infos.setWidget(2, 0, telephoneBtn);
		infos.setWidget(3, 0, extraInfosBtn);
		

		middlePanel.add(appointmentLabel);
		middlePanel.add(courseLabel);
		
		Image img5 = new Image("img/Kurse.svg");
		Image img6 = new Image("img/Raum.svg");
		
		Widget showRoomBtn = new IconButton("Raum anzeigen", img5);
		Widget showextraInfosBtn = new IconButton("Extrainfos anzeigen", img6);
		
		bottomPanel.add(showRoomBtn);
		bottomPanel.add(showextraInfosBtn);
		
		this.add(qrBox);

		this.add(personInfoPanel);
		this.add(bottomPanel);
		this.add(middlePanel);
	}
	
	@Override
	public String getHistoryKey() {
		return "personDetail";
	}

	@Override
	public String getTitle() {
		return "Name of Person";
	}

	@Override
	public void handleId(String id) {
		// TODO Auto-generated method stub
		
	}

}
