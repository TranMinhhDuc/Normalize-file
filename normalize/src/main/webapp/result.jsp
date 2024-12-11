<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Download Result</title>
    <style>
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
</head>
<body>
    <div id="menu">
        <ul>
            <li><a href="/normalize/upload_file">Normalize File</a></li>
            <li><a href="/normalize/history">History</a></li>
        </ul>
    </div>

    <%
        String navigation = (String) session.getAttribute("navigation");
        if ("download".equals(navigation)) {
    %>
        <h1>File Processed Successfully!</h1>
    <%
        } else if ("history".equals(navigation)) {
    %>
        <h1>History Detail</h1>
    <%
        }
    %>

    <h2>File Content:</h2>
    <pre style="border: 1px solid #ccc; padding: 10px; background: #f9f9f9; white-space: pre-wrap; word-wrap: break-word;">
        <%= request.getAttribute("fileContent") != null ? request.getAttribute("fileContent") : "No content available." %>
    </pre>

    <a href="download?fileName=normalized.txt" download="normalized.txt">Download Processed File</a><br>

    <%
            if ("download".equals(navigation)) {
        %>
            <a href="upload_file">Quay lại</a>
        <%
            } else if ("history".equals(navigation)) {
        %>
            <a href="history">Quay lại</a>
        <%
            }
        %>
</body>
</html>
