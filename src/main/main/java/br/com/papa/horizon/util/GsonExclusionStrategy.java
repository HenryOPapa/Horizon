package br.com.papa.horizon.util;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonExclusionStrategy implements ExclusionStrategy{
	
	
	 private final Class<?> typeToExclude;

	public GsonExclusionStrategy(Class<?> entity){
	        this.typeToExclude = entity;
	}
	 
	@Override
	public boolean shouldSkipClass(Class<?> entity) {
		return ( this.typeToExclude != null && this.typeToExclude == entity )
                || entity.getAnnotation(GsonExclude.class) != null;
	}

	@Override
	public boolean shouldSkipField(FieldAttributes field) {
		return field.getAnnotation(GsonExclude.class) != null;
	}
	
	public static Gson createGsonFromBuilder( ExclusionStrategy exs ){
	    GsonBuilder gsonbuilder = new GsonBuilder();
	    gsonbuilder.setExclusionStrategies(exs);
	    return gsonbuilder.serializeNulls().create();
	}

}
