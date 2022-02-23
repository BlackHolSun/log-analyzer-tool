package com.imladyartist.accessloganalyzer;

import java.io.File;
import java.sql.Connection;
import java.util.List;
/**
 * Main class
 *
 * Parameters:
 *
 * 0 file path to logs
 * 1 link to database
 * 2 login
 * 3 password
* */

public class App {

    public static void main(String[] args) {
        List<Content> data = new FileParser().fileParsing(new File(args[0]));
        String url = args[1];

        Connection connection = AccessLogDAO.getConnection(url, args[2], args[3]);

        AccessLogDAO.insertDataToDB(data, connection);

        System.out.println(AccessLogDAO.getLinesCount() + " rows have been inserted to database");


    }


}
