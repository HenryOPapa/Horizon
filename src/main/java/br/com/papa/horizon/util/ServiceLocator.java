package br.com.papa.horizon.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.naming.Context;
import javax.sql.DataSource;

public class ServiceLocator {

    private static final Map<String, Object> services = new ConcurrentHashMap<String, Object>();

    private static ServiceLocator instance;
    

    public static ServiceLocator getInstance() {
    	if(instance==null){
    		instance = new ServiceLocator();
    	}
    	return instance;
	}

	public static void setInstance(ServiceLocator instance) {
		ServiceLocator.instance = instance;
	}

	public static Context getContext() {
		return context;
	}

	public static void setContext(Context context) {
		ServiceLocator.context = context;
	}

	public static Map<String, Object> getServices() {
		return services;
	}

	private static Context context;

    static {
        try {
        	
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public DataSource getDataSource(String name) throws Exception {
        if (name == null || name.length() <= 0)
            throw new IllegalArgumentException("name");

        if (services.containsKey(name))
            return (DataSource) services.get(name);

        DataSource ds = (DataSource) context.lookup(name);

        services.put(name, ds);
        return ds;
    }
}
