package com.anzodeg.xslt;

import java.io.File;

import javax.xml.transform.stream.StreamSource;

import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.Serializer;
import net.sf.saxon.s9api.Xslt30Transformer;
import net.sf.saxon.s9api.XsltCompiler;
import net.sf.saxon.s9api.XsltExecutable;

/**
 * Hello world!
 *
 */
public class App 
{
    private static String STYLE_SHEET; // = "C:/Users/t-adegiulio/Documents/xslt/Anzo/test/src/main/java/com/resources/foo.xsl";
    private static String INPUT; // = "C:/Users/t-adegiulio/Documents/xslt/Anzo/test/src/main/java/com/resources/bar.xml";
    private static String OUTPUT; // = "C:/Users/t-adegiulio/Documents/xslt/Anzo/test/src/main/java/com/resources/baz.json";

    public static void main( String[] args )
    {
        STYLE_SHEET = args[0];
        INPUT = args[1];
        OUTPUT = args[2];
        
        try {
            transform();
        } catch (SaxonApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void transform() throws SaxonApiException {
        Processor processor = new Processor(false);
        XsltCompiler compiler = processor.newXsltCompiler();
        XsltExecutable styleSheet = compiler.compile(new StreamSource(new File(STYLE_SHEET)));
        Serializer out = processor.newSerializer(new File(OUTPUT));
        out.setOutputProperty(Serializer.Property.METHOD, "json");
        out.setOutputProperty(Serializer.Property.INDENT, "yes");
        Xslt30Transformer trans = styleSheet.load30();
        trans.transform(new StreamSource(new File(INPUT)), out);
    }
}
