package dependency.injection.spring.demo5;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.*;
import org.springframework.stereotype.Component;

@Configuration
public class LifeCycleListeners {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationCfgAppCtx = new AnnotationConfigApplicationContext(LifeCycleListeners.class);
        annotationCfgAppCtx.start();
        annotationCfgAppCtx.stop();
        annotationCfgAppCtx.close();
    }

    @Component
    public class ContextEventListener {
        @EventListener(classes = {
                ContextStartedEvent.class,
                ContextStoppedEvent.class,
                ContextClosedEvent.class
        })
        public void handleEvents(ApplicationContextEvent event) {
            System.out.println("Event received: " + event.getClass().getName());
        }
    }
}
