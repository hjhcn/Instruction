<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="../common/headApp.jsp">
	<s:param name="title">
		<s:property escape="false" value="news.title" />-</s:param>
	<s:param name="keywords">
		<s:property escape="false" value="news.title" />,</s:param>
	<s:param name="description"></s:param>
</s:include>
<style>
.right {
	width: 100%;
}

.news-title {
	padding: 5px;
	border-bottom: solid 1px #CCC;
	font-size: 20px;
	text-align: center;
}

.news-title-info {
	color: #F00;
	font-size: 12px;
	text-align: center;
	font-weight: 100;
	padding-bottom: 10px;
	padding-top: 5px;
}

.news-title-info a:link,.news-title-info a:hover,.news-title-info a:visited {
	color: #F00;
}

.news-detail {
	font-family: arial, helvetica, sans-serif;
	width: 98%;
	margin: 0 auto;
}

.news-detail img {
	width: 100%;
}
.news-detail p,.news-detail div,.news-detail span {
	width: 100%;
}

</style>
<div class="right">
	<div class=" right-block">
		<s:if test="news==null">
			<div style="color:#FF8000; font-size:25px;margin:0 auto; width:550px;">对不起，您查看的资讯不存在或者正在审核中。</div>
		</s:if>
		<s:else>
			<div class="title">
				<div class="news-title">
					<s:property escape="false" value="news.title" />
				</div>
				<div class="news-title-info">
					发布时间:
					<s:property escape="false" value="news.updateTimeFormat" />
					&nbsp;&nbsp;&nbsp;&nbsp;点击:
					<s:property escape="false" value="news.viewCount" />
				</div>
			</div>
			<s:if test="news.uploadTime>1371520859||news.updateTime>1371520859">
<style>
.news-detail p,.news-detail div {
	margin-bottom: 20px;
}
</style>
			</s:if>
			<div class="detail">
				<div class="news-detail">

					<s:property escape="false" value="news.content" />
				</div>
			</div>
		</s:else>
	</div>
</div>
</body>
</html>