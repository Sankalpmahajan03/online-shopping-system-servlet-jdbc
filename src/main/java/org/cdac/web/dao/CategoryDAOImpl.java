package org.cdac.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.cdac.web.entity.Category;
import org.cdac.web.exception.CategoryException;

public class CategoryDAOImpl implements CategoryDAO{
	
	
	Connection connection ;
	PreparedStatement psInsert;
	PreparedStatement psDelete;
	PreparedStatement psFindById;
	PreparedStatement psFindAll;
	
	public CategoryDAOImpl(Connection connection) {
		super();
		try {
			this.connection = connection;
			psFindAll = connection.prepareStatement("select * from Categories");
			 psInsert = connection.prepareStatement(
		                "INSERT INTO Category (categoryId, categoryName, CategoryDescription, categoryImage) VALUES (?, ?, ?, ?)"
		            );
		            psDelete = connection.prepareStatement(
		                "DELETE FROM Categories WHERE categoryId = ?"
		            );
		            psFindById = connection.prepareStatement(
		                "SELECT * FROM Categories WHERE categoryId = ?"
		            );
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addCategory(Category objCategory) throws CategoryException {
		try {
            psInsert.setInt(1, objCategory.getCategoryId());
            psInsert.setString(2, objCategory.getCategoryName());
            psInsert.setString(3, objCategory.getCategoryDescription());
            psInsert.setString(4, objCategory.getCategoryImage());
            psInsert.executeUpdate();
        } catch (SQLException e) {
            throw new CategoryException("Failed to add Category", e);
        }

	}

	@Override
	public void deleteCategory(int categoryId) throws CategoryException {
		try {
            psDelete.setInt(1, categoryId);
            int rows = psDelete.executeUpdate();
            if (rows == 0) {
                throw new CategoryException("No Category found with ID: " + categoryId);
            }
        } catch (SQLException e) {
            throw new CategoryException("Failed to delete Category", e);
        }

	}

	@Override
	public Category findById(int categoryId) throws CategoryException {
		try {
            psFindById.setInt(1, categoryId);
            try (ResultSet result = psFindById.executeQuery()) {
                if (result.next()) {
                    return new Category(
                        result.getInt("categoryId"),
                        result.getString("categoryName"),
                        result.getString("CategoryDescription"),
                        result.getString("categoryImage")
                    );
                } else {
                    throw new CategoryException("Category not found with ID: " + categoryId);
                }
            }
        } catch (SQLException e) {
            throw new CategoryException("Failed to find Category", e);
        }

	}

	@Override
	public Iterator<Category> findAll() throws CategoryException {
		ArrayList<Category> list = new ArrayList<>();
		try(ResultSet result = psFindAll.executeQuery()){
			while(result.next()) {
				Category objCategory = new Category(
						result.getInt("categoryId"),
						result.getString(2),
						result.getString(3),
						result.getString(4)
						);
				list.add(objCategory);
			}
			
		} catch (SQLException e) {
			throw new CategoryException("Failed to obtain Categories",e);
		}
		return list.iterator();
	}

	

}
