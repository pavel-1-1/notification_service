package faang.school.notificationservice.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import faang.school.notificationservice.client.UserServiceClient;
import faang.school.notificationservice.dto.redis.MentorshipAcceptedEventDto;
import faang.school.notificationservice.dto.user.UserDto;
import faang.school.notificationservice.messageBuilder.MentorshipMessageBuilder;
import faang.school.notificationservice.messageBuilder.MessageBuilder;
import faang.school.notificationservice.sender.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Map;

@Component
@Slf4j
public class MentorshipAcceptedEventListener extends AbstractEventListener<MentorshipAcceptedEventDto, Locale> implements MessageListener {

    public MentorshipAcceptedEventListener(ObjectMapper objectMapper, Map<UserDto.PreferredContact, NotificationService> notifications, Map<Class<?>, MessageBuilder> messageBuilders, UserServiceClient userServiceClient) {
        super(objectMapper, notifications, messageBuilders, userServiceClient);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        MentorshipAcceptedEventDto eventDto = getEvent(getMessageBody(message), MentorshipAcceptedEventDto.class);

        UserDto author = userServiceClient.getUser(eventDto.getRequesterId());
        UserDto receiver = userServiceClient.getUser(eventDto.getReceiverId());
        eventDto.setAuthorName(author.getUsername());
        eventDto.setReceiverName(receiver.getUsername());

        String messageText = getMessage(MentorshipMessageBuilder.class, Locale.UK, eventDto);

        sendNotification(author.getId(), "Mentorship", messageText);
        log.info("Sending notifications for event: {}", eventDto);
    }
}
