package com.dheeraj.learning.afg.tikamimetypes;

import org.apache.tika.Tika;
import java.io.File;

public class TestTikaParser {

    public TestTikaParser(int a){

    }

    public static void main(String[] args) throws Exception {
        TestTikaParser
        File file = new File("C:\\Image.txt");

        /*//parameters of parse() method
        Parser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(file);
        ParseContext context = new ParseContext();

        //Parsing the given file
        parser.parse(inputstream, handler, metadata, context);


        String[] metadataNamesb4 = metadata.names();

        for(String name : metadataNamesb4) {
            System.out.println(name + ": " + metadata.get(name));
        }

        System.out.println(metadata.get(Metadata.MIME_TYPE_MAGIC));*/

        Tika tika = new Tika();

        String mimetype = tika.detect("text.PDF");

        System.out.println("MIMEType : "+mimetype);
       /* System.out.println(Integer.MIN_VALUE * -1);
        System.out.println(Integer.MIN_VALUE);*/
    }
}
