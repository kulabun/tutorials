package dependency.injection.spring.demo2;

import dependency.injection.common.Account;
import dependency.injection.common.BankService;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableCaching
//@EnableAspectJAutoProxy(proxyTargetClass = true)
public class JdkProxyAndCgLib {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationCfgAppCtx = new AnnotationConfigApplicationContext(JdkProxyAndCgLib.class);
        BankService bankService = annotationCfgAppCtx.getBean(BankService.class);
        bankService.listAccounts();
    }

    static class CacheableBankService implements BankService {
        @Cacheable("cacheName")
        @Override
        public List<Account> listAccounts() {
            return Collections.emptyList();
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
