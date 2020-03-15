DROP TABLE IF EXISTS tblOffers;
--------
-------- Table structure for table `tblOffers`
--------
CREATE TABLE `tblOffers` (
     intOfferId int(10) unsigned NOT NULL AUTO_INCREMENT,
     vchOffer VARCHAR(255) NOT NULL,
     price VARCHAR(255) NOT NULL,
     currency VARCHAR(255) NOT NULL,
     validity VARCHAR(10) NOT NULL,
     stmStartDate timestamp NOT NULL,
     stmEndDate timestamp NOT NULL,
     status VARCHAR(255) NOT NULL,
     PRIMARY KEY (intOfferId)
);