package com.varchenko.ioc.reflections;


import java.lang.annotation.Annotation;
import java.util.Set;

public interface ReflectionAgent {
    Set<Class<?>> getClassAnnotatedWith(Class<? extends Annotation> annotation);
}
