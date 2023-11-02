package faang.school.notificationservice.messageBuilder;

import faang.school.notificationservice.dto.notification.NotificationData;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class MentorshipMessageBuilder implements MessageBuilder<NotificationData, Locale> {
    private final MessageSource messageSource;

    public String getPredefinedMessage(NotificationData data, Locale locale) {
        return messageSource.getMessage("mentorship.accepted", new Object[]{data.getFrom()}, locale);
    }

    @Override
    public String buildMessage(NotificationData data, Locale locale) {
        return getPredefinedMessage(data, locale);
    }
}
