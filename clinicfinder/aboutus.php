<?php
session_start();

// Check if the admin is logged in
if (!isset($_SESSION['admin_logged_in']) || $_SESSION['admin_logged_in'] !== true) {
    header("Location: login.php");
    exit();
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>About Us</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background: url('bg606.jpg') no-repeat center center/cover;
            margin: 0;
            padding: 0;
        }
        .header {
    background-color: white;
    padding: 15px;
    text-align: center;
    color: black;
}

.header a {
    color: black;
    margin: 0 10px;
    text-decoration: none;
    font-size: 18px;
}

.header a:hover {
    text-decoration: underline;
}

        .container {
            display: flex;
            justify-content: center;
            flex-wrap: wrap;
            padding: 20px;
        }
        .title {
            font-size: 30px;
            font-weight: bold;
            color: #fff;
            text-align: center;
            margin-bottom: 20px;
        }
        .member {
            background-color: white;
            padding: 20px;
            margin: 15px;
            border-radius: 8px;
            width: 200px;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        .member img {
            width: 100%;
            height: 200px;
            object-fit: cover;
            border-radius: 50%;
        }
        .member h3 {
            margin: 10px 0;
            color: #2C3E50;
        }
        .member p {
            color:rgb(29, 72, 115);
        }
        .footer {
            background-color: #fff;
            color: #2C3E50;
            text-align: center;
            padding: 10px;
            position: fixed;
            width: 100%;
            bottom: 0;
        }
    </style>
</head>
<body>

<div class="header">
        <a href="view.php">User Information</a>
        <a href="clinic.php">Clinic Information</a>  <!-- ✅ New Clinic Menu -->
        <a href="aboutus.php">About Us</a>
        <a href="logout.php">Logout</a>
    </div>
    <br><br>
    <div class="title">Meet Our Team</div>
    <div class="container">
        <div class="member">
            <img src="hazwan.jpg" alt="Member 1">
            <h3>MUHAMMAD SYAFIQ NORHAZWAN BIN NOR RAMZI</h3>
            <p>2023305121</p>
        </div>
        <div class="member">
            <img src="irfan.jpg" alt="Member 2">
            <h3>IRFAN NAQIUDDIN BIN MAZDI FAIZAL</h3>
            <p>2022495926</p>
        </div>
        <div class="member">
            <img src="aisyah.jpg" alt="Member 3">
            <h3>SITI AISYAH BINTI ASHRI</h3>
            <p>2021826476</p>
        </div>
        <div class="member">
            <img src="ain.jpg" alt="Member 4">
            <h3>NURUL AIN NATASYA BINTI OTHMAN</h3>
            <p>2022610424</p>
        </div>
    </div>

    <div class="footer">
        <p>&copy; 2025 Clinic Finder. All rights reserved.</p>
    </div>

</body>
</html>
