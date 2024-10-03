package com.project_spring.Admin.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.Buffer;

public class HttpUtil {
    private String value;

    public HttpUtil() {
    }

    public HttpUtil(String value) {
        this.value = value;
    }

    public <T> T toModel(Class<T> tClass) {
        try {
            return new ObjectMapper().readValue(value, tClass);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static HttpUtil of(BufferedReader bufferedReader) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String line;
            while( (line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        finally {
            if(bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return new HttpUtil(stringBuilder.toString());
    }
}
