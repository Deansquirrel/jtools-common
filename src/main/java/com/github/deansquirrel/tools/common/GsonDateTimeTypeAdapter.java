package com.github.deansquirrel.tools.common;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public class GsonDateTimeTypeAdapter extends TypeAdapter<Date> {
    @Override
    public void write(JsonWriter jsonWriter, Date date) throws IOException {
        if(!ValidateTool.isEmpty(date)) {
            jsonWriter.value(DateTool.GetDateTimeStr(date));
        }
    }

    @Override
    public Date read(JsonReader jsonReader) throws IOException {
        try {
            String str = jsonReader.nextString();
            if(ValidateTool.isEmpty(str)) {
                return null;
            }
            return DateTool.ParseDateTimeStr(str);
        } catch (ParseException e) {
            throw new IOException(e);
        }
    }
}
