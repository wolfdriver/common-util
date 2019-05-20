package wolfdriver.util.statemachines;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 *
 */
public class PropertyReader {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String read(String path) throws IOException {
        return Files.toString(readResource(path).getFile(), Charsets.UTF_8);
    }

    public static <T> T read(String path, Class<T> klazz) throws IOException {
        return objectMapper.readValue(readResource(path).getFile(), klazz);
    }

    private static Resource readResource(String path) {
        return new ClassPathResource(path);
    }
}
