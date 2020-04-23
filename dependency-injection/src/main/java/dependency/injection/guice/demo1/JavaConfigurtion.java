package dependency.injection.guice.demo1;

import com.google.inject.*;
import dependency.injection.common.Account;
import dependency.injection.common.BankService;

import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

public class JavaConfigurtion {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new BankModule());
        injector.getInstance(BankService.class);
//        injector.getInstance(Key.get(BankService.class, DefaultService.class));
    }

    static class BankModule extends AbstractModule {
        @Override
        protected void configure() {
            super.configure();
        }

        @Provides
//        @DefaultService
        BankService bankService() {
            return new BankServiceImpl();
        }
    }

    @BindingAnnotation
    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @interface DefaultService {
    }
    
    static class BankServiceImpl implements BankService {

        public BankServiceImpl() {
            System.out.println("Instance created");
        }

        @Override
        public List<Account> listAccounts() {
            System.out.println("BankServiceImpl.listAccounts()");
            return null;
        }
    }
}
