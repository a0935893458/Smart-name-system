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
    <link href="./bootstrap/css/bootstrap.css" rel="stylesheet">  			<!-- 匯入格式 -->
    <link href="./bootstrap/css/bootstrap-responsive.css" rel="stylesheet"> <!-- 匯入格式 -->

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
<div class="ncku_hidden_logo">
   <h2 class="h2_added"></h2> 
   
	<br/>
	<br/>
</div>
<center>
  <h1>設定點名</h1>
<br/>

<h2>
  
<table style="border: 5px double rgb(109, 2, 107); background-color: rgb(255, 255, 255); width: 1000px;" align="center" frame="border" rules="all">
   <tbody>
     <tr>
       <td>CLASS(*為現在點名課程)</td>
       <td>設為現在主要</td>
     </tr>
<?php
			include("db.php");
			$sql = "SELECT * FROM myclass";
			$us = mysql_query($sql);     //傳回成功  代表有資料
			while($data1=mysql_fetch_row($us))  //取得陣列 
			{
				if ($data1[2] == "1")
				 echo "<tr><td>$data1[1] * </td>";
				else
				 echo "<tr><td>$data1[1]</td>";
			 
				echo "<td><a href=\"setchange.php?id=$data1[0]\">設為主要</a></td></tr>";
			}				
?>
   </tbody>   
 </table>       <!--  從資料庫myclass抓取課程名   如果myclass第三欄是1就是點名頁面  會有*顯示   可以按設為主要來改哪個課程為主要 -->
   
   </h2>
</center>


      <hr>

  </body>
</html>
