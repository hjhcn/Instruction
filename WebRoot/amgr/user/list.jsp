<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage=""%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="../common/easyui_head.jsp" />
<script>
	$(function() {
		$('#data').datagrid({
			url : 'json/user_list_userview',
			loadFilter : function(data) {
				return data.userview;
			},
			pageNumber : 1,
			pageSize : 20,
			view : detailview,
			detailFormatter : function(index, row) {
				return '<div id="ddv-' + index + '" style="padding:5px 0"></div>';
			},
			onExpandRow : function(index, row) {
				$('#ddv-' + index).panel({
					height : 80,
					border : false,
					cache : false,
					href : 'user_statistic?uid=' + row.uid,
					onLoad : function() {
						$('#data').datagrid('fixDetailRowHeight', index);
					}
				//需要关闭进度条
				/*,extractor : function(data) {
					return "<div>hh</div>";
				}*/
				});
				$('#data').datagrid('fixDetailRowHeight', index);
			}
		});
	});
	function search() {
		$("#data").data().datagrid.cache = null;
		var queryparams = $("#data").datagrid("options").queryParams;
		queryparams.uid = $("#uid").val();
		queryparams.smsphone = $("#smsphone").val();
		queryparams.username = $("#username").val();
		$('#data').datagrid("load");
	}
	function editUser() {
		var row = $('#data').datagrid('getSelected');
		if (row) {
			alert(JSON.stringify(row));
		}
	}
</script>
<body>
	<div id="tb" style="padding:5px;height:auto">
		<!-- <div>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true"
			onclick="newUser()">New User</a> <a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editUser()">Edit User</a> <a href="javascript:void(0)"
			class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">Remove
			User</a> <input class="easyui-datetimebox" id="starttime" style="width:160px" />

	</div> -->
		<div>
			UID: <input name="uid" id="uid" class="easyui-numberbox easyui-tooltip" title="请输入完整UID" />
			手机号码: <input name="smsphone" id="smsphone" class="easyui-numberbox" /> 用户名: <input
				name="username" id="username" /> <a href="javascript:search()" class="easyui-linkbutton"
				iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
	<table id="data" pagination="true" fit="true"
		data-options="rownumbers:true,singleSelect:true,toolbar:'#tb'" border="no">
		<thead>
			<tr>
				<th data-options="field:'uid',width:80,align:'center'">uid</th>
				<th data-options="field:'username',align:'center'">用户名</th>
				<th data-options="field:'smsphone',width:100,align:'center'">手机号码</th>
				<th data-options="field:'credit',width:100,align:'center'">福币交易总额</th>
				<th data-options="field:'lastip',width:120">上次登录IP</th>
				<th data-options="field:'email',width:100">邮箱</th>
			</tr>
		</thead>
	</table>
</body>