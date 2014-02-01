package com.hw2.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;

public class RDS {

	Connection connection;

	public RDS() {
		init();
	}

	private void init() {
		
		try { 
			String hostname = "songsong.cgsg3z6jhozc.us-east-1.rds.amazonaws.com";
			String port = "3306";
			String userName = "songsong";
			String password = "songsong";
			String dbName = "songsong";

			Class.forName("com.mysql.jdbc.Driver");
			String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/"
					+ dbName + "?user=" + userName + "&password=" + password;
			connection = DriverManager.getConnection(jdbcUrl);
		

		} catch (Exception e)	{
			System.out.println("ERROR!" + e.getMessage());
			e.printStackTrace();
		}

	}

	public LinkedList<String> getVideo() {
		try {
			String queryInOrder = "select DISTINCT videoName from RatingSystem order by avgRating desc";
			if (connection != null) {
				PreparedStatement ps2 = connection.prepareStatement(queryInOrder);
				ResultSet resultVideoSet = ps2.executeQuery();
				LinkedList<String> videos = new LinkedList<String>();

				while (resultVideoSet.next()) {
					videos.add(resultVideoSet.getString(1));

				}

				return videos;
			}
			return null;
			
		} catch (SQLException e) {
			System.out.println("ERROR! "+ e.getMessage());
			e.printStackTrace();
			return null;
		}

	}
	
	public boolean setVideo(String vName, int userRating) {

		try {
			

			String query = "select videoID from RatingSystem order by videoID desc";
			if (connection != null) {
				PreparedStatement ps3 = connection.prepareStatement(query);
				ResultSet result = ps3.executeQuery();
				int vID = 1;
				if (result.next()) {
					vID = result.getInt(1) + 1;
				}

				
				java.util.Date date = new java.util.Date();
				Timestamp time = new Timestamp(date.getTime());
				int avgRating = userRating;
				int count = 0;
                
				String insertQuery = "insert into RatingSystem (videoID, videoName, avgRating, time, count, sumRating ) values ("
						+ vID
						+ ", '"
						+ vName
						+ "', "
						+ avgRating
						+ ", '" + time + "', " + count + ", " + userRating + ")";
				
				PreparedStatement ps4 = connection.prepareStatement(insertQuery);
				ps4.execute(insertQuery);
				return true;
			}
			return false;
		}

		catch (Exception e) {
			System.out.println("ERROR! " + e.getMessage());
			e.printStackTrace();
			return false;
		}

	}
	
	
	public boolean rateVideo(String vName, int userRating) {

		try {
			
			String query = "select * from RatingSystem where videoName='"
					+ vName + "'";
			float avgRating;
			float count = 0;
			float totalRating = 0;

			if (connection != null) {
				PreparedStatement ps5 = connection.prepareStatement(query);
				ResultSet result = ps5.executeQuery();
				if (result.next()) {
					count = result.getInt(5) + 1;
					totalRating = result.getInt(6) + userRating;
					avgRating = (totalRating / count);

					String updateQuery = "update RatingSystem SET avgRating ="
							+ avgRating
							+ ", count="
							+ (int) count
							+ ", sumRating="
							+ (int) totalRating
							+ " where videoName='" + vName + "'";
					
					PreparedStatement ps6 = connection.prepareStatement(updateQuery);
					ps6.execute(updateQuery);
				}
				return true;
			}
			return false;
		}

		catch (Exception e) {
			System.out.println("ERROR! " + e.getMessage());
			e.printStackTrace();
			return false;
		}

	}
	
	
	public float getObjectRating(String vName) {
		try {
			String query = "select avgRating from RatingSystem where videoName='"
					+ vName + "'";
			float rating = 0;
			if (connection != null) {
				PreparedStatement ps1 = connection.prepareStatement(query);
				ResultSet result = ps1.executeQuery();

				while (result.next()) {
					rating = result.getFloat(1);
				}

			}
			return rating;
			
		} catch (SQLException e) {
			System.out.println("ERROR!" + e.getMessage());
			e.printStackTrace();
			return 1;
		}

	}

	
	
	

	

	public boolean deleteVideo(String vName) {
		try {

			String deleteQuery = "delete from RatingSystem where videoName='"
					+ vName + "'";
			
			if (connection != null) {
				PreparedStatement ps7 = connection.prepareStatement(deleteQuery);
				ps7.executeUpdate();

				return true;
			}
			return false;
		}

		catch (Exception e) {
			System.out
			.println("ERROR! " + e.getMessage());
			e.printStackTrace();
			return false;

		}

	}

}
