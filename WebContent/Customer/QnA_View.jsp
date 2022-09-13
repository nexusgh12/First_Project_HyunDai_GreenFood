<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/Inc/Header.jspf"%>
<link rel="stylesheet" type="text/css"
	href="/First_Project_HyunDai_GreenFood/css/Customer/Customer_style.css">

<!-- container -->
<div class="container_area" id="contents">
	<!-- Body
    ################# -->
	<!-- contents : str -->
	<div class="sub_layout sub_visual_6">

		<!-- 하이어라키 -->
		<ul class="hierarchy_list">
			<li class="icon_home"><img
				src="/First_Project_HyunDai_GreenFood/img/icon/icon_home.png"
				alt="Home" /></li>
			<li class="depth_2"><a href="#" class="m_hierarchy">고객센터 </a>

				<ul class="hierarchy_depth" style="display: none;">
					<li><a href="#">고객센터</a></li>
				</ul></li>
			<li class="depth_3"><a href="#" class="t_block_hierarchy">고객의
					소리 </a>

				<ul class="hierarchy_depth">
					<li><a href="#">자주 묻는 질문</a></li>
					<li><a href="#">거래·상담</a></li>
					<li class="on"><a href="#">고객의 소리</a></li>
				</ul></li>
		</ul>
		<!-- //하이어라키 -->
		<!-- 서브 컨텐츠 -->
		<div class="sub_contents">
			<!-- 타이틀 , 디스크립션 -->
			<div class="title_description">
				<h2 class="title_sub">고객의 소리</h2>

				<p class="sub_description">고객의 작은 목소리에도 귀 기울이겠습니다.</p>
			</div>
			
			<div class="content-body">
				<h4>제목입니다.</h4>
				<ul>
					<li> 아이디 | 2022.09.13</li>
					<li>조회 10</li>
				</ul>
				<hr>
				<div class="board-content">
					<span>본문 내용입니다.</span>
				</div>
			</div>

			<div class="reply-box">
				<h5>전체 댓글</h5>
				<hr style="border-color: #46675c; border-width: 3px;">
				<!-- 댓글목록이 나올 자리 -->
				<div id="replyList"></div>

				<!-- 댓글쓰기 -->
				<div id="replyWrite">
						<form method="post" id="replyFrm">
							<input type="hidden" name="no" value="" />
							<div id="replyWrite-userid">댓글 작성</div>
							<textarea class="form-control" name="coment" id="coment"></textarea>
							<input type="submit" class="btn btn-default" id="coment-submit" value="댓글등록">
						</form>
				</div>
			</div>
			<div id="edit-box">
				<!-- 로그인 아이디와 글쓴이가 같을 경우 수정 -->
				<c:if test="${logId == vo.userid }">
					<a href="/First_Project_HyunDai_GreenFood/Customer/QnA_Edit.jsp">수정</a>
					<a href="javascript:delCheck()">삭제</a>
				</c:if>
			</div>
			<!-- contents : end -->
		</div>
		<!-- //container -->
		<%@ include file="/Inc/Footer.jspf"%>