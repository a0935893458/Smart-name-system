
<p align="center">
<h4>
<a href="index.php">首頁</a>
<a href="store.php">商品區|</a>
<a href="search.php">搜尋</a>
<a href="register.php">會員註冊</a>
<a href="talk.php">留言版</a>
<a href="shoppingflow.php">購物流程</a>
<a href="webmapping.php">網站地圖</a>
<a href="admin/index.php">管理</a>
<?php
if (isset($_SESSION['username']))
{
    if ($_SESSION['username'] != "") { 
?>
	<a href="checkshopping.php">購物管理</a>
	<a href="logout.php">登出</a>
<?php
    }
}
?>
</h4>
</p>