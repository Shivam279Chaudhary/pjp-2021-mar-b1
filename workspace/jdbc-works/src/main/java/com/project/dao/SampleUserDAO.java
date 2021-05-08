package com.project.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.entity.SampleUser;
import com.project.entity.SampleUser;
import com.project.interfaces.ISampleUserDAO;
import com.project.utils.DbConnect;

public class SampleUserDAO implements ISampleUserDAO {

	@Override
	public boolean insertUser(SampleUser user) {
		String sql = "insert into sampleuser (userName,Reputation) values(?,?)";
		try {

			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setInt(2, user.getReputation());

			return ps.executeUpdate() > 0; // DML statement
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List<SampleUser> getAllUsers() {
		String sql = "select userID, userName, reputation from sampleuser";

		List<SampleUser> list;
		list = new ArrayList<SampleUser>();
		try (   
				PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql); 
				ResultSet rs = ps.executeQuery(); // DQL																											// statement
		) {
			while (rs.next()) {

				SampleUser user = new SampleUser();
				user.setUserID(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setReputation(rs.getInt(3));

				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public SampleUser getSampleUserByID(int userID) {
		String sql = "select userID, userName, reputation from sampleuser where userID = ?";
		SampleUser user = new SampleUser();
		try (   
				PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
		) {
			ps.setInt(1, userID);
			ResultSet rs = ps.executeQuery(); // DQL																											// statement
			while (rs.next()) {
				user.setUserID(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setReputation(rs.getInt(3));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean removeUser(int userID) {
		String sql = "delete from sampleuser where userID =?";
		SampleUser user = new SampleUser();
		try (   
				PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
		) {
			ps.setInt(1, userID);
			return ps.executeUpdate()>0; // DQL statement
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
