package dependency.injection.guice.demo3;

import com.google.inject.*;
import dependency.injection.common.Account;
import dependency.injection.common.BankService;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

public class Scopes {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new BankModule());
        injector.getInstance(BankService.class);
        injector.getInstance(BankService.class);
        injector.getInstance(BankService.class);
    }

    static class BankModule extends AbstractModule {
        @Override
        protected void configure() {
            super.configure();
//            bind(BankService.class).toProvider(() -> new BankServiceImpl());
        }

        @Provides
//        @Singleton
        BankService bankService() {
            return new BankServiceImpl();
        }
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
