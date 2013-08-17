package ir2012.index;

import ir2012.bean.MedItem;
import java.io.IOException;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.LockObtainFailedException;

public class MedFilesIndex extends StandardBaseIndex<MedItem> {

    public MedFilesIndex(String indexDir) throws CorruptIndexException,
            LockObtainFailedException, IOException {
        super(indexDir);
    }

    @Override
    public void index(MedItem medItem) throws IOException {

        // deleting the item, if already exists
        writer.deleteDocuments(new Term(MedItem.ID, medItem.getId().toString()));

        Document doc = new Document();

        doc.add(new Field(MedItem.ID, medItem.getId().toString(),
                Field.Store.YES, Field.Index.NOT_ANALYZED));
        doc.add(new Field(MedItem.CONTENT, medItem.getContent(),
                Field.Store.YES, Field.Index.ANALYZED));

        // add the document to the index
        writer.addDocument(doc);
    }
}
