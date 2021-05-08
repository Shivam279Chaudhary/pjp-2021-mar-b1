package com.project.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;




public class Answer {
	
	private int AnswerID;
	private String Description;
	private int Votes;
	private String ModifiedAt;
	private int QuestionID;
	private int UserID;
	private int Reliability;
	public Answer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Answer(int answerID, String description, int votes, String modifiedAt, int questionID, int userID,
			int reliability) {
		super();
		AnswerID = answerID;
		Description = description;
		Votes = votes;
		ModifiedAt = modifiedAt;
		QuestionID = questionID;
		UserID = userID;
		Reliability = reliability;
	}
	public int getAnswerID() {
		return AnswerID;
	}
	public void setAnswerID(int answerID) {
		AnswerID = answerID;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public int getVotes() {
		return Votes;
	}
	public void setVotes(int votes) {
		Votes = votes;
	}
	public String getModifiedAt() {
		return ModifiedAt;
	}
	public void setModifiedAt(String modifiedAt) {
		ModifiedAt = modifiedAt;
	}
	public int getQuestionID() {
		return QuestionID;
	}
	public void setQuestionID(int questionID) {
		QuestionID = questionID;
	}
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	public int getReliability() {
		return Reliability;
	}
	public void setReliability(int reliability) {
		Reliability = reliability;
	}
	@Override
	public String toString() {
		return "Answer [AnswerID=" + AnswerID + ", Description=" + Description + ", Votes=" + Votes + ", ModifiedAt="
				+ ModifiedAt + ", QuestionID=" + QuestionID + ", UserID=" + UserID + ", Reliability=" + Reliability
				+ "]";
	}
	
	
	
	
	
}
