package com.stolfa.marketsurveys.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ManagedAsync;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stolfa.marketsurveys.errorhandling.BadRequestException;
import com.stolfa.marketsurveys.errorhandling.NotFoundException;
import com.stolfa.marketsurveys.response.Survey;
import com.stolfa.marketsurveys.service.MarketSurveyService;

@Component
@Path("/surveys")
public class MarketSurveyController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	MarketSurveyService marketSurveyService;

	/**
	 * Resource method that produces a response asynchronously.
	 * Caching.
	 * 
	 * @param asyncResponse
	 * @param category
	 * @param gender
	 * @param ageFrom
	 * @param ageTo
	 * @param incomeFrom
	 * @param incomeTo
	 * @param idRequester
	 * @throws NotFoundException
	 * @throws BadRequestException
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@GET
	@ManagedAsync
	@Path("/category/{category}")
    @Produces(MediaType.APPLICATION_JSON)
	public void findSurveyByFilter(@Suspended final AsyncResponse asyncResponse, @PathParam("category") String category,
			@QueryParam("gender") String gender, @QueryParam("ageFrom") Integer ageFrom, @QueryParam("ageTo") Integer ageTo, 
	  		@QueryParam("incomeFrom") Integer incomeFrom, @QueryParam("incomeTo") Integer incomeTo, @HeaderParam("idRequester") Long idRequester)  throws NotFoundException, BadRequestException, InterruptedException, ExecutionException{
		logger.info("MarketSurveyController: findSurveyByFilter");

		asyncResponse.setTimeout(1000, TimeUnit.MILLISECONDS);
        asyncResponse.setTimeoutHandler(ar -> ar.resume(
                Response.status(Response.Status.SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build()));
		
		List<Survey> result = marketSurveyService.selectSurveysByFilter(gender, ageFrom, ageTo, incomeFrom, incomeTo, category, idRequester);
		
		CacheControl cacheControl = new CacheControl();
		cacheControl.setMaxAge(60);
		
		asyncResponse.resume(Response.ok(result).cacheControl(cacheControl).build());
	}

	/**
	 * Resource method that produces a response asynchronously.
	 * Caching.
	 *  
	 * @param asyncResponse
	 * @param idSurvey
	 * @param idRequester
	 * @throws NotFoundException
	 * @throws BadRequestException
	 */
	@GET
	@ManagedAsync
	@Path("/{idSurvey}")
    @Produces(MediaType.APPLICATION_JSON)
	public void findSurvey(@Suspended final AsyncResponse asyncResponse, @PathParam("idSurvey") Long idSurvey, @HeaderParam("idRequester") Long idRequester)  throws NotFoundException, BadRequestException{
		logger.info("MarketSurveyController: findSurvey [" + idSurvey + "]");

		asyncResponse.setTimeout(1000, TimeUnit.MILLISECONDS);
        asyncResponse.setTimeoutHandler(ar -> ar.resume(
                Response.status(Response.Status.SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build()));

		Survey result = marketSurveyService.selectSurveyById(idSurvey, idRequester);

		CacheControl cacheControl = new CacheControl();
		cacheControl.setMaxAge(60);
		
		asyncResponse.resume(Response.ok(result).cacheControl(cacheControl).build());
	}
	
}