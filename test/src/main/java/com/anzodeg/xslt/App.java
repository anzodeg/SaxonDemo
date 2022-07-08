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
    private static final String STYLE_SHEET = "C:/Users/t-adegiulio/Documents/xslt/Anzo/test/src/main/java/com/resources/foo.xsl";
    private static final String INPUT = "C:/Users/t-adegiulio/Documents/xslt/Anzo/test/src/main/java/com/resources/bar.xml";
    private static final String OUTPUT = "C:/Users/t-adegiulio/Documents/xslt/Anzo/test/src/main/java/com/resources/baz.json";

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
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
        
        // Serializer out = new Serializer();
        // out.setOutputFile(new File(OUTPUT));
        // out.getProcessor
        
        // .newSerializer(new File(OUTPUT));
        // out.setOutputProperty(Serializer.Property.METHOD, "html");
        // out.setOutputProperty(Serializer.Property.INDENT, "yes");
        // Xslt30Transformer trans = styleSheet.load30();
        // trans.transform(new StreamSource(new File("data/books.xml")), out);

        //     System.out.println("Output written to books.html");
    }
}
