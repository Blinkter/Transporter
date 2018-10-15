package com.transporter.service;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;

@Component
public class WayCalc {
//	private static final String API_KEY = "AIzaSyDGBhBYu1xbTGMhT-gHUs2evHxmsLdtSsU";
//	
//	public double calculator(String origin, String destination) throws IOException {
//		OkHttpClient client = new OkHttpClient();
//
//		String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + origin + "&destinations="
//				+ destination + "&key=" + API_KEY;
//		Request request = new Request.Builder().url(url).build();
//
//		Response response = client.newCall(request).execute();
//		final String json = response.body().string();
		ObjectMapper mapper = new ObjectMapper();

//		JSONParser parser = new JSONParser();
//		JSONObject jsonobj;
//		double distance = -1;
//		try {
//			jsonobj = (JSONObject) parser.parse(json);
//			JSONArray dist = (JSONArray) jsonobj.get("rows");
//			JSONObject obj2 = (JSONObject) dist.get(0);
//			JSONArray disting = (JSONArray) obj2.get("elements");
//			JSONObject obj3 = (JSONObject) disting.get(0);
//			JSONObject obj4 = (JSONObject) obj3.get("distance");
//			JSONObject obj5 = (JSONObject) obj3.get("duration");
//			distance = Integer.parseInt(obj4.get("value").toString());
			
//			mapper.readValue(json, String.class);
//			return distance/1000;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return distance;
//	}
}
