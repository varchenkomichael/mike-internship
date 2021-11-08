package com.varchenko.ioc.context;

import java.util.Set;

public interface ApplicationContext {
    <T> T getBean(Class<T> tClass);
    void getSetOfClassAnnotatedWithComponent();
}
