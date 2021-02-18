package com.dheeraj.learning.afg.utilities;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class CompareMimeTypes {
    Map<String, String> tomcatMimeTypes = new HashMap<>();
    Map<String, ArrayList<CustomMimeType>> cloudMimeTypes = new HashMap<>();
    Map<String, Set<CustomMimeType>> tomcatNotSupported = new HashMap<>();
    Map<String, Set<CustomMimeType>> diffMimeTypes = new HashMap<>();

    public static void main(String[] args) throws IOException {
        CompareMimeTypes compareMimeTypes = new CompareMimeTypes();
        compareMimeTypes.populateTomcatMimeTypes("C:\\dev\\tools\\apache-tomcat-8.5.38\\conf\\web.xml");
        compareMimeTypes.findDifferentValuedCloudMimeTypes("C:\\Dheeraj\\Office\\Feature\\MimeType EPIC\\CCB_web");
    }

    public void populateTomcatMimeTypes(String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(new File(fileName));
        String buffer = IOUtils.toString(fis, StandardCharsets.UTF_8);

        Pattern p = Pattern.compile("(<mime-mapping>\r\n.*<extension>(.*)</extension>\r\n.*<mime-type>(.*)</mime-type>\r\n.*</mime-mapping>)");

        Matcher m = p.matcher(buffer);
        int noOfTomcatMimeTypes = 0;
        while(m.find()){
            tomcatMimeTypes.put(m.group(2),m.group(3));
            noOfTomcatMimeTypes++;
        }
        System.out.println("Number of tomcat mime-types : "+noOfTomcatMimeTypes);
    }

    public void findDifferentValuedCloudMimeTypes(String cloudDirectory) throws IOException {
        Stream<Path> files = Files.find(Paths.get(cloudDirectory),Integer.MAX_VALUE, (path, fileAttr) -> fileAttr.isRegularFile());

        files.forEach((x) -> {
            try {
                processEachCloudMimeTypes(x.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        System.out.println("Total cloud mimetype files "+fileCounter);
        System.out.println("Total cloud mimetypes "+cloudMimeTypes.size());
        System.out.println("Tomcat Supported : "+tomcatMimeTypes.size());
        System.out.println("Tomcat NOT Supported : "+tomcatNotSupported.size());
        System.out.println("Tomcat Differed mimetypes  : "+diffMimeTypes.size());


        for (String extension:
                diffMimeTypes.keySet()) {
            System.out.println("Extension : "+extension);
        }
        for (String extension:
                diffMimeTypes.keySet()) {
            System.out.println("Extension : "+extension);
            System.out.println(diffMimeTypes.get(extension));
            System.out.println("=====================================================================================");
        }

        String[] customizedMimeTypes = {"otf","ttc","wsdl","xml","ttf","js","woff"};
        System.out.println("Customers having the same mimetype as tomcat for these ");
        for (String extension :
                customizedMimeTypes) {
            System.out.println("Custom Extension : ");
            System.out.println(cloudMimeTypes.get(extension));
        }
    }
    int fileCounter=0;
    public void processEachCloudMimeTypes(String fileName) throws IOException {
        fileCounter++;
        FileInputStream fis = new FileInputStream(new File(fileName));
        String buffer = IOUtils.toString(fis, StandardCharsets.UTF_8);

        String clientName = fileName.split(Matcher.quoteReplacement(System.getProperty("file.separator")))[6];
        Pattern p = Pattern.compile("(<mime-mapping>\n.*<extension>(.*)</extension>\n.*<mime-type>(.*)</mime-type>\n.*</mime-mapping>)");

        Matcher m = p.matcher(buffer);
        while(m.find()){
            String extension = m.group(2).toLowerCase();
            String mimetype = m.group(3).toLowerCase();
            cloudMimeTypes.putIfAbsent(extension,new ArrayList<CustomMimeType>());
            cloudMimeTypes.get(extension).add(new CustomMimeType(extension, mimetype, clientName));
            if(tomcatMimeTypes.get(extension) == null) {
                tomcatNotSupported.putIfAbsent(extension, new HashSet<CustomMimeType>());
                tomcatNotSupported.get(extension).add(new CustomMimeType(extension, tomcatMimeTypes.get(extension), "Tomcat"));
                tomcatNotSupported.get(extension).add(new CustomMimeType(extension, mimetype, clientName));
            } else if (!tomcatMimeTypes.get(extension).equals(mimetype)) {
                diffMimeTypes.putIfAbsent(extension, new HashSet<CustomMimeType>());
                diffMimeTypes.get(extension).add(new CustomMimeType(extension, tomcatMimeTypes.get(extension), "Tomcat"));
                diffMimeTypes.get(extension).add(new CustomMimeType(extension, mimetype, clientName));
            }
        }
    }

    class CustomMimeType{
        String extension;
        String mimetype;
        String cloudCustomer;

        public CustomMimeType(String extension, String mimetype, String cloudCustomer) {
            this.extension = extension;
            this.mimetype = mimetype;
            this.cloudCustomer = cloudCustomer;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CustomMimeType that = (CustomMimeType) o;
            return Objects.equals(extension, that.extension) &&
                    Objects.equals(mimetype, that.mimetype) &&
                    Objects.equals(cloudCustomer, that.cloudCustomer);
        }

        @Override
        public int hashCode() {
            return Objects.hash(extension, mimetype, cloudCustomer);
        }

        @Override
        public String toString() {
            return "CustomMimeType{" +
                    "extension='" + extension + '\'' +
                    ", mimetype='" + mimetype + '\'' +
                    ", cloudCustomer='" + cloudCustomer + '\'' +
                    '}'+"\n";
        }
    }
}
