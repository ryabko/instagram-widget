package ru.kalcho.instagram.response;

/**
 *
 */
public class Data {

    private Images images;

    public Images getImages() {
        return images;
    }

    @Override
    public String toString() {
        return "Data{" +
                "images=" + images +
                '}';
    }
}
