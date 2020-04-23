package dependency.injection.guice.demo4;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.matcher.Matchers;
import dependency.injection.common.Account;
import dependency.injection.common.BankService;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

public class Aop {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new BankModule());
        BankService instance = injector.getInstance(BankService.class);
        instance.listAccounts();
    }

    static class BankModule extends AbstractModule {
        @Override
        protected void configure() {
            super.configure();
            bindInterceptor(Matchers.any(),
                    Matchers.annotatedWith(LogInvocations.class),
                    new LogInvoctionsInterceptor());
            bind(BankService.class).to(BankServiceImpl.class);
        }
    }

    static class BankServiceImpl implements BankService {
        @Override
        @LogInvocations
        public List<Account> listAccounts() {
            System.out.println("BankServiceImpl.listAccounts()");
            return null;
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface LogInvocations {
    }

    static class LogInvoctionsInterceptor implements MethodInterceptor {
        public Object invoke(MethodInvocation invocation) throws Throwable {
            try {
                System.out.println("Before " + invocation.getMethod().getName());
                return invocation.proceed();
            } finally {
                System.out.println("After " + invocation.getMethod().getName());
            }
        }
    }
}
