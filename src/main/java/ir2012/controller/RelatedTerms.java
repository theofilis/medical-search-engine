package ir2012.controller;

import ir2012.search.KnowledgeBaseTermSercher;
import ir2012.search.QueryText;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Theofilis
 */
public class RelatedTerms extends HttpServlet {
    
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
        
        KnowledgeBaseTermSercher knowledgeBaseTermSercher = new KnowledgeBaseTermSercher(5, getServletContext().getRealPath("WEB-INF/classes/indexwikipedeia"));
        try {
            List<String> synonyms = knowledgeBaseTermSercher.getSynonyms(new QueryText(q));
            
            HashSet<String> data = new HashSet<String>();
            for (String synonym : synonyms) {
                String[] tokens = synonym.split(",");
                for (int i = 0; i < tokens.length; i++) {
                    if (!tokens[i].isEmpty()) {
                        data.add(tokens[i]);
                    }
                }
            }
            
            out.println("<?xml version=\"1.0\"?>");
            out.println("<related>");
            
            for (String term : data) {
                out.println("\t<term>" + term + "</term>");
            }
            
            out.println("</related>");
            
        } catch (Exception ex) {
            Logger.getLogger(RelatedTerms.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
