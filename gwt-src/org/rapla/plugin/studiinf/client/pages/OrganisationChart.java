package org.rapla.plugin.studiinf.client.pages;


import java.util.List;

import org.rapla.plugin.freiraum.common.CategoryDescription;
import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.studiinf.client.DisplayMode;
import org.rapla.plugin.studiinf.client.LocalStorage;
import org.rapla.plugin.studiinf.client.ServiceProvider;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.search.CourseOrganigramSearch;
import org.rapla.plugin.studiinf.client.ui.AccessibilityRow;
import org.rapla.plugin.studiinf.client.ui.OrganigramButton;
import org.rapla.plugin.studiinf.client.ui.ResultTable;
import org.rapla.rest.gwtjsonrpc.common.AsyncCallback;


//import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 * 
 * @author Team StudiInf
 * 
 * Page to display organigram
 */
public abstract class OrganisationChart extends AbstractDetailPage  implements SearchPageInterface {
	
	protected AccessibilityRow access = new AccessibilityRow();
	
	protected ResultTable organigram;
	
	
	protected List <CategoryDescription> testCategoryList;
	public String helpId;
	
	private String categoryId;
	private LocalStorage ls;
	public OrganisationChart() {
		ls = new LocalStorage(getHistoryKey(), organigram, null, null, null);
		if(DisplayMode.isMobile()){
			organigram = new ResultTable(access, 1, 8);
		}
		else{
			organigram = new ResultTable(access, 1, 14);
		}
	}
	
		
	@Override
	public void init(){
		super.init();
		FlowPanel infoPanel = new FlowPanel();
		infoPanel.setStyleName("infoPanel");
		
		organigram.addStyleName("infos");			
		if(DisplayMode.isMobile()){
			infoPanel.addStyleName("mobile");
		}
		infoPanel.add(organigram);
		this.add(infoPanel);
		this.add(access);
		
		
		footer.setTargetPage(this);
		
	}

	protected void handleRessource(String id, List <CategoryDescription> resources) {
		showOrganigramLevels(resources);
	}
	
	@Override
	protected void handleId(final String id){
		this.id = id;
		footer.setTargetId(backInHistory());
		String newId = resolveHistory();
		
		if (newId.equals("null")){
			newId = null;
		}
		categoryId = newId;
		ServiceProvider.getOrganigram(newId, new AsyncCallback<List<CategoryDescription>>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onSuccess(List<CategoryDescription> result) {
				// TODO Auto-generated method stub
				handleRessource(id, result);
				
			}
			
		});
	}
	
	public String resolveHistory(){
		String last = this.id +"";
		return last.substring(last.lastIndexOf(">")+1);
	}
	public String backInHistory(){
		String last = this.id +"";
		last = last.substring(0, last.lastIndexOf(">"));
		if(last.length() <= 0 || this.id.equals("null")){
			last = null;
		}
		return last;
	}
	public String addHistory(String addon){
		return this.id + ">" + addon;
	}
	
	/**
	 * Shows categories as an organigram consisting of buttons
	 * @param categories List of resource categories
	 */
	public void showOrganigramLevels(List <CategoryDescription> categories){
		if(categories.size() <= 0){
			new CourseOrganigramSearch(categoryId, this);
		}else{
			organigram.clearResults();	
			for (CategoryDescription category : categories){
				OrganigramButton orgButton = new OrganigramButton(category.getName(), this,  addHistory(category.getId()));
				organigram.addResult(orgButton);
			}
			organigram.refresh();
		}
		
	}

	@Override
	public String getTitle() {
		return Studiinf.i18n.organigram();
	}
	
	@Override
	protected void refresh() {
		super.refresh();
	}

	@Override
	public boolean hasDefaultQrBox() {
		return false;
	}

	@Override
	protected void handleRessource(String id, ResourceDetail resource) {
		
	}

	@Override
	public void handleClickCount(String targetId) {
		ls.increaseInStorage(targetId);
	}
	@Override
	protected void showRaplaLinks(boolean b) {
		// do nothing always true;
	}

}
