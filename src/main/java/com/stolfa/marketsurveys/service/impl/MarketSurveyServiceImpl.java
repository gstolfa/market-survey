package com.stolfa.marketsurveys.service.impl;

import static java.util.stream.Collectors.groupingBy;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stolfa.marketsurveys.dao.SurveyJdbcRepository;
import com.stolfa.marketsurveys.errorhandling.BadRequestException;
import com.stolfa.marketsurveys.errorhandling.NotFoundException;
import com.stolfa.marketsurveys.model.Requester;
import com.stolfa.marketsurveys.response.Answers;
import com.stolfa.marketsurveys.response.Content;
import com.stolfa.marketsurveys.response.Page;
import com.stolfa.marketsurveys.response.Question;
import com.stolfa.marketsurveys.response.Survey;
import com.stolfa.marketsurveys.response.builder.ContentBuilder;
import com.stolfa.marketsurveys.response.builder.QuestionBuilder;
import com.stolfa.marketsurveys.response.builder.SurveyBuilder;
import com.stolfa.marketsurveys.service.MarketSurveyService;
import com.stolfa.marketsurveys.util.ContentType;

@Service
public class MarketSurveyServiceImpl implements MarketSurveyService{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	SurveyJdbcRepository surveyJdbcRepository;
	
	@Override
	public List<Survey> selectSurveysByFilter(String gender, Integer ageFrom, Integer ageTo, Integer incomeFrom, 
			Integer incomeTo, String category, Long idRequester) throws NotFoundException, BadRequestException, InterruptedException, ExecutionException {
		logger.info(">>>> selectSurveysByFilter");		
		
		/* requester by id */
		findRequesterById(idRequester);

		/* find surveys by criteria */
		List<Survey> surveys = findSurveySByTargetAndCategory(gender, ageFrom, ageTo, incomeFrom, incomeTo, category);
		
		return surveys.stream().map(s -> composeSurvey(s)).collect(Collectors.toList());

	}

	@Override
	public List<Survey> selectSurveysByFilter(String category, Long idRequester) throws NotFoundException, BadRequestException, InterruptedException, ExecutionException {
		logger.info(">>>> selectSurveysByFilter ( " + category + ", " + idRequester + " )");
		return selectSurveysByFilter(null, null, null, null, null, category, idRequester);
	}
	
	@Override
	public Survey selectSurveyById(Long idSurvey, Long idRequester) throws NotFoundException, BadRequestException {
		logger.info(">>>> selectSurveyById");

		/* requester by id */
		findRequesterById(idRequester);
		
		/* survey by id */
		Survey resSurvey = findSurveyById(idSurvey);
		
		return composeSurvey(resSurvey);
	}

	/**
	 * 
	 * @param gender
	 * @param ageFrom
	 * @param ageTo
	 * @param incomeFrom
	 * @param incomeTo
	 * @param category
	 * @return
	 * @throws NotFoundException
	 */
	private List<Survey> findSurveySByTargetAndCategory(String gender, Integer ageFrom, Integer ageTo, Integer incomeFrom, Integer incomeTo, String category)throws NotFoundException{
		
		List<com.stolfa.marketsurveys.model.Survey> surveys = surveyJdbcRepository.findSurveySByTargetAndCategory(gender, ageFrom, ageTo, incomeFrom, incomeTo, category);

		if (surveys == null || surveys.isEmpty()) {
			throw new NotFoundException("No surveys found with passed criteria.");
		}

		return surveys.stream().map(s -> new SurveyBuilder(s).build()).collect(Collectors.toList());
	}
	
	/**
	 * Return list of response Contents by id question.
	 * 
	 * @param idQuestion
	 * @return
	 */
	private List<Content> getContents(Long idQuestion){
		
		List<com.stolfa.marketsurveys.model.Content> contents = surveyJdbcRepository.findContentsByIdQuestion(idQuestion);
		
		if (contents == null) return null;

		return contents.stream().map(c -> new ContentBuilder(c).build()).collect(Collectors.toList());
		
	}
	
	
	/**
	 * Find survey by id.
	 * 
	 * @param id
	 * @return
	 * @throws NotFoundException
	 */
	private com.stolfa.marketsurveys.response.Survey findSurveyById(Long id) throws NotFoundException {
		List<com.stolfa.marketsurveys.model.Survey> surveys = surveyJdbcRepository.findSurveyById(id);
		
		if (surveys.isEmpty()) {
			throw new NotFoundException("Survey not found!");
		}
		
		return new SurveyBuilder(surveys.get(0)).build();
	}

	/**
	 * Find requester by id.
	 * 
	 * @param id
	 * @return
	 * @throws NotFoundException
	 * @throws BadRequestException 
	 */
	private Requester findRequesterById(Long id) throws NotFoundException, BadRequestException {
		
		if (id == null) {
			throw new BadRequestException("Requester id not present in header!");
		}
		
		List<Requester> requesters = surveyJdbcRepository.findRequesterById(id);
		
		if (requesters.isEmpty()) {
			throw new NotFoundException("Requester not found!");
		}
		
		return requesters.get(0);
	}
	
	/**
	 * 
	 * @param idSurvey
	 * @return
	 * @throws NotFoundException
	 */
	private Map<Integer, List<Question>> getQuestionsPerPage(Long idSurvey){
		/* questions by id survey */
		List<com.stolfa.marketsurveys.model.Question> questions = surveyJdbcRepository.findQuestionsByIdSurvey(idSurvey);
		
		/* grouping questions per page */
		Map<Integer, List<com.stolfa.marketsurveys.model.Question>> questionsPerPage = questions.stream().collect(groupingBy(com.stolfa.marketsurveys.model.Question::getPage));

		/* convert model question to response view */
		Map<Integer, List<com.stolfa.marketsurveys.response.Question>> resQuestionsPerPage =
				questionsPerPage.entrySet().stream()
		        .collect(Collectors.toMap(
		            e -> e.getKey(),
		            e -> e.getValue().stream().map(question -> new QuestionBuilder(question).build()).collect(Collectors.toList())
		        ));
		
		return resQuestionsPerPage;
	}
	
	/**
	 * 
	 * @param resQuestionsPerPage
	 * @return
	 */
	private List<Page> getPages(Map<Integer, List<Question>> resQuestionsPerPage){
		/* questions per page */
		List<Page> resPages = resQuestionsPerPage.entrySet().stream().map(e -> {
			Page page = new Page();
			page.setNumber(e.getKey());
			page.setQuestions(e.getValue());
			return page;
		}).collect(Collectors.toList()); 
		
		/* add content to questions */
		resPages = resPages.stream().map(p -> {

			List<com.stolfa.marketsurveys.response.Question> resQuestionsWithContent = p.getQuestions().stream().map(
					resQuestion -> {
						
						List<Content> resContents = getContents(resQuestion.getId());

						Map<String, List<Content>> contentPerType = resContents.stream().collect(groupingBy(Content::getType));
						
						Answers answers = new Answers();
						answers.setChoices(contentPerType.get(ContentType.CHOICE.toString()));
						answers.setRows(contentPerType.get(ContentType.ROW.toString()));
						resQuestion.setAnswers(answers);
						
						return resQuestion;
					} 
			).collect(Collectors.toList());

			Page pageWithContent = p;
			p.setQuestions(resQuestionsWithContent);
			return pageWithContent;

		}).collect(Collectors.toList()); 

		return resPages;
	}
	
	/**
	 * 
	 * @param resSurvey
	 * @return
	 * @throws NotFoundException 
	 */
	private Survey composeSurvey(Survey resSurvey){

		/* convert model question to response view */
		Map<Integer, List<Question>> resQuestionsPerPage = getQuestionsPerPage(resSurvey.getId());
		
		/* questions per page */
		List<Page> resPages = getPages(resQuestionsPerPage);
		
		/* set values and return survey */
		final Counter questionsCounter = new Counter();
		resPages.forEach(p -> questionsCounter.add(p.getQuestions().size()));
		resSurvey.setPages(resPages);
		resSurvey.setPage_count(resPages.size());
		resSurvey.setQuestion_count(questionsCounter.getValue());
		
		return resSurvey;
	}
	
	/**
	 * 
	 * @author gianfranco.stolfa
	 *
	 */
	private class Counter{
		private int value = 0;
		public void add(int i){
			this.value = this.value + i;
		}
		public int getValue(){
			return value;
		}
	}
}
