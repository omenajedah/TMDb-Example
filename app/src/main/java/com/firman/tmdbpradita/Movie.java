package com.firman.tmdbpradita;

public class Movie {
    private int id;
    private String title;
    private String overview;
    private String posterImage;
    private String backgroundImage;

    public Movie(int id, String title, String overview, String posterImage, String backgroundImage) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.posterImage = posterImage;
        this.backgroundImage = backgroundImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }
}
