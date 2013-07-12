package ir2012.search;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.search.ScoreDoc;

public class KSearchAlgorithm extends SearchAlgorithm {

    private KnowledgeBaseTermSercher synonymsearch;
    private SimpleSearchAlgorithm simplesearchalgorithm;

    public KSearchAlgorithm(String wikipedeiaindexfolder, String indexfolder, int k, int hits) {
        synonymsearch = new KnowledgeBaseTermSercher(k, wikipedeiaindexfolder);
        simplesearchalgorithm = new SimpleSearchAlgorithm(hits, indexfolder);
    }

    @Override
    public ScoreDoc[] search(QueryText q) throws CorruptIndexException, IOException {
        ScoreDoc[] doc = null;
        try {
            List<String> synonyms = synonymsearch.getSynonyms(q);

            StringBuilder builder = new StringBuilder();
            builder.append(q.getQueryText());
            
            for (String synonym : synonyms) {
                synonym = synonym.replaceAll(":", "\\:");
                synonym = synonym.replaceAll("\\(", "");
                synonym = synonym.replaceAll("\\)", "");
                builder.append(synonym);
            }

            q.setQueryText(builder.toString());
            
            doc = simplesearchalgorithm.search(q);
            

        } catch (Exception ex) {
            Logger.getLogger(KSearchAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doc;
    }

    @Override
    public Document getDoc(int docId) throws CorruptIndexException, IOException {
        return simplesearchalgorithm.getDoc(docId);
    }
}
