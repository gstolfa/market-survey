package com.stolfa.marketsurveys.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.stolfa.marketsurveys.errorhandling.BadRequestException;
import com.stolfa.marketsurveys.errorhandling.NotFoundException;
import com.stolfa.marketsurveys.response.Survey;

public interface MarketSurveyService {

	List<Survey> selectSurveysByFilter(String category, Long idRequester) throws NotFoundException, BadRequestException, InterruptedException, ExecutionException;
	
	Survey selectSurveyById(Long id, Long idRequester) throws NotFoundException, BadRequestException;

	List<Survey> selectSurveysByFilter(String gender, Integer ageFrom, Integer ageTo, Integer incomeFrom, Integer incomeTo, String category, Long idRequester) throws NotFoundException, BadRequestException, InterruptedException, ExecutionException;
}
