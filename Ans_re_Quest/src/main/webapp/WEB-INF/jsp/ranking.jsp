<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css">
<jsp:include page="head.jsp"/>
<title>ranking</title>
</head>
<body>
    <div id="totalMenu">
        <div class="ranking-container">
            <h1 class="ranking-title">ランキング</h1>
            <ul class="ranking-list">
                <li class="ranking-item">
                    <span class="ranking-position">1</span>
                    <span class="ranking-username">ユーザー1</span>
                    <span class="ranking-score">100</span>
                </li>
                <li class="ranking-item">
                    <span class="ranking-position">2</span>
                    <span class="ranking-username">ユーザー2</span>
                    <span class="ranking-score">90</span>
                </li>
                <li class="ranking-item">
                    <span class="ranking-position">3</span>
                    <span class="ranking-username">ユーザー3</span>
                    <span class="ranking-score">80</span>
                </li>
                       <li class="ranking-item">
                    <span class="ranking-position">4</span>
                    <span class="ranking-username">ユーザー4</span>
                    <span class="ranking-score">70</span>
                </li>
                       <li class="ranking-item">
                    <span class="ranking-position">5</span>
                    <span class="ranking-username">ユーザー5</span>
                    <span class="ranking-score">60</span>
                </li>
                       <li class="ranking-item">
                    <span class="ranking-position">6</span>
                    <span class="ranking-username">ユーザー6</span>
                    <span class="ranking-score">50</span>
                </li>
            </ul>
        </div>
	        <div><p style="word-wrap: break-word;" class="rankingText">ぐぬぬぬ……</p></div>
	        <img alt="" src="imgs/boss.png" class="BOSSwarai">
        
        <jsp:include page="bottomMenu.jsp"/>
    </div>
    <jsp:include page="footer.jsp"/>
</body>
</html>