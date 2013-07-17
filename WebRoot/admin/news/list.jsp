<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage=""%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="../common/easyui_head.jsp" />
<script>
    //注意：这里的ACTION和ins中的不一样，这里大写
    var ACTION={DELETE:"DELETE", VERIFY:"VERIFY", UNVERIFY:"UNVERIFY"};
	$(function() {
		$("#data").datagrid({
			url : "json/news_list_newses",
			<s:if test="uid>0">
			queryParams : {
				uid : <s:property value="uid"/>
			},
			</s:if>
			loadFilter : function(data) {
				return data.newses;
			},
			columns : [ [ {
				field : "ck",
				checkbox : true
			}, {
				title : "ID",
				field : "id",
				width : 50,
				align : "center"
			}, {
				title : "标题",
				field : "title",
				width : 300
			}, {
				title : "发布用户",
				field : "username",
				align : "center",
				width : 80,
				formatter: function(value,row,index){
					return row.user.username
				}
			}, {
				title : "上传时间",
				field : "uploadTime",
				width : 145,
				align : "center",
				sortable:true,
				formatter:function(value,row,index){
					return (new Date(value*1000)).pattern("yyyy-MM-dd HH:mm:ss");
				}
			}, {
				title : "更新时间",
				field : "updateTime",
				width : 145,
				align : "center",
				sortable:true,
				formatter:function(value,row,index){
					return (new Date(value*1000)).pattern("yyyy-MM-dd HH:mm:ss");
				}
			}, {
				title : "状态",
				field : "status",
				sortable:true,
				formatter: function(value,row,index){
					switch (value) {
					case 0:
						return "<span style='color:red;'>未审核</span>";
						break;
					case 1:
						return "<span style='color:green;'>审核通过</span>";
						break;
					}
				},
				align : "center",
				width : 80
			}]],
			pageSize:20
		}).datagrid("getPager").pagination({
		    //displayMsg:'当前显示第 {from} 条到第 {to} 条记录，共有 {total} 条记录',
		    showPageList:true,
		    //beforePageText:'当前第',
		    //afterPageText:'页，本页共 {pages}条记录',
		    //total:300,
		    pageSize:20,
		    pageList:[10,20,30,40,50,60,70,80,100,120,150,200,250,300],
		    buttons:[{  
	            iconCls:"icon-ok",
	            text:"审核通过",
	            handler:function(){
	            	operate(ACTION.VERIFY);
	            }  
	        },{  
	            iconCls:"icon-undo",
	            text:"审核不通过",
	            handler:function(){
	            	operate(ACTION.UNVERIFY);
	            }  
	        },{  
	            iconCls:"icon-cancel",
	            text:"删除",
	            handler:function(){
	            	$.messager.confirm("注意", "确定要删除选中说明书吗？删除后将不能恢复！", function(r){
	                    if (r){
	                    	operate(ACTION.DELETE);
	                    }
	                });
	            }  
	        },{  
	            iconCls:"icon-edit",
	            text:"编辑",
	            handler:function(){
	            	var rows = $("#data").datagrid("getSelections");
	        		if(rows.length<=0)$.messager.alert("出错了", "请选择一项有效");
	        		else if(rows.length>1)$.messager.alert("出错了", "只可以选择一项进行编辑");
	        		else{
	        			parent.openTab("编辑资讯("+rows[0].id+")","news_news?id="+rows[0].id);
	        		}
	            }  
	        }]
		});
		
		$("#batch-uploadfile-btn")
		.uploadify(
				{
					'uploader' : 'resources/swf/uploadify.swf',
					'script' : 'json/upload_uploadFile_feedback;jsessionid='
							+ JSESSIONID, //指定服务端处理类的入口
					'folder' : 'avatar',
					'cancelImg' : 'resources/images/cancel.png',
					'fileDataName' : 'file', //和input的name属性值保持一致就好，Struts2就能处理了   
					'queueID' : 'fileQueue',
					'auto' : true,//是否选取文件后自动上传   
					'multi' : true,//是否支持多文件上传
					'simUploadLimit' : 10,//每次最大上传文件数   
					'buttonText' : '上传文件',//按钮上的文字   
					'fileExt' : 'all',
					'fileDesc' : '支持PDF、OFFICE文件、图片(jpg\gif\png\bmp)文件',
					'displayData' : 'percentage',//有speed和percentage两种，一个显示速度，一个显示完成百分比    
					'onComplete' : function(event, queueID, fileObj,
							res, _data) {
					},
					'onError' : function(event, queueID, fileObj,
							errorObj) {
						alert(errorObj.type + "Error:" + errorObj.info);
					},
					'onSelect' : function(event, ID, fileObj) {
					},
					'onAllComplete' : function(event, uploadObj) {
						$("#upload-dialog").dialog("close");
						$("#data").datagrid("load");
					}

				});
		
		
		$("#cid").combobox({
			valueField: "id",
			textField: "name",
			mode: "remote",
			formatter: function(row){
				var opts = $(this).combobox("options");
				var parentId=0;
				$.map($(this).combobox("getData"), function(item){
					if(item.id==row[opts.valueField])
						parentId=item.parentId;
                });
				if(parentId==0)
			       return "<strong>"+row[opts.textField]+"</strong>";
			    else 
			    	return "&nbsp&nbsp&nbsp&nbsp"+row[opts.textField];
			},
			loader:function(param,success,error){
	              $.getJSON("json/category_list_categorys",{},function(data){
	                var items = $.map(data.categorys, function(item){
	                		return {
	                        	id: item.id,
	                        	name: item.name,
	                        	parentId: item.parentId
	                        };
	                });
	                success(items);
	              });
			},
			onSelect:function(rec){
		        $.getJSON("json/category_brandsByCid_brands",{"cid":rec.id},function(data){
				   $("#bid").combobox("clear").combobox("loadData",data.brands);
		        });
			}
		});
		
		/*
		var statusesData=[{value:-100,text:"全部"},{value:0,text:"未审核"},{value:1,text:"审核通过"}];
		$("#status").combobox({
	        data : statusesData,
	        multiple : true,
	        panelHeight:"auto"
	    });
		*/
		
		

	});
	

	var titleLoader = function(param,success,error){
        var q = param.q || "";
        if (q.length <= 1){return false}
        $.getJSON("json/news_list_newses",{"search": q},function(data){
                var items = $.map(data.newses.rows, function(item){
                    return {
                    	title: item.title,
                    };
                });
                success(items);
        });
    }
	
	function operate(operate){
		var ids = "";
		var rows = $("#data").datagrid("getSelections");
		for ( var i = 0; i < rows.length; i++) {
			    var row = rows[i];
				if (i > 0)
					ids += ",";
				ids += row.id;
		}
		if (ids.length > 0) {
			$.getJSON("json/news_operate_feedback", {
				"ids" : ids,
				"operate":operate
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
			$.messager.alert("出错了", "请至少选择一条有效");
		}
	}
	
	
	
	function search() {
		$("#data").data().datagrid.cache = null;
		var queryparams = $("#data").datagrid("options").queryParams;
		var startTS=Math.floor((new Date($("#startTimeStamp").datetimebox("getValue")).getTime() )/ 1000);
		var endTS=Math.floor((new Date($("#endTimeStamp").datetimebox("getValue")).getTime() )/ 1000);
		queryparams["tsRange.startTimeStamp"]=startTS;
		queryparams["tsRange.endTimeStamp"]=endTS;
		queryparams["search"]=$("#title").combobox("getValue");
		//queryparams["status"]=$("#status").combobox("getValue");
		$("#data").datagrid("load");
	}
	 
</script>
<body>
	<div id="tb" style="padding:5px;height:auto">
		<div>
			标题: <input class="easyui-combobox" id="title" style="width:200px"
				data-options="loader:titleLoader,mode: 'remote',valueField: 'title',textField: 'title',panelHeight:'auto'">
			<!-- 状态: <select id="status" style="width:155px"></select> -->
			开始日期: <input class="easyui-datetimebox" style="width:160px" id="startTimeStamp"
				value="<s:bean name="instruction.util.Time" id="time"><s:property escape="false"  value="#time.timeFormat(tsRange.startTimeStamp,'MM/dd/yyyy hh:mm:ss')"/></s:bean>">
			截止日期: <input class="easyui-datetimebox" style="width:160px" id="endTimeStamp"
				value="<s:bean name="instruction.util.Time" id="time"><s:property escape="false"  value="#time.timeFormat(tsRange.endTimeStamp,'MM/dd/yyyy hh:mm:ss')"/></s:bean>">
			<a href="javascript:search()" class="easyui-linkbutton" iconCls="icon-search" type="sub"
				plain="true">搜索</a>
		</div>
	</div>
	<table id="data" fit="true" pagination="true" data-options="toolbar:'#tb'" border="no">
	</table>

	<div id="upload-dialog" class="easyui-dialog" title="批量上传" style="width:600px;padding:10px;"
		closed="true" data-options="iconCls: 'icon-save',buttons:'#dialog-buttons'">
		<div id="fileQueue" style="height: 200px;overflow: auto;"></div>
	</div>
	<div id="dialog-buttons">
		<input type="file" id="batch-uploadfile-btn" />
	</div>
</body>
</html>