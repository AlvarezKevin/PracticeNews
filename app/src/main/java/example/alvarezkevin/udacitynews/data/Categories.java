package example.alvarezkevin.udacitynews.data;

/**
 * Created by Kevin on 4/4/2017.
 */

public class Categories {
    private String id;
    private String title;
    private String webUrl;
    private String apiUrl;

    public Categories(String id, String title, String webUrl, String apiUrl) {
        this.id = id;
        this.title = title;
        this.webUrl = webUrl;
        this.apiUrl = apiUrl;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getApiUrl() {
        return apiUrl;
    }
}
