package com.varchenko.ioc.reflections;

import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.Set;

public class ReflectionsApiAgent implements ReflectionAgent {
    private final Reflections reflections;

    public ReflectionsApiAgent(Object...namePackage) {
        reflections = new Reflections(namePackage);
    }

    @Override
    public Set<Class<?>> getClassAnnotatedWith(Class<? extends Annotation> annotation) {
        return reflections.getTypesAnnotatedWith(annotation);
    }
}