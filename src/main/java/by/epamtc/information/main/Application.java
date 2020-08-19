package by.epamtc.information.main;

import by.epamtc.information.configuration.ObjectFactory;
import by.epamtc.information.configuration.impl.JavaConfig;

import java.util.Map;

public class Application {
    public static ApplicationContext run(String packageToSkan, Map<Class, Class> ifc2ImplClass){
        JavaConfig config = new JavaConfig(packageToSkan, ifc2ImplClass);
        ApplicationContext context = new ApplicationContext(config);
        ObjectFactory objectFactory = new ObjectFactory(context);

        context.setFactory(objectFactory);
        return context;

    }
}
