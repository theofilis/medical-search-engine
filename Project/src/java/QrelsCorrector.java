import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class QrelsCorrector {

    private static PrintStream out;

    public static void main(String[] args) throws FileNotFoundException {

        String fileIn, fileOut;

        if (args.length == 2) {
        } else {
            System.err.println("Wrong number of arguments");
        }
        
        fileIn = args[0];
        fileOut = args[1];

        out = new PrintStream(new File(fileOut));
        QrelsParser parser = new QrelsParser(fileIn);
        while (parser.hashNext()) {
            String[] w = parser.next();
            out.printf("%-8s%-8s%-8s%-8s\n", w[0], "Q0", w[1], "1");
        }

        parser.close();
    }

    private static class QrelsParser {

        private Scanner scan;

        public QrelsParser(String qrels) {
            try {
                scan = new Scanner(new File(qrels));
            } catch (FileNotFoundException e) {
                System.err.println("Document not Found");
            }
        }

        String[] next() {
            return scan.nextLine().split("\\s+");
        }

        boolean hashNext() {
            return scan.hasNext();
        }

        void close() {
            this.scan.close();
        }
    }
}
