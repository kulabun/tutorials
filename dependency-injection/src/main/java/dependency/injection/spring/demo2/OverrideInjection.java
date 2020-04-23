package dependency.injection.spring.demo2;

import dependency.injection.common.Account;
import dependency.injection.common.BankService;
import dependency.injection.common.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Configuration
public class OverrideInjection {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationCfgAppCtx = new AnnotationConfigApplicationContext(OverrideInjection.class);
        BankService bankService = annotationCfgAppCtx.getBean(BankService.class);
        bankService.listAccounts();
    }

    @Component
    public static class DefaultConfigBankServiceImpl implements BankService {
        @Override
        public List<Account> listAccounts() {
            System.out.println("DefaultConfigBankServiceImpl.listAccounts() called");
            return null;
        }

        @Override
        public List<Account> listAccounts(Person person) {
            System.out.println("OverrideConfigBankServiceImpl.listAccounts(Person) called");
            return null;
        }
    }

    @Component
//    @Primary
    public static class OverrideBankServiceImpl implements BankService {
        @Override
        public List<Account> listAccounts() {
            System.out.println("OverrideConfigBankServiceImpl.listAccounts() called");
            return null;
        }

        @Override
        public List<Account> listAccounts(Person person) {
            System.out.println("OverrideConfigBankServiceImpl.listAccounts(Person) called");
            return null;
        }
    }
}
