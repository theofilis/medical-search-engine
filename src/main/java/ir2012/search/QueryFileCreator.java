package ir2012.search;

import ir2012.bean.MedItem;
import java.io.File;
import java.io.PrintStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.ScoreDoc;

public class QueryFileCreator {

    private static String format = "%-8s%-8s%-8s%-8d%-8f\t%-8s\n";

    public static void run(SearchAlgorithm searcher, String queryfile, String resultfile) throws Exception {

        QueryFileParser g = new QueryFileParser(queryfile);
        PrintStream out = new PrintStream(new File(resultfile));

        ScoreDoc[] hits;
        QueryText querytext;
        System.out.println("Start searching for queries");
        while (g.hashNext()) {
            querytext = g.next();
            
            System.out.printf("Search for query%d\n", querytext.getId());
            hits = searcher.search(querytext);

            for (int i = 0; i < hits.length; ++i) {

                int docId = hits[i].doc;
                Document d = searcher.getDoc(docId);

                out.printf(format, querytext.getId(), "Q0", d.get(MedItem.ID), i, hits[i].score, "lucene");
            }
        }
        System.out.println("Finish with queries");
    }
}
