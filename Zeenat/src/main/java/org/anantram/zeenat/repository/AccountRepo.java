package org.anantram.zeenat.repository;

import org.anantram.zeenat.domain.entities.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepo extends CrudRepository<Account, Long> {

}
