package Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.servlet.http.Part;

import DAO.HistoryDAO;

public class HomeService {
	
	private NormalizeFile normalizeFile;
	private HistoryDAO historyDAO;
	
	public HomeService() {
		normalizeFile = new NormalizeFile();
		historyDAO = new HistoryDAO();
	}

	public String inputProcess(Part filePart) throws IOException {
		InputStream fileContent = filePart.getInputStream();
		
		if (fileContent != null) {
			String processContent = normalizeFile.normalize(fileContent);
			System.out.println(saveOutputFile(processContent));
			return processContent;
		} else return "false process";
	}
	
	private boolean saveOutputFile(String content) {
	    String randomFileName = UUID.randomUUID() + ".txt";
	    File outputDir = new File("D:\\output");
	    File outputFile = new File(outputDir, randomFileName);

	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
	        writer.write(content);

	        String location = outputFile.getAbsolutePath();
	        String describe = content.length() > 50 ? content.substring(0, 50) + "..." : content;
			LocalDateTime time = LocalDateTime.now();
	        return historyDAO.insertHistory(describe, time, location);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
}
