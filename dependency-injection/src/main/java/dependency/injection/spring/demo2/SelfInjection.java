package dependency.injection.spring.demo2;

import dependency.injection.common.Account;
import dependency.injection.common.BankService;
import dependency.injection.common.Person;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Configuration
@EnableCaching
public class SelfInjection {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationCfgAppCtx = new AnnotationConfigApplicationContext(SelfInjection.class);
        BankService bankService = annotationCfgAppCtx.getBean(BankService.class);

        Person person = new Person("Konstantin", "Labun");
        IntStream.range(1, 10).forEach(i -> bankService.listAccounts(person));
    }

    static class CacheableBankService implements BankService {
//        @Autowired
//        private BankService bankService;

        @Cacheable("cacheName")
        @Override
        public List<Account> listAccounts() {
            System.out.println("listAccounts was called!");
            return Collections.emptyList();
        }

        @Override
        public List<Account> listAccounts(Person person) {
//            return bankService.listAccounts().stream()
//                    .filter(it -> it.getPerson().equals(person))
//                    .collect(Collectors.toList());
            return listAccounts().stream()
                    .filter(it -> it.getPerson().equals(person))
                    .collect(Collectors.toList());
        }
    }

    @Bean
    BankService bankService() {
        return new CacheableBankService();
    }

    @Bean
    CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("cacheName");
    }
}
