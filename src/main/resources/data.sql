DROP TABLE IF EXISTS tblOffers;
--------
-------- Table structure for table `tblOffers`
--------
CREATE TABLE tblOffers (
     offerId INT AUTO_INCREMENT PRIMARY KEY,
     offer VARCHAR(255) NOT NULL,
     price VARCHAR(255) NOT NULL,
     currency VARCHAR(255) NOT NULL,
     validity VARCHAR(10) NOT NULL,
     startDate TIMESTAMP NOT NULL,
     endDate TIMESTAMP NOT NULL,
     status VARCHAR(255) NOT NULL,
     paymentMode VARCHAR(255) NOT NULL
);