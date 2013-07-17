<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>“走进生态湿地，太湖深呼吸”—太湖湿地公园快乐游报名</title>
<meta http-equiv="keywords" content="走进生态湿地，太湖深呼吸,报名">
	<meta http-equiv="description" content="“走进生态湿地，太湖深呼吸”—太湖湿地公园快乐游报名">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="/css/normal.css" rel="stylesheet" />
		<script src="/js/jquery.js"></script>
		<style>
input[type="text"] {
	font-size: 15px;
	padding: 3px;
	width: 200px;
}

table td {
	font-size: 15px;
	font-family: Arial;
}
</style>
		<script>
			function changeType() {
				var type = $("#type").val();
				switch (type) {
				case "CAR":
					$("#motorcycleTypeTr").show();
					$("#plateNumberTr").show();
					$("#enrollmentTr").show();
					$("#yearTr").hide();
					$("#studentOrCommunityTr").hide();
					$("#playerOrFriendsTr").hide();
					$("#needBusOrNotTr").hide();
					$("#needDormOrNotTr").hide();
					$("#motorcycleTypeTr").hide();
					$("#schoolTr").hide();
					break;
				case "BIK":
					$("#motorcycleTypeTr").show();
					$("#plateNumberTr").hide();
					$("#enrollmentTr").hide();
					$("#yearTr").show();
					$("#studentOrCommunityTr").show();
					$("#playerOrFriendsTr").show();
					$("#needBusOrNotTr").show();
					$("#needDormOrNotTr").show();
					$("#motorcycleTypeTr").show();
					if ($('input:radio[name="studentOrCommunity"]:checked')
							.val() == 1) {
						$("#schoolTr").show();
					}
					break;
				case "OTR":
					$("#motorcycleTypeTr").hide();
					$("#plateNumberTr").hide();
					$("#enrollmentTr").hide();
					$("#yearTr").hide();
					$("#studentOrCommunityTr").hide();
					$("#playerOrFriendsTr").hide();
					$("#needBusOrNotTr").hide();
					$("#needDormOrNotTr").hide();
					$("#motorcycleTypeTr").hide();
					$("#schoolTr").hide();
					break;
				default:
					break;
				}

			}

			$(function() {
				if ($.browser.msie) {
					$('input:radio').click(function() {
						this.blur();
						this.focus();
					});
				}
				$("#studentOrCommunityTr")
						.change(
								function() {
									if ($(
											'input:radio[name="studentOrCommunity"]:checked')
											.val() == 1) {
										$("#schoolTr").show();
									} else {
										$("#schoolTr").val("").hide();
									}
								});
			});

			function sub() {
				var type = $("#type").val();
				var name = $("#name").val();
				var gender = $('input:radio[name="gender"]:checked').val();
				var idCardNo = $("#idCardNo").val();
				var phone = $("#phone").val();
				var year = $("#year").val();
				var enrollment = $("#enrollment").val();
				var plateNumber = $("#plateNumber").val();

				var studentOrCommunity = $(
						'input:radio[name="studentOrCommunity"]:checked').val();
				var playerOrFriends = $(
						'input:radio[name="playerOrFriends"]:checked').val();

				var needBusOrNot = 0;
				if ($("input[name=needBusOrNot]").attr('checked') != undefined)
					needBusOrNot = 1;
				var needDormOrNot = 0;
				if ($("input[name=needDormOrNot]").attr('checked') != undefined)
					needDormOrNot = 1;

				var motorcycleType = $("#motorcycleType").val();
				var school = $("#school").val();

				var param = {
					"travelSignInput.type" : type,
					"travelSignInput.name" : name,
					"travelSignInput.gender" : gender,
					"travelSignInput.idCardNo" : idCardNo,
					"travelSignInput.phone" : phone,
					"travelSignInput.year" : year,
					"travelSignInput.enrollment" : enrollment,
					"travelSignInput.plateNumber" : plateNumber,
					"travelSignInput.studentOrCommunity" : studentOrCommunity,
					"travelSignInput.playerOrFriends" : playerOrFriends,
					"travelSignInput.needBusOrNot" : needBusOrNot,
					"travelSignInput.needDormOrNot" : needDormOrNot,
					"travelSignInput.motorcycleType" : motorcycleType,
					"travelSignInput.school" : school
				};
				$("#response_info").html("加载中...");
				$.post("/json/travelSign_sign_feedback?time="
						+ new Date().getTime() / 1000, param, function(data) {
					if (data.feedback == 100) {
						$("#table").hide();
						$("#success").html("恭喜您，您的报名已成功提交！「苏州生活网」");
					} else {
						var response_info = "";
						if (data.feedback == -1301)
							response_info = "数据提交有误!";
						else if (data.feedback == -1302)
							response_info = "请选择类型!";
						else if (data.feedback == -1303)
							response_info = "姓名不可以为空!";
						else if (data.feedback == -1304)
							response_info = "身份证号码格式不正确,或者您已经报名!";
						else if (data.feedback == -1305)
							response_info = "联系方式不正确，请填写电话号码或手机号码，且为全数字!";
						else if (data.feedback == -1306)
							response_info = "人数必须为数字!";
						else if (data.feedback == -1307)
							response_info = "请填写车牌号码!";
						else if (data.feedback == -1308)
							response_info = "请填写正确的年龄!";

						alert(response_info);
						$("#response_info").html(response_info).delay(2000)
								.fadeOut();
					}
				}, "json");
			}
		</script>
</head>

<body style="margin: 0px; padding: 0px;">
	<div style="width: 1000px; margin: 0px auto;">
		<div>
			<img src="/css/images/travelsign/banner2.jpg" border="0px" />
		</div>
		<div style="width: 100%; background-color:#caf2a8; ">
			<div style="width: 905px; margin: 0px auto; padding-top: 30px;">
				<div style="float: left; width:321px; ">
					<div style="background: url('/css/images/travelsign/top3.jpg'); width:321px; height: 20px;"></div>
					<div style="background-color: #e1f8cd;padding: 20px;">
						<div style="border-bottom: 1px #31a052 solid; color: #31a052; font-size: 20px; font-weight: bold;">活动简介</div>
						<div style="font-size: 13px; line-height: 30px; color: #31a052;">
							<p>第三届长三角高校车协交流赛</p>

							<p>
								主办方：苏州大学自行车协会<br /> 特别赞助：苏州生活网行频道
							</p>

							<p>比赛时间：5月18日</p>

							<p>参赛对象：各高校自行车类社团，参赛选手要求为在读本科、专科生、硕士生、博士生。也欢迎其他自行车爱好者参与，但不计名次。</p>

							<p>颁奖晚会：5月18日晚苏州大学东校区</p>

							<p>
								比赛详情请见：&ldquo;骑行天下&rdquo; <a href="http://bbs.139life.com/forum-366-1.html" target="_blank">http://bbs.139life.com/forum-366-1.html</a>&nbsp;
							</p>

						</div>

					</div>
					<div style="background: url('/css/images/travelsign/bottom3.jpg'); width:321px; height: 20px;"></div>
				</div>
				<div style="float: left; width:564px; padding-left: 20px;">
					<div style="background: url('/css/images/travelsign/top2.jpg'); width:564px; height: 20px;"></div>
					<div style="background-color: #e1f8cd;padding: 30px;">
						<div id="success" style="font-size: 16px; font-weight: 600;color:green;"></div>
						<table cellpadding="15px" cellspacing="15px" style="color: #31a052;" border="0px" id="table">
							<tr id="typeTr">
								<td width="90px" align="right">类别：</td>
								<td><select style="font-size: 25px; width: 200px;" onchange="changeType()" id="type">
										<option value="BIK">第三届长三角高校车协交流赛</option>
										<!-- <option value="CAR">自驾游</option> -->
										<option value="OTR">其他用户</option>
								</select></td>
							</tr>
							<tr id="nameTr">
								<td align="right">姓名：</td>
								<td><input name="name" type="text" id="name" /></td>
							</tr>
							<tr id="genderTr">
								<td align="right">性别：</td>
								<td><input name="gender" type="radio" checked="checked" value="1" /> 男&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="gender" type="radio" value="0" /> 女</td>
							</tr>
							<tr id="idCardNoTr">
								<td align="right">身份证号码：</td>
								<td><input name="idCardNo" type="text" id="idCardNo" /></td>
							</tr>
							<tr id="phoneTr">
								<td align="right">联系方式：</td>
								<td><input name="phone" type="text" id="phone" /></td>
							</tr>
							<tr id="yearTr">
								<td align="right">年龄：</td>
								<td><select id="year" style="font-size: 25px; width: 200px;">
										<s:bean name="org.apache.struts2.util.Counter" id="counter">
											<s:param name="first" value="10" />
											<s:param name="last" value="99" />
											<s:iterator>
												<s:if test="current==20">
													<option value="<s:property value="current"/>" selected="selected">
														<s:property value="current" />
													</option>
												</s:if>
												<s:else>
													<option value="<s:property value="current"/>">
														<s:property value="current" />
													</option>
												</s:else>
											</s:iterator>
										</s:bean>
								</select>（岁）</td>
							</tr>
							<tr id="enrollmentTr" style="display:none;">
								<td align="right">人数：</td>
								<td><select id="enrollment" style="font-size: 25px; width: 200px;">
										<s:bean name="org.apache.struts2.util.Counter" id="counter">
											<s:param name="first" value="0" />
											<s:param name="last" value="9" />
											<s:iterator>
												<s:if test="current==4">
													<option value="<s:property value="current"/>" selected="selected">
														<s:property value="current" />
													</option>
												</s:if>
												<s:else>
													<option value="<s:property value="current"/>">
														<s:property value="current" />
													</option>
												</s:else>
											</s:iterator>
										</s:bean>
								</select>（个）</td>
							</tr>
							<tr id="plateNumberTr" style="display:none;">
								<td align="right">车牌号码：</td>
								<td><input name="plateNumber" type="text" id="plateNumber" />
								</td>
							</tr>
							<tr id="studentOrCommunityTr">
								<td></td>
								<td><input name="studentOrCommunity" type="radio" checked="checked" value="1" /> 在校学生&nbsp;&nbsp;<input name="studentOrCommunity" type="radio" value="0" /> 社会选手</td>
							</tr>
							<tr id="schoolTr">
								<td align="right">学校名称：</td>
								<td><input name="school" type="text" id="school" />
								</td>
							</tr>
							<tr id="playerOrFriendsTr">
								<td></td>
								<td><input name="playerOrFriends" type="radio" checked="checked" value="1" /> 比赛选手&nbsp;&nbsp;<input name="playerOrFriends" type="radio" value="0" /> 亲友团</td>
							</tr>
							<tr id="needBusOrNotTr">
								<td></td>
								<td><input name="needBusOrNot" type="checkbox" />是否需要乘坐大巴前往比赛场地</td>
							</tr>
							<tr id="needDormOrNotTr">
								<td></td>
								<td><input name="needDormOrNot" type="checkbox" />是否需要提供统一住宿</td>
							</tr>
							<tr id="motorcycleTypeTr">
								<td align="right">车型：</td>
								<td><input name="motorcycleType" type="text" id="motorcycleType" /></td>
							</tr>
							<tr>
								<td></td>
								<td><div style="width:120px; margin: 0 auto; margin-top: 40px;padding-bottom: 20px;">
										<a href="javascript:sub()"><img src="/css/images/travelsign/sub.png" width="120px">
										</a>
									</div></td>
							</tr>
							<tr>
								<td colspan="2" align="center" id="response_info" style="color: red"></td>
							</tr>
						</table>
					</div>
					<div style="background: url('/css/images/travelsign/bottom2.jpg'); width:564px; height: 20px;"></div>
				</div>


			</div>

			<s:include value="/common/foot.jsp" />
		</div>

	</div>

</body>
</html>
