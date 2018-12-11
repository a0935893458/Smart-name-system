<?php
session_start();
?>
<?php
$cap = 'notEq';
if (isset($_POST['submit'])) {
	$username = trim($_POST["username"]);
	$password = trim($_POST["password"]);
	
	if($username == "admin" && $password == "admin") {
		$_SESSION['username']= "admin";
		echo '<script type="text/javascript">window.location = "index.php"</script>';
	} else {
		$msgOut = "Err";		
	}

}      //如果使用admin  登入則回到index介面  然後會有  老師的使用選項  不是就不能登 也不會跳頁面
?>
<!DOCTYPE html>
<html lang="zh-tw">
  <head>
    <meta charset="utf-8">
    <title>NFC點名系統</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Le styles -->
    <link href="./bootstrap/css/bootstrap.css" rel="stylesheet">    		<!-- 匯入格式 -->
    <link href="./bootstrap/css/bootstrap-responsive.css" rel="stylesheet"> <!-- 匯入格式 -->

	
	<style>
		#message-style {
			padding: 20px;
			width: 210px;
			margin: 0px auto;
			margin-left: 100px;
			border: 1px solid lightgrey;
			background: lightgreen;
		}
		
		#message-header-style {
			height: 20px;
			padding: 10px;
			background: green;
			font-weight: bold;
			color: yellow;
		}
		 .cap_status{
				margin-top: 10px;
                width: 185px;
                padding: 10px;
                font: 14px arial;
                color: #fff;
                background-color: oeange;
                display: none;
        }
        .cap_status_error{
           background-color: orange;                
        }
	</style>
	
  </head>

  <body>		<!-- 背景 -->
	<div class="container flld_background">
      <div class="row">
       	       	<div class="span2">
          <div class="well sidebar-nav">
            <ul class="nav nav-list">
              <li class="nav-header">選單</li>
              <li><a href="index.php">首頁</a></li>
			  <li class="nav-header">會員專區</li>
<?php
if (isset($_SESSION['username']))
{
    if ($_SESSION['username'] != "") { 
?>
	<li><a href="adddata.php">新增課程</a></li>
	<li><a href="change.php">設定點名</a></li>	
    <li><a href="checkdata.php">點名頁面</a></li>
	<li><a href="deletedata.php">刪除頁面</a></li>
	<li><a href="check.php">學生出席情況</a></li>	
	<li><a href="logout.php">登出</a></li>  
			</ul>
<?php
    }  //有登入才會顯示
}
else
{
?>
<li><a href="student.php">學生查詢出席情況</a></li>
<li><a href="login.php">導師登入</a></li>
<?php
}      //無登入顯示這個
?>
     
          </div><!--/.well -->
        </div><!--/span-->

        <div class="span10">
          <div class="row">
            <div class="span10"> 
              <div class="hero-unit">	<!-- 主頁logo -->  
          <h1>&nbsp;</h1>
            <p>&nbsp;</p>
          </div>
          <div class="row">
            <div class="span10"> 

<br/><br/>    

<center>
   <div id="message-style">

<div id="message-header-style">
	<center>導師登入</center>
</div>
<form class="form-horizontal" action="login.php" method="post">
	
	<div class="form-group">
		<label for="name">帳號</label>
		<input type="text" name="username" id="username" value="" placeholder="Username" autofocus required style="height: 30px;" />
	</div>
	<div class="form-group">
		<label for="name">密碼</label>
		<input type="password" value="" name="password" id="password" placeholder="Password" required style="height: 30px;" />
	</div>
	<div class="control-group" style="margin-top: 10px;">
		<input type="submit" name="submit" value="登入會員" class="btn btn-primary"/>
		<br>
	</div>
</form>
</div>
</center>

  </body>
</html>
