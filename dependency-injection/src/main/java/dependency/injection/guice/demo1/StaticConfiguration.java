package dependency.injection.guice.demo1;

import com.google.inject.*;
import dependency.injection.common.Account;
import dependency.injection.common.BankService;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

public class StaticConfiguration {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new BankModule());
        injector.getInstance(BankService.class);
//        injector.getInstance(Key.get(BankService.class, MyAnnotation.class));
    }

    static class BankModule extends AbstractModule {
        @Override
        protected void configure() {
            super.configure();
            bind(BankService.class).to(BankServiceImpl.class);
//            bind(BankService.class).toInstance(new BankServiceImpl());
//            bind(Key.get(BankService.class, MyAnnotation.class)).to(BankServiceImpl.class);

//            bind(Key.get(BankService.class, MyAnnotation.class)).to(Key.get(BankService.class));
        }
    }

    @BindingAnnotation
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @interface MyAnnotation {
    }

    @MyAnnotation
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
