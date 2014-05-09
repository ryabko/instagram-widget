package ru.kalcho.instagram.response;

/**
 *
 */
public class Images {

    private Image lowResolution;
    private Image thumbnail;
    private Image standardResolution;

    @Override
    public String toString() {
        return "Images{" +
                "lowResolution=" + lowResolution +
                ", thumbnail=" + thumbnail +
                ", standardResolution=" + standardResolution +
                '}';
    }
}
