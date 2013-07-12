package ir2012.index;

import ir2012.bean.MedItem;

public class MedCollectionIndexCreator {

    public static void run(String indexdirectory, String medlars) throws Exception {

        // creating the indexer and indexing the items
        MedFilesIndex indexer = new MedFilesIndex(indexdirectory);

        MedCollectioFileParser parser = new MedCollectioFileParser(
                medlars);

        while (parser.hashNext()) {
            MedItem item = parser.next();
            System.out.println("Indexing med document " + item.getId());
            indexer.index(item);
            System.out.println("Successfull indexing document " + item.getId());
        }
        parser.close();

        indexer.close();
    }
}
