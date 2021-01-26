package ru.otus.appcontainer;

import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();

    public AppComponentsContainerImpl(Class<?> initialConfigClass) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        processConfig(initialConfigClass);
    }

    private void processConfig(Class<?> configClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        checkConfigClass(configClass);
        Constructor<?> constructor = configClass.getDeclaredConstructor();
        Object applicationConfig = constructor.newInstance();

        Method[] declaredMethods = configClass.getDeclaredMethods();

        Object invoke = declaredMethods[0].invoke(applicationConfig);
        System.out.println("!!!!!!!!!!!!" + invoke);

        System.out.println(Arrays.toString(declaredMethods));
        //Список возвращаемых интерфейсов
        List<Type> collect = Arrays.stream(declaredMethods)
                .filter(method -> method.isAnnotationPresent(AppComponent.class))
                .map(method -> method.getGenericReturnType())
                .collect(Collectors.toList());
        System.out.println(collect);

        //Список возвращаемых интерфейсов
        List<Type> collect1 = Arrays.stream(declaredMethods)
                .filter(method -> method.isAnnotationPresent(AppComponent.class))
                .map(method -> method.getReturnType())
                .collect(Collectors.toList());
        System.out.println(collect1);

    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) {


        return null;
    }

    @Override
    public <C> C getAppComponent(String componentName) {


        return null;
    }
}
