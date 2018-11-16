# Market Survey

| Technology | Framework |
| --- | --- |
| Stand-alone Spring based Applications | Spring boot |
| RESTful Web Services | Jersey 2 |
| In-memory databases | h2database |

Main class to run: MarketsurveysApplication.java

# API Endpoint - the app is deployed on Heroku
http://localhost:8080/surveys/{id}<br>
data available with:<br>
https://market-survey.herokuapp.com/surveys/81111600<br>
 
| Header parameter Name | Header parameter Value |
| --- | --- |
| idRequester | 1001 |


http://localhost:8080/surveys/category/{category}<br>

| Request parameter Name | Request parameter type |
| --- | --- |
| gender | 'M'/'F' |
| ageFrom | integer |
| ageTo | integer |
| incomeFrom | integer |
| incomeTo | integer |

.small[data available with:] <br>
https://market-survey.herokuapp.com/surveys/category/Travel<br>

| Request parameter Name | Request parameter type |
| --- | --- |
| gender | 'M' |
| ageFrom | 17 |
| ageTo | 40 |
| incomeFrom | 1000 |
| incomeTo | 2000 |

| Header parameter Name | Header parameter Value |
| --- | --- |
| idRequester | 1001 |

# Postman collection link
https://www.getpostman.com/collections/d1940e43a0ec56f19d43
