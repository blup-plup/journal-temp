package com.TheJournal.Login.Repositories;

import com.TheJournal.Login.Entities.UserSession;
import org.springframework.data.repository.CrudRepository;

public interface UserSessionRepository extends CrudRepository<UserSession,Integer> {
}
