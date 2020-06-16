package com.dheeraj.learning.afg.utilities;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LargeFileProcessingUtil2 {
    public static void main(String[] args) throws IOException {
        String directory = "C:\\Users\\gopad\\Desktop\\Projects\\Bugs\\operator presence spamming messages\\Latest Agilestudio logs\\";
        File dir = new File(directory);
        //File file = new File(directory+"Kafka log - 4 hours.txt");
        File file2 = new File(directory+"DheerajLogs.txt");
        //File file3 = new File(directory+"CompleteLinesDheeraj.csv");
        //LineIterator it = FileUtils.lineIterator(file, "UTF-8");
        Map<String, Integer> map = new TreeMap<>();
        Map<String, ArrayList<String>> mapList = new TreeMap<>();
        if(dir.isDirectory()){
            File[] files = dir.listFiles();
            int length=files.length;
            for (int i = 0;i<length;i++) {
                if (files[i].getName().endsWith("log")) {
                    LineIterator it=null;
                    try{
                        it = FileUtils.lineIterator(files[i], "UTF-8");
                        while (it.hasNext()) {
                            String line = it.nextLine();
                            doesItMatch(line, map, mapList);
                        }
                    }finally {
                        LineIterator.closeQuietly(it);
                    }
                }else{
                    System.out.println("Skipping file"+files[i].getName());
                }
            }
        } else {
            System.out.println("Not a dir");
        }




        /*for (String operator : map.keySet()) {
            Integer count = map.get(operator);
            System.out.println(operator + " : " + count);
        }*/

        System.out.println("Total number of operators : "+map.keySet().size());
        System.out.println("Min number of events : "+ Collections.min(map.values()));
        System.out.println("Max number of events : "+ Collections.max(map.values()));

        try{
            for (String operator : mapList.keySet()) {
                ArrayList<String> list = mapList.get(operator);
                for(String line:list){
                    FileUtils.write(file2,line+"\n","UTF8",true);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * "OperatorID":"ernsg@pegasystems.com"
     *
     * @param line
     * @return
     */
    public static boolean doesItMatch(String line, Map<String, Integer> map, Map<String, ArrayList<String>> mapList) {
        String patternString1 = "\"(OperatorID)\":\"(gopad@pegasystems.com)\",";
        //String patternString2 = "(\"Name\"):\"(.+?)\",\"State\":";
        //String patternString2 = "sr.+=com.pega.platform.message.internal.broadcast.BroadcastMessage.*(\"OperatorID\"):\"(.+?)\".*Standard\"]";

        Pattern pattern = Pattern.compile(patternString1);
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            map.putIfAbsent(matcher.group(2), 0);
            map.put(matcher.group(2), map.get(matcher.group(2)) + 1);
            mapList.computeIfAbsent(matcher.group(2), key->new ArrayList<>());
            mapList.get(matcher.group(2)).add(line);

            return true;
        }

        return false;
    }
}

