package com.shixzh.java.interview.pattern;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Service provider framework sketch
 * 第1条：考虑用静态工厂方法代替构造器
 * 
 * @author shixiang.zhao
 */
//Noninstantiable class for service registration and access.
public class Services {

    private Services() {// Prevents instantiation

    }

    //Maps service names to services
    private static final Map<String, Provider> providers = new ConcurrentHashMap<String, Provider>();

    public static final String DEFAULT_PROVIDER_NAME = "<def>";

    //Provider registration API
    public static void registerDefaultProvider(Provider p) {
        registerProvider(DEFAULT_PROVIDER_NAME, p);
    }

    public static void registerProvider(String name, Provider p) {
        providers.put(name, p);
    }

    //Service access API
    public static Service newInstance() {
        return newInstance(DEFAULT_PROVIDER_NAME);
    }

    public static Service newInstance(String name) {
        Provider p = providers.get(name);
        if (p == null) {
            throw new IllegalArgumentException("No provider registered with name: " + name);
        }
        return p.newService();
    }

    public static void main(String[] args) {
        //private static final Map<String, List<String>> m = HashMap.newInstance();
        Provider p = null;
        Services.registerDefaultProvider(p);
        Service service = Services.newInstance();
    }
}

//Service interface
interface Service {

}

//Service provider interface
interface Provider {

    Service newService();
}
