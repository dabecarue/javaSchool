# javaSchool
Url shortener spring boot application

use mvn clean install spring-boot:run to deploy application

use POST <requestBody> to create a new alias 
  Example  POST: “url”:”https://espanol.yahoo.com/?p=us” returns “alias”:”AcZ4G0p”
  
use GET /<alias> to redirect to the associated url
  Example  GET “http:localhost/AcZ4G0p” redirect to “https://espanol.yahoo.com/?p=us"
