package ir2012.search;

import ir2012.bean.MedItem;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;

public class QueryText {

    private int id;
    private String query;
    private static int nextID = 1;

    public QueryText(String query_text) {
        this.id = nextID++;
        this.query = query_text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQueryText() {
        return query;
    }

    public void setQueryText(String query) {
        this.query = query;
    }

    public Query getQuery() throws ParseException {
        return new QueryParser(Version.LUCENE_36, MedItem.CONTENT,
                new StandardAnalyzer(Version.LUCENE_36)).parse(this.query);
    }

    @Override
    public String toString() {
        return "Query [id=" + id + ", query=" + query + "]";
    }
}
