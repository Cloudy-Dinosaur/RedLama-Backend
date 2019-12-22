package jchat.repository;

import jchat.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.userName = ?1")
    Iterable<User> findUserByUserName(String userName);

    @Query("SELECT u FROM User u WHERE u.emailAddress = ?1")
    Iterable<User> findUserByEmailAddress(String emailAddress);
}
