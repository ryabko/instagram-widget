package ru.kalcho.instagram.response;

import java.util.Arrays;

/**
 *
 */
public class Response {

    private Meta meta;

    private Data[] data;

    public Meta getMeta() {
        return meta;
    }

    public Data[] getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "meta=" + meta +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
