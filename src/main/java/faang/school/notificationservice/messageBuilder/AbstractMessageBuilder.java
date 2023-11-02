package faang.school.notificationservice.messageBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import faang.school.notificationservice.client.UserServiceClient;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
@Component
@Getter
public abstract class AbstractMessageBuilder {
    private final UserServiceClient userServiceClient;
    private Map<String, Map<String, String>> patternMessageYaml;

    @PostConstruct
    public void patternMessageYaml() {
        try {
            patternMessageYaml = new ObjectMapper(new YAMLFactory()).readValue(new File("src/main/resources/messages.yaml"), Map.class);
        } catch (IOException ignored) {
        }
    }
}
