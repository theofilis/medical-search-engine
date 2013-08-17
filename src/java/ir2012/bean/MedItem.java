package ir2012.bean;

public class MedItem {

    public static final String CONTENT = "content";
    public static final String ID = "id";
    private String content;
    private Long id;

    public MedItem(String content, Long id) {
        super();
        this.content = content;
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
