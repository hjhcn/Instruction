jQuery(function($) {
	// 加载评论
	var Iid = $("#iid").val();
	$
			.getJSON(
					"/json/comment_list?time=" + new Date().getTime() / 1000,
					{
						iid : Iid
					},
					function(data) {
						$(".comment-list").html("<ul></ul>");
						jQuery
								.each(
										data.pageView.rows,
										function(index, comment) {
											var starHTML = "";
											for ( var i = 0; i < comment.grade; i++) {
												starHTML += "<span class='grade-star1'></span>";
											}
											for ( var i = 0; i < 5 - comment.grade; i++) {
												starHTML += "<span class='grade-star2'></span>";
											}
											$(".comment-list ul")
													.append(
															"<li><div class='avatar'><div><a target='_blank' href='http://www.139life.com/home.php?mod=space&uid="
																	+ comment.uid
																	+ "'><img src='http://www.139life.com/uc_server/avatar.php?uid="
																	+ comment.uid
																	+ "&size=middle' width='68px' height='68px'></a></div><div class='username'>"
																	+ comment.username
																	+ "</div></div><div class='usercomment'><div class='star'>"
																	+ starHTML
																	+ "</div><div class='content'>"
																	+ comment.content
																	+ "</div></div></li>");
										});
					});

	var star = -1;
	$(".grade-star").children().hover(function() {
		var star_list = $(this).parent().children();
		for ( var i = 0; i < star_list.length; i++) {
			if (i <= $(this).index()) {
				star_list.eq(i).removeClass("grade-star2").addClass("grade-star1");
			} else {
				star_list.eq(i).removeClass("grade-star1").addClass("grade-star2");
			}
		}
	}, function() {
		var star_list = $(this).parent().children();
		for ( var i = 0; i < star_list.length; i++) {
			if (i <= star) {
				star_list.eq(i).removeClass("grade-star2").addClass("grade-star1");
			} else {
				star_list.eq(i).removeClass("grade-star1").addClass("grade-star2");
			}
		}
	}).click(function() {
		star = $(this).index();
	});

	$("#comment_submit")
			.click(
					function() {
						var Grade = star + 1;
						var comment = $("#comment_content").val();
						if (Grade <= 0)
							alert("给个评分先~~");
						else if (comment == "")
							alert("还是写点什么吧~~");
						else {
							$("#comment_content").val("加载中...");
							$
									.getJSON(
											"/json/comment_postAuth_userSession",
											{
												iid : Iid,
												grade : Grade,
												content : comment,
												dateline : new Date().getTime()
											},
											function(data) {
												if (data.feedback > 0) {
													var starHTML = "";
													for ( var i = 0; i < Grade; i++) {
														starHTML += "<span class='grade-star1'></span>";
													}
													for ( var i = 0; i < 5 - Grade; i++) {
														starHTML += "<span class='grade-star2'></span>";
													}
													var comment_item_template = "<li><div class='avatar'><div><a target='_blank' href='http://www.139life.com/home.php?mod=space&uid="
															+ data.userSession.uid
															+ "'><img src='http://www.139life.com/uc_server/avatar.php?uid="
															+ data.userSession.uid
															+ "&size=middle' width='68px' height='68px'></a></div><div class='username'>"
															+ data.userSession.username
															+ "</div></div><div class='usercomment'><div class='star'>"
															+ starHTML
															+ "</div><div class='content'>"
															+ comment + "</div></div></li>";
													$(".comment-list ul").prepend(
															comment_item_template);
													$(".comment-list ul li:first").hide();
													$(".comment-list ul li:first").fadeIn();
													// $(".comment-list ul
													// li:gt(1)").fadeIn(function(){$(".comment-list
													// ul li:first").fadeIn();});
													// 清空
													star = -1;
													$("#comment_content").val("");
													$(".grade-star").children().removeClass(
															"grade-star1").addClass("grade-star2");
												} else if (data.feedback == -201) {
													$("#comment_content").val(comment);
													$("html,body").animate({
														scrollTop : $(".login").offset().top
													}, 1000);
													alert("登录后才能发表评论哦！");
												}
											});
						}
					});

});
