<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:set var="pb" value="${requestScope.pb}" />




<style>
div.boardlist {
	width: 80%;
}

div.boardlist>h3 {
	font-weight: bold;
	text-align: center;
}

div.boardlist>div.pageInfo {
	text-align: right;
	font-style: italic;
}

div.boardlist>div.table {
	display: table;
	width: 80%;
	margin: 20px auto;
}

div.boardlist>div.table>div.tr {
	display: table-row;
}

div.boardlist>div.table>div.tr>div.th {
	display: table-cell;
	font-weight: bold;
	text-align: center;
}

div.boardlist>div.table>div.tr>div.td {
	display: table-cell;
}

div.boardlist>div.table, div.boardlist div.th, div.boardlist div.td {
	border: 1px solid #93DAFF;
	border-collapse: collapse;
}

div.boardlist>div.pagegroup {
	width: 90%;
	text-align: center;
}

div.boardlist>div.pagegroup>ul {
	margin: 10px auto;
}

div.boardlist>div.pagegroup>ul>li {
	list-style: none;
	display: inline-block;
}

div.boardlist>div.pagegroup a {
	text-decoration: none;
}
</style>



<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>


<script>
	$(function() {
		$("div.boardlist>div.table>div.tr").click(function() {
			if ($(this).index() != 0) {
				var board_seq = $(this).find("div.board_seq").html().trim();
				alert(board_seq + "번 글의 상세정보를 보여줍니다.");
				/* $.ajax({
					url:'boarddetail',
					method:'get',
					data:'board_seq='+board_seq,
					success:function(result){
						//~~~~
					}
				}); */
			}
			return false;
		});
		$("div.boardlist>div.pagegroup a").click(function() {
			var currentPage = $(this).attr("href");
			alert(currentPage + "페이지를 보여줍니다.");
			$.ajax({
				url : '${pb.url}',
				method : 'get',
				data : 'currentPage=' + currentPage,
				success : function(result) {
					$("section").html(result.trim());
				}
			});
			return false;
		});
	});
</script>
 



<div class="boardlist">
	<h3>게시글 목록</h3>
	<div class="pageInfo">현재페이지:${pb.currentPage}page/
		총페이지:${pb.totalPage}page</div>
	<div class="table">
		<div class="tr">
			<div class="th board_seq">글번호</div>
			<div class="th board_subject">글제목</div>
			<div class="th board_writer">작성자</div>
			<div class="th board_date">작성일자</div>
			<div class="th board_viewcont">조회수</div>
		</div>
		<c:forEach var="repBoard" items="${pb.list}">
			<div class="tr">
				<div class="td board_seq">${repBoard.board_seq}</div>
				<div class="td board_subject">
					<%--답글에 대한 들여쓰기(&#10551)를 하려면 글LEVEL이 필요하다.
      --%>
					<c:forEach begin="2" end="${repBoard.level}">
				    &#10551;
				    </c:forEach>
					${repBoard.board_subject}
				</div>
				<div class="td board_writer">${repBoard.board_writer}</div>
				<div class="td board_date">${repBoard.board_date}</div>
				<div class="td board_viewcont">0</div>
			</div>
		</c:forEach>
	</div>
	<div class="pagegroup">
		<ul>
			<c:if test="${pb.startPage > 1 }">
				<li><a href="${pb.startPage - 1}">◀</a></li>
			</c:if>

			<c:forEach begin="${pb.startPage}" end="${pb.endPage}" var="i">
				<c:choose>
					<c:when test="${pb.currentPage == i}">
						<li>${i}</li>
					</c:when>
					<c:otherwise>
						<li><a href="${i}">${i}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${pb.totalPage > pb.endPage }">
				<li><a href="${pb.endPage+1}">▶</a></li>
			</c:if>
		</ul>
	</div>
</div>