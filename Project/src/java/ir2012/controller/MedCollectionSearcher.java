package ir2012.controller;

import ir2012.bean.MedItem;
import ir2012.search.KnowledgeBaseTermSercher;
import ir2012.search.QueryText;
import ir2012.search.SimpleSearchAlgorithm;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.ScoreDoc;

/**
 *
 * @author Theofilis
 */
public class MedCollectionSearcher extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        sendResponse(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        sendResponse(req, resp);
    }

    private void sendResponse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/xml;charset=UTF-8");

        PrintWriter out = resp.getWriter();

        String q = req.getParameter("q");
        int k = Integer.parseInt(req.getParameter("k"));
        boolean automatic = Boolean.parseBoolean(req.getParameter("autoselect"));

        String p = req.getParameter("page");
        int page = Integer.parseInt((p == null) ? "1" : p);

        if (automatic == true) {
            KnowledgeBaseTermSercher knowledgeBaseTermSercher = new KnowledgeBaseTermSercher(k, getServletContext().getRealPath("WEB-INF/classes/indexwikipedeia"));
            List<String> synonyms;
            try {
                synonyms = knowledgeBaseTermSercher.getSynonyms(new QueryText(q));
                HashSet<String> data = new HashSet<String>();
                for (String synonym : synonyms) {
                    String[] tokens = synonym.split(",");
                    for (int i = 0; i < tokens.length; i++) {
                        if (!tokens[i].isEmpty()) {
                            data.add(tokens[i]);
                        }
                    }
                }

                StringBuilder buff = new StringBuilder();
                buff.append(q);
                for (String term : data) {
                    buff.append(" ").append(term).append(" ");
                }

                q = buff.toString();
            } catch (Exception ex) {
                Logger.getLogger(MedCollectionSearcher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        SimpleSearchAlgorithm searcher = new SimpleSearchAlgorithm(1000, getServletContext().getRealPath("WEB-INF/classes/index"));

        try {

            ScoreDoc[] hits = searcher.search(new QueryText(q.toString()));

            int maxpage = (int) Math.ceil(hits.length / 10f);

            out.printf("<documentcollection size=\"%d\" maxpage=\"%d\" page=\"%d\">\n", hits.length, maxpage, page);

            int mp = (page) * 10;
            for (int i = (page - 1) * 10; i < ((mp > hits.length) ? hits.length : mp); ++i) {
                int docId = hits[i].doc;
                Document d = searcher.getDoc(docId);

                String text = d.get(MedItem.CONTENT);
                text = text.replaceAll("<", "&lt;");
                text = text.replaceAll(">", "&gt;");
                out.printf("<document id=\"%d\">%s</document>\n", docId, text);
            }

            out.printf("</documentcollection>");

        } catch (Exception ex) {
            Logger.getLogger(MedCollectionSearcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
