package ir2012.search;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import util.Parser;

public class QueryFileParser extends Parser<QueryText> {

    public QueryFileParser(String filename) throws FileNotFoundException {
        super(filename);
    }

    @Override
    public QueryText next() throws Exception {
        if (!hashNext()) {
            throw new NoSuchElementException();
        }

        String query_text;
        query_text = scan.next();

        query_text = query_text.replaceAll("\\s*\\d*\\s*\\.W", "");

        return new QueryText(query_text);
    }
}
