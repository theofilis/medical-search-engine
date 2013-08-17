package ir2012.index;

import ir2012.bean.KnowledgeBaseTerm;
import java.io.IOException;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.LockObtainFailedException;

public class KnowledgeBaseIndex extends StandardBaseIndex<KnowledgeBaseTerm> {

    public KnowledgeBaseIndex(String indexDirectory)
            throws CorruptIndexException, LockObtainFailedException,
            IOException {
        super(indexDirectory);
    }

    @Override
    public void index(KnowledgeBaseTerm term) throws IOException {
        // deleting the item, if already exists
        writer.deleteDocuments(new Term(KnowledgeBaseTerm.ID, term.getId().toString()));

        Document doc = new Document();

        doc.add(new Field(KnowledgeBaseTerm.ID, term.getId(), Field.Store.YES,
                Field.Index.NO));
        doc.add(new Field(KnowledgeBaseTerm.SYNONYM, term.getSynonymToString(), Field.Store.YES, Field.Index.NO));
        doc.add(new Field(KnowledgeBaseTerm.IS_A, term.getIs_a(),
                Field.Store.YES, Field.Index.NO));
        doc.add(new Field(KnowledgeBaseTerm.CONTENT, term.getContent(),
                Field.Store.YES, Field.Index.ANALYZED));

        // add the document to the index
        writer.addDocument(doc);
    }
}
