package ir2012.search;

import java.io.IOException;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.search.ScoreDoc;

public abstract class SearchAlgorithm {

    public abstract ScoreDoc[] search(QueryText q) throws CorruptIndexException, IOException;

    public abstract Document getDoc(int docId) throws CorruptIndexException, IOException;
}
