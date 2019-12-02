package core;

import org.json.JSONObject;

// Static class for backend to access during runtime. Runtime will hold sets of these objects.

enum objectType {
	ASSET, LOCATION, REQUESTER, AGENT;
}

public class APIObj {
	
	
	private static objectType type; // not yet working
	private static String json;
	private static JSONObject parsed_obj;
	private static String apiPath = "NO_API_PATH";
	private String location_id = "NO_LOCATION_ID";
	private String name = "NO_NAME";
	
	public APIObj (String json) {
		System.out.println("APIOBJ INIT STR ->" + json);
		// MUST at least define object type classification
		JSONObject nest = new JSONObject(json);
	
		
		// check nest for appropriate category
		if (nest.has("asset")) {
			parsed_obj = nest.getJSONObject("asset");
			name = parsed_obj.getString("name");
			apiPath = "assets/" + parsed_obj.get("display_id").toString();
			location_id = parsed_obj.get("location_id").toString();
		} else if (nest.has("locations")) {
			parsed_obj = nest.getJSONObject("locations");
			location_id = parsed_obj.get("location_id").toString();
			}
		}
		
		public objectType getObjectType() {
			return type;
		}
		
		public String getJSON_PUT () {
			//returns raw json string, strips unneeded fields for PUT
			JSONObject return_obj = new JSONObject(parsed_obj.toString());
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
			return parsed_obj.getString("description");
		}
		
		public String getLocID() {
			return parsed_obj.get("location_id").toString();
		}
		
		public String getName() {
			return name;
		}
		
		public void updateOBJ(JSONObject updated) {
			parsed_obj = new JSONObject(updated.toString());
			System.out.println("APIObj parsed_obj updated to = " + parsed_obj.toString());
		}
		
		public JSONObject cloneJOBJ() {
			return parsed_obj;
		}
		
		public String toString() {
			
			JSONObject nest = new JSONObject(json);
			JSONObject obj = nest.getJSONObject("asset");
			System.out.println(obj);
			
			String name = obj.getString("name");
			String displayid = obj.get("display_id").toString();
			String atag = obj.getString("asset_tag");
			String locationid = obj.getNumber("location_id").toString();
			String agentid = obj.get("agent_id").toString();
			String userid = obj.getNumber("user_id").toString();
			String desc = obj.get("description").toString();
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

		
	}
	
	
	