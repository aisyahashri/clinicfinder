<?php
session_start();

// Check if the form is submitted
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Hardcoded username and password
    $username = "admin";
    $password = "1234";

    // Get the input values
    $inputUsername = $_POST['username'];
    $inputPassword = $_POST['password'];

    // Check if the input username and password match the hardcoded ones
    if ($inputUsername == $username && $inputPassword == $password) {
        // Store the username in the session and redirect to the view page
        $_SESSION['admin_logged_in'] = true;
        header("Location: view.php");
        exit;
    } else {
        // Invalid login message
        $error = "Invalid username or password!";
    }
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Login</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap');
        
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Poppins', sans-serif;
        }
        
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background: url('bg606.jpg') no-repeat center center/cover;
        }
        
        .container {
            background: #ffffff;
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);
            text-align: center;
            width: 350px;
        }
        
        .logo {
            width: 150px;
            margin-bottom: 15px;
        }
        
        h2 {
            color: #333333;
            margin-bottom: 20px;
            font-weight: 600;
        }
        
        label {
            display: block;
            color: #555555;
            font-size: 14px;
            text-align: left;
            margin-bottom: 5px;
        }
        
        input {
            width: 100%;
            padding: 12px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 8px;
            background: #f9f9f9;
            color: #333;
            font-size: 14px;
            outline: none;
            transition: 0.3s;
        }
        
        input::placeholder {
            color: #999;
        }
        
        input:focus {
            background: #ffffff;
            border-color: #0072ff;
        }
        
        .error {
            color: #ff4b5c;
            font-size: 14px;
            margin-bottom: 10px;
        }
        
        .btn {
            width: 100%;
            padding: 12px;
            border: none;
            border-radius: 8px;
            background: linear-gradient(45deg, #00c6ff, #0072ff);
            color: white;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            transition: 0.3s;
        }
        
        .btn:hover {
            background: linear-gradient(45deg, #0072ff, #00c6ff);
        }
        
    </style>
</head>
<body>
    <div class="container">
        <img src="logo602.png" alt="Clinic Finder Logo" class="logo">
        <h2>Admin Login</h2>
        <?php if (isset($error)) echo "<p class='error'>$error</p>"; ?>
        <form method="post">
            <label for="username">Username</label>
            <input type="text" id="username" name="username" placeholder="Enter username" required>
            
            <label for="password">Password</label>
            <input type="password" id="password" name="password" placeholder="Enter password" required>
            
            <input type="submit" class="btn" value="Login">
        </form>
    </div>
</body>
</html>

