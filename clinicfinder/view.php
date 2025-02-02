<?php
session_start();
require_once('db.php'); // Ensure this connects to your database

// Check if the admin is logged in
if (!isset($_SESSION['admin_logged_in']) || $_SESSION['admin_logged_in'] !== true) {
    header("Location: login.php");
    exit();
}

// Fetch users from the database
$query = "SELECT * FROM users ORDER BY date DESC";
$result = mysqli_query($link, $query);
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Information</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: url('bg606.jpg') no-repeat center center/cover;
            color: #2C3E50;
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
            width: 80%;
            margin: 20px auto;
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #BDC3C7;
        }
        th {
            background-color: #2980B9;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #F9F9F9;
        }
    </style>
</head>
<body>
    <div class="header">
        <a href="view.php">User Information</a>
        <a href="clinic.php">Clinic Information</a>
        <a href="aboutus.php">About Us</a>
        <a href="logout.php">Logout</a>
    </div>

    <div class="container">
        <h1 style="text-align:center;">User Information</h1>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Date</th>
                    <th>IP Address</th>
                    <th>Location</th>
                    <th>User Agent</th>
                </tr>
            </thead>
            <tbody>
                <?php while ($row = mysqli_fetch_assoc($result)) { ?>
                <tr>
                    <td><?= htmlspecialchars($row['id']) ?></td>
                    <td><?= htmlspecialchars($row['name']) ?></td>
                    <td><a href="mailto:<?= htmlspecialchars($row['email']) ?>"><?= htmlspecialchars($row['email']) ?></a></td>
                    <td><?= htmlspecialchars($row['date']) ?></td>
                    <td><?= htmlspecialchars($row['ip_address']) ?></td>
                    <td><blockquote><em><?= nl2br(htmlspecialchars($row['location'])) ?></em></blockquote></td>
                    <td><?= htmlspecialchars($row['user_agent']) ?></td>
                </tr>
                <?php } ?>
            </tbody>
        </table>
    </div>
</body>
</html>
