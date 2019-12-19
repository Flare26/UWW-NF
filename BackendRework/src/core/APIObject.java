package core;

import org.json.JSONObject;

// Static class for backend to access during runtime. Runtime will hold sets of these objects.

enum objectType {
	ASSET, LOCATION, REQUESTER, AGENT;
}

public class APIObject {
	
	
	private static objectType type; // not yet working
	private static String JSON_str;
	private static JSONObject JSON_obj;
	private static String apiPath = "NO_API_PATH";
	private String location_id = "NO_LOCATION_ID";
	private String name = "NO_NAME";
	
	public APIObject (objectType type, JSONObject target) {
		this.type = type;
		setJSON_obj(target); // if initialized with an object parsed already, store immediately.
		name = target.getString("name");
	}
	
	public APIObject (objectType type, String json) {
		System.out.println("APIOBJ INIT STR ->" + json);
		// MUST at least define object type classification
		// if api object is being created without json, do not try to parse
		if (! json.equals("")) {
			JSONObject nest = new JSONObject(json);
			if (nest.has("asset")) {
				setJSON_obj(nest.getJSONObject("asset"));
				name = getJSON_obj().getString("name");
				apiPath = "assets/" + getJSON_obj().get("display_id").toString();
				location_id = getJSON_obj().get("location_id").toString();
			} else if (nest.has("locations")) {
				setJSON_obj(nest.getJSONObject("locations"));
				location_id = getJSON_obj().get("location_id").toString();
				}
		}
		
		switch ( type ) {
		case LOCATION:
				apiPath = "locations/";
				break;
		case ASSET:
			apiPath = "assets";
			break;
		
	
			};
		
		}

		
		
		// check nest for appropriate category

		
		public objectType getObjectType() {
			return type;
		}
		
		public String getJSON_PUT () {
			//returns raw json string, strips unneeded fields for PUT
			JSONObject return_obj = new JSONObject(getJSON_obj().toString());
			return_obj.remove("created_at");
			return_obj.remove("author_type");
			return_obj.remove("display_id");
			return_obj.remove("updated_at");
			return_obj.remove("id");
			return return_obj.toString();
		}
		
		public String getAPIPath() {
			return apiPath;
		}
		public String getDesc() {
			return getJSON_obj().getString("description");
		}
		
		public Long getLocID() {
			return getJSON_obj().getLong("location_id");
		}
		
		public String getName() {
			return name;
		}
		
		public void updateOBJ(JSONObject updated) {
			setJSON_obj(new JSONObject(updated.toString()));
			System.out.println("APIObj parsed_obj updated to = " + getJSON_obj().toString());
		}
		
		public void editField(String key, String data) {
			getJSON_obj().put(key, data);
		}
		
		// Cover integers too
		public void editField(String key, Long data) {
			getJSON_obj().put(key, data);
		}
		
		public String toString() {
			
			JSONObject nest = new JSONObject(JSON_str);
			JSONObject obj = nest.getJSONObject("asset");
			System.out.println(obj);
			
			String name = JSON_obj.getString("name");
			String displayid = JSON_obj.get("display_id").toString();
			String atag = JSON_obj.getString("asset_tag");
			String locationid = JSON_obj.getNumber("location_id").toString();
			String agentid = JSON_obj.get("agent_id").toString();
			String userid = JSON_obj.getNumber("user_id").toString();
			String desc = JSON_obj.get("description").toString();
			//TEST
			System.out.println(name);
			System.out.println(displayid);
			System.out.println(atag);
			System.out.println(locationid);
			System.out.println(agentid);
			System.out.println(userid);
			System.out.println("DESC ="+ desc);
			
			return name + displayid + atag + locationid + agentid + userid ;
					
		}

		public static JSONObject getJSON_obj() {
			return JSON_obj;
		}

		public static void setJSON_obj(JSONObject jSON_obj) {
			JSON_obj = jSON_obj;
		}

		
	}
	
	
	