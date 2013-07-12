package ir2012.search;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class SimpleSearchAlgorithm extends SearchAlgorithm {

    private int hitsPerPage;
    private Directory index;
    private IndexSearcher searcher;

    public SimpleSearchAlgorithm(String indexDir) {
        hitsPerPage = 200;
        try {
            this.index = FSDirectory.open(new File(indexDir));
        } catch (IOException e) {
        }
    }

    public SimpleSearchAlgorithm(int hitsPerPage, String indexDir) {
        this.hitsPerPage = hitsPerPage;
        try {
            this.index = FSDirectory.open(new File(indexDir));
        } catch (IOException e) {
        }
    }

    public int getHitsPerPage() {
        return hitsPerPage;
    }

    public void setHitsPerPage(int hitsPerPage) {
        this.hitsPerPage = hitsPerPage;
    }

    public Directory getIndex() {
        return index;
    }

    public void setIndex(Directory index) {
        this.index = index;
    }

    @Override
    public ScoreDoc[] search(QueryText q) throws CorruptIndexException, IOException {
        IndexReader reader = IndexReader.open(index);
        searcher = new IndexSearcher(reader);

        TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
        try {
            searcher.search(q.getQuery(), collector);
        } catch (ParseException ex) {
            Logger.getLogger(SimpleSearchAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return collector.topDocs().scoreDocs;
    }

    @Override
    public Document getDoc(int docID) throws CorruptIndexException, IOException {
        return searcher.doc(docID);
    }
}
