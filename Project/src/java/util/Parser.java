package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 * @param <Item> 
 * @author Theofilis George
 */
public abstract class Parser<Item> {

    /**
     * 
     */
    protected Scanner scan;

        /**
         * 
         * @param filename
         * @throws FileNotFoundException
         */
        protected Parser(String filename) throws FileNotFoundException {
		scan = new Scanner(new File(filename));
		scan.useDelimiter("\\.I");
	}

        /**
         * 
         * @return
         * @throws Exception
         */
        public abstract Item next() throws Exception;

        /**
         * 
         * @return
         */
        public boolean hashNext() {
		return scan.hasNext();
	}

        /**
         * 
         */
        public void close() {
		this.scan.close();
	}
}
