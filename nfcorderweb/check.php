<?php session_start(); ?>

<?php header('refresh: 5;url="check.php"') ?>  <!-- 5秒更新一次資料庫訊息 -->

<!DOCTYPE html>
<html lang="utf-8">
  <head>
    <meta charset="utf-8">
    <title>NFC點名系統</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="./bootstrap/css/bootstrap.css" rel="stylesheet">    		<!-- 匯入格式 -->
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
    }    //有登入才會顯示
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
 <h1>本課程出席次數</h1>
<br>

<h2>
   
<table style="border: 5px double rgb(109, 2, 107); background-color: rgb(255, 255, 255); width: 1000px;" align="center" frame="border" rules="all">
   <tbody>
     <tr>
       <td>CLASS</td>
       <td width="150">照片</td>
       <td width="150">學號</td>
       <td width="100">姓名</td>
       <td>總出席次數</td>
     </tr>
<?php
			include("db.php");
				
			$sql = "SELECT * FROM myclass where noworder = '1'";   //取得noworder為1的
			$us = mysql_query($sql);   //傳回成功  代表有資料
			
			$nowclass=mysql_fetch_row($us);     //取得課程 
			
			$sql = "SELECT * FROM tbl_user";    //取得tb1_user
			$us2 = mysql_query($sql);  //傳回成功  代表有資料
			
			while($data=mysql_fetch_row($us2))  //取得使用者
			{
				$sql = "SELECT * FROM nfcorder where mclass = '$nowclass[1]'";  //在nfcorder取得mclass
				$us1 = mysql_query($sql);  //傳回成功  代表有資料
				$counter = 0;
				while ($data1=mysql_fetch_row($us1))   //取得陣列 
				{
					$counter++;
				}
				
				if ($counter != 0 )
				{
					 echo "<tr><td>$nowclass[1]</td>";
					 echo "<td><img src=\"./upload/$data[3]\" height=\"100\" width=\"100\"></td>";
					 echo "<td>$data[1]</td>";
					 echo "<td>$data[2]</td>";
					 echo "<td>$counter</td></tr>";
					 $counter = 0;
				}
				
			}				
?>
   </tbody>
 </table>    
 <table style="border: 5px double rgb(109, 2, 107); background-color: rgb(255, 255, 255); width: 1000px;" align="center" frame="border" rules="all">
   <tbody>
     <tr>
       <td>CLASS</td>
       <td width="150">照片</td>
       <td width="150">學號</td>
       <td width="100">姓名</td>
       <td>總出席次數</td>
     </tr>
<?php
			include("db.php");
				
			$sql = "SELECT * FROM myclass where noworder = '1'";   //取得noworder為1的
			$us = mysql_query($sql);   //傳回成功  代表有資料
			
			$nowclass=mysql_fetch_row($us);     //取得課程 
			
			$sql = "SELECT * FROM tbl_user";    //取得tb1_user
			$us2 = mysql_query($sql);  //傳回成功  代表有資料
			
			while($data=mysql_fetch_row($us2))  //取得使用者
			{
				$sql = "SELECT * FROM nfcorder where mclass = '$nowclass[1]'";  //在nfcorder取得mclass
				$us1 = mysql_query($sql);  //傳回成功  代表有資料
				$counter = 0;
				while ($data1=mysql_fetch_row($us1))   //取得陣列 
				{
					$counter++;
				}
				
				if ($counter != 0 )
				{
					 echo "<tr><td>$nowclass[1]</td>";
					 echo "<td><img src=\"./upload/$data[3]\" height=\"100\" width=\"100\"></td>";
					 echo "<td>$data[1]</td>";
					 echo "<td>$data[2]</td>";
					 echo "<td>$counter</td></tr>";
					 $counter = 0;
				}
				
			}				
?>
   </tbody>
 </table>    
   
   </h2>
</center>


      <hr>


  </body>
</html>
