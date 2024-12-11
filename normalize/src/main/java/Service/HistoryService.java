package Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import DAO.HistoryDAO;
import Model.History;

public class HistoryService {
	
	private HistoryDAO historyDAO;
	private List<History> histories;
	public HistoryService() {
		historyDAO = new HistoryDAO();
		histories = new ArrayList<>();
	}
	
	
	public List<History> getPage(int page) {
		histories = historyDAO.getHistory(page);
		return histories;
	}
	
	public History getHistoryById(int id) {
		
		if(id == 0) return null;
		History history = historyDAO.getHistoryById(id);
		return history;
	}

	public String getFileContent(String location) {
		try {
			Path path = Paths.get(location);
			return Files.readString(path);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean deleteHistory (String id) {
		return historyDAO.deleteHistoryById(Integer.parseInt(id));
	}
}
