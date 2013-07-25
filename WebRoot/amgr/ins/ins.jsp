<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage=""%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="../common/easyui_head.jsp" />
<script type="text/javascript" src="resources/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="resources/ueditor/ueditor.all.min.js"></script>
<link rel="stylesheet" href="resources/ueditor/themes/default/css/ueditor.css" />
<script type="text/javascript" src="resources/scripts/flexpaper_flash.js"></script>
<script>
    //编辑ID
    var id= <s:property value="id"/>;
	var isInit=true;
    var ins={};
	var isSWFOpen=false;

	$.extend($.fn.validatebox.defaults.rules, {
		modelCheck : {
			validator : function(value, param) {
				if (value.length < 3) {
					$.fn.validatebox.defaults.rules.modelCheck.message = "型号请至少输入3位";
					return false;
				} else if ($("#bid").combobox("getValue") == "") {
					$.fn.validatebox.defaults.rules.modelCheck.message = "请先输入品牌";
					return false;
				} else {
					var result = eval("(" + $.ajax({
						url : "json/ins_judageModel_feedback",
						data : {
							"insUpload.id" : $("#id").val(),
							"insUpload.bid" : $("#bid").combobox("getValue"),
							"insUpload.model" : value
						},
						type : "get",
						dataType : "json",
						async : false,
						cache : false
					}).responseText + ")");
					if (result.feedback > 0) {
						return true;
					} else {
						$.fn.validatebox.defaults.rules.modelCheck.message = "型号已存在";
						return false;
					}
				}
			},
			message : ""
		},
		combobox : {
			validator : function(value, param) {
				var value = $("#" + param[0]).combobox("getValue");
				var text = $("#" + param[0]).combobox("getText");
				if (value != text && !isNaN(value)) {
					return true;
				}else return false;
			},
			message : "您的输入有问题，请选择正确的结果项"
		}
	});

	$(function() {
		<s:if test="id>0">
		//初始化表单
		init();
	    </s:if>
		
		var editor = new UE.ui.Editor();
		editor.render("description");
		
		$("#cid").combobox({
			valueField : "id",
			textField : "name",
			mode : "remote",
			formatter : function(row) {
				var opts = $(this).combobox("options");
				var parentId = 0;
				$.map($(this).combobox("getData"), function(item) {
					if (item.id == row[opts.valueField])
						parentId = item.parentId;
				});
				if (parentId == 0)
					return "<strong>" + row[opts.textField] + "</strong>";
				else
					return "&nbsp&nbsp&nbsp&nbsp" + row[opts.textField];
			},
			loader : function(param, success, error) {
				$.getJSON("json/category_list_categorys", {}, function(data) {
					var items = $.map(data.categorys, function(item) {
						return {
							id : item.id,
							name : item.name,
							parentId : item.parentId
						};
					});
					success(items);
				});
			},
			onSelect : function(rec) {
				$.getJSON("json/category_brandsByCid_brands", {
					"cid" : rec.id
				}, function(data) {
					$("#bid").combobox("clear").combobox("loadData", data.brands);
				});
			},
			onLoadSuccess:function(){
				<s:if test="id>0">
					var cid=<s:property value="ins.category.id" escape="false"/>;
					$("#cid").combobox("setValue",cid);
					$.getJSON("json/category_brandsByCid_brands", {
						"cid" : cid
					}, function(data) {
						$("#bid").combobox("loadData", data.brands);
					});
				</s:if>
			}
		});

		$("#bid").combobox({
			valueField : "id",
			textField : "name",
			mode : "remote",
			onLoadSuccess:function(){
				if(id>0&&isInit){
					$("#bid").combobox("setValue","<s:property value="ins.brand.id" escape="false"/>");
					isInit=false;
				}
			}
			
		});

		$("#upload-icon-btn").uploadify(
				{
					'uploader' : 'resources/swf/uploadify.swf',
					'script' : 'json/upload_uploadFile_uploadFile;jsessionid=' + JSESSIONID, // 指定服务端处理类的入口
					'folder' : 'avatar',
					'cancelImg' : 'resources/images/cancel.png',
					'fileDataName' : 'file', // 和input的name属性值保持一致就好，Struts2就能处理了
					'queueID' : 'fileQueue2',
					'auto' : true,// 是否选取文件后自动上传
					'multi' : false,// 是否支持多文件上传
					'simUploadLimit' : 1,// 每次最大上传文件数
					'buttonText' : '上传图片',// 按钮上的文字
					'fileExt' : '*.jpg;*.gif;*.png;*.jpeg',
					'fileDesc' : '请上传图片文件',
					'displayData' : 'percentage',// 有speed和percentage两种，一个显示速度，一个显示完成百分比
					'onComplete' : function(event, queueID, fileObj, res, _data) {
						data = JSON.parse(res);
						if (data.feedback > 0) {
							var uploadFile = data.uploadFile;
							$("#icon").combobox("setValue", uploadFile.id).combobox("setText",
									uploadFile.description);
						} else if (data.feedback == -1203) {
							$.messager.alert("出错了", "上传的格式不被接收，请上传pdf或office文档");
						} else {
							$.messager.alert("出错了", "文件上传出错");
						}
					},
					'onError' : function(event, queueID, fileObj, errorObj) {
						alert(errorObj.type + "Error:" + errorObj.info);
					},
					'onSelect' : function() {
						$("#icon").combobox("setText", "").combobox("setValue", 0);
					}

				});

		$("#upload-ins-btn").uploadify(
				{
					'uploader' : 'resources/swf/uploadify.swf',
					'script' : 'json/upload_uploadFile_uploadFile;jsessionid=' + JSESSIONID, // 指定服务端处理类的入口
					'folder' : 'avatar',
					'cancelImg' : 'resources/images/cancel.png',
					'fileDataName' : 'file', // 和input的name属性值保持一致就好，Struts2就能处理了
					'queueID' : 'fileQueue2',
					'auto' : true,// 是否选取文件后自动上传
					'multi' : false,// 是否支持多文件上传
					'simUploadLimit' : 1,// 每次最大上传文件数
					'buttonText' : '上传文件',// 按钮上的文字
					'fileExt' : '*.pdf;*.doc;*.docx;*.ppt;*.pptx;*.xls;*.xlsx',
					'fileDesc' : '请上传PDF或office文件',
					'displayData' : 'percentage',// 有speed和percentage两种，一个显示速度，一个显示完成百分比
					'onComplete' : function(event, queueID, fileObj, res, _data) {
						data = JSON.parse(res);
						if (data.feedback > 0) {
							var uploadFile = data.uploadFile;
							$("#ins").combobox("setValue", uploadFile.id).combobox("setText",
									uploadFile.description);
						} else if (data.feedback == -1203) {
							$.messager.alert("出错了", "上传的格式不被接收，请上传pdf或office文档");
						} else {
							$.messager.alert("出错了", "文件上传出错");
						}
					},
					'onError' : function(event, queueID, fileObj, errorObj) {
						alert(errorObj.type + "Error:" + errorObj.info);
					},
					'onSelect' : function() {
						$("#ins").combobox("setText", "").combobox("setValue", 0);
					}

				});

		$("#model").blur(
				function() {
					var title = ($("#bid").combobox("getText")).replace(/\s+/g, "")
							+ $("#model").val()
							+ ($("#cid").combobox("getText")).replace(/\s+/g, "") + "说明书下载";
					$("#title").val(title);
				});
		
		
		$("#sub").click(
				function() {
					if ($("#fm").form("validate")&&verifyCombobox("icon",true,"‘图片’文件输入有误")&&verifyCombobox("ins",true,"‘文档’文件输入有误")&&verifyCombobox("mobileUfid",false,"‘手机体验’文件输入有误")&&verifyCombobox("mobile3DUfid",false,"‘3D说明书’文件输入有误")) {
						var param = {
							"insUpload.cid" : $("#cid").combobox("getValue"),
							"insUpload.bid" : $("#bid").combobox("getValue"),
							"insUpload.model" : $("#model").val(),
							"insUpload.title" : $("#title").val(),
							"insUpload.description" : editor.getContent(),
							"insUpload.iconUfid" : $("#icon").combobox("getValue"),
							"insUpload.insUfid" : $("#ins").combobox("getValue"),
							"insUpload.g3Url" : $("#g3Url").val(),
							"status" : ($("#status").is(":checked")) ? 1: 0,
							"insUpload.mobileUfid" : $("#mobileUfid").combobox("getValue"),
							"insUpload.mobile3DUfid" : $("#mobile3DUfid").combobox("getValue")
						};

						var url = "json/ins_upload_feedback";
						var succword="创建成功！";
						if (id> 0) {
							param["insUpload.id"] = $("#id").val();
							url = "json/ins_edit_feedback";
							succword="编辑成功！";
						}

						$.getJSON(url, param, function(data) {
							if (data.feedback == 100)
								alert(succword);
							else {
								if (data.feedback == -602) {
									$("#title-notification").addClass("error").html("标题已存在");
									alert("标题已存在");
								} else if (data.feedback == -608) {
									$("#model-notification").addClass("error").html(
											$("#bid").find("option:selected").text() + "下型号“"
													+ $("#model").val() + "”已存在！");
									alert($("#bid").find("option:selected").text() + "下型号“"
											+ $("#model").val() + "”已存在！");
								}
							}
						});
					}

				});
		

		
		$("#showSWF").click(function(){
			    if(isSWFOpen){
			    	isSWFOpen=false;
			    	$("#viewerPlace").hide();
			    }
			    else{
			    	isSWFOpen=true;
			    	$("#viewerPlace").show();
				    $("html,body").animate({
					   scrollTop : $("#viewerPlace").offset().top
				    }, 1000);
				    if (ins.swfCount > 0&&ins.swfUrl.indexOf("_SWF/[")<=0)
					    ins.swfUrl = "{" + ins.swfUrl.substring(0, ins.swfUrl.lastIndexOf(".")) + "_SWF/[*,0].swf,"
							+ ins.swfCount + "}";
				    var fp = new FlexPaperViewer('resources/swf/FlexPaperViewer', 'viewerPlace', {
					   config : {
						SwfFile : ins.swfUrl,
						Scale : 0.6,
						ZoomTransition : 'easeOut',
						ZoomTime : 0.5,
						ZoomInterval : 0.2,
						FitPageOnLoad : true,
						FitWidthOnLoad : true,
						FullScreenAsMaxWindow : false,
						ProgressiveLoading : false,
						MinZoomSize : 0.2,
						MaxZoomSize : 5,
						SearchMatchAll : false,
						InitViewMode : 'Portrait',
						PrintPaperAsBitmap : false,
						ViewModeToolsVisible : true,
						ZoomToolsVisible : true,
						NavToolsVisible : true,
						CursorToolsVisible : true,
						SearchToolsVisible : false,
						localeChain : 'zh_CN'
					   }
				    });
			    }
		});

	});

	<s:if test="id>0">
	function init(){
			$("#id").val(id);
			$("#model").val("<s:property value="ins.model" escape="false"/>");
			$("#title").val("<s:property value="ins.title" escape="false"/>");
			$("#description").val('<s:property value="ins.description.replaceAll('(?:\n|\r|\t)', '')" escape="false"/>');
			$("#g3Url").val("<s:property value="ins.g3Url" escape="false"/>");
			
			
			$("#icon").combobox("setValue","<s:property value="ins.iconFile.id"/>");
			$("#ins").combobox("setValue","<s:property value="ins.insFile.id"/>");
			
			
			<s:if test="ins.mobileSWFFile!=null">
			$("#mobileUfid").combobox("setText","<s:property value="ins.mobileSWFFile.description"/>");
			</s:if>
			<s:if test="ins.mobile3DSWFFile!=null">
			$("#mobile3DUfid").combobox("setValue",<s:property value="ins.mobile3DSWFFile.id"/>);
			</s:if>
			
			ins.swfUrl="/<s:property escape="false" value="ins.insFile.server" />/<s:property value="ins.insFile.fileUrl.replaceAll('(?:.pdf|.PDF|.docx|.doc|.pptx|.ppt|.zip|.rar)','.swf')" />";
			ins.swfCount=<s:property value="ins.insFile.swfCount" />;
	}
    </s:if>
	
	
	//解决combobox validatebox可能存在的冲突
	function verifyCombobox(id,required,tap){
		var value = $("#" + id).combobox("getValue");
		var text = $("#" + id).combobox("getText");
		if(text==""&&required==false)return true;
		if (value != text && !isNaN(value)) {
			return true;
		}else{
			$.messager.alert("文件输入有误", tap);
			return false;
		}
	}

	var uploadFileLoader = function(param, success, error) {
		var q = param.q || "";
		var params={"search" : q};
		if(param.ext!=undefined&&param.ext!="")
		   params.ext=param.ext;
		$.getJSON("json/upload_list_uploadFiles", params, function(data) {
			var items = $.map(data.uploadFiles.rows, function(item) {
				return {
					id : item.id,
					description : item.description+"."+item.fileType,
				};
			});
			<s:if test="id>0">
		    	//编辑时，增加原有文件项
				items.push({id:<s:property value="ins.iconFile.id"/>,description:"<s:property value="ins.iconFile.description" escape="false"/>.<s:property value="ins.iconFile.fileType" escape="false"/>"});
				items.push({id:<s:property value="ins.insFile.id"/>,description:"<s:property value="ins.insFile.description" escape="false"/>.<s:property value="ins.insFile.fileType" escape="false"/>"});
				<s:if test="ins.mobileSWFFile!=null">
				items.push({id:<s:property value="ins.mobileSWFFile.id"/>,description:"<s:property value="ins.mobileSWFFile.description" escape="false"/>.<s:property value="ins.mobileSWFFile.fileType" escape="false"/>"});
				</s:if>
				<s:if test="ins.mobile3DSWFFile!=null">
				items.push({id:<s:property value="ins.mobile3DSWFFile.id"/>,description:"<s:property value="ins.mobile3DSWFFile.description" escape="false"/>.<s:property value="ins.mobile3DSWFFile.fileType" escape="false"/>"});
				</s:if>
		    </s:if>
			success(items);
		});
	}
	

</script>
<style type="text/css">
form {
	font-size: 12px;
}

label {
	width: 150px;
	text-align: right;
}

.fitem {
	padding: 15px 10;
}

input[type="text"] {
	width: 400px;
}
</style>
<body>
	<form id="fm" method="post">
		<input id="id" type="hidden"></input>
		<div class="fitem">
			<label>分类:</label> <input id="cid" editable="false" required="true" type="text"></input>
		</div>
		<div class="fitem">
			<label>品牌:</label> <input id="bid" editable="false" required="true" type="text" />
		</div>
		<div class="fitem">
			<label>型号:</label> <input id="model" type="text" class="easyui-validatebox" required="true"
				validType="modelCheck" />
		</div>
		<div class="fitem">
			<label>标题:</label> <input id="title" class="easyui-validatebox" type="text" required="true" />
		</div>
		<div class="fitem">
			<label>图片:</label> <input id="icon" class="easyui-combobox" type="text" required="true"
				data-options="loader:uploadFileLoader,onBeforeLoad:function(param){param.ext= 'PIC';},mode: 'remote',valueField: 'id',textField: 'description',panelHeight:'auto'" />
			<input type="file" id="upload-icon-btn" />
		</div>
		<div class="fitem">
			<label>文档:</label> <input id="ins" class="easyui-combobox" type="text" required="true"
				data-options="loader:uploadFileLoader,onBeforeLoad:function(param){param.ext= 'DOC';},mode: 'remote',valueField: 'id',textField: 'description',panelHeight:'auto'" />
			<input type="file" id="upload-ins-btn" />
		</div>
		<div class="fitem">
			<label>G3链接:</label> <input id="g3Url" type="text" class="easyui-validatebox"
				data-options="validType:'url'" />
		</div>
		<div class="fitem">
			<label>手机体验:</label> <input id="mobileUfid" type="text" class="easyui-combobox"
				data-options="loader:uploadFileLoader,onBeforeLoad:function(param){param.ext= 'SWF';},mode: 'remote',valueField: 'id',textField: 'description',panelHeight:'auto'" />
		</div>
		<div class="fitem">
			<label>3D外观:</label> <input id="mobile3DUfid" type="text" class="easyui-combobox"
				data-options="loader:uploadFileLoader,onBeforeLoad:function(param){param.ext= 'SWF';},mode: 'remote',valueField: 'id',textField: 'description',panelHeight:'auto'" />
		</div>
		<div class="fitem">
			<label>说明书描述:</label>
			<textarea id="description" name="description"
				style="width:800px; margin-left: 83px;height: 400px;"></textarea>
		</div>
		<div class="fitem">
			<label></label> <input type="checkbox" id="status" checked />是否直接审核通过
		</div>
		<div class="fitem">
			<label></label> <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search"
				id="sub">提交</a>
		</div>

		<s:if test="id>0">
			<div class="fitem">
				<label></label> <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload"
					id="showSWF">查看FLASH</a>
			</div>
			<div class="fitem">
				<a href="swf" />
				<div id="viewerPlace"></div>
			</div>
		</s:if>



	</form>

</body>
</html>