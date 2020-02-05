package core;

import org.json.JSONObject;

public class Chromebook extends APIObject implements Updatable {
	
	
	public Chromebook(JSONObject target) {
		// If there is no source json it will need to be replace manually with chromebook.getRequest();
		super(objectType.ASSET, target);
	}
	public Chromebook(String jsonstr) {
		super(objectType.ASSET, jsonstr);
 
	}
	
	public void getRequest()
	{
		
	}
	
	@Override
	public int putCurrentState(APISession runtime_apisession) {
		JSONObject request_target = getJSON_obj();
		int dispid = request_target.getInt("display_id"); // save before removing
		request_target.remove("created_at");
		request_target.remove("author_type");
		request_target.remove("display_id");
		request_target.remove("updated_at");
		request_target.remove("id");
		
		runtime_apisession.putThisAsset(request_target, dispid);
		return 0;
	}
	public String getUserID() {
		String userid = "NO ASSIGNEE";
		if (! JSON_obj.get("user_id").equals(null)) // it is possible for an asset to have no assigned user
		{
			userid = JSON_obj.get("user_id").toString();
		} else 
		{
			userid = "NO ASSIGNEE";
		}
		return userid;
	}
	
}
