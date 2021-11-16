package utils.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static utils.ReflectionUtils.getFieldValue;

public class JacksonCustomSerializers {
    public static class FileInputStreamSerializer extends JsonSerializer<FileInputStream> {
        @Override
        public void serialize(FileInputStream value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            Map<String, Object> map = new HashMap<>();
            String fieldName = "path";
            Object path = getFieldValue(FileInputStream.class, value, fieldName);
            map.put(fieldName, path);
            gen.writeObject(map);
        }
    }
}
