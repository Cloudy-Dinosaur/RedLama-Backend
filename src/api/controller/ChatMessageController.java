package api.controller;

import api.entity.ChatMessage;
import api.entity.User;
import api.repository.ChatDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;

@RestController
public class ChatMessageController {

    @Autowired
    ChatDataRepository chatDataRepository;


    @PostMapping("/send")
    ChatMessage createChatMessage(@RequestBody ChatMessage chatMessage) {
        chatDataRepository.save(chatMessage);
        return chatMessage;
    }

    @RequestMapping(
            value = "/sendmessage",
            params = {"sender", "receiver", "message"}
    )
    @ResponseBody
    public ChatMessage buildChatMessage(@RequestParam("sender") String sender,
                                   @RequestParam("receiver") String receiver,
                                   @RequestParam("message") String message) {

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSender(sender);
        chatMessage.setReceiver(receiver);
        chatMessage.setText(message);
        chatMessage.setTime(System.currentTimeMillis());
        chatDataRepository.save(chatMessage);
        return chatMessage;

//        return getString(sender, receiver, message, chatMessage.getTime());
    }

    private String getString(String sender, String receiver, String message, long time) {

        return "sender: " + sender +
                "       receiver: " + receiver +
                "       message: " + message +
                "       time: " + time;
    }

//    TODO get timestamp from the specific message out of the database
    private Timestamp getTimestamp(){
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setTime(System.currentTimeMillis());
        long time = chatMessage.getTime();
        return new Timestamp(time);
    }
}