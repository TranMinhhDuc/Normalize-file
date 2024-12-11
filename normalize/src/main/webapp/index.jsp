<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Upload File</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            height: 100vh;
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
        .upload-container {
            background: #fff;
            padding: 30px 40px;
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            text-align: center;
            width: 100%;
            max-width: 450px;
        }
        h1 {
            font-size: 1.8rem;
            margin-bottom: 20px;
            color: #333;
            font-weight: bold;
        }
        .file-input {
            margin: 20px 0;
            display: block;
            width: 100%;
            padding: 12px;
            border: 1px solid #ddd;
            border-radius: 6px;
            font-size: 1rem;
        }
        .btn {
            background-color: #4CAF50;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-size: 1rem;
            transition: background-color 0.3s ease;
            width: 100%;
        }
        .btn:hover {
            background-color: #45a049;
        }
        .btn:focus {
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
    <div class="upload-container">
        <h1>Upload File</h1>
        <form method="post" enctype="multipart/form-data">
            <input type="file" name="file" class="file-input" required>
            <button type="submit" class="btn">Normalize</button>
        </form>
    </div>
</body>
</html>
