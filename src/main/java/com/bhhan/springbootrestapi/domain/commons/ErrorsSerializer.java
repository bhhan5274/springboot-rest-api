package com.bhhan.springbootrestapi.domain.commons;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.validation.Errors;

import java.io.IOException;

/**
 * Created by hbh5274@gmail.com on 2020-01-16
 * Github : http://github.com/bhhan5274
 */

@JsonComponent
public class ErrorsSerializer extends JsonSerializer<Errors> {
    @Override
    public void serialize(Errors errors, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();

        errors.getFieldErrors().stream().forEach(e -> {
            try {
                jsonGenerator.writeStartObject();

                jsonGenerator.writeStringField("field", e.getField());
                jsonGenerator.writeStringField("objectName", e.getObjectName());
                jsonGenerator.writeStringField("code", e.getCode());
                jsonGenerator.writeStringField("defaultMessage", e.getDefaultMessage());
                final Object rejectedValue = e.getRejectedValue();
                if(rejectedValue != null){
                    jsonGenerator.writeStringField("rejectedValue", rejectedValue.toString());
                }

                jsonGenerator.writeEndObject();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        errors.getGlobalErrors().stream().forEach(e -> {
            try {
                jsonGenerator.writeStartObject();

                jsonGenerator.writeStringField("objectName", e.getObjectName());
                jsonGenerator.writeStringField("code", e.getCode());
                jsonGenerator.writeStringField("defaultMessage", e.getDefaultMessage());

                jsonGenerator.writeEndObject();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        jsonGenerator.writeEndArray();
    }
}
