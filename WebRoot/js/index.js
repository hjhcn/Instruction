jQuery(function($) {
	// 首页分类
	$(".cat_tab .label li").click(function() {
		if ($(this).attr("id") == "3c") {
			window.open("/news_list");
		} else if ($(this).attr("id") == "3d") {
			window.open("/ins_list?mobile3d=1");
		} else {
			$(".cat_tab .label li .left_sel").removeClass("left_sel").addClass("left_def");
			$(".cat_tab .label li .middle_sel").removeClass("middle_sel").addClass("middle_def");
			$(".cat_tab .label li .right_sel").removeClass("right_sel").addClass("right_def");
			$(this).find(".left").removeClass("left_def").addClass("left_sel");
			$(this).find(".middle").removeClass("middle_def").addClass("middle_sel");
			$(this).find(".right").removeClass("right_def").addClass("right_sel");
			$(".cat_tab .content li").hide();
			$(".cat_tab .content li").eq($(this).index()).show();
		}
	});

	// 列表页分类初始化
	var selCat = $("#cat" + $("#cid").html()).parents(".cat");
	selCat.find(".level2").show();
	selCat.find(".levelswitch").removeClass("open").addClass("close");
	var catlevel1 = selCat.find(".name").html();
	$(".track").append(catlevel1);

	// 列表页分类开关
	$(".level1").click(function() {
		if ($(this).find(".levelswitch").attr("class").indexOf("open") > 0) {
			if (selCat != null) {
				selCat.find(".levelswitch").removeClass("close").addClass("open");
				selCat.find(".level1").css("border-bottom", "");
				selCat.find(".level2").hide("blind");
			}
			selCat = $(this).parent();
			selCat.find(".levelswitch").removeClass("open").addClass("close");
			selCat.find(".level1").css("border-bottom", "solid 1px #cacaca");
			selCat.find(".level2").show("blind");
		} else {
			$(this).find(".levelswitch").removeClass("close").addClass("open");
			$(this).css("border-bottom", "solid 1px #cacaca");
			$(this).parent().find(".level2").hide("blind");
			selCat = null;
		}
	});

	String.prototype.NoSpace = function() {
		return (this.replace(/\s+/g, "")).replace(/[\r\n]/g, "");
	}

	$("#read-btn,#read-btn2").click(
			function() {
				$("html,body").animate({
					scrollTop : $("#read").offset().top
				}, 1000);
				$("#desc").hide();
				$("#viewerPlace").show();
				var swfUrl = ($("#viewerPlace").html()).NoSpace();
				var swfCount = ($("#viewerCount").html()).NoSpace();
				if (swfCount > 0)
					swfUrl = "{" + swfUrl.substring(0, swfUrl.lastIndexOf(".")) + "_SWF/[*,0].swf,"
							+ swfCount + "}";
				var fp = new FlexPaperViewer('/swf/FlexPaperViewer', 'viewerPlace', {
					config : {
						SwfFile : swfUrl,
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
			});
	$("#down-btn").click(function() {
		$("html,body").animate({
			scrollTop : $("#detail").offset().top
		}, 1000);
	});

});