jQuery(function($) {
	$(".username").focus(function() {
		$(this).hide();
		$("input[name=username]").show().focus();
	});
	$("input[name=username]").blur(function() {
		if ($(this).val() == "") {
			$(this).hide();
			$(".username").show();
		}
	});

	$(".password").focus(function() {
		$(this).hide();
		$("#password").show().focus();
	});
	$("input[name=password]").blur(function() {
		if ($(this).val() == "") {
			$(this).hide();
			$(".password").show();
		}
	});

	$("#search-btn").click(function() {
		Search();
	});
	$("input[name=keyword]").keydown(function(e) {
		if (e.keyCode == 13)
			Search();
	});
	function Search() {
		var keyword = $("input[name=keyword]").val();
		if (keyword == "")
			alert("输入关键字，有助于我们的查询~");
		else {
			location.href = "/ins_list?search=" + keyword;
		}
	}
	$(".login-btn").click(function() {
		var Username = $("input[name=username]").val();
		var Password = $("input[name=password]").val();
		var resinfo = "";
		if (Username == "")
			resinfo = "请输入手机号码或邮箱";
		// else if (!/^(1)\d{10}$/.test(Username))resinfo="您输入的手机号码格式不对哦~";
		// else if(Password=="")resinfo="请输入10086密码";
		// else if(!/^\d{6}$/.test(Password))resinfo="请输入6位数字密码";

		if (resinfo == "") {
			$("#loginform").submit();
			// $("input[name=username]").hide();
			// $(".username").val(Username).show();
			// $("input[name=password]").hide();
			// $(".password").val("......").show();
			// $(".resinfo").html("加载中...").show();
			// $.getJSON("/json/user_loginAuth_feedback",
			// {"loginname":Username,"password":Password,dateline:new
			// Date().getTime()},function(data){
			// if(data.feedback>=-1){
			// var user=data.loginUser.user;
			// $(".userinfo .icon
			// img").attr("src","http://www.139life.com/uc_server/avatar.php?uid="+data.loginUser.user.uid+"&size=middle");
			// $(".userinfo .detail .username").html(data.loginUser.user.username+",欢迎您！");
			// $(".userinfo .detail #lfb").html(data.loginUser.lfb);
			// $(".userinfo").show();
			// $(".login-form").hide();
			// return;
			// }else if(data.feedback==-2){
			// resinfo="手机号码，密码错误";
			// }else if(data.feedback==-3||data.feedback==-4){
			// resinfo="登录失败，可以前往<a href='http://www.139life.com'>主站</a>重试。";
			// }
			// else resinfo=data.feedback+"服务器错误";
			// $(".Username").val("手机号码").hide();
			// $("input[name=Username]").show();
			// $(".password").val("10086密码").hide();
			// $("input[name=password]").show();
			// $(".resinfo").html(resinfo).show();
			// });
		} else
			$(".resinfo").html(resinfo);
	});

	$("#logout")
			.click(
					function() {
						$
								.getJSON(
										"/json/user_logoutAuth_feedback",
										{
											dateline : new Date().getTime()
										},
										function(data) {
											if (data.feedback == 100) {
												location.href = 'http://www.139life.com/loginapi.php?a=logout&isforward=that&forward='
														+ location.href;
												$(".userinfo").hide();
												$(".smsphone").val("手机号码").show();
												$("input[name=smsphone]").val("").hide();
												$(".password").val("10086密码").show();
												$("input[name=password]").val("").hide();
												$(".resinfo").html("");
												$(".login-form").show();
											}
										});
					});

	$.getJSON("/json/user_queryLfbAuth_lfb", {
		dateline : new Date().getTime()
	}, function(data) {
		$(".userinfo .detail #lfb").html(data.lfb);
	});

	/*
	 * $.getJSON("/json/notification_newCountAuth_feedback", {dateline:new
	 * Date().getTime()},function(data){ $(".userinfo #notification").html("通知("+data.newCount+")");
	 * });
	 */

});