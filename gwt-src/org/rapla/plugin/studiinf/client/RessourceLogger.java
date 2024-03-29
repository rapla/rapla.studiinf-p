package org.rapla.plugin.studiinf.client;

import org.rapla.plugin.studiinf.client.pages.AbstractPage;
//import org.rapla.rest.RemoteLogger;
import org.rapla.rest.RemoteLogger;
import org.rapla.rest.gwtjsonrpc.common.AsyncCallback;
import org.rapla.rest.gwtjsonrpc.common.VoidResult;

import com.google.gwt.core.client.GWT;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.rpc.ServiceDefTarget;


/**
 * 
 * Static class for logging purposes
 *
 */
public class RessourceLogger {
	private static RemoteLogger service = null;
	private static String searchString = "";
	
	private static final String localeStorageClientId = "org.rapla.plugin.studiinf.clientId";
		
	private static String clientId = null;
	
	/**
	 * Returns the logging-service of Rapla
	 * @return Logging-service of Rapla
	 */
	private static RemoteLogger getService() {
		if(service == null){
			service = GWT.create(RemoteLogger.class);
			String address = GWT.getModuleBaseURL() + "../rapla/json/" + RemoteLogger.class.getName();
		 	((ServiceDefTarget) service).setServiceEntryPoint(address);
		}
		return service;
	}
	
	/**
	 * generate a random id for a client
	 * @return random id
	 */
	private static String generateClientId(){
		String tmpClientId = "";
		 String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ123456789+-_";
		 for (int i=0; i<64; i++)
	      {
	          int index = (int)(Random.nextDouble()*letters.length());
	          tmpClientId += letters.substring(index, index+1);
	      }
		return tmpClientId;
	}
	
	/**
	 * returns the Id of a client from LocalStorage or generates and returns a new clientId
	 * @return id of the client
	 */
	private static String getClientId(){
		if(clientId == null){
			Storage localStorage = Storage.getLocalStorageIfSupported();
			if(localStorage != null){
				String tmpClientId = localStorage.getItem(localeStorageClientId);
				if(tmpClientId != null){
					clientId =  tmpClientId;
				}else{
					clientId = generateClientId();
				}
			}else{
				clientId = generateClientId();
			}
		}
		return clientId;
	}
	
	/**
	 * Sets the searchString to be logged
	 * @param search the text which was searched for
	 */
	public static void setSearchString(String search){
		searchString = search;
	}
	
	/**
	 * log the change between two pages
	 * @param targetPage the page to which was navigated
	 * @param sourcePage the page from which was navigated
	 */
	public static void logRessource(AbstractPage targetPage, AbstractPage sourcePage) {
		if(DisplayMode.isStele()){
			String clientId = getClientId();
			String currentPage = targetPage.getHistoryKey();
			String previousPage = sourcePage.getHistoryKey();
			try {
				getService().info("csvaccesslog",clientId+';'+currentPage+';'+searchString+';'+previousPage).get(new AsyncCallback<VoidResult>() {
					
					@Override
					public void onSuccess(VoidResult result) {
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						
					}
				});
				searchString = "";
			} catch (Exception e) {
				e.printStackTrace();
			}

			
		}
	}
	
	
}
