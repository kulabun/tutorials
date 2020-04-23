package dependency.injection.spring.demo2;

import dependency.injection.common.Account;
import dependency.injection.common.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.util.List;

@Configuration
public class Injection {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationCfgAppCtx = new AnnotationConfigApplicationContext(Injection.class);
        BankService bankService = annotationCfgAppCtx.getBean(BankService.class);
        bankService.listAccounts();
    }

    @Component
    static class BankServiceImpl implements BankService {
        @Autowired
        @Qualifier("server-port")
//        @Inject
//        @Resource
        private Integer serverPort;

        @Override
        public List<Account> listAccounts() {
            System.out.println("Server port: " + serverPort);
            return null;
        }

//        @Autowired
//        @Qualifier("server-port")
//        @Inject
//        @Resource
//        void doSomething(Integer serverPort) {
//            System.out.println("Server port: " + serverPort);
//        }
    }

    @Bean("server-port")
    Integer serverPort() {
        return 8080;
    }
}
