package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// Definição correta da annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface TabelaAnnotation {
}

// Classe com método anotado
class MyClass {
    @TabelaAnnotation
    public void myMethod() {
        System.out.println("Método TabelaAnnotation!");
    }
}

// Classe que processa a annotation
class Processor {
    public void process(Object obj) throws IllegalAccessException, InvocationTargetException {
        var methods = obj.getClass().getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(TabelaAnnotation.class)) {
                System.out.println("Annotation encontrada! Executando método...");
                method.invoke(obj);
            }
        }
    }
}

// Classe principal
public class Main {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
        var obj = new MyClass();
        new Processor().process(obj);
    }
}
