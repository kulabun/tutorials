package dependency.injection.guice.demo3;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.TypeLiteral;
import com.google.inject.spi.InjectionListener;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;
import dependency.injection.common.Account;
import dependency.injection.common.BankService;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

public class Hooks {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new BankModule());
        injector.getInstance(BankService.class);
    }

    static class BankModule extends AbstractModule {
        @Override
        protected void configure() {
            bind(BankService.class).to(BankServiceImpl.class);
//            bindListener(Matchers.any(), new PostConstructListener());
        }
    }

    static class BankServiceImpl implements BankService {

        public BankServiceImpl() {
            System.out.println("Instance created");
        }

        @PostConstruct
        void init() {
            System.out.println("Post construct called!");
        }

        @Override
        public List<Account> listAccounts() {
            System.out.println("BankServiceImpl.listAccounts()");
            return null;
        }
    }

    static class PostConstructListener implements TypeListener {
        @Override
        public <I> void hear(final TypeLiteral<I> typeLiteral, TypeEncounter<I> typeEncounter) {
            typeEncounter.register((InjectionListener<I>) obj -> {
                Arrays.stream(obj.getClass().getDeclaredMethods())
                        .filter(it -> it.getAnnotation(PostConstruct.class) != null)
                        .forEach(method -> {
                            try {
                                method.invoke(obj);
                            } catch (Exception e) {

                            }
                        });
            });
        }
    }
}
