<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage=""%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="../common/easyui_head.jsp" />
<script>
	var editedIndex = [];
	var editIndex = undefined;
	$(function() {
		$("#data").datagrid({
			url : "json/credit_listRule_creditRules",
			loadFilter : function(data) {
				return data.creditRules;
			},
			singleSelect : true,
			columns : [ [ {
				title : "id",
				field : "id",
				width : 50,
				align : "center",
				sortable : true,
				sorter : function(a, b) {
					return 1;
				}
			}, {
				title : "积分原因",
				field : "name",
				align : "center"
			}, {
				title : "来福币",
				field : "credit",
				width : 100,
				align : "center",
				editor : {
					type : "numberbox"
				}
			}, {
				title : "日上限",
				field : "dayThreshold",
				width : 100,
				align : "center",
				sortable : true,
				editor : {
					type : "numberbox"
				}
			}, {
				title : "月上限",
				field : "monthThreshold",
				width : 100,
				align : "center",
				sortable : true,
				editor : {
					type : "numberbox"
				}
			}, {
				title : "描述",
				field : "description",
				editor : {
					type : "text"
				}
			}, {
				title : "状态"
			} ] ],
			onClickRow : function(index) {
				if (editIndex != index) {
					if (endEditing()) {
						if ($('#data').datagrid("getRows")[index].id != 8) {
							$('#data').datagrid('selectRow', index).datagrid('beginEdit', index);
							editIndex = index;
							editedIndex.push(index);
						} else {
							$.messager.show({
								title : "提示",
								msg : "“" + $("#data").datagrid("getRows")[index].name + "”不可以修改！"
							});
						}
					} else {
						$('#data').datagrid('selectRow', editIndex);
					}
				}
			}
		});
	});

	function endEditing() {
		if (editIndex == undefined) {
			return true
		}
		if ($("#data").datagrid("validateRow", editIndex)) {
			$("#data").datagrid("endEdit", editIndex);
			editIndex = undefined;
			return true;
		} else {
			alert("validateRow");
			return false;
		}
	}
	function ok() {
		if (endEditing()) {
			$("#data").datagrid("acceptChanges");
		}
	}
	function reject() {
		$("#data").datagrid("rejectChanges");
		editIndex = undefined;
	}
	function save() {
		var rows = $("#data").datagrid("getRows");
		for ( var i = 0; i < editedIndex.length; i++) {
			var param = {
				"crid" : rows[editedIndex[i]].id,
				"credit" : rows[editedIndex[i]].credit,
				"description" : rows[editedIndex[i]].description,
				"dayThreshold" : rows[editedIndex[i]].dayThreshold,
				"monthThreshold" : rows[editedIndex[i]].monthThreshold
			};
			$.getJSON("json/credit_editRule_feedback?time=" + new Date().getTime() / 1000, param,
					function(data) {
						if (data.feedback == 100) {
							$.messager.show({
								title : "成功",
								msg : "成功了" + data.feedback + "记录"
							});
							editedIndex = [];
							editIndex = undefined;
						} else {
							$.messager.alert("操作没有成功", "操作没有成功");
							$("#data").datagrid("reload");
						}
					});
		}
	}
</script>
<body>
	<div id="tb" style="padding:5px;height:auto">
		<div>
			<a href="javascript:ok()" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true">确定</a>
			<a href="javascript:reject()" class="easyui-linkbutton"
				data-options="iconCls:'icon-undo',plain:true">撤销</a><a href="javascript:save()"
				class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true">保存</a>
		</div>
	</div>
	<table id="data" fit="true">
	</table>
</body>
</html>