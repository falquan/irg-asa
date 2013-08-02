package com.irg.asa.wcs.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.irg.asa.bl.components.generic.entity.GenericConfiguration;
import com.irg.asa.wcs.domain.Category;
import com.irg.asa.wcs.service.WCService;

public class CategoryController {
	
	private static final Logger log = LoggerFactory.getLogger(CategoryController.class);

	public CategoryController() {
	}
	
	public List<Category> getTopCategories(GenericConfiguration configuration) {
		
		String url = configuration.getProperties().get("topCatURL", "http://localhost/wcs/resources/store/10152/categoryview/@top");
		int timeout = 5000;
		
		WCService wcService = configuration.getSlingHelper().getService(WCService.class);
		String topCategoriesJSON = wcService.getJSON(url, timeout);
		
		List<Category> topCategories = parseJSON(topCategoriesJSON);
		
		return topCategories;
	}
	
	private List<Category> parseJSON(String json) {
		
		final String JSON_FIELD_TOP_CATEGORIES = "CatalogGroupView";
		final String JSON_FIELD_CATEGORY_NAME = "name";
		
		List<Category> topCategories = new ArrayList<Category>();
		
		if (StringUtils.isNotBlank(json)) {
			try {
				JSONObject jsonObject = new JSONObject(json);
				if (jsonObject.has(JSON_FIELD_TOP_CATEGORIES)){
					JSONArray jsonArray = jsonObject.getJSONArray(JSON_FIELD_TOP_CATEGORIES);
					for(int i=0;i<jsonArray.length();i++){
						Category category = new Category();
						JSONObject jsonCat = jsonArray.getJSONObject(i);
						category.setName(jsonCat.getString(JSON_FIELD_CATEGORY_NAME));						
						topCategories.add(category);
					}
				}
			} catch (JSONException e) {
				log.error(e.getMessage(), e);
			}
		}
		
		return topCategories;
	}
	
}