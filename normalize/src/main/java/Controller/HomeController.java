package Controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import Service.HomeService;

@MultipartConfig
@WebServlet(urlPatterns = {"/upload_file", "/normalize"})
public class HomeController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private HomeService homeService;

    @Override
    public void init() throws ServletException {
        homeService = new HomeService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Part filePart = req.getPart("file");
            String result = homeService.inputProcess(filePart);
            System.out.println(result);
            if ("false process".equals(result)) {
                req.setAttribute("errorMessage", "File processing failed.");
                RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
                dispatcher.forward(req, resp);
                return;
            }

            String tempDir = getServletContext().getRealPath("/temp");
            File dir = new File(tempDir);

            File processedFile = new File(dir, "normalized.txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(processedFile))) {
                writer.write(result);
            }

            HttpSession session = req.getSession(false);
            session.setAttribute("navigation", "download");
            req.setAttribute("fileContent", result);
            RequestDispatcher dispatcher = req.getRequestDispatcher("result.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errorMessage", "An error occurred while processing the file.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
