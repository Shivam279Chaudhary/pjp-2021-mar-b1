package com.project.dao;

import java.security.InvalidAlgorithmParameterException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.entity.Category;
import com.project.entity.SampleUser;
import com.project.exception.InvalidCategoryInputs;
import com.project.exception.InvalidID;
import com.project.interfaces.ICategoryDAO;
import com.project.utils.DbConnect;

public class CategoryDAO implements ICategoryDAO {

	@Override
	public boolean insertCategory(Category category) {
		try {
			checkInputs(category);
		} catch (InvalidCategoryInputs e1) {
			e1.printStackTrace();
			return false;
		}
		
		String sql = "INSERT INTO category (categoryName) VALUES (?)	";
		try {

			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setString(1, category.getCategoryName());

			return ps.executeUpdate() > 0; // DML statement
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List<Category> getAllCategory() {
		String sql = "SELECT categoryID, categoryName FROM category";

		List<Category> list = new ArrayList<>();
		try (PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);) {
			ResultSet rs = ps.executeQuery(); // DQL // statement
			while (rs.next()) {
				Category cat = new Category();
				cat.setCategoryID(rs.getInt(1));
				cat.setCategoryName(rs.getString(2));
				list.add(cat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Category getCategoryById(int categoryID) {
		try {
			checkID(categoryID);
		} catch (InvalidID e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
		String sql = "SELECT categoryID, categoryName FROM category WHERE categoryID = ?";
		Category cat = new Category();
		try (PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);) {
			ps.setInt(1, categoryID);
			ResultSet rs = ps.executeQuery(); // DQL // statement
			while (rs.next()) {
				cat.setCategoryID(rs.getInt(1));
				cat.setCategoryName(rs.getString(2));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cat;
	}

	@Override
	public boolean removeCategory(int categoryID)  {
		try {
			checkID(categoryID);
		} catch (InvalidID e1) {
			e1.printStackTrace();
		}
		String sql = "DELETE FROM category WHERE categoryID = ?";

		try (PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);) {
			ps.setInt(1, categoryID);
			return ps.executeUpdate() > 0; // DQL statement
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
 
	@Override
	public boolean updateCategory(Category category) {
		try {
			checkID(category.getCategoryID());
		} catch (InvalidID e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sql = "UPDATE category SET categoryName=? WHERE categoryID = ?";

		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setString(1, category.getCategoryName());
			ps.setInt(2, category.getCategoryID());

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	private void checkID(int ID) throws InvalidID {
		String sqlForException = "SELECT categoryID FROM category WHERE categoryID=?";
		try {
			PreparedStatement psException = DbConnect.getMySQLConn().prepareStatement(sqlForException);
			psException.setInt(1, ID);
			ResultSet rs = psException.executeQuery();
			if (!rs.next()) {
				throw new InvalidID("Category");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	
	private void checkInputs(Category category) throws InvalidCategoryInputs {
		String sql = "SELECT * FROM category where categoryName= ?";
		int size = 0;
		try {
			PreparedStatement psException = DbConnect.getMySQLConn().prepareStatement(sql);
			psException.setString(1, category.getCategoryName());
			ResultSet rs = psException.executeQuery();
			if (rs != null) {
				rs.last(); // moves cursor to the last row
				size = rs.getRow(); // get row id
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (size >= 1)
			throw new InvalidCategoryInputs("\n Category Name");
	}

	@Override
	public int getCategoryIDByCategoryName(String name) {
		String sql = "SELECT categoryID FROM category where categoryName=?";
		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if (!rs.next()) {
				throw new InvalidID("Category");
			}
			return rs.getInt(1);
		}catch (InvalidID e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return -1;
	}

}
