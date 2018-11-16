# Market Survey
stand-alone Spring based Applications: Spring boot<br>
RESTful Services: Jersey 2<br>
in-memory databases: h2database<br>
main class to run: MarketsurveysApplication.java 

# API Endpoint
http://localhost:8080/surveys/{id}<br>
data available with:<br>
http://localhost:8080/surveys/81111600 <br><br>

http://localhost:8080/surveys/category/{category}<br>
queryParam => gender [M, F]<br>
ageFrom => integer<br>
ageTo => integer<br>
incomeFrom => integer<br>
incomeTo => integer<br><br>
data available with:<br>
http://localhost:8080/surveys/category/Travel

