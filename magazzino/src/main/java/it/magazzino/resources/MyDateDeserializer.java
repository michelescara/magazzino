package it.magazzino.resources;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class MyDateDeserializer extends JsonDeserializer<Date> {

    private SimpleDateFormat dateFormat = new SimpleDateFormat(
            "dd/MM/yyyy");

    @Override
    public Date deserialize(JsonParser paramJsonParser,
            DeserializationContext paramDeserializationContext)
            throws IOException, JsonProcessingException {
        String str = paramJsonParser.getText().trim();
        try {
            return (Date) dateFormat.parse(str);
        } catch (ParseException e) {
            // Handle exception here
        }
        return (Date) paramDeserializationContext.parseDate(str);
    }
}
