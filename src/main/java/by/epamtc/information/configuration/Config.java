package by.epamtc.information.configuration;


import org.reflections.Reflections;

public interface Config {

    <T> Class<? extends T> getImplClass(Class<T> ifc);

    Reflections getScanner();
}
