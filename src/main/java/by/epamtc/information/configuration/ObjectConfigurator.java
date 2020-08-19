package by.epamtc.information.configuration;

import by.epamtc.information.main.ApplicationContext;

public interface ObjectConfigurator {
    void configure(Object t, ApplicationContext context);
}
