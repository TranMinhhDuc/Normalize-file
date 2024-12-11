package Controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.HistoryDAO;
import Model.History;
import Service.HistoryService;

@WebServlet(urlPatterns = {("/history")})
public class HistoryController extends HttpServlet {
	private List<History> histories;
	private HistoryService historyService;
	private History history;
	private HistoryDAO historyDAO;
	@Override
	public void init() throws ServletException {
		histories = new ArrayList<>();
		historyService = new HistoryService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int page = req.getParameter("page") != null?Integer.parseInt(req.getParameter("page")):0;
		int totalPages = historyDAO.totalPage;
		String action = req.getParameter("action") != null ? req.getParameter("action"):null;
		histories = historyService.getPage(page);
		if ("detail".equals(action)){
			String id = req.getParameter("id");
			String location = req.getParameter("location");
			String content = historyService.getFileContent(location);
			System.out.println("file content" + " " + content);
			String tempDir = getServletContext().getRealPath("/temp");
			File dir = new File(tempDir);
			File processedFile = new File(dir, "normalized.txt");
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(processedFile))) {
				writer.write(content);
			}

			HttpSession session = req.getSession(false);
			session.setAttribute("navigation", "history");
			req.setAttribute("fileContent", content);
			RequestDispatcher dispatcher = req.getRequestDispatcher("result.jsp");
			dispatcher.forward(req, resp);

		} else {
			req.setAttribute("currentPage", page);
			req.setAttribute("totalPages", totalPages);
			req.setAttribute("histories", histories);
			RequestDispatcher dispatcher = req.getRequestDispatcher("History.jsp");
			dispatcher.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action") != null? req.getParameter("action"):null;
		if ("delete".equals(action)) {
			String id = req.getParameter("id");
			HttpSession session = req.getSession(false);
			session.setAttribute("message", "xóa thành công");
			System.out.println(historyService.deleteHistory(id));
			resp.sendRedirect("history");
		}
	}
}
