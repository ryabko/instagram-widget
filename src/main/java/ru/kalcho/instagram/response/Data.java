package ru.kalcho.instagram.response;

/**
 *
 */
public class Data {

    private Images images;

    private Caption caption;

    public Images getImages() {
        return images;
    }

    public Caption getCaption() {
        return caption;
    }

    @Override
    public String toString() {
        return "Data{" +
                "images=" + images +
                ", caption=" + caption +
                '}';
    }
}
