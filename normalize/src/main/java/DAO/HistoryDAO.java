package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import Model.History;

public class HistoryDAO extends DAO {
    
    private List<History> histories;
    public static int totalPage;

    public HistoryDAO() {
        super();
    }

    public List<History> getHistory(int page) {
        String query = "SELECT * FROM history "
                + "ORDER BY time DESC "
                + "LIMIT 10 OFFSET ?;";
        String totalPageQuery = "SELECT CEIL(COUNT(*) / 10.0) AS totalPage FROM history;";


        
        try {
            histories = new ArrayList<>();
            PreparedStatement ps = con.prepareStatement(query);
            PreparedStatement psPage = con.prepareStatement(totalPageQuery);

            ps.setInt(1, page * 10);

            ResultSet rs = ps.executeQuery();
            ResultSet rsTotalPage = psPage.executeQuery();

            // Fetch records
            while (rs.next()) {
                History history = new History(); // Create a new instance for each record
                history.setId(rs.getInt("id"));
                history.setDescribe(rs.getString("describe"));
                history.setLocation(rs.getString("location"));
                history.setTime(rs.getTimestamp("time").toLocalDateTime());

                histories.add(history);
            }

            // Fetch total pages
            if (rsTotalPage.next()) {
                totalPage = rsTotalPage.getInt("totalPage");
            }

            return histories;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public History getHistoryById(int id) {
        String query = "SELECT * FROM history "
                + "WHERE id = ?;";

        History history = new History();
        try {
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                history.setId(rs.getInt("id"));
                history.setDescribe(rs.getString("describe"));
                history.setLocation(rs.getString("location"));
                history.setTime(rs.getTimestamp("time").toLocalDateTime());

            }

            return history;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean deleteHistoryById(int id) {
    	String query = "DELETE FROM history WHERE id = ?;";
    	
    	try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			int rowDelete = ps.executeUpdate();
			return rowDelete > 0;
			
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
    }
    
    public boolean insertHistory(String describe, LocalDateTime time, String location) {
	
    String query = "INSERT INTO history (`describe`, time, location) VALUES (?, ?, ?);";
    try {
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, describe);
		ps.setTimestamp(2, Timestamp.valueOf(time));
		ps.setString(3, location);
		
		int rowInsert = ps.executeUpdate();
		return rowInsert > 0;
	} catch (Exception e) {
		// TODO: handle exception
		return false;
	}
	}	
}
