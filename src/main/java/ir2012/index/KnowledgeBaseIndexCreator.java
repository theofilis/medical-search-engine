package ir2012.index;

import ir2012.bean.KnowledgeBaseTerm;

/**
 *
 * @author Theofilis
 */
public class KnowledgeBaseIndexCreator {

    /**
     *
     * @param indexdirectory
     * @throws Exception
     */
    public static void run(String indexdirectory, String basedirectory) throws Exception {

        KnowledgeBaseIndex indexer = new KnowledgeBaseIndex(indexdirectory);

        KnowledgeBaseParser parser = new KnowledgeBaseParser(
                basedirectory);

        while (parser.hashNext()) {
            KnowledgeBaseTerm item = parser.next();
            System.out.println("Indexing knowledge base term " + item.getId());
            indexer.index(item);
            System.out.println("Successfull indexing document " + item.getId());
        }

        indexer.close();
    }

    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        KnowledgeBaseIndexCreator.run("src/java/indexwikipedeia", "src/java/data/Knowledge_base.xml");
    }
}
