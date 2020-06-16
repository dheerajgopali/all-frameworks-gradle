package com.dheeraj.learning.afg.utilities;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LargeFileProcessingUtil {
    public static void main(String[] args) throws IOException {
        String directory = "C:\\Users\\gopad\\Desktop\\Projects\\Bugs\\operator presence spamming messages\\";
        File file = new File(directory+"Kafka log - 4 hours.txt");
        File file2 = new File(directory+"CompleteLinesAllUsers.csv");
        File file3 = new File(directory+"CompleteLinesDheeraj.csv");
        LineIterator it = FileUtils.lineIterator(file, "UTF-8");
        Map<String, Integer> map = new TreeMap<>();
        Map<String, ArrayList<String>> mapList = new TreeMap<>();
        try {

            while (it.hasNext()) {
                String line = it.nextLine();
                doesItMatch(line, map, mapList);
                // do something with line
            }
        } finally {
            LineIterator.closeQuietly(it);
        }


        /*for (String operator : map.keySet()) {
            Integer count = map.get(operator);
            System.out.println(operator + " : " + count);
        }*/

        System.out.println("Total number of operators : "+map.keySet().size());
        System.out.println("Min number of events : "+ Collections.min(map.values()));
        System.out.println("Max number of events : "+ Collections.max(map.values()));

        try{
            ArrayList<String> list3 = mapList.get("Dheeraj Gopali");
            for(String line:list3){
                FileUtils.write(file3,line+"\n","UTF8",true);
            }
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
        //String patternString1 = "(\"\"OperatorID\"\"):\"\"(.+?)\"\",";
        //String patternString2 = "(\"Name\"):\"(.+?)\",\"State\":";
        String patternString2 = "sr.+=com.pega.platform.message.internal.broadcast.BroadcastMessage.*(\"OperatorID\"):\"(.+?)\".*Standard\"]";

        Pattern pattern = Pattern.compile(patternString2);
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            System.out.println(matcher.toString());

            map.putIfAbsent(matcher.group(2), 0);
            map.put(matcher.group(2), map.get(matcher.group(2)) + 1);
            mapList.computeIfAbsent(matcher.group(2), key->new ArrayList<>());
            mapList.get(matcher.group(2)).add(line);

            return true;
        }

        return false;
    }
}

