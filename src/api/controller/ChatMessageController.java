package api.controller;

import api.entity.ChatMessage;
import api.repository.ChatDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
public class ChatMessageController {

    @Autowired
    ChatDataRepository chatDataRepository;

    @PostMapping("/send")
    ChatMessage send(@RequestBody ChatMessage chatMessage) {
        chatMessage.setTime(System.currentTimeMillis());
        chatMessage.setSeen(false);
        chatDataRepository.save(chatMessage);
        return chatMessage;
    }

    @RequestMapping(
            value = "/getmessages",
            params = {"sender", "receiver"}
    )
    @ResponseBody
    public Object returnChatmessages(@RequestParam("sender") String sender,
                                     @RequestParam("receiver") String receiver) {

        return chatDataRepository.allMessagesForReceiverFromSender(sender, receiver);
    }

    @RequestMapping(
            value = "/getmessagesfromsender",
            params = {"sender"}
    )
    @ResponseBody
    public Object returnChatmessagesTest(@RequestParam("sender") String sender) {
        return chatDataRepository.findMessageBySender(sender);
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
    }
}
