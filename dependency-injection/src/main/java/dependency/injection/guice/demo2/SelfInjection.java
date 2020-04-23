package dependency.injection.guice.demo2;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import dependency.injection.common.BankService;

public class SelfInjection {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new BankModule());
        injector.getInstance(BankService.class);
    }

    static class BankModule extends AbstractModule {
        @Override
        protected void configure() {
            super.configure();
            bind(BankService.class).to(BankServiceImpl.class);
        }
    }

    static class BankServiceImpl implements BankService {
        @Inject
        private BankService delegate;

        public BankServiceImpl() {
            System.out.println("OverrideBankService Instance created");
            System.out.println("Delegate class: " + delegate.getClass().getName());
        }
    }
}
