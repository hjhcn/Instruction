<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="../common/head.jsp" />
<div class="main">
	<div class="track">
		<a href="/index">首页</a> >
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

	<div class="right-block">
		<div class="label label2">产品列表</div>
		<div class="content">
			<s:if test="inses.totalpage>1">
				<div class="page">
					<s:bean name="org.apache.struts2.util.Counter" id="counter">
						<s:param name="first" value="inses.pageindex.startindex" />
						<s:param name="last" value="inses.pageindex.endindex" />
						<s:if test="inses.pageindex.startindex>1">
							<a
								href="/list/<s:property value="cid"/>-<s:property value="bid"/>-<s:property value="search"/>-1-<s:property value="pageSize"/>-<s:property value="mobile3d"/>.html"
								class="page_link">1</a>
							<s:if test="inses.pageindex.startindex>2">
								<span class="omission">...</span>
							</s:if>
						</s:if>
						<s:iterator>
							<s:if test="inses.currentpage!=current-1">
								<a
									href="/list/<s:property value="cid"/>-<s:property value="bid"/>-<s:property value="search"/>-<s:property value="current-1"/>-<s:property value="pageSize"/>-<s:property value="mobile3d"/>.html"
									class="page_link"><s:property value="current-1" /> </a>
							</s:if>
							<s:else>
								<span class="current_page"><s:property value="current-1" /> </span>
							</s:else>
						</s:iterator>
						<s:if test="inses.pageindex.endindex<inses.totalpage">
							<s:if test="inses.totalpage-inses.pageindex.endindex>1">
								<span class="omission">...</span>
							</s:if>
							<a
								href="/list/<s:property value="cid"/>-<s:property value="bid"/>-<s:property value="search"/>-<s:property value="inses.totalpage"/>-<s:property value="pageSize"/>.html"
								class="page_link"><s:property value="inses.totalpage" /> </a>
						</s:if>
					</s:bean>
				</div>
			</s:if>
			<div class="list">
				<ul>
					<s:if test="inses.records.size==0">
                       该列别下目前还没有说明书！
                   </s:if>
					<s:iterator value="inses.records" status="st" id="ins">
						<li class="item">
							<div class="icon">
								<a target="_blank" href="/ins/<s:property escape="false"  value="#ins.id"/>.html"><img
									width="130px" onerror="this.src='/css/images/default130.png'"
									src="<s:property escape="false"  value="#ins.iconUrl"/>"
									alt="<s:property escape="false"  value="#ins.title"/>" /> </a>
							</div>
							<div class="info-list">
								<div class="titlelink">
									<a target="_blank" href="/ins/<s:property escape="false"  value="#ins.id"/>.html"><s:property
											escape="false" value="#ins.title" /> </a>
								</div>
								<ul>
									<li>更新时间: <s:property escape="false" value="#ins.updateTimeFormat" /></li>
									<li>授权形式: 免费</li>
									<li>评论等级: <span>★★★★★</span>
									</li>
									<li>评论次数: <s:property escape="false" value="#ins.commentCount" />次</li>
									<li>语言: 简体中文</li>
									<li>下载次数: <s:property escape="false" value="#ins.downloadCount" />次</li>
								</ul>
							</div>
							<div class="btn-list">
								<a href="/ins_downloadAuth?id=<s:property escape="false"  value="#ins.id"/>" target="_blank"><div
										class="btn">
										<div class="down-btn"></div>
										<div class="txt">点击下载</div>
									</div> </a> <a href="/ins/<s:property escape="false"  value="#ins.id"/>.html#read" target="_blank"><div
										class="btn">
										<div class="read-btn"></div>
										<div class="txt">在线阅读</div>
									</div> </a>
								<s:if test="#ins.mobileSWFUrl!=null&&#ins.mobileSWFUrl!=''">
									<a href="/ins/<s:property escape="false"  value="#ins.id"/>.html#down" target="_blank"><div
											class="btn">
											<div class="down-btn"></div>
											<div class="txt">手机体验</div>
										</div> </a>
								</s:if>
								<s:if test="#ins.mobile3DSWFUrl!=null&&#ins.mobile3DSWFUrl!=''">
									<a href="/ins/<s:property escape="false"  value="#ins.id"/>.html#down" target="_blank"><div
											class="btn">
											<div class="down-btn"></div>
											<div class="txt">立体外观</div>
										</div> </a>
								</s:if>
							</div></li>
					</s:iterator>
				</ul>
			</div>

			<s:if test="inses.totalpage>1">
				<div class="page">
					<s:bean name="org.apache.struts2.util.Counter" id="counter">
						<s:param name="first" value="inses.pageindex.startindex" />
						<s:param name="last" value="inses.pageindex.endindex" />
						<s:if test="inses.pageindex.startindex>1">
							<a
								href="/list/<s:property value="cid"/>-<s:property value="bid"/>-<s:property value="search"/>-1-<s:property value="pageSize"/>-<s:property value="mobile3d"/>.html"
								class="page_link">1</a>
							<s:if test="inses.pageindex.startindex>2">
								<span class="omission">...</span>
							</s:if>
						</s:if>
						<s:iterator>
							<s:if test="inses.currentpage!=current-1">
								<a
									href="/list/<s:property value="cid"/>-<s:property value="bid"/>-<s:property value="search"/>-<s:property value="current-1"/>-<s:property value="pageSize"/>-<s:property value="mobile3d"/>.html"
									class="page_link"><s:property value="current-1" /> </a>
							</s:if>
							<s:else>
								<span class="current_page"><s:property value="current-1" /> </span>
							</s:else>
						</s:iterator>
						<s:if test="inses.pageindex.endindex<inses.totalpage">
							<s:if test="inses.totalpage-inses.pageindex.endindex>1">
								<span class="omission">...</span>
							</s:if>
							<a
								href="/list/<s:property value="cid"/>-<s:property value="bid"/>-<s:property value="search"/>-<s:property value="inses.totalpage"/>-<s:property value="pageSize"/>.html"
								class="page_link"><s:property value="inses.totalpage" /> </a>
						</s:if>
					</s:bean>
				</div>
			</s:if>
		</div>

	</div>

</div>
</div>
<!--t-page end-->
</div>
<s:include value="../common/foot.jsp" />
</body>
</html>
