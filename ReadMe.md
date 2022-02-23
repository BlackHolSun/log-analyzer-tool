### **Access Log Analyzer Tool**

Given a text file with access logs.
An application should:

1. Read lines from the file. Only the lines with specific context ("abc") value are required.
2. Parse dateTime, operation, url, responseCode, size, duration, bearer, userAgent values from each line.
3. Insert data to a Postgres database.