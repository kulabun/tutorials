package dependency.injection.spring.demo4;

import dependency.injection.common.Account;
import dependency.injection.common.BankService;
import dependency.injection.common.Person;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Configuration
@ComponentScan(basePackages = "dependency.injection.spring.demo5.model")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AopCgLib {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationCfgAppCtx = new AnnotationConfigApplicationContext(AopCgLib.class);
        BankService bankService = annotationCfgAppCtx.getBean(BankService.class);
        bankService.listAccounts();
    }

    @Component
    @Aspect
    public class LoggingAspect {

        @Pointcut("@target(org.springframework.stereotype.Service)")
        public void allComponents() {};

        @Before("allComponents()")
        public void logBeforeMethodCall(JoinPoint jp) {
            String methodName = jp.getSignature().getName();
            System.out.println("Before " + methodName);
        }

        @After("allComponents()")
        public void logAfterMethodCall(JoinPoint jp) {
            String methodName = jp.getSignature().getName();
            System.out.println("After " + methodName);
        }
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
