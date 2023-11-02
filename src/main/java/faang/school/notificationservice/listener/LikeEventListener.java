package faang.school.notificationservice.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import faang.school.notificationservice.client.UserServiceClient;
import faang.school.notificationservice.dto.redis.LikeEventDto;
import faang.school.notificationservice.dto.user.UserDto;
import faang.school.notificationservice.messageBuilder.MessageBuilder;
import faang.school.notificationservice.messageBuilder.LikeMessageBuilder;
import faang.school.notificationservice.sender.NotificationService;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component

public class LikeEventListener extends AbstractEventListener<LikeEventDto, String> implements MessageListener {

    public LikeEventListener(ObjectMapper objectMapper, Map<UserDto.PreferredContact, NotificationService> notifications, Map<Class<?>, MessageBuilder> messageBuilders, UserServiceClient userServiceClient) {
        super(objectMapper, notifications, messageBuilders, userServiceClient);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        LikeEventDto likeEventDto = getEvent(getMessageBody(message), LikeEventDto.class);

        UserDto contentAuthor = (likeEventDto.getPostId() != 0) ?
                userServiceClient.getUser(likeEventDto.getPostAuthor()) :
                (likeEventDto.getCommentAuthor() != 0) ?
                        userServiceClient.getUser(likeEventDto.getCommentAuthor()) :
                        new UserDto();

        sendNotification(contentAuthor.getId()
                , "New like"
                , getMessage(LikeMessageBuilder.class
                        , contentAuthor.getLocale().getCountry()
                        , likeEventDto));
    }
}
