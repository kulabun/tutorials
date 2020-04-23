package dependency.injection.spring.demo5;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Configuration
public class Hooks {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationCfgAppCtx = new AnnotationConfigApplicationContext(Hooks.class);
        annotationCfgAppCtx.start();
        annotationCfgAppCtx.close();
    }

    @Component
    static class Person {
        @PostConstruct
        void init() {
            System.out.println("Post construct called!");
        }

        @PreDestroy
        void terminate() {
            System.out.println("Pre destoy called!");
        }
    }
}
