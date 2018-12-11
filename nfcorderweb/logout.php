<?php
session_start();
unset($_SESSION['username']);   //刪除session
echo "<meta http-equiv=REFRESH CONTENT=1;url=index.php>";  //無法觀看上一頁的情況
?>