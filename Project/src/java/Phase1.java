import ir2012.index.MedCollectionIndexCreator;
import ir2012.search.QueryFileCreator;
import ir2012.search.SimpleSearchAlgorithm;

/**
 * args[0] index folder path 
 * args[1] medlar file collection 
 * args[2] query file
 * args[3] query result file
 *
 * @author Theofilis
 */
public class Phase1 {

    public static void main(String args[]) throws Exception {
        // 1. Create the index
        MedCollectionIndexCreator.run(args[0], args[1]);

        // 2. Make the queries
        QueryFileCreator.run(new SimpleSearchAlgorithm(1000, args[0]), args[2], args[3]);
    }
}
