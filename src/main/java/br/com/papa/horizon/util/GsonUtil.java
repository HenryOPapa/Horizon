package br.com.papa.horizon.util;



import br.com.papa.horizon.entity.Equipamento;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {

	
	
	public Gson treeView(){
		Gson gson = new GsonBuilder()
				.setDateFormat("dd-MM-yyyy HH:mm:ss")
				.serializeNulls()
			    .setExclusionStrategies(new ExclusionStrategy() {

			        public boolean shouldSkipClass(Class<?> clazz) {
			            return false;
			        }

			        /**
			          * Custom field exclusion goes here
			          */	
			        public boolean shouldSkipField(FieldAttributes f) {
			            return (f.getDeclaredClass() == Equipamento.class && f.getName().equals("cliente"));
			        }

			     })
			    /**
			      * Use serializeNulls method if you want To serialize null values 
			      * By default, Gson does not serialize null values
			      */
			    .serializeNulls()
			    .setDateFormat("dd-MM-yyyy HH:mm:ss")
			    .create();
		
		return gson;
	}
	 
}