package dependency.injection.guice.demo2;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.multibindings.OptionalBinder;
import dependency.injection.common.BankService;

public class OverrideInjection {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new BankModuleOne(), new BankModuleTwo());
        injector.getInstance(BankService.class);
    }

    static class BankModuleOne extends AbstractModule {
        @Override
        protected void configure() {
            super.configure();
            OptionalBinder.newOptionalBinder(binder(), BankService.class)
                    .setDefault()
                    .to(DefaultBankServiceImpl.class);
        }
    }

    static class BankModuleTwo extends AbstractModule {
        @Override
        protected void configure() {
            super.configure();
            OptionalBinder.newOptionalBinder(binder(), BankService.class)
                    .setBinding()
                    .to(OverrideBankServiceImpl.class);
        }
    }

    static class DefaultBankServiceImpl implements BankService {
        public DefaultBankServiceImpl() {
            System.out.println("DefaultBankService Instance created");
        }
    }

    static class OverrideBankServiceImpl implements BankService {
        public OverrideBankServiceImpl() {
            System.out.println("OverrideBankService Instance created");
        }
    }
}
