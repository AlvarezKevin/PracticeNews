package example.alvarezkevin.udacitynews.data;

/**
 * Created by Kevin on 4/3/2017.
 */

public class Article {
    private String id;
    private String sectionId;
    private String sectionName;
    private String webPublicationDate;
    private String title;
    private String url;

    public Article(String id, String sectionId, String sectionName, String webPublicationDate, String title, String url) {
        this.id = id;
        this.sectionId = sectionId;
        this.sectionName = sectionName;
        this.webPublicationDate = webPublicationDate;
        this.title = title;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getSectionId() {
        return sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getWebPublicationDate() {
        return webPublicationDate;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
