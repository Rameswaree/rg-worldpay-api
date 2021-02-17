A REST API Payment using Java and Spring Boot

Note: This application will fetch all the records present in the table. It also has the provision to add new records. Although it does not show any error message while trying to add a duplicate offer into the table, it does not allow duplicate records to be added. Similarly, if a record is not in EXPIRED status, the order gets cancelled successfully. If the order is already expired, then it does not update anything on that record.

New enhancements Made:

1. Ability to add offers in multiple currencies included.
2. Option to update price and end date of existing offers. Validations include end date cannot be less than current business date(CBD).
3. Offers can be created for specific payment mode such as Credit Cards/Debit Cards, Wallets.
4. Filtering the available offers is possible based on conditions such as offer, payment mode, currency and status, irrespective of case sensitivity.
5. Customised exception called OfferNotFoundException has been created.

Steps to run the application:

1. Clone the git repository or download the extract zip file.
2. Go to the path where the project is present and type 'mvn clean install' from the command terminal.
3. After this, type 'java -jar target\rg-worldpay-api-1.8.0.jar' to run the application in Windows. (For Mac users, please type 'java -jar target/rg-worldpay-api-1.8.0.jar').
4. Open the browser of your choice.
5. Type "http://localhost:9999/worldpay/availableOffers". This will give an empty list.
6. Go to the terminal and run the command 'curl -X POST "http://localhost:9999/worldpay/createOffers?offer=NEW_YEAR&price=20&currency=GBP&validity=30&paymentMode=CC/DC"'. This will add the data into the table and also on refreshing the browser, which contains the URL as shown in Step 5, will show the value present. Validity is present in days. Keep adding more offers and check the results in the browser.
7. Now once all the offers are added, type the following command in command terminal to cancel the offer and change it to expired status.
'curl -X PUT "http://localhost:9999/worldpay/cancelOffers?offer=NEW_YEAR"'. After executing the curl command, check the browser to see if the status for the chosen offer has changed to EXPIRED. If the offer is not present in the list, it will throw a custom exception called OfferNotFoundException.
8. To update the offer, run the command 'curl -X PATCH "http://localhost:9999/worldpday/updateOffers?offer=NEW_YEAR&price=50&endDate=2020-03-29%2022:19:00"'. Currently, this allows updation of price irrespective of whether the end date given is past or future, however, the end date gets updated only if it is ahead of current business date.
9. The URL given in step 5 shows all the entries in the database. To filter out on the basis of status, it can be amended to 'http://localhost:9999/worldpay/availableOffers?status=active'. The validation of parameter value is case insensitive. Similarly, the URL can be filtered on the basis of offer, payment mode and currency. If the parameter value is not present in the list, then it shows the entire list.
10. To view the values in a table format, type "http://localhost:9999/h2-console" in the browser and connect to the database.
11. Select the table "TBL_OFFERS" and run the Select command present in the console.
12. The results can be viewed in the table.

H2 has been used as database. Duplicate offers are not allowed to be added. When an expired offer is updated with the price and new end date and if the end date is ahead of the current business date, then the offer becomes active.
