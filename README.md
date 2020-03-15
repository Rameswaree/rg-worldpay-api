A REST API Payment using Java and Spring Boot

Note: This application will fetch all the records present in the table. It also has the provision to add new records. Although it does not show any error message while trying to add a duplicate offer into the table, it does not allow duplicate records to be added. Similarly, if a record is not in EXPIRED status, the order gets cancelled successfully. If the order is already expired, then it does not update anything on that record.

Steps to run the application:

1. Clone the git repository or download the extract zip file.
2. Go to the path where the project is present and type 'mvn clean install' from the command terminal.
3. After this, type 'java -jar target\rg-worldpay-api-1.0.0.jar' to run the application in Windows. (For Mac users, please type 'java -jar target/rg-worldpay-api-1.0.0.jar').
4. Open the browser of your choice.
5. Type "http://localhost:9999/worldpay/availableOffers". This will give an empty list.
6. Go to the terminal and run the command 'curl -X POST "http://localhost:9999/worldpay/createOffers?offer=NEW_YEAR&price=20&validity=30"'. This will add the data into the table and also on refreshing the browser, which contains the URL as shown in Step 5, will show the value present. Validity is present in days. Keep adding more offers and check the results in the browser.
7. Now once all the offers are added, type the following command in command terminal to cancel the offer and change it to expired status.
'curl -X PUT "http://localhost:9999/worldpay/cancelOffers?offer=NEW_YEAR"'. After executing the curl command, check the browser to see if the status for the chosen offer has changed to EXPIRED.
8. To view the values in a table format, type "http://localhost:9999/h2-console" in the browser and connect to the database.
9. Select the table "TBL_OFFERS" and run the Select command present in the console.
10. The results can be viewed in the table.

H2 has been used as database. Many more scenarios need to be handled at the moment, but right now, this application does not allow duplicates to be added and also does not allow to update the end date of offers that have already been expired.
