<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="../common/head.jsp">
	<s:param name="title">说明书上传-</s:param>
	<s:param name="keywords"></s:param>
	<s:param name="description"></s:param>
</s:include>
<script type="text/javascript" src="/js/uploadify.js"></script>
<script type="text/javascript">
	var JSESSIONID = "${pageContext.session.id}";
</script>
<script type="text/javascript" src="/js/user.js?v=040201"></script>

<div class="main">
	<div class="track">
		<a href="index">首页</a> > 上传说明书 >
		<s:property escape="false" value="ins.title" />
	</div>
	<div class="t-page">
		<div class="left">
			<div class="left-block" id="thread">
				<div class="label label3">网友热议</div>
				<div class="content">
					<ul>
						<s:iterator value="threads" status="st" id="thread">
							<li>. <a target="_blank" href="<s:property value="#thread.link"/>" class="ins"><s:property
										escape="false" value="#thread.title" />
							</a>
							</li>
						</s:iterator>
					</ul>
				</div>
			</div>
		</div>
		<div class="right">
			<div class="upload">
				<div class="label">
					上传说明书<span class="remarks">(上传审核通过后奖励 <span class="credit"><s:property
								escape="false" value="credit" />
					</span> 个来福币)</span><a href="ins_listInsOfUserAuth">我已上传</a>
				</div>
				<form action="#" method="post">
					<!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
					<p>
						<label>产品类型</label> <select id="cat" name="cat" class="medium-input">
							<option value="0">请选择产品类型</option>
							<s:iterator value="cates" status="st" id="cat">
								<option
									value="<s:property escape="false"  value="#cat.id"/>|<s:property escape="false"  value="#cat.level"/>">
									<s:bean name="org.apache.struts2.util.Counter" id="counter">
										<s:param name="first" value="1" />
										<s:param name="last" value="#cat.level" />
										<s:iterator>&nbsp;&nbsp;&nbsp;&nbsp;</s:iterator>
									</s:bean>
									<s:property escape="false" value="#cat.name" />
								</option>
							</s:iterator>
						</select>
					<div class="input-notification" id="cid-notification">请选择二级及以下分类</div>
					</p>
					<p>
						<label>选择品牌</label> <select id="bid" name="bid" class="medium-input">
							<option value="0">请选择品牌</option>
						</select>
					<div class="input-notification" id="bid-notification">如无你需要上传的品牌，请选择“其他”</div>
					</p>
					<p>
						<label>型号</label> <input class="text-input small-input" type="text" name="model" id="model" />
					<div class="input-notification" id="model-notification">请准确输入型号，不包括品牌、其他等</div>
					<!-- Classes for input-notification: success, error, information, attention -->
					</p>
					<p>
						<label>标题</label> <input class="text-input medium-input" type="text" name="title" id="title" />
					<div class="input-notification" id="title-notification">标题格式为：品牌+型号+类型+说明书下载
						例如：三星GT-i9108手机说明书下载</div>
					<!-- Classes for input-notification: success, error, information, attention -->
					</p>
					<p>
						<label>上传图片</label> <input class="text-input medium-input" type="text" name="iconUrl"
							id="iconUrl" readonly /> <input type="hidden" name="iconUfid" id="iconUfid" /> <span><input
							type='file' id='imgBtn' />
						</span>
					<div class="input-notification" id="iconUrl-notification">请上传清晰产品图片</div>
					<div class="form-right">
						<div id='fileQueue1'></div>
						<div id='imgshow'></div>
					</div>
					</p>
					<p>
						<label>上传文件</label> <input class="text-input medium-input" type="text" name="insUrl"
							id="insUrl" readonly /> <input type="hidden" name="insUfid" id="insUfid" /> <span><input
							type='file' id='fileBtn' />
						</span>
					<div class="input-notification" id="insUrl-notification">请上传office文件或PDF文件</div>
					<div class="form-right">
						<div id='fileQueue2'></div>
					</div>
					</p>
					<p>
						<label>说明书描述</label>
						<textarea class="text-input textarea wysiwyg" style="color:#CCC" id="description"
							name="description" cols="79" rows="15">/请添加产品简单说明/</textarea>
					<div class="input-notification" id="description-notification"></div>
					</p>
					<p>
						<label>&nbsp;</label> <input type="button" class="button" id="sub" value="提交" />
					</p>
					<div class="clear"></div>
					<!-- End .clear -->
				</form>

			</div>
			<!-- End upload -->

		</div>
	</div>
</div>
<s:include value="../common/foot.jsp" />
</body>
</html>
