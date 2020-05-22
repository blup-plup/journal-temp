package com.TheJournal.Login.Repositories;

import com.TheJournal.Login.Entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer>{

    public User findByEmail(String email);


}
