package com.gcu.data;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.gcu.model.PostModel;

@Service
public class OrdersDataService implements DataAccessInterface<PostModel> {

	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	public OrdersDataService(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<PostModel> findAll() {
		
		String sql = "SELECT * FROM posts JOIN users ON users.ID = posts.users_ID";
		List<PostModel> orders = new ArrayList<PostModel>();
		try {
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
			while(srs.next()) {
				orders.add(new PostModel(srs.getString("Title"),
											srs.getString("Username"),
											srs.getString("Caption"),
											srs.getString("Timestamp")));
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return orders;
	}


	@Override
	public boolean create(PostModel post) {
		
		String sql = "INSERT INTO posts(Title, Caption, Timestamp, users_ID, friends_ID) VALUES (?, ?, ?, ?, ?)";
		try {
			int rows = jdbcTemplateObject.update(sql, post.getContent(), post.getTimestamp());
					
			return rows == 1 ? true : false;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean update(PostModel t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(PostModel t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PostModel findByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
