package by.epamtc.information.main;

import by.epamtc.information.configuration.Config;
import by.epamtc.information.configuration.ObjectFactory;
import by.epamtc.information.configuration.annotation.Singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {
    private ObjectFactory factory;
    private Map<Class, Object> cache = new ConcurrentHashMap<>();
    private Config config;

    public ApplicationContext(Config config) {
        this.config = config;
    }

    public <T> T getObject(Class<T> type){
        if (cache.containsKey(type)){
            return (T) cache.get(type);
        }
        Class<? extends T> implClass = type;
        if (type.isInterface()){
            implClass = config.getImplClass(type);
        }
        T t = factory.createObject(implClass);

        if (implClass.isAnnotationPresent(Singleton.class)){
            cache.put(type, t);
        }
        return t;
    }


    public Config getConfig() {
        return config;
    }

    public void setFactory(ObjectFactory factory) {
        this.factory = factory;
    }
}
