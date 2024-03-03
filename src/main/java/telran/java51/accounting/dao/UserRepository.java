package telran.java51.accounting.dao;

import org.springframework.data.repository.CrudRepository;

import telran.java51.accounting.model.UserAccount;

public interface UserRepository extends CrudRepository<UserAccount, String>{

}
