<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="../common/head.jsp">
	<s:param name="title">通知中心-</s:param>
</s:include>

<script>
	jQuery(function($) {
		$.getJSON("/admin/json/readAllNotification?time=" + new Date().getTime() / 1000, function(
				data) {
		});
	});
</script>
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
										escape="false" value="#thread.title" /> </a></li>
						</s:iterator>
					</ul>
				</div>
			</div>
		</div>
		<div class="right">
			<div class="list-user-ins">
				<div class="label">通知中心</div>
				<table>
					<thead>
						<tr>
							<th>通知</th>
							<th width="150px">时间</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="pagination">
									<s:bean name="org.apache.struts2.util.Counter" id="counter">
										<s:param name="first" value="creditLogs.pageindex.startindex" />
										<s:param name="last" value="creditLogs.pageindex.endindex" />
										<s:if test="creditLogs.pageindex.startindex>1">
											<a href="?page=1&rows=<s:property value="pageSize"/>" class="number">1</a>
											<s:if test="creditLogs.pageindex.startindex>2">
												<span class="omission">...</span>
											</s:if>
										</s:if>
										<s:iterator>
											<a href="?page=<s:property value="current-1"/>&rows=<s:property value="pageSize"/>"
												class="number
                            <s:if test="inses.currentpage==current-1"> current</s:if>
                            "><s:property
													value="current-1" /> </a>
										</s:iterator>
										<s:if test="creditLogs.pageindex.endindex<inses.totalpage">
											<s:if test="creditLogs.totalpage-inses.pageindex.endindex>1">
												<span class="omission">...</span>
											</s:if>
											<a href="?page=<s:property value="inses.totalpage"/>&rows=<s:property value="pageSize"/>"
												class="number"><s:property value="creditLogs.totalpage" /> </a>
										</s:if>
									</s:bean>
								</div> <!-- End .pagination -->
								<div class="clear"></div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<s:iterator value="creditLogs.records" status="st" id="log">
							<tr>
								<td><s:property escape="false" value="#log.description" /></td>
								<td align="center"><s:property escape="false" value="#log.datelineFormat" /></td>
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
