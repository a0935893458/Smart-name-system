<?php session_start(); ?>

<!DOCTYPE html>
<html lang="utf-8">
  <head>
    <meta charset="utf-8">
    <title>NFC點名系統</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="./bootstrap/css/bootstrap.css" rel="stylesheet"> 			<!-- 匯入格式 -->
    <link href="./bootstrap/css/bootstrap-responsive.css" rel="stylesheet"> <!-- 匯入格式 -->

  </head>

  <body>	<!-- 背景 -->
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
    }   //有登入才會顯示
}
else
{
?>
<li><a href="student.php">學生查詢出席情況</a></li>
<li><a href="login.php">導師登入</a></li>
<?php
}       //無登入顯示這個
?>
     
          </div><!--/.well -->
        </div><!--/span-->

        <div class="span10">
          <div class="row">
            <div class="span10"> 
              <div class="hero-unit">	 <!-- 主頁logo -->    
          <h1>&nbsp;</h1>
            <p>&nbsp;</p>
          </div>
          <div class="row">
            <div class="span10"> 
<div class="ncku_hidden_logo">
            

<h2 class="h2_added"></h2>

<br/><br/>
</div>

</table>

<center>
	<h2>
					<form name="form" method="post" action="add.php">
  					新增課程(英文)
  					<input type="text" name="keyword" />
					<br><br>
  					<input type="submit" name="button" value="search" />
 					&nbsp;&nbsp; <br/><br><br>
					<p align="center"></p>
					</form>
					<p>&nbsp;</p>
   </h2>
</center>


      <hr>


    </div>

  </body>
</html>
