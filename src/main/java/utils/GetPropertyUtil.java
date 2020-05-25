package utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.Properties;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GetPropertyUtil {

    public static final Properties CREDENTIALS = getProperties("credentials.properties");

    @SneakyThrows
    private static Properties getProperties(String propertyFilePath) {
        Properties properties = new Properties();
        properties.load(utils.GetPropertyUtil.class.getClassLoader().getResourceAsStream(propertyFilePath));
        return properties;
    }
}
