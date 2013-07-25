<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage=""%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="../common/easyui_head.jsp" />
<script>
	/**
	 *有待增加 ‘评论积分同步情况’
	 */
	$(function() {
		$("#data")
				.datagrid(
						{
							url : "json/comment_list_comments",
							<s:if test="uid!=0">
							queryParams : {
								uid : <s:property value="uid"/>
							},
							</s:if>
							loadFilter : function(data) {
								return data.comments;
							},
							columns : [ [
									{
										field : "ck",
										checkbox : true
									},
									{
										field : "id",
										hidden : true
									},
									{
										title : "用户名",
										field : "username",
										align : "center"
									},
									{
										title : "说明书",
										field : "insTitle",
										width : 250
									},
									{
										title : "评分",
										field : "grade",
										width : 50,
										align : "center"
									},
									{
										title : "评论时间",
										field : "dateline",
										width : 150,
										align : "center",
										formatter : function(value, row, index) {
											return (new Date(value*1000)).pattern("yyyy-MM-dd HH:mm:ss");
										}
									},
									{
										title : "给予积分情况",
										field : "creditStatus",
										formatter : function(value, row, index) {
											switch (value) {
											case 0:
												return "<span style='color:black;'>未处理</span>";
												break;
											case 1:
												return "<span style='color:blue;'>已处理，不给予积分</span>";
												break;
											case 2:
												return "<span style='color:green;'>已给予积分</span>";
												break;
											case 3:
												return "<span style='color:red;'>尝试给予积分但未成功，原因：</span><br/>("
														+ row.creditDesc + ")";
												break;
											}
										},
										sortable : true
									}, {
										title : "评论内容",
										field : "content"
									} ] ],
							pageNumber : 1,
							pageSize : 20
						}).datagrid("getPager").pagination({
					showPageList : true,
					pageSize : 20,
					buttons : [ {
						iconCls : "icon-ok",
						text : "给予积分",
						handler : function() {
							credit(2);
						}
					}, {
						iconCls : "icon-cancel",
						text : "不给予积分",
						handler : function() {
							credit(1);
						}
					} ]
				});

		/*
		var statusesData = [ {
			value : -100,
			text : "全部"
		}, {
			value : 0,
			text : "未操作"
		}, {
			value : 1,
			text : "不给积分"
		}, {
			value : 2,
			text : "给予积分"
		}, {
			value : 3,
			text : "积分同步失败"
		} ];
		$("#status").combobox({
			data : statusesData,
			multiple : true,
			panelHeight : "auto"
		});
		 */
		 
		//初始化uid
		<s:if test="uid>0">
		  $("#uid").combobox("setValue",<s:property value="uid"/>).combobox("setText","<%=request.getParameter("username")%>");
		</s:if>
	});

	function credit(creditStatus) {
		var ids = "";
		var rows = $("#data").datagrid("getSelections");
		for ( var i = 0; i < rows.length; i++) {
			var row = rows[i];
			if (row.creditStatus < creditStatus) {
				if (i > 0)
					ids += ",";
				ids += row.id;
			}
		}
		if (ids.length > 0) {
			$.getJSON("json/comment_credit_feedback", {
				"ids" : ids,
				"creditStatus" : creditStatus
			}, function(data) {
				if (data.feedback > 0) {
					$("#data").datagrid("reload");
					$.messager.show({
						title : "成功",
						msg : "成功了" + data.feedback + "记录"
					});
				} else {
					$.messager.alert("操作没有成功", "操作没有成功");
				}
			});
		} else {
			$.messager.alert("出错了", "请选择按规则选择记录:为保证安全，积分操作不可逆！等级：未操作&lt;已处理，不给予积分&lt;已给予积分！");
		}
	}

	var userLoader = function(param, success, error) {
		var q = param.q || "";
		if (q.length <= 1) {
			return false
		}
		$.getJSON("json/user_list_userview", {
			"username" : q
		}, function(data) {
			var items = $.map(data.userview.rows, function(item) {
				return {
					uid : item.uid,
					detail : item.username
				};
			});
			success(items);
		});
	}

	var insLoader = function(param, success, error) {
		var q = param.q || "";
		if (q.length <= 1) {
			return false
		}
		$.getJSON("json/ins_list_inses", {
			"search" : q
		}, function(data) {
			var items = $.map(data.inses.rows, function(item) {
				return {
					iid : item.id,
					title : item.title
				};
			});
			success(items);
		});
	}

	function search() {
		$("#data").data().datagrid.cache = null;
		var queryparams = $("#data").datagrid("options").queryParams;
		var uid = $("#uid").combobox("getValue");
		if (isNaN(uid)) {
			$.messager.show({
				title : "提示",
				msg : "没有选择正确的用户"
			});
			uid = 0;
		}

		var iid = $("#iid").combobox("getValue");
		if (isNaN(iid)) {
			$.messager.show({
				title : "提示",
				msg : "没有选择正确的说明书"
			});
			iid = 0;
		}

		if (uid > 0 || iid > 0) {
			queryparams.uid = uid;
			queryparams.iid = iid;
			$("#data").datagrid("load");
		} else
			$.messager.show({
				title : "提示",
				msg : "至少填写一项"
			});
	}
</script>
<body>

	<div id="tb">
		<a href="javascript:credit(2)" class="easyui-linkbutton" iconCls="icon-ok" plain="true">给予积分</a> <a
			href="javascript:credit(1)" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">不给予积分</a>

		用户：<input class="easyui-combobox" id="uid" style="width:150px"
			data-options="loader:userLoader,mode: 'remote',valueField: 'uid',textField: 'detail',panelHeight:'auto'">
		说明书：<input class="easyui-combobox" id="iid" style="width:200px"
			data-options="loader:insLoader,mode: 'remote',valueField: 'iid',textField: 'title',panelHeight:'auto',">
		<!-- 状态: <select id="statuses" style="width:155px"></select> -->
		<a href="javascript:search()" class="easyui-linkbutton" iconCls="icon-search" type="sub"
			plain="true">搜索</a>
	</div>
	<table id="data" pagination="true" fit="true" data-options="rownumbers:true,toolbar:'#tb'"
		border="no"></table>
</body>