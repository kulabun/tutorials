package dependency.injection.guice.demo2;

import com.google.inject.*;
import dependency.injection.common.Account;
import dependency.injection.common.BankService;

import javax.inject.Named;
import java.util.List;

public class Injection {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new BankModule());
        BankService service = injector.getInstance(BankService.class);
        service.listAccounts();
    }

    static class BankModule extends AbstractModule {
        @Override
        protected void configure() {
            super.configure();
            bind(BankService.class).to(BankServiceImpl.class);

        }

        @Provides
//        @Named("server-port")
        Integer serverPort() {
            return 8080;
        }
    }

    static class BankServiceImpl implements BankService {
        @Inject
//        @Named("server-port")
        private Integer serverPort;

        @Override
        public List<Account> listAccounts() {
            System.out.println("Server port: " + serverPort);
            return null;
        }
    }
}
