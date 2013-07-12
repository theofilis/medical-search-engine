package ir2012.index;

import ir2012.bean.MedItem;
import java.io.FileNotFoundException;
import util.Parser;

public class MedCollectioFileParser extends Parser<MedItem> {

    private static Long id = (long) 1;

    public MedCollectioFileParser(String filename) throws FileNotFoundException {
        super(filename);
    }

    @Override
    public MedItem next() throws Exception {
        String document = scan.next();
        document = document.replaceAll("\\s*\\d*\\s*\\.W", "");
        MedItem item = new MedItem(document, id++);
        return item;
    }
}
