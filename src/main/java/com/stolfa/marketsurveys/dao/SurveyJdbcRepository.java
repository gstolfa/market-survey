package com.stolfa.marketsurveys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.stolfa.marketsurveys.model.Content;
import com.stolfa.marketsurveys.model.Question;
import com.stolfa.marketsurveys.model.Requester;
import com.stolfa.marketsurveys.model.Survey;

@Repository
public class SurveyJdbcRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public static final String SELECT_QUESTIONS_BY_ID_SURVEY = "SELECT Q.ID, SQ.page, Q.HEADING , Q.POSITION , Q.FAMILY  FROM R_SURVEY_QUESTION SQ, QUESTION Q WHERE SQ.ID_SURVEY = ? AND SQ.ID_QUESTION = Q.ID";

	public static final String SELECT_CONTENTS_BY_ID_QUESTION = "SELECT QC.ID_QUESTION , QC.ID_CONTENT , QC.POSITION , QC.VISIBLE , QC.REQUIRED , QC.ANSWER_TYPE , C.TEXT , C.IMAGE_URL FROM R_QUESTION_CONTENT  QC, CONTENT C  WHERE C.ID = QC.ID_CONTENT AND QC.ID_QUESTION = ? ORDER BY QC.POSITION";

	public static final String SELECT_SURVEY_BY_ID = "SELECT * FROM SURVEY WHERE ID=?"; 

	public static final String SELECT_REQUESTER_BY_ID = "SELECT S.CHANNEL , S.CATEGORY , S.FREQUENCY , R.ID , R.NAME , R.EMAIL , R.ADDRESS , R.TELEPHONE_NUMBER  FROM SUBSCRIPTION S, REQUESTER R WHERE R.ID = S.ID_REQUESTER  AND R.ID = ?";

	public static final String SELECT_SURVEY_BY_TARGET_AND_CATEGORY = "SELECT S.ID, S.TITLE, S.CATEGORY, S.LANGUAGE, S.DATE_CREATED, S.ID_TARGET, S.COUNTRY FROM TARGET T, SURVEY S WHERE S.ID_TARGET = T.ID "; 
	
	public static final String SELECT_REQUESTERS_BY_FREQUENCY = "SELECT S.CHANNEL , S.CATEGORY , S.FREQUENCY , R.ID , R.NAME , R.EMAIL , R.ADDRESS , R.TELEPHONE_NUMBER FROM REQUESTER R, SUBSCRIPTION S WHERE R.ID = S.ID_REQUESTER  AND S.FREQUENCY  = ?";
	
	class RequesterRowMapper implements RowMapper<Requester> {
		@Override
		public Requester mapRow(ResultSet rs, int rowNum) throws SQLException {

			Requester requester = new Requester();

			requester.setId(rs.getLong("id"));
			requester.setTelephoneNumber(rs.getString("telephone_number"));
			requester.setAddress(rs.getString("address"));
			requester.setEmail(rs.getString("email"));
			requester.setName(rs.getString("name"));
			requester.setChannel(rs.getString("channel"));
			requester.setCategory(rs.getString("category"));
			requester.setFrequency(rs.getString("frequency"));
			
			return requester;
		}
	}

	
	class ContentRowMapper implements RowMapper<Content> {
		@Override
		public Content mapRow(ResultSet rs, int rowNum) throws SQLException {
			Content content = new Content();
			content.setId(rs.getLong("id_content"));
			content.setIdQuestion(rs.getLong("id_question"));
			content.setPosition(rs.getInt("position"));
			content.setVisible(rs.getBoolean("visible"));
			content.setRequired(rs.getBoolean("required"));
			content.setAnswerType(rs.getString("answer_type"));
			content.setText(rs.getString("text"));
			content.setImageUrl(rs.getString("image_url"));
			return content;
		}
	}
	
	class QuestionRowMapper implements RowMapper<Question> {
		@Override
		public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
			Question question = new Question();
			question.setId(rs.getLong("id"));
			question.setPage(rs.getInt("page"));
			question.setHeading(rs.getString("heading"));
			question.setPosition(rs.getInt("position"));
			question.setFamily(rs.getString("family"));
			return question;
		}
	}
	
	class SurveyRowMapper implements RowMapper<Survey> {
		@Override
		public Survey mapRow(ResultSet rs, int rowNum) throws SQLException {
			Survey survey = new Survey();
			survey.setId(rs.getLong("id"));
			survey.setCategory(rs.getString("category"));
			survey.setCountry(rs.getString("country"));
			survey.setDateCreated(rs.getDate("date_created"));
			survey.setIdTarget(rs.getLong("id_target"));
			survey.setLanguage(rs.getString("language"));
			survey.setTitle(rs.getString("title"));
			return survey;
		}
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
	 */
	public List<Survey> findSurveySByTargetAndCategory(String gender, Integer ageFrom, Integer ageTo, Integer incomeFrom, Integer incomeTo, String category) {

		List<Object> parametersList = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder(SELECT_SURVEY_BY_TARGET_AND_CATEGORY);
		if (gender != null && !gender.isEmpty()) {
			parametersList.add(gender);
			sql.append(" AND T.GENDER = ? ");
		}
		if (ageFrom != null && ageTo != null) {
			parametersList.add(ageFrom);
			parametersList.add(ageTo);
			sql.append(" AND T.AGE_FROM >= ? AND T.AGE_TO <= ? ");
		}
		if (incomeFrom != null && incomeTo != null) {
			parametersList.add(incomeFrom);
			parametersList.add(incomeTo);
			sql.append(" AND T.INCOME_FROM >= ? AND T.INCOME_TO <= ? ");
		}
		if (category != null && !category.isEmpty()) {
			parametersList.add(category);
			sql.append(" AND S.CATEGORY = ? ");
		}

		return jdbcTemplate.query(sql.toString(), parametersList.toArray(), new SurveyRowMapper());
	}

	/**
	 * Find requester by frequency.
	 * 
	 * @param frequency
	 * @return
	 */
	public List<Requester> findRequestersByFrequency(String frequency) {
		return jdbcTemplate.query(SELECT_REQUESTERS_BY_FREQUENCY, new Object[] { frequency }, new RequesterRowMapper());
	}

	
	/**
	 * Find requester by id.
	 * 
	 * @param id
	 * @return
	 */
	public List<Requester> findRequesterById(long id) {
		return jdbcTemplate.query(SELECT_REQUESTER_BY_ID, new Object[] { id }, new RequesterRowMapper());
	}
	
	/**
	 * Find survey by id.
	 * 
	 * @param id
	 * @return
	 */
	public List<Survey> findSurveyById(long id) {
		return jdbcTemplate.query(SELECT_SURVEY_BY_ID, new Object[] { id }, new SurveyRowMapper());
	}

	/**
	 * Find all contents object by question id.
	 * 
	 * @param id
	 * @return
	 */
	public List<Content> findContentsByIdQuestion(long id) {
		return jdbcTemplate.query(SELECT_CONTENTS_BY_ID_QUESTION, new Object[] { id }, new ContentRowMapper());
	}

	
	/**
	 * Find all questions object by Survey id.
	 * 
	 * @param id
	 * @return
	 */
	public List<Question> findQuestionsByIdSurvey(long id) {
		return jdbcTemplate.query(SELECT_QUESTIONS_BY_ID_SURVEY, new Object[] { id }, new QuestionRowMapper());
	}
	
}
