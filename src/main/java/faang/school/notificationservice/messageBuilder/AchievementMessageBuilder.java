package faang.school.notificationservice.messageBuilder;

import faang.school.notificationservice.client.UserServiceClient;
import faang.school.notificationservice.dto.achievement.UserEventAchievementDto;
import faang.school.notificationservice.dto.user.UserDto;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class AchievementMessageBuilder extends AbstractMessageBuilder implements MessageBuilder<UserEventAchievementDto, Locale> {
    @Getter
    private UserDto userDto;

    public AchievementMessageBuilder(UserServiceClient userServiceClient) {
        super(userServiceClient);
    }

    @Override
    public String buildMessage(UserEventAchievementDto event, Locale locale) {
        userDto = getUserServiceClient().getUser(event.getUserId());

        return String.format(getPatternMessageYaml().get("achievement").get("start"), userDto.getUsername(), event.getAchievement().getTitle()
                , event.getAchievement().getDescription(), event.getAchievement().getRarity());
    }
}
