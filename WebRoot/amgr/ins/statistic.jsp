<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage=""%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="../common/head.jsp" />
<script type="text/javascript">
	jQuery(function($) {
		$('#startTime').datepicker({
			dateFormat : "mm/dd/yy",
			changeMonth : true,
			changeYear : true
		});
		$('#endTime').datepicker({
			dateFormat : "mm/dd/yy",
			changeMonth : true,
			changeYear : true
		});
	});
</script>

<body>
	<div id="main-content">
		<!-- Main Content Section with everything -->
		<noscript>
			<!-- Show a notification if the user has disabled javascript -->
			<div class="notification error png_bg">
				<div>对不起，您的浏览器没有开启或者不支持 Javascript. 请开启javascript或者更新您的浏览器.</div>
		</noscript>

		<div class="content-box">
			<div class="content-box-header">
				<h3>统计信息</h3>
				<div class="clear"></div>
			</div>
			<!-- End .content-box-header -->
			<div class="content-box-content">
				<div>
					<form name="statistic-filter" id="statistic-filter-form" method="get">
						<input type="hidden" name="adminUid" id="adminUid" type="text" value="<s:property escape="false"  value="adminUid"/>" /> 起止日期: <input id="startTime" name="startTime" type="text" value="<s:bean name="instruction.util.Time" id="time"><s:property escape="false"  value="#time.timeFormat(startTime,'MM/dd/yyyy')"/></s:bean>" /> ~ <input name="endTime" id="endTime" type="text" value="<s:bean name="instruction.util.Time" id="time"><s:property escape="false"  value="#time.timeFormat(endTime,'MM/dd/yyyy')"/></s:bean>" /> <a class="button" href="#" id="statistic-filter">筛选</a>
					</form>
				</div>
				<div class="tab-content default-tab">
					<table>
						<thead>
							<tr>
								<th>UID</th>
								<th>上传用户 <s:if test="adminUid!=0">
										<a style="border:solid 1px #4FA71F" href="ins_statistic?adminUid=0&startTime=<s:property escape="false"  value="startTime"/>&endTime=<s:property escape="false"  value="endTime"/>"><s:property escape="false" value="adminStatistics[0].username" /> X</a>
									</s:if>
								</th>
								<th>手机号码</th>
								<s:if test="adminUid!=0">
									<th>品牌</th>
								</s:if>
								<th>上传数量(个)</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="adminStatistics" status="st" id="as">
								<tr>
									<td><s:property escape="false" value="#as.uid" /></td>
									<td><a href="ins_statistic?adminUid=<s:property escape="false"  value="#as.uid"/>&startTime=<s:property escape="false"  value="startTime"/>&endTime=<s:property escape="false"  value="endTime"/>"><s:property escape="false" value="#as.username" /> </a></td>
									<td><s:property escape="false" value="#as.smsphone" /></td>
									<s:if test="adminUid!=0">
										<td><s:property escape="false" value="#as.brandName" /></td>
									</s:if>
									<td><s:property escape="false" value="#as.uploadCount" /></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
				<!-- End #tab3 -->
			</div>
			<!-- End .content-box-content -->
		</div>
		<!-- End .content-box -->
		<div class="clear"></div>

		<s:include value="../common/foot.jsp" />
	</div>
</body>
</html>