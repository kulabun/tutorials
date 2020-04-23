package dependency.injection.spring.demo4;

import dependency.injection.common.Account;
import dependency.injection.common.BankService;
import dependency.injection.common.Person;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.lang.reflect.Proxy;
import java.util.List;

@Configuration
@ComponentScan(basePackages = "dependency.injection.spring.demo4.model")
public class JdkProxy {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationCfgAppCtx = new AnnotationConfigApplicationContext(JdkProxy.class);
        BankService bankService = annotationCfgAppCtx.getBean(BankService.class);
        bankService.listAccounts();
    }

    @Bean
    BeanPostProcessor customBeanPostProcessor() {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                if (!(bean instanceof BankService)) return bean;
                System.out.println("before bean initialization");

                return Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{BankService.class},
                        (o, method, objects) -> {
                            System.out.println(String.format("Proxy was called before %s!", method.getName()));
                            method.invoke(bean);
                            System.out.println(String.format("Proxy was called after %s!", method.getName()));
                            return null;
                        });
            }

            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                System.out.println("after bean initialization");
                return bean;
            }
        };
    }

    @Service
    public class BankServiceImpl implements BankService {
        @Override
        public List<Account> listAccounts() {
            System.out.println("AnnotationConfigBankServiceImpl.listAccounts() called");
            return null;
        }

        @Override
        public List<Account> listAccounts(Person person) {
            System.out.println("AnnotationConfigBankServiceImpl.listAccounts(Person) called");
            return null;
        }
    }
}
