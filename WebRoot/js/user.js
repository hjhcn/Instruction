jQuery(function($) {
	function overallFeedbackHandle(feedback) {
		if (feedback == -201)
			alert("您还没有登录哦~");
	}
	$("#cat").change(
			function() {
				var cat = $(this).children('option:selected').val();
				var strs = new Array();
				strs = cat.split("|");
				var cid = strs[0];
				if ($("#bid") != null) {
					$("#bid").html("");
					$.getJSON("/json/category_brandsByCid_brands?time=" + new Date().getTime()
							/ 1000, {
						"cid" : cid
					}, function(data) {
						overallFeedbackHandle(data.feedback);
						jQuery.each(data.brands,
								function(index, brand) {
									$("#bid").append(
											"<option value='" + brand.id + "'>" + brand.name
													+ "</option>");
								});
						$("#bid").append("<option value='-12345'>其他</option>");
					});
				}
			});

	$('#imgBtn').uploadify({
		'uploader' : '/css/images/uploadify.swf',
		'script' : '/json/upload_uploadFileAuth_uploadFile;jsessionid=' + JSESSIONID, // 指定服务端处理类的入口
		'folder' : 'avatar',
		'cancelImg' : '/css/images/cancel.png',
		'fileDataName' : 'file', // 和input的name属性值保持一致就好，Struts2就能处理了
		'queueID' : 'fileQueue1',
		'auto' : true,// 是否选取文件后自动上传
		'multi' : false,// 是否支持多文件上传
		'simUploadLimit' : 1,// 每次最大上传文件数
		'buttonText' : '上传图片',// 按钮上的文字
		'fileExt' : '*.jpg;*.gif;*.png;*.bmp;',
		'fileDesc' : '请上传图片，支持JPG,PNG,GIF,BMP',
		'displayData' : 'percentage',// 有speed和percentage两种，一个显示速度，一个显示完成百分比
		'onComplete' : function(event, queueID, fileObj, res, _data) {
			data = JSON.parse(res);
			overallFeedbackHandle(data.feedback);
			if (data.feedback > 0) {
				$("#iconUfid").val(data.uploadFile.id);
				$("#iconUrl").val(data.uploadFile.description);
				$("#imgshow").html("<img src='" + data.uploadFile.webUrl + "'  width='240px'/>");
				$("#iconUrl-notification").html("").removeClass("error").addClass("success");
			} else if (data.feedback == -1203) {
				alert("上传的格式不被接收，请上传pdf或office文档");
			} else {
				alert("文件上传出错");
			}
		},
		'onError' : function(event, queueID, fileObj, errorObj) {
			alert("Error");
			// alert(errorObj.type + "Error:" + errorObj.info);
		}
	});

	$('#fileBtn').uploadify({
		'uploader' : '/css/images/uploadify.swf',
		'script' : '/json/upload_uploadFileAuth_uploadFile;jsessionid=' + JSESSIONID, // 指定服务端处理类的入口
		'folder' : 'avatar',
		'cancelImg' : '/css/images/cancel.png',
		'fileDataName' : 'file', // 和input的name属性值保持一致就好，Struts2就能处理了
		'queueID' : 'fileQueue2',
		'auto' : true,// 是否选取文件后自动上传
		'multi' : false,// 是否支持多文件上传
		'simUploadLimit' : 1,// 每次最大上传文件数
		'buttonText' : '上传文件',// 按钮上的文字
		'fileExt' : '*.pdf;*.doc;*.ppt;*.docx;*.pptx',
		'fileDesc' : '请上传PDF或者office文件',
		'displayData' : 'percentage',// 有speed和percentage两种，一个显示速度，一个显示完成百分比
		'onComplete' : function(event, queueID, fileObj, res, _data) {
			data = JSON.parse(res);
			overallFeedbackHandle(data.feedback);
			if (data.feedback > 0) {
				$("#insUfid").val(data.uploadFile.id);
				$("#insUrl").val(data.uploadFile.description);
				$("#insUrl-notification").html("").removeClass("error").addClass("success");
			} else if (data.feedback == -1203) {
				alert("上传的格式不被接收，请上传pdf或office文档");
			} else {
				alert("文件上传出错");
			}
		},
		'onError' : function(event, queueID, fileObj, errorObj) {
			alert("Error");
			// alert(errorObj.type + "Error:" + errorObj.info);
		}
	});

	var description_content_init = "/请添加产品简单说明/";
	$("#description").focus(function() {
		if ($(this).val() == description_content_init) {
			$(this).val("");
			$(this).css("color", "#000");
		}
	}).blur(function() {
		if ($(this).val() == "") {
			$(this).val(description_content_init);
			$(this).css("color", "#ccc");
		}
	});

	$("#sub").click(
			function() {
				var cat = $("#cat").children('option:selected').val();
				var strs = new Array();
				strs = cat.split("|");
				var cid = strs[0];
				var cat_level = strs[1];
				var bid = $("#bid").val();
				var model = $("#model").val();
				var title = $("#title").val();
				var description = $("#description").val();
				var iconUfid = $("#iconUfid").val();
				var insUfid = $("#insUfid").val();
				var status = 0;
				if ($("input[name=status]").attr('checked') != undefined)
					status = 1;

				var verify = true;
				if (cid == "" || cid == 0) {
					$("#cid-notification").addClass("error").html("请选择分类");
					verify = false;
				} else {
					$("#cid-notification").html("").removeClass("error").addClass("success");
				}
				if (cat_level == 0) {
					$("#cid-notification").addClass("error").html("请选择二级及以下分类");
					verify = false;
				} else {
					$("#cid-notification").html("").removeClass("error").addClass("success");
				}
				if (bid == "" || bid == 0) {
					$("#bid-notification").addClass("error").html("请选择品牌");
					verify = false;
				} else {
					$("#bid-notification").html("").removeClass("error").addClass("success");
				}
				if (model == "") {
					$("#model-notification").addClass("error").html("请输入型号");
					verify = false;
				} else {
					$("#model-notification").html("").removeClass("error").addClass("success");
				}
				if (title == "") {
					$("#title-notification").addClass("error").html("请输入标题");
					verify = false;
				} else {
					$("#title-notification").html("").removeClass("error").addClass("success");
				}
				if (iconUfid <= 0) {
					$("#iconUrl-notification").addClass("error").html("请上传图片");
					verify = false;
				} else {
					$("#iconUrl-notification").html("").removeClass("error").addClass("success");
				}
				if (insUfid <= 0) {
					$("#insUrl-notification").addClass("error").html("请上传文件");
					verify = false;
				} else {
					$("#insUrl-notification").html("").removeClass("error").addClass("success");
				}

				if (verify) {
					var param = {
						"insUpload.cid" : cid,
						"insUpload.bid" : bid,
						"insUpload.model" : model,
						"insUpload.title" : title,
						"insUpload.description" : description,
						"insUpload.iconUfid" : iconUfid,
						"insUpload.insUfid" : insUfid,
						"status" : status
					};
					$.getJSON("/json/ins_uploadAuth_feedback?time=" + new Date().getTime() / 1000,
							param, function(data) {
								overallFeedbackHandle(data.feedback);
								if (data.feedback == 100) {
									alert("上传成功,正在等待审核，通过后将给您增加来福币，谢谢您的支持~");
									location.href = "/ins_listInsOfUserAuth";
								} else {
									// alert("上传失败，错误编号:"+data.feedback);
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
				} else {
				}
			});

	$("#model").blur(function() {
		$.getJSON("/json/ins_judgeModelAuth_feedback?time=" + new Date().getTime() / 1000, {
			"insUpload.model" : $(this).val(),
			"insUpload.bid" : $("#bid").val()
		}, function(data) {
			overallFeedbackHandle(data.feedback);
			if (data.feedback == 100) {
				$("#model-notification").html("").removeClass("error").addClass("success");
			} else {
				$("#model-notification").addClass("error").html("输入的型号已经上传了哦！");
			}
		});
	});

	$("#cat,#bid,#model").change(
			function() {
				var title = ($("#bid").find("option:selected").text()).replace(/\s+/g, "")
						+ $("#model").val()
						+ ($("#cat").find("option:selected").text()).replace(/\s+/g, "") + "说明书下载";
				$("#title").val(title);
				$("#title").val($("#title").val().replace(/\s+/g, ""));
			});

	$('tbody tr:even').addClass("alt-row");

});