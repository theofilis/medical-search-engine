package ir2012.bean;

import java.util.ArrayList;
import java.util.List;

public class KnowledgeBaseTerm {

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String SYNONYM = "synonym";
    public static final String DEF = "def";
    public static final String IS_A = "is_a";
    public static final String CONTENT = "content";
    private String id;
    private String name;
    private String def;
    private String is_a;
    private List<String> synonym;

    public KnowledgeBaseTerm() {
        this("", "", "", "", new ArrayList<String>());
    }

    public KnowledgeBaseTerm(String id, String name, String def, String is_a,
            List<String> list) {
        super();
        this.id = id;
        this.name = name;
        this.def = def;
        this.is_a = is_a;
        this.synonym = list;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }

    public String getIs_a() {
        return is_a;
    }

    public void setIs_a(String is_a) {
        this.is_a = is_a;
    }

    public List<String> getSynonym() {
        return synonym;
    }

    public void setSynonym(List<String> synonym) {
        this.synonym = synonym;
    }

    public String getContent() {
        return this.name + " " + this.def;
    }

    public String getSynonymToString() {
        StringBuilder buff = new StringBuilder();

        for (String syn : this.synonym) {
            buff.append(syn).append(",");
        }

        return buff.toString();
    }

    @Override
    public String toString() {
        return "KnowledgeBaseTerm [id=" + id + ", name=" + name + ", def="
                + def + ", is_a=" + is_a + ", getSynonymToString()="
                + getSynonymToString() + "]";
    }
}
