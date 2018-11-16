# Market Survey
stand-alone Spring based Applications: Spring boot<br>
RESTful Services: Jersey 2<br>
in-memory databases: h2database<br>
main class to run: MarketsurveysApplication.java 

# API Endpoint - the app is deploied on Heroku
http://localhost:8080/surveys/{id}<br>
data available with:<br>
https://market-survey.herokuapp.com/surveys/81111600<br>
header parameter:<br> 
idRequester = 1001<br><br>

http://localhost:8080/surveys/category/{category}<br>
queryParam => gender [M, F]<br>
ageFrom => integer<br>
ageTo => integer<br>
incomeFrom => integer<br>
incomeTo => integer<br><br>
data available with:<br>
https://market-survey.herokuapp.com/surveys/category/Travel<br>
request parameters:<br>
gender=M<br>
ageFrom=17<br>
ageTo=40<br>
incomeFrom=1000<br>
incomeT0=2000<br>
header parameter: <br>
idRequester = 1001<br><br>

# Postman colelction link
https://www.getpostman.com/collections/d1940e43a0ec56f19d43
