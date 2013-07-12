package ir2012.search;

import ir2012.bean.KnowledgeBaseTerm;
import java.util.ArrayList;
import java.util.List;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.ScoreDoc;

public class KnowledgeBaseTermSercher {

    private static final String INDEXDIR = "src/java/indexwikipedeia";
    private String indexDir;
    private int k;

    public KnowledgeBaseTermSercher(int k) {
        this(k, INDEXDIR);
    }

    public KnowledgeBaseTermSercher(int k, String filename) {
        this.k = k;
        this.indexDir = filename;
    }

    public List<String> getSynonyms(QueryText querytext) throws Exception {
        List<String> synonyms = new ArrayList<String>();
        SimpleSearchAlgorithm searcher = new SimpleSearchAlgorithm(indexDir);
        searcher.setHitsPerPage(k);
        ScoreDoc[] hits;
        hits = searcher.search(querytext);
        for (int i = 0; i < hits.length; ++i) {
            int docId = hits[i].doc;
            Document d = searcher.getDoc(docId);

            synonyms.add(d.get(KnowledgeBaseTerm.SYNONYM));

        }
        return synonyms;
    }

    public static void main(String[] args) throws Exception {
        KnowledgeBaseTermSercher knowledgeBaseTermSercher = new KnowledgeBaseTermSercher(5);

        System.out.println(knowledgeBaseTermSercher.getSynonyms(new QueryText("blood")));
    }
}
