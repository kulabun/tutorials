package dependency.injection.spring.demo2;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Configuration
@ComponentScan(basePackages = "dependency.injection.spring.demo4.model")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class CglibOom {

    static class Person {
    }

    interface IPerson {
    }

    public static void main(String[] args) {
        int counter = 0;
        while (true) {
            createCglibSubClass();
//            createJdkProxy();

            counter++;
            System.out.println(counter + ": class successfully created");
        }
    }

    private static Object createJdkProxy() {
        return Proxy.newProxyInstance(CglibOom.class.getClassLoader(), new Class[]{IPerson.class}, (o, method, objects) -> null);
    }

    private static void createCglibSubClass() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Person.class);
        enhancer.setUseCache(false);
        enhancer.setCallback((MethodInterceptor) (obj, method, objects, proxy) -> proxy.invoke(obj, new String[]{}));
        enhancer.create();
    }
}
