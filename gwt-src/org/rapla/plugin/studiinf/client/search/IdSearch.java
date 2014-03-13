package org.rapla.plugin.studiinf.client.search;

import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescription;
import org.rapla.plugin.studiinf.client.pages.AbstractSearchPage;
import org.rapla.plugin.studiinf.client.ui.RessourceButton;

import com.google.gwt.user.client.Window;

/**
 * 
 *
 */
public class IdSearch extends AbstractSearch {

	private MFRButtonHandler mfrButtonHandler;
	
	public IdSearch(MFRButtonHandler mfrButtonHandler, AbstractSearchPage page, String targetId) {
		super(targetId, page, false);
		this.mfrButtonHandler = mfrButtonHandler;
		init();
	}

	@Override
	protected NoDuplicatesList<ResourceDescription> searchRessources(
			List<ResourceDescription> resources) {
		NoDuplicatesList<ResourceDescription> resourceMatched = new NoDuplicatesList<ResourceDescription>();
//		Window.alert("matched: "+resourceMatched.toString());
		ResourceDescription resourceDescriptor = SearchUtils.byId(searchString, resources);
		
		if (resourceDescriptor!=null){
			mfrButtonHandler.onSuccess(resourceDescriptor);
			resourceMatched.add(resourceDescriptor);
		}
		else{
			mfrButtonHandler.onFailure(null);
		}
			
		return null;
	}
	
	@Override
	public void onSuccess(List<ResourceDescription> arg0) {
		if(!resourcesMap.containsKey(page)){
			resourcesMap.put(page, arg0);
		}
		searchRessources(resourcesMap.get(page));


	}
	@Override
	public void onFailure(Throwable arg0) {
		// TODO Auto-generated method stub
		//Window.alert(arg0.toString());
		
	}
	
	

}