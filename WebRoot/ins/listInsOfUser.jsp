<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="../common/head.jsp" />
<div class="main">
	<div class="track">
		<a href="index">首页</a> > 上传说明书 >
		<s:property escape="false" value="ins.title" />
	</div>
	<div class="t-page">
		<div class="left">

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
			<div class="list-user-ins">
				<div class="label">
					我上传的说明书 <a href="ins_uploadPageAuth">(我要上传)</a>
				</div>
				<table>
					<thead>
						<tr>
							<th>产品类型</th>
							<th>品牌</th>
							<th>标题</th>
							<th>上传时间</th>
							<th>状态</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="7">
								<div class="pagination">
									<s:bean name="org.apache.struts2.util.Counter" id="counter">
										<s:param name="first" value="inses.pageindex.startindex" />
										<s:param name="last" value="inses.pageindex.endindex" />
										<s:if test="inses.pageindex.startindex>1">
											<a
												href="?cid=<s:property value="cid"/>&bid=<s:property value="bid"/>&search=<s:property value="search"/>&page=1&rows=<s:property value="pageSize"/>"
												class="number">1</a>
											<s:if test="inses.pageindex.startindex>2">
												<span class="omission">...</span>
											</s:if>
										</s:if>
										<s:iterator>
											<a
												href="?cid=<s:property value="cid"/>&bid=<s:property value="bid"/>&search=<s:property value="search"/>&page=<s:property value="current-1"/>&rows=<s:property value="pageSize"/>"
												class="number
                            <s:if test="inses.currentpage==current-1"> current</s:if>
                            "><s:property
													value="current-1" /> </a>
										</s:iterator>
										<s:if test="inses.pageindex.endindex<inses.totalpage">
											<s:if test="inses.totalpage-inses.pageindex.endindex>1">
												<span class="omission">...</span>
											</s:if>
											<a
												href="?cid=<s:property value="cid"/>&bid=<s:property value="bid"/>&search=<s:property value="search"/>&page=<s:property value="inses.totalpage"/>&rows=<s:property value="pageSize"/>"
												class="number"><s:property value="inses.totalpage" /> </a>
										</s:if>
									</s:bean>
								</div> <!-- End .pagination -->
								<div class="clear"></div></td>
						</tr>
					</tfoot>
					<tbody>
						<s:iterator value="inses.records" status="st" id="ins">
							<tr>
								<td><s:property escape="false" value="#ins.category.name" />
								</td>
								<td><s:property escape="false" value="#ins.brand.name" />
								</td>
								<td><a href="/ins/<s:property escape="false"  value="#ins.id"/>.html" target="_blank"><s:property
											escape="false" value="#ins.title" /> </a>
								</td>
								<td><s:property escape="false" value="#ins.updateTimeFormat" />
								</td>
								<td class="status"><s:if test="#ins.status==0">
										<span class="red">等待审核</span>
									</s:if> <s:elseif test="#ins.status==1">
										<span class="green">通过审核</span>
									</s:elseif>
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>

			</div>
			<!-- End upload -->

		</div>
	</div>
</div>
<s:include value="../common/foot.jsp" />
</body>
</html>
