package utils.jackson;

import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

public class DisablingJsonIgnoreIntrospector extends JacksonAnnotationIntrospector {

    @Override
    public boolean hasIgnoreMarker(final AnnotatedMember m) {
        m.getAllAnnotations().annotations();
        return false;
    }
}
