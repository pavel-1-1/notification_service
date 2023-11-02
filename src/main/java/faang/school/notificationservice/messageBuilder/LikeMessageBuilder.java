package faang.school.notificationservice.messageBuilder;

import faang.school.notificationservice.client.UserServiceClient;
import faang.school.notificationservice.dto.redis.LikeEventDto;
import faang.school.notificationservice.dto.user.UserDto;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class LikeMessageBuilder extends AbstractMessageBuilder implements MessageBuilder<LikeEventDto, Locale> {

    public LikeMessageBuilder(UserServiceClient userServiceClient) {
        super(userServiceClient);
    }

    @Override
    public String buildMessage(LikeEventDto event, Locale locale) {
        UserDto userDto = getUserServiceClient().getUser(event.getLikeAuthor());
        return String.format(getPatternMessageYaml().get("like").get("start"), userDto.getUsername());
    }
}
