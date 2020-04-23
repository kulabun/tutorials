package dependency.injection.spring.demo1;

import dependency.injection.common.Account;
import dependency.injection.common.BankService;
import dependency.injection.common.Person;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Configuration
@ComponentScan(basePackages = "dependency.injection.spring.demo1.model",
        excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, classes = Service.class)
)
public class AnnotationConfiguration {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationCfgAppCtx = new AnnotationConfigApplicationContext(AnnotationConfiguration.class);
        BankService bankService = annotationCfgAppCtx.getBean(BankService.class);
        bankService.listAccounts();
    }

    @Component
    public static class AnnotationConfigBankServiceImpl implements BankService {
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
