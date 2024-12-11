<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="Model.History"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.0/css/all.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/styles/main.css">
    <style>
        .table-container {
            margin-left: 2rem;
            margin-right: 2rem;
        }
        .pagination {
            justify-content: center;
            margin-top: 1rem;
        }
         #menu {
            width: 100%;
            background-color: #fff;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }
        #menu ul {
            display: flex;
            justify-content: center;
            gap: 30px;
            padding: 15px 0;
            list-style: none;
            margin: 0;
        }
        #menu ul li {
            list-style: none;
        }
        #menu a {
            color: #333;
            text-decoration: none;
            font-size: 1rem;
            font-weight: bold;
            padding: 10px 15px;
            border-radius: 6px;
            transition: background-color 0.3s ease;
        }
        #menu a:hover {
            background-color: #f1f1f1;
        }
        #menu a:focus {
            outline: none;
        }
    </style>
    <title>Major</title>
</head>
<body>
    <div id="menu">
        <ul>
            <li><a href="/normalize/upload_file">Normalize File</a></li>
            <li><a href="/candidate/experience-candidate">History</a></li>
        </ul>
    </div>
    <h2 class="text-center">History</h2>

    <%
        String message = (String) session.getAttribute("message");
        if (message != null && !message.isEmpty()) {
            session.removeAttribute("message");
    %>
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            <%= message %>
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    <%
        }
    %>

    
    <div class="table-container">
        <table id="history-table" class="table text-center">
            <thead>
                <tr>
                    <th>STT</th>
                    <th>Describe</th>
                    <th>Time</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<History> histories = (List<History>) request.getAttribute("histories");
                    if (histories != null && !histories.isEmpty()) {
                        int stt = 1; // Initialize serial number
                        for (History history : histories) {
                %>
                            <tr>
                                <td><%= stt++ %></td>
                                <td><%= history.getDescribe() %></td>
                                <td><%= history.getTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) %></td>
                                <td>
                                    <form method="get" style="display: inline;">
                                        <input type="hidden" name="action" value="detail">
                                        <input type="hidden" name="id" value="<%= history.getId() %>">
                                        <input type="hidden" name="location" value="<%= history.getLocation() %>">
                                        <button type="submit" class="btn btn-primary btn-sm">Detail</button>
                                    </form>
                                    <form method="post" style="display: inline;">
                                        <input type="hidden" name="action" value="delete">
                                        <input type="hidden" name="id" value="<%= history.getId() %>">
                                        <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Delete forever!');">Delete</button>
                                    </form>
                                </td>
                            </tr>
                <%
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="4">No history records found.</td>
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <div class="pagination">
                <%
                    int currentPage = (int) request.getAttribute("currentPage");
                    int totalPage = (int) request.getAttribute("totalPages");
                    if (currentPage > 0) {
                %>
                    <a href="?page=<%= currentPage - 1 %>">Previous</a>
                <%
                    }
                %>
                <span>Page <%= currentPage + 1 %></span>
                <% if( currentPage + 1 < totalPage) {
                %>
                    <a href="?page=<%= currentPage + 1 %>">Next</a>
                <%
                }
                %>
            </div>
    </div>
</body>
</html>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
