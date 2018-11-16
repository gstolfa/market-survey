--=================================================================
-- insert REQUESTER AND SUBSCRIPTION
--=================================================================    
insert into REQUESTER values(1001, 'Caravelo', 'info@caravelo.es', '','');
insert into SUBSCRIPTION values (3000, 1001, 'EMAIL', 'Travel', 'WEEKLY');

--=================================================================
-- insert TARGET
--=================================================================
insert into TARGET values(1, 'M', 18, 40, 1000, 2000, 'EURO');
insert into TARGET values(2, 'F', 0, 100, 0, 5000, 'EURO');
insert into TARGET values(3, 'M/F', 30, 80, 1000, 2000, 'EURO');
insert into TARGET values(4, 'M', 10, 20, 0, 0, 'EURO');
--=================================================================
-- first survey: Customer satisfaction survey of Travel Agencies
--=================================================================
insert into survey  values (81111600, 'Customer satisfaction survey of Travel Agencies', 'Travel', 'EN', CURRENT_TIMESTAMP(), 1, 'Italy');

--=================================================================
-- first survey, first question, first page
--=================================================================
insert into QUESTION values (1, 'Please confirm your gender:', 1, 1); -- id, heading, position, family 
insert into CONTENT values(1, 'male', '');   -- id, text, imageurl
insert into CONTENT values(2, 'female', ''); -- id, text, imageurl
insert into R_QUESTION_CONTENT values (1, 1, 1, true, true, 'CHOICE'); -- id_question, id_content, position, visible, required, answer_type
insert into R_QUESTION_CONTENT values (1, 2, 2, true, true, 'CHOICE'); -- id_question, id_content, position, visible, required, answer_type
insert into R_SURVEY_QUESTION values (81111600, 1, 1); -- id_survey, id_question, page

--=================================================================
-- first survey, second question, first page
--=================================================================
insert into QUESTION values (2, 'Why did you choose us from other travel agencies?', 2, 1); -- id, heading, position, family
insert into CONTENT values(3, 'Personal experience with us', ''); -- id, text, imageurl
insert into CONTENT values(4, 'Suggestion(s) of a friend(s)', ''); -- id, text, imageurl
insert into CONTENT values(5, 'Our prices', ''); -- id, text, imageurl
insert into CONTENT values(6, 'Advertising', ''); -- id, text, imageurl
insert into CONTENT values(7, 'The reputation of our agency', ''); -- id, text, imageurl
insert into CONTENT values(8, 'Other (please specify)', ''); -- id, text, imageurl
insert into R_QUESTION_CONTENT values (2, 3, 1, true, true, 'CHOICE'); -- id_question, id_content, position, visible, required, answer_type
insert into R_QUESTION_CONTENT values (2, 4, 2, true, true, 'CHOICE'); -- id_question, id_content, position, visible, required, answer_type
insert into R_QUESTION_CONTENT values (2, 5, 3, true, true, 'CHOICE'); -- id_question, id_content, position, visible, required, answer_type
insert into R_QUESTION_CONTENT values (2, 6, 4, true, true, 'CHOICE'); -- id_question, id_content, position, visible, required, answer_type
insert into R_QUESTION_CONTENT values (2, 7, 5, true, true, 'CHOICE'); -- id_question, id_content, position, visible, required, answer_type
insert into R_QUESTION_CONTENT values (2, 8, 6, true, true, 'CHOICE'); -- id_question, id_content, position, visible, required, answer_type
insert into R_SURVEY_QUESTION values (81111600, 2, 1); -- id_survey, id_question, page

--=================================================================
-- first survey, third question, first page
--=================================================================
insert into QUESTION values (3, 'Which method did you use to book with us?', 3, 1); -- id, heading, position, family
insert into CONTENT values(9, 'In person (in the street shop)', ''); -- id, text, imageurl
insert into CONTENT values(10, 'On our online catalog', ''); -- id, text, imageurl
insert into CONTENT values(11, 'Via telephone', ''); -- id, text, imageurl
insert into CONTENT values(12, 'The trip has been booked by others', ''); -- id, text, imageurl
insert into R_QUESTION_CONTENT values (3, 9, 1, true, true, 'CHOICE'); -- id_question, id_content, position, visible, required, answer_type
insert into R_QUESTION_CONTENT values (3, 10, 2, true, true, 'CHOICE'); -- id_question, id_content, position, visible, required, answer_type
insert into R_QUESTION_CONTENT values (3, 11, 3, true, true, 'CHOICE'); -- id_question, id_content, position, visible, required, answer_type
insert into R_QUESTION_CONTENT values (3, 12, 4, true, true, 'CHOICE'); -- id_question, id_content, position, visible, required, answer_type
insert into R_QUESTION_CONTENT values (3, 8, 5, true, true, 'CHOICE'); -- id_question, id_content, position, visible, required, answer_type
insert into R_SURVEY_QUESTION values (81111600, 3, 1); -- id_survey, id_question, page


--=================================================================
-- first survey, fourth question, second page
--=================================================================
insert into QUESTION values (4, 'What kind of transport did you choose to travel?', 4, 1); -- id, heading, position, family
insert into CONTENT values(13, 'Bus or rental', ''); -- id, text, imageurl
insert into CONTENT values(14, 'Plane', ''); -- id, text, imageurl
insert into CONTENT values(15, 'Personal means (car, etc.)', ''); -- id, text, imageurl
insert into R_QUESTION_CONTENT values (4, 13, 1, true, true, 'CHOICE'); -- id_question, id_content, position, visible, required, answer_type
insert into R_QUESTION_CONTENT values (4, 14, 2, true, true, 'CHOICE'); -- id_question, id_content, position, visible, required, answer_type
insert into R_QUESTION_CONTENT values (4, 15, 3, true, true, 'CHOICE'); -- id_question, id_content, position, visible, required, answer_type
insert into R_SURVEY_QUESTION values (81111600, 4, 2); -- id_survey, id_question, page

--=================================================================
-- first survey, fifth question, second page [matrix]
--=================================================================
insert into QUESTION values (5, 'Have you been offered the purchase of the following services before traveling?', 5, 4); -- id, heading, position, family
insert into CONTENT values(16, 'Insurance coverage', ''); -- id, text, imageurl [row]
insert into CONTENT values(17, 'Omnicomprehensive insurance', ''); -- id, text, imageurl [row]
insert into CONTENT values(18, 'Transfer to / from the airport', ''); -- id, text, imageurl [row]
insert into CONTENT values(19, 'Optional excursions', ''); -- id, text, imageurl [row]
insert into CONTENT values(20, 'yes', ''); -- id, text, imageurl [choice]
insert into CONTENT values(21, 'I do not remember', ''); -- id, text, imageurl [choice]
insert into CONTENT values(22, 'no', ''); -- id, text, imageurl [choice]
insert into R_QUESTION_CONTENT values (5, 16, 1, true, true, 'ROW'); -- id_question, id_content, position, visible, required, answer_type
insert into R_QUESTION_CONTENT values (5, 17, 2, true, true, 'ROW'); -- id_question, id_content, position, visible, required, answer_type
insert into R_QUESTION_CONTENT values (5, 18, 3, true, true, 'ROW'); -- id_question, id_content, position, visible, required, answer_type
insert into R_QUESTION_CONTENT values (5, 19, 4, true, true, 'ROW'); -- id_question, id_content, position, visible, required, answer_type
insert into R_QUESTION_CONTENT values (5, 20, 1, true, true, 'CHOICE'); -- id_question, id_content, position, visible, required, answer_type
insert into R_QUESTION_CONTENT values (5, 21, 2, true, true, 'CHOICE'); -- id_question, id_content, position, visible, required, answer_type
insert into R_QUESTION_CONTENT values (5, 22, 3, true, true, 'CHOICE'); -- id_question, id_content, position, visible, required, answer_type
insert into R_SURVEY_QUESTION values (81111600, 5, 2); -- id_survey, id_question, page

--=================================================================
-- first survey, sixth question, second page 
--=================================================================
insert into QUESTION values (6, 'How satisfied were you with the level and quality of the travel service?', 6, 1); -- id, heading, position, family
insert into CONTENT values(23, 'Satisfied', ''); -- id, text, imageurl [choice]
insert into CONTENT values(24, 'Almost satisfied', ''); -- id, text, imageurl [choice]
insert into CONTENT values(25, 'Neither satisfied nor dissatisfied', ''); -- id, text, imageurl [choice]
insert into CONTENT values(26, 'Almost dissatisfied', ''); -- id, text, imageurl [choice]
insert into CONTENT values(27, 'dissatisfied', ''); -- id, text, imageurl [choice]
insert into R_QUESTION_CONTENT values (6, 23, 1, true, true, 'CHOICE'); -- id_question, id_content, position, visible, required, answer_type
insert into R_QUESTION_CONTENT values (6, 24, 2, true, true, 'CHOICE'); -- id_question, id_content, position, visible, required, answer_type
insert into R_QUESTION_CONTENT values (6, 25, 3, true, true, 'CHOICE'); -- id_question, id_content, position, visible, required, answer_type
insert into R_QUESTION_CONTENT values (6, 26, 4, true, true, 'CHOICE'); -- id_question, id_content, position, visible, required, answer_type
insert into R_QUESTION_CONTENT values (6, 27, 5, true, true, 'CHOICE'); -- id_question, id_content, position, visible, required, answer_type
insert into R_SURVEY_QUESTION values (81111600, 6, 2); -- id_survey, id_question, page