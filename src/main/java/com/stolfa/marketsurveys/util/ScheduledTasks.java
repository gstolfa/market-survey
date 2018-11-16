package com.stolfa.marketsurveys.util;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.stolfa.marketsurveys.dao.SurveyJdbcRepository;
import com.stolfa.marketsurveys.errorhandling.BadRequestException;
import com.stolfa.marketsurveys.errorhandling.NotFoundException;
import com.stolfa.marketsurveys.model.Requester;
import com.stolfa.marketsurveys.response.Survey;
import com.stolfa.marketsurveys.service.MarketSurveyService;

@Component
public class ScheduledTasks {

	private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
	
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    
	@Autowired
	SurveyJdbcRepository surveyJdbcRepository;

	@Autowired
	MarketSurveyService marketSurveyService;

    @Scheduled(cron = "0 0 1 * * MON")
    public void everyWeek() {
        logger.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()) );

        List<Requester> requesters = surveyJdbcRepository.findRequestersByFrequency(SubscriptionFrequency.WEEKLY.name());
        
        notify(requesters);
    }

    @Scheduled(cron="0 0 0 1 1/1 *")
    public void everyMonth() {
        logger.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()) );
        
        List<Requester> requesters = surveyJdbcRepository.findRequestersByFrequency(SubscriptionFrequency.MONTHLY.name());
        
        notify(requesters);
    }

	/**
	 * 
	 * @param requesters
	 */
	private void notify(List<Requester> requesters){
        requesters.forEach(r -> {
        	try {
        		List<Survey> surveys = marketSurveyService.selectSurveysByFilter(r.getCategory(), r.getId());
				
        		switch (r.getChannel()) {
				case "EMAIL":
					logger.info("send email to " + r.getEmail());
					surveys.forEach(s -> logger.info("Survey: " + s.getTitle()));					
					break;
				case "TELEPHONE_NUMBER":
					logger.info("send sms to " + r.getTelephoneNumber());
					break;
				}
			} catch (NotFoundException | BadRequestException | InterruptedException | ExecutionException e) {
				logger.info(e.toString());
			}
        });		
	}
	
}
