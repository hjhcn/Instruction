<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="../common/head.jsp">
	<s:param name="title">
		<s:property escape="false" value="news.title" />-</s:param>
	<s:param name="keywords">
		<s:property escape="false" value="news.title" />,</s:param>
	<s:param name="description"></s:param>
</s:include>
<div class="main">
	<div class="track">
		<a href="/index">首页</a> > <a href="/news/list">3C资讯</a> >
		<s:property escape="false" value="news.title" />
	</div>
	<div class="t-page">
		<div class="left">
			<div class="cat-block">
				<div class="label label1">全部分类</div>
				<div class="content">
					<div id="cid" class="hide">
						<s:property value="cid" />
					</div>
					<s:iterator value="cates" id="cat" status="st">
						<s:if test="#cat.level==0">
							<s:if test="#st.index>0">
								</ul>
				</div>
			</div>
			</s:if>
			<div class="cat">
				<div class="level1">
					<div class="name">
						<a id="cat<s:property value="#cat.id"/>" <s:if test="#cat.id==cid">class="sel-cat"</s:if>
							href="/list/<s:property value="#cat.id"/>.html"><s:property value="#cat.name" /> </a>
					</div>
					<div class="levelswitch open"></div>
				</div>
				<div class="level2 hide">
					<ul>
						</s:if>
						<s:else>
							<li><a id="cat<s:property value="#cat.id"/>"
								<s:if test="#cat.id==cid">class="sel-cat"</s:if>
								href="/list/<s:property value="#cat.id"/>.html"><s:property value="#cat.name" /> </a>
							</li>
						</s:else>
						</s:iterator>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="left-block" id="thread">
		<div class="label label3">网友热议</div>
		<div class="content">
			<ul>
				<s:iterator value="threads" status="st" id="thread">
					<li>. <a target="_blank" href="<s:property value="#thread.link"/>" class="ins"><s:property
								escape="false" value="#thread.title" /> </a>
					</li>
				</s:iterator>
			</ul>
		</div>
	</div>
</div>
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
					<a href="http://sms.139life.com">http://sms.139life.com</a>&nbsp;&nbsp;&nbsp;&nbsp;发布时间:
					<s:property escape="false" value="news.updateTimeFormat" />
					&nbsp;&nbsp;&nbsp;&nbsp;点击:
					<s:property escape="false" value="news.viewCount" />
				</div>
			</div>
			<s:if test="news.uploadTime>1371520859||news.updateTime>1371520859">
				<style>
.news-detail {
	font-family: arial, helvetica, sans-serif;
}

.news-detail p,.news-detail div {
	margin-bottom: 20px;
	font-size: 16px;
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
</div>
</div>
<s:include value="/common/foot.jsp" />
</body>
</html>
