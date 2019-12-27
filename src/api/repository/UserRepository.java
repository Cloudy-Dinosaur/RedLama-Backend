package api.repository;

import api.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface UserRepository extends CrudRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.userName = ?1")
    ArrayList<User> findUserByUserName(String userName);

    @Query("SELECT u FROM User u WHERE u.emailAddress = ?1")
    ArrayList<User> findUserByEmailAddress(String emailAddress);

}