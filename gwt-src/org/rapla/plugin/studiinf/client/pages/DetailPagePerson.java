package org.rapla.plugin.studiinf.client.pages;

import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.ServiceProvider;
import org.rapla.plugin.studiinf.client.search.PersonDescribtor;
import org.rapla.plugin.studiinf.client.ui.IconButton;
import org.rapla.plugin.studiinf.client.ui.NavigationIconButton;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwtjsonrpc.common.AsyncCallback;

public class DetailPagePerson extends AbstractDetailPage {

	private FlowPanel personInfoPanel = new FlowPanel();
	private FlowPanel bottomPanel = new FlowPanel();
	private FlowPanel middlePanel = new FlowPanel();
	private Label personInfoLabel = new Label("Information");
	private Grid infos = new Grid(4, 1);
	private FlowPanel picture = new FlowPanel();
	private Label appointmentLabel = new Label("Anstehende Termine");
	private Label courseLabel = new Label("Kurse");
	private String name;
	
	private NavigationIconButton roomNrBtn;
	private IconButton mailBtn;
	private IconButton telephoneBtn;
	
	
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
		
		roomNrBtn = new NavigationIconButton("D 935", img1,Navigation.roomDetail,"935");
		mailBtn = new IconButton("test@mail.de", img2);
		telephoneBtn = new IconButton("0122- 5675765", img3);
		Widget extraInfosBtn = new IconButton("Zusätzliche Infos", img4);
		
		infos.setWidget(0, 0, roomNrBtn);
		infos.setWidget(1, 0, mailBtn);
		infos.setWidget(2, 0, telephoneBtn);
		infos.setWidget(3, 0, extraInfosBtn);
		

		middlePanel.add(appointmentLabel);
		middlePanel.add(courseLabel);
		
		Image img5 = new Image("img/Kurse.svg");
		Image img6 = new Image("img/Raum.svg");
		
		Widget showRoomBtn = new NavigationIconButton("D 935", img5,Navigation.roomDetail,"935");
		Widget showextraInfosBtn = new IconButton("Extrainfos anzeigen", img6);
		
		bottomPanel.add(showRoomBtn);
		bottomPanel.add(showextraInfosBtn);
		

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
		if(name == null){
			name ="$NAME$";
		}
		return name;
	}

	@Override
	protected void handleId(String id) {
		ServiceProvider.getResource(id, new AsyncCallback<ResourceDetail>() {
			
			@Override
			public void onSuccess(ResourceDetail arg0) {
				//Window.alert(arg0.getKeys().toString());
				PersonDescribtor person = new PersonDescribtor(arg0);
				
				name = person.getName();
				roomNrBtn.setText(person.getRoomNr());
				if(!person.getMail().equals("")){
				mailBtn.setText(person.getMail());
				mailBtn.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
				}else{
					mailBtn.getElement().getStyle().setDisplay(Display.NONE);
				}
				
				if(!person.getPhoneNr().equals("")){
					telephoneBtn.setText(person.getPhoneNr());
					telephoneBtn.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
					}else{
						telephoneBtn.getElement().getStyle().setDisplay(Display.NONE);
					}
				
				refresh();
			}
			
			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	@Override
	protected void refresh() {
		super.refresh();
		
	}

}
