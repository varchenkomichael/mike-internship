package com.varchenko.ioc.context;

import com.varchenko.ioc.reflections.ReflectionsApiAgent;
import org.junit.jupiter.api.Test;

class ApplicationConfigApplicationContextTest {

    @Test
    void scan() {
        ReflectionsApiAgent reflectionsApiAgent = new ReflectionsApiAgent("com.varchenko.ioc");
        ApplicationContext app = new ApplicationConfigApplicationContext(reflectionsApiAgent);
        app.getSetOfClassAnnotatedWithComponent();
    }
}