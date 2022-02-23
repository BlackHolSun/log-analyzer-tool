package com.imladyartist.accessloganalyzer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Parsing logs to Content class fields
 * */


public class FileParser {

    public List<Content> fileParsing(File file) {

        List<Content> lines = new ArrayList<Content>();

        BufferedReader fileReader;

        Content content;


        try {
            fileReader = new BufferedReader(new FileReader(file));

            String line;
            String abcLine;

            while ((line = fileReader.readLine()) != null) {

                String testLine = line.substring(line.indexOf("- - \""), line.indexOf("HTTP"));

                //only ABC related lines needed

                if (testLine.contains("/abc")) {

                    abcLine = line.substring(line.indexOf("\"line\" : \"") + 10);

                    String dateTime = abcLine.substring(abcLine.indexOf("\"[") + 2, abcLine.indexOf("]"));


                    String operation = abcLine.substring(abcLine.indexOf("- - \"") + 5, abcLine.indexOf("- - \"") + 8);


                    String url = abcLine.substring(abcLine.indexOf("/abc"), abcLine.indexOf("HTTP/"));

                    String responseCode = abcLine.substring(abcLine.indexOf("HTTP") + 10, abcLine.indexOf("HTTP") + 13);

                    String tempDurationPrep;

                    String bearer = null;
                    String bearerPrep = abcLine.substring(abcLine.indexOf("Bearer") + 7);


                    //if there's no Bearer we put null

                    if (abcLine.contains("Bearer")) {
                        tempDurationPrep = abcLine.substring(abcLine.indexOf("HTTP") + 14, abcLine.indexOf("Bearer") - 1);


                        String[] bearerPrepParts = bearerPrep.split(" ");
                        bearer = bearerPrepParts[0];
                    } else {


                        String intermediate = abcLine.substring(abcLine.indexOf("/abc"));
                        tempDurationPrep = intermediate.substring(intermediate.indexOf("HTTP") + 14);
                    }

                    String[] splitToGetDuration = tempDurationPrep.split(" ");


                    String size = splitToGetDuration[0];

                    String duration = splitToGetDuration[1];

                    String [] userAgentPrep = abcLine.split("\"");
                    int length = userAgentPrep.length;

                    String userAgent = userAgentPrep[length - 3];


                    content = new Content(dateTime, operation, url, responseCode, size, duration, bearer, userAgent);
                    lines.add(content);


                }

            }

            fileReader.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();

        }



        return lines;

    }
}
