package dependency.injection.spring.demo1;

import com.google.j2objc.annotations.AutoreleasePool;
import dependency.injection.common.Account;
import dependency.injection.common.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.StaticApplicationContext;

import java.util.List;

@Configuration
public class JavaConfiguration {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationCfgAppCtx = new AnnotationConfigApplicationContext(JavaConfiguration.class);
        BankService bankService = annotationCfgAppCtx.getBean(BankService.class);
        bankService.listAccounts();
    }

    @Bean
    public String endpoint(){
        return "https://example.com";
    }

    @Bean
    public BankService bankService(@Value("endpoint") String endpoint) {
        return new JavaConfigBankServiceImpl(endpoint);
    }

    static class JavaConfigBankServiceImpl implements BankService {
        private String endpoint;

        public JavaConfigBankServiceImpl(String endpoint) {
            this.endpoint = endpoint;
        }

        @Override
        public List<Account> listAccounts() {
            System.out.println("JavaConfigBankServiceImpl.listAccounts(). Endpoint: " + endpoint);
            return null;
        }
    }
}
