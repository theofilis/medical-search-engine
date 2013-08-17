package ir2012.index;

import ir2012.bean.KnowledgeBaseTerm;
import java.io.File;
import java.util.Iterator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class KnowledgeBaseParser {

    private Document doc;
    private Iterator<Element> iterator;

    @SuppressWarnings("unchecked")
    public KnowledgeBaseParser(String xmlfilename) throws DocumentException {
        SAXReader xmlReader = new SAXReader();
        this.doc = xmlReader.read(new File(xmlfilename));

        org.dom4j.Element root = this.doc.getRootElement();
        this.iterator = root.elementIterator();
    }

    public KnowledgeBaseTerm next() {
        KnowledgeBaseTerm term = new KnowledgeBaseTerm();

        Element point = iterator.next();

        @SuppressWarnings("unchecked")
        Iterator<Element> childrens = point.elementIterator();

        while (childrens.hasNext()) {
            Element child = childrens.next();
            String text = child.getText();

            if (child.getName().equalsIgnoreCase(KnowledgeBaseTerm.ID)) {
                term.setId(text);
            } else if (child.getName().equalsIgnoreCase(KnowledgeBaseTerm.DEF)) {
                term.setDef(text);
            } else if (child.getName().equalsIgnoreCase(KnowledgeBaseTerm.IS_A)) {
                term.setIs_a(text);
            } else if (child.getName().equalsIgnoreCase(KnowledgeBaseTerm.NAME)) {
                term.setName(text);
            } else if (child.getName().equalsIgnoreCase(
                    KnowledgeBaseTerm.SYNONYM)) {
                term.getSynonym().add(text);
            }

        }

        return term;
    }

    public boolean hashNext() {
        return iterator.hasNext();
    }
}
