package com.imladyartist.accessloganalyzer;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 1. Connect to the DB
 * 2. Insert data to DB
 * */

public class AccessLogDAO {

    private static int linesCount = 0;

    public static int getLinesCount() {
        return linesCount;
    }



    public static Connection getConnection(String url, String user, String password) {

        Connection connection = null;


        try {
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;

    }


    public static void insertDataToDB(List<Content> logs, Connection connection) {
        String SQL = "INSERT INTO yourusername.logs_test(date_time, operation, url, response_code, size, duration, bearer, user_agent)"
                + "VALUES(?,?, ?, ?, ?, ?, ?, ?)";



        for (Content data : logs) {
            PreparedStatement pstmt;

            String strDate = data.getDateTime();
            Timestamp timestamp = convertStringToTimestamp(strDate);

            String operation = data.getOperation();
            String url = data.getUrl();
            int responseCode = Integer.parseInt(data.getResponseCode());
            int size = Integer.parseInt(data.getSize());
            double duration = Double.parseDouble(data.getDuration());
            String bearer = data.getBearer();
            String userAgent = data.getUserAgent();

            try {

                pstmt = connection.prepareStatement(SQL);

                pstmt.setTimestamp(1, timestamp);
                pstmt.setString(2, operation);
                pstmt.setString(3, url);
                pstmt.setInt(4, responseCode);
                pstmt.setInt(5, size);
                pstmt.setDouble(6, duration);
                pstmt.setString(7, bearer);
                pstmt.setString(8, userAgent);

                pstmt.executeUpdate();

                ++linesCount;

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public static Timestamp convertStringToTimestamp(String strDate) {
        Timestamp timestamp = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy hh:mm:ss z");
            Date parseDate = dateFormat.parse(strDate.replaceFirst(":", " "));
            timestamp = new java.sql.Timestamp(parseDate.getTime());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return timestamp;
    }

}
