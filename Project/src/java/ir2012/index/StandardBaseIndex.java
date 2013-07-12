package ir2012.index;

import java.io.File;
import java.io.IOException;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;

public abstract class StandardBaseIndex<Item> {

    protected IndexWriter writer;

    public StandardBaseIndex(String indexDirectory)
            throws CorruptIndexException, LockObtainFailedException,
            IOException {
        if (writer == null) {
            IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_36, new StandardAnalyzer(Version.LUCENE_36));
            iwc.setOpenMode(OpenMode.CREATE);

            writer = new IndexWriter(
                    FSDirectory.open(new File(indexDirectory)), iwc);
        }
    }

    /**
     * This method will add the items into index
     *
     * @param medItem
     * @throws IOException
     */
    public abstract void index(Item medItem) throws IOException;

    /**
     * Closing the index
     *
     * @throws IOException
     */
    public void close() throws IOException {
        writer.close();
    }
}
