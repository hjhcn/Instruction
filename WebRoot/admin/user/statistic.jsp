<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<style type="text/css">
.dv-table td {
	border: 0;
	font-size: 12px;
}

.dv-label {
	font-weight: bold;
	color: #15428B;
	width: 60px;
}
</style>

<table class="dv-table" border="0" style="width:100%;">
	<tr>
		<td rowspan="3" style="width:60px"><img
			src="http://www.139life.com/uc_server/avatar.php?uid=<s:property value="uid"/>&size=middle"
			style="width:60px" /></td>
		<td class="dv-label" align="right">说明书上传:</td>
		<td width="60px" align="center"><s:property value="userStatistic.insAdd" />
		</td>
		<td class="dv-label" align="right">通过审核数:</td>
		<td width="60px" align="center"><s:property value="userStatistic.insPass" />
		</td>
		<td width="60px" align="center"><a
			href="javascript:parent.openTab('说明书管理(<s:property value="userStatistic.user.username"/>)','ins_list_page?uid=<s:property value="uid"/>&username=<s:property escape="false" value="userStatistic.user.username"/>')">详情
		</a>
		</td>
	</tr>
	<tr>
		<td class="dv-label" align="right">来福币获得:</td>
		<td width="60px" align="center"><s:property value="userStatistic.creditAdd" />
		</td>
		<td class="dv-label" align="right">来福币扣除:</td>
		<td width="60px" align="center"><s:property value="userStatistic.creditCut" />
		</td>
		<td width="60px" align="center"><a
			href="javascript:parent.openTab('来福币管理(<s:property value="userStatistic.user.username"/>)','credit_list_log_page?uid=<s:property value="uid"/>&username=<s:property escape="false" value="userStatistic.user.username"/>')">详情
		</a>
		</td>
	</tr>
	<tr>
		<td class="dv-label" align="right">发表评论数:</td>
		<td width="60px" align="center"><s:property value="userStatistic.comAdd" />
		</td>
		<td class="dv-label" align="right">评论通过数:</td>
		<td width="60px" align="center"><s:property value="userStatistic.comPass" />
		</td>
		<td width="60px" align="center"><a
			href="javascript:parent.openTab('评论管理(<s:property value="userStatistic.user.username"/>)','comment_list_page?uid=<s:property value="uid"/>&username=<s:property escape="false" value="userStatistic.user.username"/>',true)">详情
		</a>
		</td>
	</tr>
</table>

