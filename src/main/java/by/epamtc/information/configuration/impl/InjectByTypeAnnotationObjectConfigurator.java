package by.epamtc.information.configuration.impl;

import by.epamtc.information.configuration.ObjectConfigurator;
import by.epamtc.information.configuration.annotation.InjectByType;
import by.epamtc.information.main.ApplicationContext;
import lombok.SneakyThrows;

import java.lang.reflect.Field;

public class InjectByTypeAnnotationObjectConfigurator implements ObjectConfigurator {
    @SneakyThrows
    @Override
    public void configure(Object t, ApplicationContext context) {
        for (Field declaredField : t.getClass().getDeclaredFields()) {
            if (declaredField.isAnnotationPresent(InjectByType.class)){
                declaredField.setAccessible(true);
                Object object = context.getObject(declaredField.getType());
                declaredField.set(t, object);
            }
        }
    }
}
