package com.project.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.entity.Question;
import com.project.interfaces.IQuestionDAO;
import com.project.utils.DbConnect;

public class QuestionDAO implements IQuestionDAO {

	public boolean insertQuestion(Question question) {

		String sql = "insert into questions values(?,?,?,?,?,?,?,?,?)";
		try {

			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, question.getQuestionID());
			ps.setString(2, question.getTitle());
			ps.setString(3, question.getDescription());
			ps.setInt(4, question.getVote());
			ps.setString(5, question.getModifiedAt());
			ps.setInt(6, question.getCategoryID());
			ps.setInt(7, question.getUserID());
			ps.setString(8, question.getImageLink());
			ps.setInt(9, question.getReliability());

			return ps.executeUpdate() > 0; // DML statement
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public List<Question> getAllQuestion() {
		String sql = "select questionId,title,description,votes,modifiedAt,categoryID,userID,imageLink,reliability from questions";

		List<Question> list;
		list = new ArrayList<Question>();
		try (PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql); ResultSet rs = ps.executeQuery(); // DQL
																														// statement
		) {
			 while (rs.next()) {

				Question question = new Question();
				question.setQuestionID(rs.getInt(1));
				question.setTitle(rs.getString(2));
				question.setDescription(rs.getString(3));
				question.setVote(rs.getInt(4));
				question.setCategoryID(rs.getInt(6));
				question.setUserID(rs.getInt(7));
				question.setImageLink(rs.getString(8));
				question.setReliability(rs.getInt(9));
				question.setModifiedAt(rs.getString(5));
				
				
				list.add(question); 
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Question getQuestion(int questionID) {
		// TODO Auto-generated method stub
		return null;
	}

}
