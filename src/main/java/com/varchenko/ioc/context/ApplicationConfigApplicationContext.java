package com.varchenko.ioc.context;

import com.varchenko.ioc.annotations.Component;
import com.varchenko.ioc.reflections.ReflectionAgent;

import java.util.*;

public class ApplicationConfigApplicationContext implements ApplicationContext {

    private final Map<Class<?>, Object> beanByType = new HashMap<>();
    private final ReflectionAgent reflect;

    public ApplicationConfigApplicationContext(ReflectionAgent reflect) {
        this.reflect = reflect;
        getSetOfClassAnnotatedWithComponent();
    }

    @Override
    public <T> T getBean(Class<T> className) {
        return className.cast(beanByType.get(className));
    }

    public void getSetOfClassAnnotatedWithComponent() {
        Set<Class<?>> set = reflect.getClassAnnotatedWith(Component.class);
        for (Class<?> classType : set) {
            createAndPutInstanceInMap(classType);
            System.out.println("Component: " + beanByType);
        }
    }

    private void createAndPutInstanceInMap(Class<?> classType) {
        try {
            Object instance = createBean(classType);
            beanByType.put(classType, instance);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private Object createBean(Class<?> beanType) throws InstantiationException, IllegalAccessException {
        return beanType.newInstance();
    }

}

