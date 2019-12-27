package api.repository;

import api.entity.ChatMessage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ChatDataRepository extends CrudRepository<ChatMessage, Integer> {
    @Query("SELECT u FROM ChatMessage u WHERE u.receiver = ?1")
    Iterable<ChatMessage> findMessageByReceiver(String userName);

    @Query("SELECT u FROM ChatMessage u WHERE u.sender = ?1")
    Iterable<ChatMessage> findMessageBySender(String userName);

}