package org.anantram.zeenat.repository;

import org.anantram.zeenat.domain.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepo extends CrudRepository<Account, Integer> {

}
