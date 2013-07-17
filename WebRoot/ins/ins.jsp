<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="/common/head.jsp">
	<s:param name="title">
		<s:property escape="false" value="ins.title" />-</s:param>
	<s:param name="keywords">
		<s:property escape="false" value="ins.title" />,</s:param>
	<s:param name="description"></s:param>
</s:include>

<link href="/css/jquery-ui-1.10.2.custom.min.css" rel="stylesheet" />
<script type="text/javascript" src="/js/flexpaper_flash.js"></script>
<script type="text/javascript" src="/js/jquery-ui-1.10.2.custom.min.js"></script>
<script src="/js/comment.js?070101"></script>

<script>
	jQuery(function($) {
		$("#swf-dialog").dialog({
			autoOpen : false,
			width : 750,
			height : 540,
			overlay : {
				opacity : 0.5,
				background : "black",
				overflow : 'hidden'
			}
		});
		$("#mobile-btn,#mobile-btn2").click(
				function() {
					swfobject.embedSWF($(this).attr("title"), "swf", "723", "480", "9.0.0",
							"expressInstall.swf");
					$("#swf-dialog").dialog("option", "title", "手机体验").dialog("open");
				});
		$("#mobile3D-btn,#mobile3D-btn2").click(
				function() {
					swfobject.embedSWF($(this).attr("title"), "swf", "723", "480", "9.0.0",
							"expressInstall.swf");
					$("#swf-dialog").dialog("option", "title", "立体外观").dialog("open");
				});
	});

	$("#detail-label #selectable").selectable(
			{
				filter : "li",
				stop : function(event, ui) {
					if ($(".ui-selected", this).size() > 1) {
					} else {
						$(".ui-selected", this).each(
								function() {
									var index = $("#selectable li").index(this);
									$("#detail-content>ul>li").hide();
									if (index == 0) {
										$("#detail-content>ul>li").eq(index).show();
									} else {
										$("#detail-content>ul>li").eq(1).show();
										swfobject.embedSWF($("#swf-url li").eq(index - 1).attr(
												"title"), "player", "680", "450", "9.0.0",
												"expressInstall.swf", {
													width : "680",
													height : "450"
												}, {
													menu : "false"
												}, {
													width : "680",
													height : "450"
												});
										//swfobject.createSWF({data:$("#swf-url li").eq(index-1).attr("title"),width:"723",height:"480"},{},"player");
									}
								});
					}
				}
			});
</script>

<div class="main">
	<div class="track">
		<a href="/index">首页</a>
		<s:if test="ins!=null">> <a href="/list/<s:property value="ins.category.id"/>.html"><s:property
					escape="false" value="ins.category.name" /> </a> > <s:property escape="false" value="ins.title" />
		</s:if>
	</div>
	<div class="t-page">
		<div class="left">
			<div class="left-block taglist">
				<div class="label label1">相关分类</div>
				<div class="content">
					<ul>
						<s:iterator value="cates" status="st" id="cat">
							<s:if test="#st.index<10">
								<li><a target="_blank" href="/list/<s:property value="#cat.id"/>.html"><s:property
											value="#cat.name" /> </a></li>
							</s:if>
						</s:iterator>
					</ul>
				</div>
			</div>
			<div class="left-block taglist">
				<div class="label label2">相关品牌</div>
				<div class="content">
					<ul>
						<s:iterator value="ins.category.brands" status="st" id="brand">
							<s:if test="#st.index<10">
								<li><a target="_blank"
									href="/list/<s:property value="ins.category.id"/>-<s:property value="#brand.id"/>.html"><s:property
											value="#brand.name" /> </a></li>
							</s:if>
						</s:iterator>
					</ul>
				</div>
			</div>
			<div class="left-block" id="thread">
				<div class="label label3">网友热议</div>
				<div class="content">
					<ul>
						<s:iterator value="threads" status="st" id="thread">
							<li>. <a target="_blank" href="<s:property value="#thread.link"/>" class="ins"><s:property
										escape="false" value="#thread.title" /> </a></li>
						</s:iterator>
					</ul>
				</div>
			</div>
		</div>
		<div class="right">
			<s:if test="ins==null">
				<div style="color:#FF8000; font-size:25px;margin:0 auto; width:550px;">对不起，您查看的说明书不存在或者正在审核中。</div>
			</s:if>
			<s:else>
				<div class="title">
					<s:property escape="false" value="ins.title" />
				</div>
				<div class="detail">
					<div class="icon">
						<img width="240px" onerror="this.src='/css/images/default130.png'"
							src="<s:property escape="false"  value="ins.iconUrl"/>"
							alt="<s:property escape="false"  value="ins.title"/>" />
					</div>
					<div class="info-list">
						<ul>
							<li>授权形式: 免费</li>
							<li>更新时间: <s:property escape="false" value="ins.updateTimeFormat" />
							</li>
							<li>类别: 手机</li>
							<li>语言: 简体中文</li>
							<li>评论等级: <span>★★★★★</span></li>
							<li>下载次数: <s:property escape="false" value="ins.downloadCount" />次</li>
						</ul>
					</div>
					<div class="btn-list">
						<s:if test="ins.insFile.swfCount!=0">
							<!--存在SWF文件-->
							<div class="btn" id="read-btn">
								<div class="read-btn"></div>
								<div class="txt">在线阅读</div>
							</div>
						</s:if>
						<div class="btn"
							onclick="javascript:location.href='/ins_downloadAuth?id=<s:property value="ins.id"/>'">
							<div class="down-btn"></div>
							<div class="txt">点击下载</div>
						</div>
						<s:if test="ins.g3Url!=null&&ins.g3Url!=''">
							<div class="btn" id="g3-btn">
								<div class="read-btn"></div>
								<div class="txt">
									<a href="<s:property escape="false"  value="ins.g3Url"/>" target="_blank">去G3频道</a>
								</div>
							</div>
						</s:if>

						<s:if test="ins.mobileSWFUrl!=null&&ins.mobileSWFUrl!=''">
							<div class="btn" id="mobile-btn"
								title="/<s:property escape="false"  value="ins.server"/>/<s:property escape="false"  value="ins.mobileSWFUrl"/>">
								<div class="mobile-btn"></div>
								<div class="txt">手机体验</div>
							</div>
						</s:if>
						<s:if test="ins.mobile3DSWFUrl!=null&&ins.mobile3DSWFUrl!=''">
							<div class="btn" id="mobile3D-btn"
								title="/<s:property escape="false"  value="ins.server"/>/<s:property escape="false"  value="ins.mobile3DSWFUrl"/>">
								<div class="mobile3D-btn"></div>
								<div class="txt">立体外观</div>
							</div>
						</s:if>
					</div>
					<div id="swf-dialog" style="display:none;">
						<div id="swf"></div>
					</div>
				</div>
				<div class="right-block" id="read">
					<div class="label label1">
						说明书摘要<a name="read"></a>
					</div>
					<div class="content">
						<div id="viewerPlace">
							/
							<s:property escape="false" value="ins.insFile.server" />
							/
							<s:property
								value="ins.insFile.fileUrl.replaceAll('(?:.pdf|.PDF|.docx|.doc|.pptx|.ppt|.zip|.rar)','.swf')" />
						</div>
						<div id="viewerCount" class="hide">
							<s:property value="ins.insFile.swfCount" />
						</div>
						<div class="desc" id="desc">
							<s:property escape="false" value="ins.description" />
						</div>
						<s:if test="ins.insFile.swfCount!=0">
							<!--存在SWF文件-->
							<div style="clear: both;padding-left:200px;float: left; padding-bottom: 5px;">
								<div id="read-btn2" class="read-btn2"></div>
								<s:if test="ins.mobileSWFUrl!=null&&ins.mobileSWFUrl!=''">
									<div id="mobile-btn2" class="mobile-btn2"
										title="/<s:property escape="false"  value="ins.server"/>/<s:property escape="false"  value="ins.mobileSWFUrl"/>"></div>
								</s:if>
								<s:if test="ins.mobile3DSWFUrl!=null&&ins.mobile3DSWFUrl!=''">
									<div id="mobile3D-btn2" class="mobile3D-btn2"
										title="/<s:property escape="false"  value="ins.server"/>/<s:property escape="false"  value="ins.mobile3DSWFUrl"/>"></div>
								</s:if>
							</div>
						</s:if>

					</div>
				</div>
				<div class="right-block" id="detail">
					<div class="label" id="detail-label">
						<ul id="selectable">
							<li class="ui-selected">下载列表<a name="down"></a></li>
							<!--
                  <s:if test="ins.mobileSWFUrl!=null&&ins.mobileSWFUrl!=''"><li>手机体验</li></s:if>
                  <s:if test="ins.mobile3DSWFUrl!=null&&ins.mobile3DSWFUrl!=''"><li>3D外观</li></s:if>
                  -->
						</ul>
					</div>
					<div class="content" id="detail-content">
						<ul>
							<li class="downlist">
								<ul>
									<li><span class="down-btn"></span><a target="_blank"
										href="/ins_downloadAuth?id=<s:property value="ins.id"/>" class="ins"><s:property
												escape="false" value="ins.title" /> </a></li>
								</ul>
							</li>
							<!--
                  <li>
                     <div id="swf-url">
                     <ul>
                         <s:if test="ins.mobileSWFUrl!=null&&ins.mobileSWFUrl!=''"><li class="mobile" title="/<s:property escape="false"  value="ins.server"/>/<s:property escape="false"  value="ins.mobileSWFUrl"/>"></li></s:if>
                         <s:if test="ins.mobile3DSWFUrl!=null&&ins.mobile3DSWFUrl!=''"><li class="mobile3D" title="/<s:property escape="false"  value="ins.server"/>/<s:property escape="false"  value="ins.mobile3DSWFUrl"/>"></li></s:if>
                     </ul>
                     </div>
                     <div id="player"></div>
                  </li>
                  -->
						</ul>
					</div>
					<div class="clear"></div>
				</div>

				<div class="comment">
					<div class="post">
						<div class="label">发表评论</div>
						<div class="form">
							<div class="gradeline">
								<div class="grade-label">评分：</div>
								<div class="grade-star">
									<div class="grade-star2"></div>
									<div class="grade-star2"></div>
									<div class="grade-star2"></div>
									<div class="grade-star2"></div>
									<div class="grade-star2"></div>
								</div>
							</div>
							<div>
								<textarea name="content" id="comment_content"></textarea>
								<input type="hidden" id="iid" value="<s:property value="ins.id"/>">
							</div>
							<div id="comment_submit"></div>
						</div>
					</div>
					<div class="list"></div>
				</div>

				<div class="comment-list">评论加载中....</div>
			</s:else>
		</div>
	</div>
</div>
<s:include value="common/foot.jsp" />
</body>
</html>
