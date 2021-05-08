package com.project.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.entity.Answer;
import com.project.exception.InvalidID;
import com.project.interfaces.IAnswerDAO;
import com.project.utils.DbConnect;


@Service
public class AnswerDAO implements IAnswerDAO {

	@Override
	public boolean insertAnswer(Answer answer) {
		// TODO Auto-generated method stub

		String sql = "insert into answer (Description,Votes,ModifiedAt,QuestionID,UserID,Reliability) values(?,?,?,?,?,?)";
		try {

			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setString(1, answer.getDescription());
			ps.setInt(2, answer.getVotes());
			ps.setString(3, answer.getModifiedAt());
			ps.setInt(4, answer.getQuestionID());
			ps.setInt(5, answer.getUserID());
			ps.setInt(6, answer.getReliability());

			return ps.executeUpdate() > 0; // DML statement
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public Answer getAnswerByAnswerID(int AnswerID) {

		try {
			checkID(AnswerID);
		} catch (InvalidID e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}

		String sql = "select * from answer where AnswerID=?";
		try {

			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, AnswerID);
			ResultSet rs = ps.executeQuery();

			rs.next();

			Answer answer = new Answer();
			answer.setAnswerID(rs.getInt(1));
			answer.setDescription(rs.getString(2));
			answer.setVotes(rs.getInt(3));
			answer.setModifiedAt(rs.getString(4));
			answer.setQuestionID(rs.getInt(5));
			answer.setUserID(rs.getInt(6));
			answer.setReliability(rs.getInt(7));

			return answer;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<Answer> getAllAnswersASC(int QuestionID) {
		try {
			checkQuestionID(QuestionID);
		} catch (InvalidID e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}

		String sql = "select * from answer where QuestionID= ? order by Votes ASC";

		List<Answer> list;
		list = new ArrayList<Answer>();
		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, QuestionID);
			ResultSet rs = ps.executeQuery(); // DQL statement

			while (rs.next()) {

				Answer answer = new Answer();
				answer.setAnswerID(rs.getInt(1));
				answer.setDescription(rs.getString(2));
				answer.setVotes(rs.getInt(3));
				answer.setModifiedAt(rs.getString(4));
				answer.setQuestionID(rs.getInt(5));
				answer.setUserID(rs.getInt(6));
				answer.setReliability(rs.getInt(7));

				list.add(answer);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	@Override
	public List<Answer> getAllAnswersDESC(int QuestionID) {
		// TODO Auto-generated method stub

		try {
			checkQuestionID(QuestionID);
		} catch (InvalidID e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
		String sql = "select * from answer where QuestionID= ? order by Votes DESC";

		List<Answer> list;
		list = new ArrayList<Answer>();
		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, QuestionID);
			ResultSet rs = ps.executeQuery(); // DQL statement

			while (rs.next()) {

				Answer answer = new Answer();
				answer.setAnswerID(rs.getInt(1));
				answer.setDescription(rs.getString(2));
				answer.setVotes(rs.getInt(3));
				answer.setModifiedAt(rs.getString(4));
				answer.setQuestionID(rs.getInt(5));
				answer.setUserID(rs.getInt(6));
				answer.setReliability(rs.getInt(7));

				list.add(answer);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean deleteAnswer(int AnswerID) {
		// TODO Auto-generated method stub
		try {
			checkID(AnswerID);
		} catch (InvalidID e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}

		try {
			String sql = "DELETE from answer where AnswerID= ? ";
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, AnswerID);

			return ps.executeUpdate() > 0; // DML statement

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

	@Override
	public boolean deleteAnswersByQuestionID(int QuestionID) {
		// TODO Auto-generated method stub
		try {
			checkQuestionID(QuestionID);
		} catch (InvalidID e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}

		try {
			String sql = "DELETE from answer where QuestionID= ? ";
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, QuestionID);

			return ps.executeUpdate() > 0; // DML statement

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

	@Override
	public boolean updateAnswerByAnswerID(Answer answer) {
		// TODO Auto-generated method stub
		try {
			checkID(answer.getAnswerID());
		} catch (InvalidID e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}

		String sql = "UPDATE answer SET Description=?,Votes=?,ModifiedAt=?,QuestionID=?,UserID=?,Reliability=? WHERE AnswerID=?";

		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setString(1, answer.getDescription());
			ps.setInt(2, answer.getVotes());
			ps.setString(3, answer.getModifiedAt());
			ps.setInt(4, answer.getQuestionID());
			ps.setInt(5, answer.getUserID());
			ps.setInt(6, answer.getReliability());
			ps.setInt(7, answer.getAnswerID());

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

	@Override
	public int getLatestAnswerID() {

		String sql = "SELECT AnswerID from answer ORDER BY AnswerID DESC LIMIT 1";

		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			rs.next();

			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;

	}

	private void checkID(int ID) throws InvalidID {
		String sqlForException = "SELECT * FROM answer WHERE AnswerID=?";
		try {
			PreparedStatement psException = DbConnect.getMySQLConn().prepareStatement(sqlForException);
			psException.setInt(1, ID);
			ResultSet rs = psException.executeQuery();
			if (!rs.next()) {
				throw new InvalidID("Answer");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void checkQuestionID(int questionID) throws InvalidID {
		// TODO Auto-generated method stub

		String sqlForException = "SELECT * FROM questions WHERE QuestionID=?";
		try {
			PreparedStatement psException = DbConnect.getMySQLConn().prepareStatement(sqlForException);
			psException.setInt(1, questionID);
			ResultSet rs = psException.executeQuery();
			if (!rs.next()) {
				throw new InvalidID("Question");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

//	private void checkInputs(Answer answer) throws InvalidCategoryInputs {
//		String sql = "SELECT * FROM category where categoryName= ?";
//		int size = 0;
//		try {
//			PreparedStatement psException = DbConnect.getMySQLConn().prepareStatement(sql);
//			psException.setString(1, category.getCategoryName());
//			ResultSet rs = psException.executeQuery();
//			if (rs != null) {
//				rs.last(); // moves cursor to the last row
//				size = rs.getRow(); // get row id
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		if (size >= 1)
//			throw new InvalidCategoryInputs("\n Category Name \n CategoryName");
//	}

}
