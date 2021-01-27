package ru.otus.appcontainer;

import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;

import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();

    public AppComponentsContainerImpl(Class<?> initialConfigClass) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        processConfig(initialConfigClass);
    }

    private void processConfig(Class<?> configClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        checkConfigClass(configClass);

        Method[] declaredMethods = configClass.getDeclaredMethods(); //Методы

        List<Method> methods = Arrays.stream(declaredMethods)
                .filter(method -> method.isAnnotationPresent(AppComponent.class))
                .sorted(Comparator.comparingInt(method -> method.getAnnotation(AppComponent.class).order()))
                .collect(Collectors.toList());

        Constructor<?> constructor = configClass.getDeclaredConstructor(); //конструктор
        Object instanceConfigClass = constructor.newInstance(); // инстанс класса

        for (Method method : methods) {
            Object instance;
            List<Object> listParametersMethod = new ArrayList<>();
            Type[] parameterMethod = method.getGenericParameterTypes();

            if (parameterMethod.length == 0) {
                instance = method.invoke(instanceConfigClass);
                appComponents.add(instance);
                appComponentsByName.put(getNameClass(method), instance);
            } else {
                for (Type parameters : parameterMethod) {
                    Object parameter = appComponentsByName.get(parameters.toString().split(" ")[1]);
                    if (parameter != null) {
                        listParametersMethod.add(parameter);
                    } else throw new RuntimeException(String.format("Класс \"%s\" не найден", parameters.toString()));
                }
                Object[] arrayParametersMethod = listParametersMethod.toArray();
                instance = method.invoke(instanceConfigClass, arrayParametersMethod);
                appComponents.add(instance);
                appComponentsByName.put(getNameClass(method), instance);
            }
        }
    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) {
        String nameClass = componentClass.toString().split(" ")[1];
        C returnClazz = (C) appComponentsByName.get(nameClass);
        System.out.println("CLASS=" + nameClass);
        if (returnClazz == null) {
            for (Object instance : appComponents) {
                if (nameClass.equals(getNameInstance(instance))) {
                    returnClazz = (C) instance;
                }
            }
        }
        if (returnClazz == null) {
            throw new RuntimeException(String.format("Класс \"%s\" не найден", componentClass.toString()));
        } else return returnClazz;
    }

    @Override
    public <C> C getAppComponent(String componentName) throws ClassNotFoundException {
        Class<?> aClass =  Class.forName(componentName);
        C clazz = (C) appComponentsByName.get(componentName);
        return clazz;
    }

    private String getNameClass(Method method) {
        return method.getReturnType().toString().split(" ")[1];
    }

    private String getNameInstance(Object object) {
        String name = object.getClass().toString().split(" ")[1];
        return name;
    }
}
