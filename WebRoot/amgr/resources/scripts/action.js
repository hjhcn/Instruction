var editor;
var ins_editor;
function overallFeedbackHandle(feedback) {
	if (feedback == -501)
		top.location.href = "/amgr/";
	else if (feedback < 0) {
		alert("操作返回错误代码：" + feedback);
	}
}

jQuery(function($) {

	$("#loading").ajaxStart(function() {
		$(this).show();
		$(this).css("color", "black");
		$(this).html('加载中...');
		$(this).css("left", document.body.clientWidth / 2 - parseInt($(this).css("width")) / 2);
	});
	$("#loading").ajaxStop(function() {
		$(this).fadeOut(500);
	});
	$("#loading").ajaxComplete(function() {
		$(this).fadeOut(500);
	});

	$("#main-nav ul a").click(function() {
		$(this).parents("#main-nav").find("a").removeClass("current");
		$(this).parents("li").parents("li").find(".nav-top-item").addClass("current");
		$(this).addClass("current");
	});

	$("#filter.button").click(function() {
		$("#filter-form").submit();
	});

	$("#cat").change(function() {
		var cat = $(this).children('option:selected').val();
		var strs = new Array();
		strs = cat.split("|");
		var cid = strs[0];
		if ($("#bid") != null)
			setBrandByCid(cid);
	});

	$("#icon-default").click(function() {
		var use_default_icon = false;
		if ($(this).attr('checked') != undefined)
			use_default_icon = true;
		if (use_default_icon) {
			$("#iconUrl").val("default.png");
			$("#imgshow").html("<img src='/uploadfileTemp/default.png'  width='240px'/>");
			$("#iconUfid").val(1);
		} else {
			$("#iconUrl").val("");
			$("#imgshow").html("");
			$("#iconUfid").val(0);
		}
	});

	$("#tempfile-copy").click(
			function() {
				var tempfile_copy = false;
				if ($(this).attr('checked') != undefined)
					tempfile_copy = true;
				$("#fileUrl").val("");
				if (tempfile_copy) {
					$("#manual-input").removeAttr('checked');
					$("#fileUrl").attr("readonly", "readonly");
					$(".file-btn").hide();
					$("#fileList").show();

					$.getJSON("json/ins_uploadTemp_fileList?time=" + new Date().getTime()
							/ 1000, function(data) {
						overallFeedbackHandle(data.feedback);
						if (data.feedback == 100) {
							$("#fileListSelect").html("");
							jQuery.each(data.fileList, function(index, file) {
								$("#fileListSelect").append(
										"<option value='" + file + "'>" + file + "</option>");
							});
						}
					});

				} else {
					$(".file-btn").show();
					$("#fileList").hide();
				}
			});

	$("#select-btn").click(
			function() {
				var filename = $(fileListSelect).val();
				if (filename != "") {
					var param = {
						"sdf" : 'dsf',
						"tempFileName" : filename
					};
					$.getJSON("json/ins_selectTempFile_fileFolderAndName?time="
							+ new Date().getTime() / 1000 + "&tempFileName=" + filename, function(
							data) {
						overallFeedbackHandle(data.feedback);
						if (data.feedback == 100) {
							$("#fileUrl").val(data.fileFolderAndName);
						}
					});
				} else {
					alert("请选择文件");
				}
			});

	$('#imgBtn')
			.uploadify(
					{
						'uploader' : 'resources/swf/uploadify.swf',
						'script' : 'json/upload_uploadFile_uploadFile;jsessionid='
								+ JSESSIONID, // 指定服务端处理类的入口
						'folder' : 'avatar',
						'cancelImg' : 'resources/images/cancel.png',
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
								var uploadFile = data.uploadFile;
								$("#iconUfid").val(uploadFile.id);
								$("#iconUrl").val(uploadFile.description);
								$("#imgshow").html(
										"<img src='" + uploadFile.webUrl + "'  width='240px'/>");
								$("#iconUrl-notification").html("").removeClass("error").addClass(
										"success");
							} else if (data.feedback == -1203) {
								$("#iconUrl-notification").addClass("error").html(
										"上传格式不被接收，目前支持的格式有：JPG,PNG,GIF,BMP");
							} else {
								$("#iconUrl-notification").addClass("error").html("图片上传出错");
							}
						},
						'onError' : function(event, queueID, fileObj, errorObj) {
							// alert("Error");
							alert(errorObj.type + "Error:" + errorObj.info);
						},
						'onSelect' : function() {
							$("#iconufid").val(0);
						}
					});

	$('#fileBtn').uploadify({
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
		'fileExt' : '*.pdf;*.doc;*.ppt;*.docx;*.pptx',
		'fileDesc' : '请上传PDF或者office文件',
		'displayData' : 'percentage',// 有speed和percentage两种，一个显示速度，一个显示完成百分比
		'onComplete' : function(event, queueID, fileObj, res, _data) {
			data = JSON.parse(res);
			overallFeedbackHandle(data.feedback);
			if (data.feedback > 0) {
				var uploadFile = data.uploadFile;
				$("#insUfid").val(uploadFile.id);
				$("#insUrl").val(uploadFile.description);
				$("#insUrl-notification").html("").removeClass("error").addClass("success");
			} else if (data.feedback == -1203) {
				$("#insUrl-notification").addClass("error").html("上传的格式不被接收，请上传pdf或office文档");
			} else {
				$("#insUrl-notification").addClass("error").html("文件上传出错");
			}
		},
		'onError' : function(event, queueID, fileObj, errorObj) {
			alert(errorObj.type + "Error:" + errorObj.info);
		},
		'onSelect' : function() {
			$("#insufid").val(0);
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
				var description = CKEDITOR.instances.description.getData();
				var g3Url = $("#g3Url").val();
				var status = 0;
				if ($("input[name=status]").attr('checked') != undefined)
					status = 1;
				var mobileUfid = $("#mobileUfid").val();
				var mobile3DUfid = $("#mobile3DUfid").val();
				var iconUfid = $("#iconUfid").val();
				var insUfid = $("#insUfid").val();

				var verify = true;
				if (cid == "" || cid == 0) {
					$("#cid-notification").addClass("error").html("请选择分类");
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
						"insUpload.g3Url" : g3Url,
						"status" : status,
						"insUpload.mobileUfid" : mobileUfid,
						"insUpload.mobile3DUfid" : mobile3DUfid
					};
					$.getJSON(
							"json/ins_upload_feedback?time=" + new Date().getTime() / 1000,
							param, function(data) {
								overallFeedbackHandle(data.feedback);
								if (data.feedback == 100)
									alert("上传成功");
								else {
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
					alert("数据提交有误！");
				}
			});

	$("#edit-sub").click(
			function() {
				var id = $("#act-id").val();
				var cat = $("#cat").children('option:selected').val();
				var strs = new Array();
				strs = cat.split("|");
				var cid = strs[0];
				var cat_level = strs[1];
				var bid = $("#bid").val();
				var model = $("#model").val();
				var title = $("#title").val();
				var description = CKEDITOR.instances.description.getData();
				var g3Url = $("#g3Url").val();
				var mobileUfid = $("#mobileUfid").val();
				var mobile3DUfid = $("#mobile3DUfid").val();
				var status = 0;
				if ($("input[name=status]").attr('checked') != undefined)
					status = 1;
				var iconUfid = $("#iconUfid").val();
				var insUfid = $("#insUfid").val();

				var verify = true;
				if (id == "" || id == 0) {
					alert("非法操作！请选择说明书后操作！");
					verify = false;
					return;
				} else {
				}
				if (cid == "" || cid == 0) {
					$("#cid-notification").addClass("error").html("请选择分类");
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
						"insUpload.id" : id,
						"insUpload.cid" : cid,
						"insUpload.bid" : bid,
						"insUpload.model" : model,
						"insUpload.title" : title,
						"insUpload.description" : description,
						"insUpload.iconUfid" : iconUfid,
						"insUpload.insUfid" : insUfid,
						"insUpload.g3Url" : g3Url,
						"status" : status,
						"insUpload.mobileUfid" : mobileUfid,
						"insUpload.mobile3DUfid" : mobile3DUfid
					};
					$.post("json/ins_edit_feedback?time=" + new Date().getTime() / 1000,
							param, function(data) {
								overallFeedbackHandle(data.feedback);
								if (data.feedback == 100)
									alert("更新成功");
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

										var result = confirm("若用户上传重复，您是否要删除该说明书？");
										if (result) {
											result = confirm("您确定删除ID为" + id + "的说明书？删除后不能恢复！");
											if (result) {
												$.getJSON("json/ins_delete_?time="
														+ new Date().getTime() / 1000, {
													ids : id
												}, function(data) {
													overallFeedbackHandle(data.feedback);
													if (data.feedback == 1) {
														$(".content-box-tabs .list").click();
														alert("说明书已删除。");
														$(
																"tbody tr:eq("
																		+ $("#act-number").val()
																		+ ")").fadeOut();
													}
												});
											}
										}

									}
								}
							}, "json");
				}
			});

	$("#mobile").autocomplete(
			{
				minLength : 1,
				source : function(request, response) {
					$.ajax({
						url : "json/upload_list_uploadFiles",
						dataType : "json",
						data : {
							"search" : request.term,
							"ext" : "SWF",
							"rows" : 10000
						},
						success : function(data) {
							if (data.feedback > 0) {
								response($.map(data.uploadFiles.rows, function(item) {
									return {
										label : item.description,
										value : item.fileUrl + "(" + item.description + ")",
										id : item.id
									}
								}));
							}
						}
					});
				},
				search : function(event, ui) {
					$("#mobileUfid").val(0);
				},
				select : function(event, ui) {
					$("#mobileUfid").val(ui.item.id);
				},
				change : function(event, ui) {
					if ($("#mobileUfid").val() == 0) {
						$("#mobile-notification").html("没有选择正确的手机体验swf，默认不提交该项！").removeClass(
								"success").addClass("attention");
					} else {
						$("#mobile-notification").html("").removeClass("attention").addClass(
								"success");
					}
				}
			});

	$("#mobile3D").autocomplete(
			{
				minLength : 1,
				source : function(request, response) {
					$.ajax({
						url : "json/upload_list_uploadFiles",
						dataType : "json",
						data : {
							"search" : request.term,
							"ext" : "SWF",
							"rows" : 10000
						},
						success : function(data) {
							if (data.feedback > 0) {
								response($.map(data.uploadFiles.rows, function(item) {
									return {
										label : item.description,
										value : item.fileUrl + "(" + item.description + ")",
										id : item.id
									}
								}));
							}
						}
					});
				},
				search : function(event, ui) {
					$("#mobile3DUfid").val(0);
				},
				select : function(event, ui) {
					$("#mobile3DUfid").val(ui.item.id);
				},
				change : function(event, ui) {
					if ($("#mobile3DUfid").val() == 0) {
						$("#mobile3D-notification").html("没有选择正确的3D外观swf，默认不提交该项！").removeClass(
								"success").addClass("attention");
					} else {
						$("#mobile3D-notification").html("").removeClass("attention").addClass(
								"success");
					}
				}
			});

	$("#reCoverter").click(function() {
		var id = $("#act-id").val();
		$.getJSON("json/ins_reCoverter_?time=" + new Date().getTime() / 1000, {
			"id" : id
		}, function(data) {
			overallFeedbackHandle(data.feedback);
			if (data.feedback == 100)
				$("#swf-notification").html("重新生成请求已提交").show().delay(2000).fadeOut();
		});
	});

	$(".edit-link,.detail-link").click(
			function() {
				var aIns = $(this).parents("tr");
				var iid = aIns.find("input[name='iid']").val();
				$("#act-id").val(iid);
				$("#act-number").val($(aIns).index());
				$.getJSON("json/ins_ins_ins?time=" + new Date().getTime() / 1000, {
					id : iid
				}, function(data) {
					overallFeedbackHandle(data.feedback);
					var ins = data.ins;
					if (data.feedback == 100) {
						$("#cat").val(ins.category.id);
						setBrandByCid(ins.category.id, function() {
							if (ins.brand == null)
								alert("尚未选择品牌");
							else {
								$("#bid").val(ins.brand.id);
							}
						});
						$("#model").val(ins.model);
						$("#title").val(ins.title);
						// $('#description').data('wysiwyg').setContent(
						// ins.description);
						CKEDITOR.instances.description.setData(ins.description);
						$("#iconUrl").val(ins.iconFile.description);
						$("#iconUfid").val(ins.iconFile.id);
						$("#fileUrl").val(ins.insFile.description);
						$("#insUfid").val(ins.insFile.id);
						$("#g3Url").val(ins.g3Url);
						$("#server").val(ins.server);
						$("#mobile").val(ins.mobileSWFUrl);
						$("#mobile3D").val(ins.mobile3DSWFUrl);
						if (ins.iconUrl != null && ins.iconUrl != "")
							$("#imgshow").html(
									"<img src='/" + ins.iconFile.server + "/"
											+ ins.iconFile.fileUrl + "'  width='240px'/>");
						if (ins.files.length > 0) {
							$("#fileUrl").val(ins.files[0].fileUrl);
							$("#swfCount").val(ins.files[0].swfCount);
						}
						$('.content-box ul.content-box-tabs li a.detail').click();
					}
				});
			});

	$(".verify-link").click(function() {
		var aIns = $(this).parents("tr");
		var iid = aIns.find("input[name='iid']").val();
		var action = "verify";
		var resultHtml = "<span class='green'>通过审核</span>";
		if (aIns.find(".status").html().indexOf("green") > 0) {
			action = "unverify";
			resultHtml = "<span class='red'>未通过</span>";
		}
		$.getJSON("json/ins_" + action + "_?time=" + new Date().getTime() / 1000, {
			ids : iid
		}, function(data) {
			overallFeedbackHandle(data.feedback);
			if (data.feedback > 0) {
				aIns.find(".status").html(resultHtml);
			}
		});
	});

	$(".delete-link").click(function() {
		var aIns = $(this).parents("tr");
		var iid = aIns.find("input[name='iid']").val();
		var result = confirm("您确定删除ID为" + iid + "的说明书？删除后不能恢复！")
		if (result) {
			$.getJSON("json/ins_delete_?time=" + new Date().getTime() / 1000, {
				ids : iid
			}, function(data) {
				overallFeedbackHandle(data.feedback);
				if (data.feedback == 1) {
					aIns.fadeOut();
				}
			});
		}
	});

	$("#ins-set-apply").click(
			function() {
				var operate = $("select[name=operate]").val();
				var ids = "";
				var idArray = new Array();
				$("input[name=check]").each(function(index) {
					if ($(this).attr('checked') != undefined) {
						if (ids != "")
							ids += ",";
						ids += $(this).parent().find("input[name='iid']").val();
						idArray.push(index);
					}
				});
				if (ids == "")
					alert("请选择说明书!");
				else {
					var result;
					if (operate == "delete")
						result = confirm("您确定删除ID为" + ids + "的说明书？删除后不能恢复！");
					else if (operate == "verify")
						result = confirm("您确定将ID为" + ids + "的说明书审核通过吗？");
					if (result) {
						$.getJSON("json/ins_" + operate + "_?time=" + new Date().getTime()
								/ 1000, {
							"ids" : ids
						}, function(data) {
							overallFeedbackHandle(data.feedback);
							if (data.feedback > 0) {
								jQuery.each(idArray, function(index) {
									if (operate == "delete") {
										$("input[name=check]").eq(index).parents("tr").fadeOut();
									} else if (operate == "verify") {
										$("input[name=check]").eq(index).parents("tr").find(
												".status").html("<span class='green'>通过审核</span>");
									}
								});
							}
						});
					}
				}
			});

	$("#cid,#bid,#model").blur(
			function() {
				var title = ($("#bid").find("option:selected").text()).replace(/\s+/g, "")
						+ $("#model").val()
						+ ($("#cat").find("option:selected").text()).replace(/\s+/g, "") + "说明书下载";
				$("#title").val(title);
			});

	$("#model").blur(function() {
		$.getJSON("json/ins_judageModel_feedback?time=" + new Date().getTime() / 1000, {
			"insUpload.model" : $(this).val(),
			"insUpload.bid" : $("#bid").val(),
			"insUpload.id" : $("#act-id").val()
		}, function(data) {
			overallFeedbackHandle(data.feedback);
			if (data.feedback == 100) {
				$("#model-notification").html("").removeClass("error").addClass("success");
			} else {
				$("#model-notification").addClass("error").html("输入的型号已经上传了哦！");
			}
		});
	});

	function setBrandByCid(Cid, callback) {
		$("#bid").html("");
		$.getJSON("json/category_brandsByCid_brands?time=" + new Date().getTime() / 1000, {
			cid : Cid
		}, function(data) {
			overallFeedbackHandle(data.feedback);
			jQuery.each(data.brands, function(index, brand) {
				$("#bid").append("<option value='" + brand.id + "'>" + brand.name + "</option>");
			});
			if (callback)
				callback();
		});
	}

	$(".cat-edit-link,.cat-detail-link").click(
			function() {
				var aCat = $(this).parents("tr");
				var cid = aCat.find("input[name='cid']").val();
				$("#act-id").val(cid);
				$.getJSON("json/category_category_category?time=" + new Date().getTime() / 1000, {
					"cid" : cid
				}, function(data) {
					overallFeedbackHandle(data.feedback);
					var category = data.category;
					if (data.feedback == 100) {
						$("#name").val(category.name);
						$("#parentId").val(category.parentId);
						$('.content-box ul.content-box-tabs li a.detail').click();
					}
					$("#brands").html("");
					jQuery.each(data.category_brandsBWC, function(index, brand) {
						$("#brands").append(
								'<div class="notification select" id="' + brand.id + '"><div>'
										+ brand.name + '</div></div>');
					});
					jQuery.each(data.category_brandsNBWC, function(index, brand) {
						$("#brands").append(
									'<div class="notification default" id="' + brand.id + '"><div>'
											+ brand.name + '</div></div>');
					});
					$("#brands .notification").click(function() {
						if ($(this).hasClass('select'))
							$(this).removeClass("select").addClass("default");
						else
							$(this).removeClass("default").addClass("select");
					});
				});
			});

	$(".cat-delete-link").click(function() {
		var aCat = $(this).parents("tr");
		var cid = aCat.find("input[name='cid']").val();
		var result = confirm("您确定删除CID为" + cid + "的分类？删除后不能恢复！")
		if (result) {
			$.getJSON("json/category_delete_?time=" + new Date().getTime() / 1000, {
				"cid" : cid
			}, function(data) {
				overallFeedbackHandle(data.feedback);
				if (data.feedback == 100) {
					aCat.fadeOut();
				}
			});
		}
	});

	$("#cat-edit-sub").click(
			function() {
				var cid = $("#act-id").val();
				var name = $("#name").val();
				var parentId = $("#parentId").val();
				var bids = "";
				$("#brands .select").each(function() {
					if (bids != "")
						bids += ",";
					bids += $(this).attr("id");
				});

				var verify = true;
				if (cid == "" || cid == 0) {
					alert("非法操作！请选择分类后操作！");
					verify = false;
					return;
				} else {
				}
				if (name == "") {
					$("#name-notification").addClass("error").html("请输入分类名");
					verify = false;
				} else {
					$("#name-notification").html("").removeClass("error").addClass("success");
				}
				if (verify) {
					var param = {
						"cid" : cid,
						"name" : name,
						"parentId" : parentId,
						"bids" : bids
					};
					$.getJSON("json/category_edit_?time=" + new Date().getTime() / 1000,
							param, function(data) {
								overallFeedbackHandle(data.feedback);
								if (data.feedback == 100)
									alert("更新成功");
								else if (data.feedback == -702)
									$("#name-notification").addClass("error").html("分类名已存在");
							});
				}
			});

	$("#cat-add-sub").click(
			function() {
				var name = $("#name").val();
				var parentId = $("#parentId").val();
				var verify = true;
				if (name == "") {
					$("#name-notification").addClass("error").html("请输入分类名");
					verify = false;
				} else {
					$("#name-notification").html("").removeClass("error").addClass("success");
				}
				if (verify) {
					var param = {
						"name" : name,
						"parentId" : parentId
					};
					$.getJSON("json/category_add_?time=" + new Date().getTime() / 1000,
							param, function(data) {
								overallFeedbackHandle(data.feedback);
								if (data.feedback == 100)
									alert("添加成功");
								else if (data.feedback == -702)
									$("#name-notification").addClass("error").html("分类名已存在");
							});
				}
			});

	$(".brand-edit-link,.brand-detail-link").click(function() {
		var aBrand = $(this).parents("tr");
		var bid = aBrand.find("input[name='bid']").val();
		$("#act-id").val(bid);
		$.getJSON("json/brand_brand_brand?time=" + new Date().getTime() / 1000, {
			"bid" : bid
		}, function(data) {
			overallFeedbackHandle(data.feedback);
			var brand = data.brand;
			if (data.feedback == 100) {
				$("#name").val(brand.name);
				$("#nameEn").val(brand.nameEn);
				$("#nameZh").val(brand.nameZh);
				$('.content-box ul.content-box-tabs li a.detail').click();
			}
		});
	});

	$(".brand-delete-link").click(function() {
		var aBrand = $(this).parents("tr");
		var bid = aBrand.find("input[name='bid']").val();
		var result = confirm("您确定删除ID为" + bid + "的品牌？删除后不能恢复！")
		if (result) {
			$.getJSON("json/brand_delete_?time=" + new Date().getTime() / 1000, {
				"bid" : bid
			}, function(data) {
				overallFeedbackHandle(data.feedback);
				if (data.feedback == 100) {
					aBrand.fadeOut();
				}
			});
		}
	});

	$("#brand-edit-sub").click(
			function() {
				var bid = $("#act-id").val();
				var name = $("#name").val();
				var nameEn = $("#nameEn").val();
				var nameZh = $("#nameZh").val();
				var verify = true;
				if (bid == "" || bid == 0) {
					alert("非法操作！请选择品牌后操作！");
					verify = false;
					return;
				} else {
				}
				if (name == "") {
					$("#name-notification").addClass("error").html("请输入常用名");
					verify = false;
				} else {
					$("#name-notification").html("").removeClass("error").addClass("success");
				}
				// if(nameEn==""){
				// $("#nameEn-notification").addClass("error").html("请输入英文名");verify=false;
				// }else {
				// $("#nameEn-notification").html("").removeClass("error").addClass("success");
				// }
				// if(nameZh==""){
				// $("#nameZh-notification").addClass("error").html("请输入中文名");verify=false;
				// }else {
				// $("#nameZh-notification").html("").removeClass("error").addClass("success");
				// }
				if (verify) {
					var param = {
						"bid" : bid,
						"name" : name,
						"nameEn" : nameEn,
						"nameZh" : nameZh
					};
					$.getJSON("json/brand_edit_?time=" + new Date().getTime() / 1000, param,
							function(data) {
								overallFeedbackHandle(data.feedback);
								if (data.feedback == 100)
									alert("更新成功");
								else if (data.feedback == -802)
									$("#name-notification").addClass("error").html("品牌名已存在");
							});
				}
			});

	$("#brand-add-sub").click(
			function() {
				var name = $("#name").val();
				var nameEn = $("#nameEn").val();
				var nameZh = $("#nameZh").val();
				var verify = true;
				if (name == "") {
					$("#name-notification").addClass("error").html("请输入常用名");
					verify = false;
				} else {
					$("#name-notification").html("").removeClass("error").addClass("success");
				}
				// if(nameEn==""){
				// $("#nameEn-notification").addClass("error").html("请输入英文名");verify=false;
				// }else {
				// $("#nameEn-notification").html("").removeClass("error").addClass("success");
				// }
				// if(nameZh==""){
				// $("#nameZh-notification").addClass("error").html("请输入中文名");verify=false;
				// }else {
				// $("#nameZh-notification").html("").removeClass("error").addClass("success");
				// }
				if (verify) {
					var param = {
						"name" : name,
						"nameEn" : nameEn,
						"nameZh" : nameZh
					};
					$.getJSON("json/brand_add_?time=" + new Date().getTime() / 1000, param,
							function(data) {
								overallFeedbackHandle(data.feedback);
								if (data.feedback == 100)
									alert("添加成功");
								else if (data.feedback == -802)
									$("#name-notification").addClass("error").html("品牌名已存在");
							});
				}
			});

	$("#statistic-filter").click(
			function() {
				var startTime = $("#startTime").val();
				var endTime = $("#endTime").val();
				if (startTime == "")
					startTime = "01/01/1970";
				if (endTime == "")
					endTime = new Date();
				location.href = "ins_statistic?startTime=" + Date.parse(startTime + " 00:00:00")
						/ 1000 + "&endTime=" + Date.parse(endTime + " 23:59:59") / 1000
						+ "&adminUid=" + $("#adminUid").val();
			});

	$("#export-filter").click(
			function() {
				var startTime = $("#startTime").val();
				var endTime = $("#endTime").val();
				if (startTime == "")
					startTime = "01/01/1970";
				if (endTime == "")
					endTime = new Date();
				location.href = "ins-dataExport?startTime=" + Date.parse(startTime + " 00:00:00")
						/ 1000 + "&endTime=" + Date.parse(endTime + " 23:59:59") / 1000;
			});

	// javascript:alert(Date.parse("1970-02-01"));

	// admin setting start
	$("#newPswd-sub").click(
			function() {
				var password = $("#password").val();
				var newPassword = $("#newPassword").val();
				var reNewPassword = $("#reNewPassword").val();
				var verify = true;
				if (password == "") {
					$("#password-notification").addClass("error").html("请输入旧密码");
					verify = false;
				} else {
					$("#password-notification").html("").removeClass("error").addClass("success");
				}
				if (newPassword == "" || newPassword.length < 6 || newPassword.length > 16) {
					$("#newPassword-notification").addClass("error").html("请输入6位至16位密码");
					verify = false;
				} else {
					$("#newPassword-notification").html("").removeClass("error")
							.addClass("success");
				}
				if (reNewPassword == "" || reNewPassword != newPassword) {
					$("#reNewPassword-notification").addClass("error").html("请确认密码，并保证两次输入一致");
					verify = false;
				} else {
					$("#reNewPassword-notification").html("").removeClass("error").addClass(
							"success");
				}
				if (verify) {
					var param = {
						"password" : password,
						"newPassword" : newPassword
					};
					$.getJSON("json/admin_password_feedback?time=" + new Date().getTime()
							/ 1000, param, function(data) {
						overallFeedbackHandle(data.feedback);
						if (data.feedback == 100)
							alert("密码修改成功!");
						else if (data.feedback == -503)
							$("#password-notification").addClass("error").html("旧密码出错");
						else if (data.feedback == -505)
							$("#password-notification").addClass("error").html("新密码格式出错");
					});
				}

			});
	// admin setting end

	// credit setting start
	$(".cr-edit-link").click(function() {
		var aCredit = $(this).parents("tr");
		var crid = aCredit.find("input[name='crid']").val();
		$("#crid").val(crid);
		$.getJSON("json/credit_creditRule_creditRule?time=" + new Date().getTime() / 1000, {
			"crid" : crid
		}, function(data) {
			overallFeedbackHandle(data.feedback);
			var creditRule = data.creditRule;
			if (data.feedback == 100) {
				$("#name").val(creditRule.name);
				$("#credit").val(creditRule.credit);
				$("#dayThreshold").val(creditRule.dayThreshold);
				$("#monthThreshold").val(creditRule.monthThreshold);
				$("#description").val(creditRule.description);
				$('.content-box ul.content-box-tabs li a.detail').click();
			}
		});
	});
	$("#cr-edit-sub").click(
			function() {
				var credit = $("#credit").val();
				var dayThreshold = $("#dayThreshold").val();
				var monthThreshold = $("#monthThreshold").val();
				var description = $("#description").val();
				var crid = $("#crid").val();
				var verify = true;
				if (credit == "" || BASEisNum(credit)) {
					$("#password-notification").addClass("error").html("请输入积分值");
					verify = false;
				} else {
					$("#password-notification").html("").removeClass("error").addClass("success");
				}
				if (dayThreshold == "" || BASEisNum(credit)) {
					$("#newPassword-notification").addClass("error").html("请输入每日上限");
					verify = false;
				} else {
					$("#newPassword-notification").html("").removeClass("error")
							.addClass("success");
				}
				if (verify) {
					var param = {
						"crid" : crid,
						"credit" : credit,
						"description" : description,
						"dayThreshold" : dayThreshold,
						"monthThreshold" : monthThreshold
					};
					$.getJSON("json/credit_editRule_feedback?time=" + new Date().getTime()
							/ 1000, param, function(data) {
						overallFeedbackHandle(data.feedback);
						if (data.feedback == 100)
							alert("修改成功!");
					});
				}
			});

	$(".cl-recreidt-link").click(function() {
		var aCl = $(this).parents("tr");
		var clid = aCl.find("input[name='clid']").val();
		$.getJSON("json/credit_reCredit_feedback?time=" + new Date().getTime() / 1000, {
			"ids" : clid
		}, function(data) {
			overallFeedbackHandle(data.feedback);
			if (data.feedback > 0) {
				aCl.find(".status").html('<span class="green">同步积分成功</span>');
			} else {
				alert("操作没有成功！");
			}
		});
	});

	$("#credit-set-apply").click(function() {
		var ids = "";
		var idArray = new Array();
		$("input[name=check]").each(function(index) {
			if ($(this).attr('checked') != undefined) {
				if (ids != "")
					ids += ",";
				ids += $(this).parent().find("input[name='clid']").val();
				idArray.push(index);
			}
		});
		if (ids != "") {
			$.getJSON("json/credit_reCredit_feedback?time=" + new Date().getTime() / 1000, {
				"ids" : ids
			}, function(data) {
				overallFeedbackHandle(data.feedback);
				if (data.feedback > 0) {
					location.reload();
				} else {
					alert("操作没有成功！");
				}
			});
		} else {
			alert("请选择至少一条记录！");
		}
	});

	$("#credit-manual-sub").click(function() {
		var uid = $("#uid").val();
		var credit = $("#credit").val();
		var description = $("#description").val();
		$.getJSON("json/credit_manual_feedback?time=" + new Date().getTime() / 1000, {
			"uid" : uid,
			"credit" : credit,
			"description" : description
		}, function(data) {
			overallFeedbackHandle(data.feedback);
			if (data.feedback > 0) {
				alert("给予成功~");
			} else {
				alert("uid不存在或者手机号码错误！");
			}
		});
	});
	// credit setting end

	// comment setting start
	$(".comment-credit-link").click(function() {
		var aComment = $(this).parents("tr");
		var cid = aComment.find("input[name='cid']").val();
		$("#comment-id").val(cid);
		var creditStatus = 2;
		var statusHtml = '<span class="green">已给予积分</span>';
		if ($(this).attr("id") == "comment-nocredit") {
			creditStatus = 1;
			statusHtml = '<span class="yellow">已处理，不给予积分</span>';
		}
		$.getJSON("json/comment_credit_feedback?time=" + new Date().getTime() / 1000, {
			"ids" : cid,
			"creditStatus" : creditStatus
		}, function(data) {
			overallFeedbackHandle(data.feedback);
			if (data.feedback == 100) {
				aComment.find(".status").html(statusHtml);
			} else if (data.feedback == -902) {
				aComment.find(".status").html("<span class='yellow'>评论积分规则超过上限，未给予！</span>");
			}
			aComment.find(".action").html("");
		});
	});

	$("#comment-set-apply").click(function() {
		var creditStatus = $("select[name=creditStatus]").val();
		var ids = "";
		var idArray = new Array();
		$("input[name=check]").each(function(index) {
			if ($(this).attr('checked') != undefined) {
				if (ids != "")
					ids += ",";
				ids += $(this).parent().find("input[name='cid']").val();
				idArray.push(index);
			}
		});
		if (ids != "") {
			$.getJSON("json/comment_credit_feedback?time=" + new Date().getTime() / 1000, {
				"ids" : ids,
				"creditStatus" : creditStatus
			}, function(data) {
				overallFeedbackHandle(data.feedback);
				if (data.feedback > 0) {
					location.reload();
				} else {
					alert("操作没有成功！");
				}
			});
		} else {
			alert("请选择至少一个评论！");
		}
	});
	// comment setting end

	// bbsthread setting start
	$("#bbsthread-set-apply").click(
			function() {
				var creditStatus = $("select[name=creditStatus]").val();
				var inputArrayStr = "";
				var idArray = new Array();
				$("input[name=check]").each(
						function(index) {
							if ($(this).attr('checked') != undefined) {
								var parent = $(this).parent().parent();
								var itemStr = parent.find("input[name='tid']").val() + ","
										+ parent.find("input[name='title']").val() + ","
										+ parent.find("input[name='link']").val() + ","
										+ parent.find("input[name='order']").val();
								if (inputArrayStr != "")
									inputArrayStr += "|";
								inputArrayStr += itemStr;
								idArray.push(index);
							}
						});
				if (inputArrayStr != "") {
					$.getJSON("json/bbsThread_edit_feedback?time=" + new Date().getTime()
							/ 1000, {
						"inputArrayStr" : inputArrayStr
					}, function(data) {
						overallFeedbackHandle(data.feedback);
						if (data.feedback > 0) {
							location.reload();
						} else {
							alert("操作没有成功！");
						}
					});
				} else {
					alert("请选择至少一个热帖！");
				}
			});

	$("#bbsThread-add-sub").click(
			function() {
				var title = $("#title").val();
				var _link = $("#link").val();
				var order = $("#order").val();
				var verify = true;
				if (title == "") {
					$("#title-notification").addClass("error").html("请输入标题");
					verify = false;
				} else {
					$("#title-notification").html("").removeClass("error").addClass("success");
				}
				if (_link == "") {
					$("#link-notification").addClass("error").html("请输入链接");
					verify = false;
				} else {
					$("#link-notification").html("").removeClass("error").addClass("success");
				}
				if (order == "") {
					$("#order-notification").addClass("error").html("请输入优先级");
					verify = false;
				} else if (BASEisNum(order)) {
					$("#order-notification").addClass("error").html("优先级必须是数字");
					verify = false;
				} else {
					$("#order-notification").html("").removeClass("error").addClass("success");
				}
				if (verify) {
					var param = {
						"title" : title,
						"link" : _link,
						"order" : order
					};
					$.getJSON("json/bbsThread_add_?time=" + new Date().getTime() / 1000,
							param, function(data) {
								overallFeedbackHandle(data.feedback);
								if (data.feedback == 100)
									alert("添加成功");
								else {
									alert("添加失败！");
								}
							});
				}
			});

	$(".bbs-delete-link").click(function() {
		var aBbs = $(this).parents("tr");
		var tid = aBbs.find("input[name='tid']").val();
		var result = confirm("您确定删除ID为" + tid + "的热帖？删除后不能恢复！")
		if (result) {
			$.getJSON("json/bbsThread_delete_?time=" + new Date().getTime() / 1000, {
				"id" : tid
			}, function(data) {
				overallFeedbackHandle(data.feedback);
				if (data.feedback > 0) {
					aBbs.fadeOut();
				}
			});
		}

	});
	// bbsthread setting end

	// news setting start
	$("#news-set-apply").click(function() {
		var operate = $("select[name=operate]").val();
		var ids = "";
		var idArray = new Array();
		$("input[name=check]").each(function(index) {
			if ($(this).attr('checked') != undefined) {
				if (ids != "")
					ids += ",";
				ids += $(this).parent().find("input[name='id']").val();
				idArray.push(index);
			}
		});
		if (ids != "") {
			$.getJSON("json/news_operate_feedback?time=" + new Date().getTime() / 1000, {
				"ids" : ids,
				"operate" : operate
			}, function(data) {
				overallFeedbackHandle(data.feedback);
				if (data.feedback > 0) {
					location.reload();
				} else {
					alert("操作没有成功！");
				}
			});
		} else {
			alert("请选择至少一个资讯！");
		}
	});
	$("#news-add-sub").click(
			function() {
				var title = $("#title").val();
				var ufid = $("#ufid").val();
				var description = $("#description").val();
				var content = CKEDITOR.instances.content.getData();
				var status = 0;
				if ($("input[name=status]").attr('checked') != undefined)
					status = 1;
				var verify = true;
				if (title == "") {
					$("#title-notification").addClass("error").html("请输入标题");
					verify = false;
				} else {
					$("#title-notification").html("").removeClass("error").addClass("success");
				}
				if (description == "") {
					$("#description-notification").addClass("error").html("请输入简介");
					verify = false;
				} else {
					$("#description-notification").html("").removeClass("error")
							.addClass("success");
				}
				if (content == "") {
					$("#content-notification").addClass("error").html("请输入内容");
					verify = false;
				} else {
					$("#content-notification").html("").removeClass("error").addClass("success");
				}
				if (verify) {
					var param = {
						"news.title" : title,
						"news.description" : description,
						"news.content" : content,
						"news.status" : status,
						"news.ufid" : ufid
					};
					$.post("json/news_add_feedback?time=" + new Date().getTime() / 1000,
							param, function(data, state) {
								overallFeedbackHandle(data.feedback);
								if (data.feedback == 100)
									alert("提交成功");
								else if (data.feedback == -1101) {
									alert("提交失败！");
									$("#title-notification").addClass("error").html("标题重复");
								} else {
									alert("提交失败！");
								}
							}, "json");
				}
			});
	$("#news-edit-sub").click(
			function() {
				var id = $("#act-id").val();
				var description = $("#description").val();
				var title = $("#title").val();
				var ufid = $("#ufid").val();
				var content = CKEDITOR.instances.content.getData();
				var status = 0;
				if ($("input[name=status]").attr('checked') != undefined)
					status = 1;
				var verify = true;
				if (title == "") {
					$("#title-notification").addClass("error").html("请输入标题");
					verify = false;
				} else {
					$("#title-notification").html("").removeClass("error").addClass("success");
				}
				if (description == "") {
					$("#description-notification").addClass("error").html("请输入简介");
					verify = false;
				} else {
					$("#description-notification").html("").removeClass("error")
							.addClass("success");
				}
				if (content == "") {
					$("#content-notification").addClass("error").html("请输入内容");
					verify = false;
				} else {
					$("#content-notification").html("").removeClass("error").addClass("success");
				}
				if (verify) {
					var param = {
						"news.id" : id,
						"news.title" : title,
						"news.description" : description,
						"news.content" : content,
						"news.status" : status,
						"news.ufid" : ufid
					};
					$.post("json/news_edit_feedback?time=" + new Date().getTime() / 1000,
							param, function(data, state) {
								overallFeedbackHandle(data.feedback);
								if (data.feedback == 100)
									alert("提交成功");
								else if (data.feedback == -1101) {
									alert("提交失败！");
									$("#title-notification").addClass("error").html("标题重复");
								} else {
									alert("提交失败！");
								}
							}, "json");
				}
			});
	$(".news-edit-link").click(function() {
		var aRow = $(this).parents("tr");
		var id = aRow.find("input[name='id']").val();
		$("#act-id").val(id);
		$.getJSON("json/news_news_?time=" + new Date().getTime() / 1000, {
			"id" : id
		}, function(data) {
			overallFeedbackHandle(data.feedback);
			var news = data.news;
			if (data.feedback == 100) {
				$("#title").val(news.title);
				$("#description").val(news.description);
				CKEDITOR.instances.content.setData(news.content);
				$('.content-box ul.content-box-tabs li a.detail').click();
				$("#filePageNum").val(1);
				listUploadPic();
			}
		});
	});
	$(".news-delete-link").click(function() {
		var aRow = $(this).parents("tr");
		var id = aRow.find("input[name='id']").val();
		var result = confirm("您确定删除ID为" + id + "的新闻？删除后不能恢复！")
		if (result) {
			$.getJSON("json/news_operate_feedback?time=" + new Date().getTime() / 1000, {
				"ids" : id,
				"operate" : "DELETE"
			}, function(data) {
				overallFeedbackHandle(data.feedback);
				if (data.feedback > 0) {
					aRow.fadeOut();
				}
			});
		}
	});
	$(".news-verify-link").click(function() {
		var aRow = $(this).parents("tr");
		var id = aRow.find("input[name='id']").val();
		var operate = "VERIFY";
		var resultHtml = "<span class='green'>通过审核</span>";
		if (aRow.find(".status").html().indexOf("green") > 0) {
			operate = "UNVERIFY";
			resultHtml = "<span class='red'>未通过</span>";
		}
		$.getJSON("json/news_operate_feedback?time=" + new Date().getTime() / 1000, {
			ids : id,
			"operate" : operate
		}, function(data) {
			overallFeedbackHandle(data.feedback);
			if (data.feedback > 0) {
				aRow.find(".status").html(resultHtml);
			}
		});
	});

	$("#next-page").show().click(function() {
		listUploadPic();
	});

	$("#cover #selectable").selectable({
		filter : "div",
		stop : function() {
			var result = $("#select-result").empty();
			if ($(".ui-selected", this).size() > 1) {
				alert("只能选择一个图片做封面！");
			} else {
				$("#ufid").val($(".ui-selected", this).attr("id"));
			}
		}
	});

	// news setting end

	// uploadfile setting start

	// uploadfile setting end

});// jQuery end

function listUploadPic() {
	$.getJSON("json/upload_list_uploadFiles?time=" + new Date().getTime() / 1000, {
		'ext' : 'PIC',
		'page' : $("#filePageNum").val(),
		'rows' : parseInt($("#selectable").width() / 63)
	}, function(data) {
		overallFeedbackHandle(data.feedback);
		if (data.feedback > 0) {
			jQuery.each(data.uploadFiles.rows, function(index, uploadFile) {
				$("#selectable").append(
						"<div class='ui-state-default' id='" + uploadFile.id + "'><img src='"
								+ uploadFile.webUrl + "' width='50px' height='50px' title='"
								+ uploadFile.description + "'/></div>");
			});
			$("#selectable").append("<p class='clear'></p>");
			if ($("#filePageNum").val() < data.uploadFiles.totalpage) {
				$("#filePageNum").val(parseInt($("#filePageNum").val()) + 1);
			} else {
				$("#next-page").html("");
			}
		}
	});
}

function BASEisNum(theNum) {// 判断是否为数字
	theNum = theNum.replace(/\s+/g, "")
	if (theNum == "")
		return false;
	for ( var i = 0; i < theNum.length; i++) {
		oneNum = theNum.substring(i, i + 1);
		if (oneNum < "0" || oneNum > "9")
			return true;
	}
	return false;
}