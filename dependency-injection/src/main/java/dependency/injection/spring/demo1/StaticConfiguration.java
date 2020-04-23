package dependency.injection.spring.demo1;

import dependency.injection.common.Account;
import dependency.injection.common.BankService;
import org.springframework.context.support.StaticApplicationContext;

import java.util.List;

public class StaticConfiguration {
    public static void main(String[] args) {
        StaticApplicationContext staticCtx = new StaticApplicationContext();
        staticCtx.registerBean(BankService.class, () -> new StaticBankServiceImpl());
//        staticCtx.registerBean(StaticBankServiceImpl.class);
        BankService bankService = staticCtx.getBean(BankService.class);
        bankService.listAccounts();
    }

    static class StaticBankServiceImpl implements BankService {
        @Override
        public List<Account> listAccounts() {
            System.out.println("StaticBankServiceImpl.listAccounts()");
            return null;
        }
    }
}
