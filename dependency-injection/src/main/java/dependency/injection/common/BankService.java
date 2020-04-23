package dependency.injection.common;

import java.util.List;

public interface BankService {
    default List<Account> listAccounts() {
        return null;
    }

    default List<Account> listAccounts(Person person) {
        return null;
    }
}
