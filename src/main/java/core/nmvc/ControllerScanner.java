package core.nmvc;

import core.annotation.Controller;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ControllerScanner {
    Logger log = LoggerFactory.getLogger(ControllerScanner.class);

    Reflections reflections;

    public ControllerScanner(Object... basePackage) {
        this.reflections = new Reflections(basePackage);
    }

    public Map<Class<?>, Object> getControllers() {
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Controller.class);
        return instaniateControllers(annotated);
    }

    Map<Class<?>, Object> instaniateControllers(Set<Class<?>> preInitiatedControllers) {
        Map<Class<?>, Object> controllers = new HashMap<>();
        for (Class<?> clazz : preInitiatedControllers) {
            try {
                controllers.put(clazz, clazz.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                log.error(e.getMessage());
            }
        }
        return controllers;
    }
}
