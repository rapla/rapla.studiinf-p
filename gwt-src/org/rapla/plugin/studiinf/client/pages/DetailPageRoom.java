package org.rapla.plugin.studiinf.client.pages;

import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.studiinf.client.IconProvider;
import org.rapla.plugin.studiinf.client.Picture;
import org.rapla.plugin.studiinf.client.search.RoomDescriptor;
import org.rapla.plugin.studiinf.client.ui.IconButton;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class DetailPageRoom extends AbstractDetailPage {

	private FlowPanel infoPanel = new FlowPanel();
	private FlowPanel roomPanel = new FlowPanel();
	private Label infoLabel = new Label("Information");
	
	private Grid infos = new Grid(4, 1);
	
	private String roomNumber;
	private String roomType;
	private String courseOfStudy;
	
	private IconButton nameBtn;
	private IconButton typeBtn;
	private IconButton studyBtn;
	private IconButton roomBtn;
	
	private Image noNavigationImg = new Image(IconProvider.MISSING_MAP);
	private Image wayDescriptionImg = new Image(IconProvider.MISSING_MAP);
	
	

	@Override
	public void init(){
		super.init();
		
		infoPanel.setStyleName("infoPanel");
		roomPanel.setStyleName("roomPanel");
		infoLabel.setStyleName("infoLabel");
		infos.setStyleName("infos");
		
		Image roomNameImg = new Image(IconProvider.ROOMS);
		Image roomTypeImg = new Image(IconProvider.ROOM_TYPE);
		Image studyImg = new Image(IconProvider.COURSE);
		Image roomImg = new Image(IconProvider.CALENDAR);
		
		
		if (roomNumber.equals("A051") || roomNumber.equals("A052")  || roomNumber.equals("LA051") || roomNumber.equals("LA052")  || roomNumber.equals("RA051") || roomNumber.equals("RA052")){
			Window.alert(Picture.getImageURL(roomNumber));
			wayDescriptionImg = new Image(Picture.getImageURL(roomNumber));
			wayDescriptionImg.setStyleName("navigationPicture");
			this.add(wayDescriptionImg);
		}
		else{
			Window.alert(Picture.getImageURL(roomNumber));
			noNavigationImg.setStyleName("navigationPicture");
			this.add(noNavigationImg);
		}
		
		
		nameBtn = new IconButton(roomNumber, roomNameImg);
		typeBtn = new IconButton(roomType, roomTypeImg);
		studyBtn = new IconButton(courseOfStudy, studyImg);
		roomBtn = new IconButton("Raumbelegung", roomImg);
		
		infos.setWidget(0, 0, nameBtn);
		infos.setWidget(1, 0, typeBtn);
		infos.setWidget(2, 0, studyBtn);
		infos.setWidget(3, 0, roomBtn);
		
		infoPanel.add(infoLabel);
		infoPanel.add(infos);
		
			
		Image occupancyImg = new Image(IconProvider.CALENDAR);
		
		Widget bottomRoomBtn = new IconButton("Raumbelegung anzeigen", occupancyImg);
		roomPanel.add(bottomRoomBtn);
		
		this.add(infoPanel);
		this.add(roomPanel);
		
	}
	
	
	@Override
	public String getHistoryKey() {
		return "roomDetail";
	}

	@Override
	public String getTitle() {
		if(roomNumber == null){
			roomNumber = "";
		}
		return roomNumber;
	}


	
	@Override
	protected void refresh() {
		super.refresh();
		nameBtn.setText(roomNumber);
		typeBtn.setText(roomType);
		studyBtn.setText(courseOfStudy);
		
		this.remove(wayDescriptionImg);
		this.remove(noNavigationImg);
		
		if (roomNumber.equals("A051") || roomNumber.equals("A052")  || roomNumber.equals("LA051") || roomNumber.equals("LA052")  || roomNumber.equals("RA051") || roomNumber.equals("RA052")){
			Window.alert(Picture.getImageURL(roomNumber));
			wayDescriptionImg = new Image(Picture.getImageURL(roomNumber));
			wayDescriptionImg.setStyleName("navigationPicture");
			this.add(wayDescriptionImg);
		}
		else{
			Window.alert(Picture.getImageURL(roomNumber));
			noNavigationImg.setStyleName("navigationPicture");
			this.add(noNavigationImg);
		}
		
	}


	@Override
	public boolean hasDefaultQrBox() {
		return true;
	}


	@Override
	protected void handleRessource(String id, ResourceDetail resource) {
		RoomDescriptor rd = new RoomDescriptor(resource);
		
		if (!rd.getRoomNr().equals("")){
			roomNumber = rd.getRoomNr();
			roomBtn.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
			}else{
				roomBtn.getElement().getStyle().setDisplay(Display.NONE);
			}
		if (!rd.getRoomType().equals("")){
			roomType = rd.getRoomType();
			typeBtn.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
			}else{
				typeBtn.getElement().getStyle().setDisplay(Display.NONE);
			}
		if (!rd.getDepartment().equals("")){
			courseOfStudy = rd.getDepartment();
			studyBtn.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
			}else{
				studyBtn.getElement().getStyle().setDisplay(Display.NONE);
			}
		
//		Window.alert(rd.getPicture());
		refresh();
		
	}

}
