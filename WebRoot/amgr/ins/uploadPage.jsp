<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage=""%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="../common/head.jsp" />
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	jQuery(function($) {
		ins_editor = CKEDITOR.replace("description");
	});
</script>

<body>
	<div id="main-content">
		<div id="loading"></div>
		<!-- Main Content Section with everything -->
		<noscript>
			<!-- Show a notification if the user has disabled javascript -->
			<div class="notification error png_bg">
				<div>对不起，您的浏览器没有开启或者不支持 Javascript. 请开启javascript或者更新您的浏览器.</div>
		</noscript>

		<!-- End .clear -->
		<div class="content-box">
			<!-- Start Content Box -->
			<div class="content-box-header">
				<h3>说明书上传</h3>
				<ul class="content-box-tabs">
					<li><a href="#tab1" class="default-tab">上传</a>
					</li>
				</ul>
				<div class="clear"></div>
			</div>
			<!-- End .content-box-header -->
			<div class="content-box-content">

				<div class="tab-content default-tab" id="tab1">
					<form action="#" method="post">
						<fieldset>
							<!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
							<p>
								<label>产品类型</label> <select id="cat" name="cat" class="small-input">
									<option value="0">请选择产品类型</option>
									<s:iterator value="categorys" status="st" id="cat">
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
								</select> <span class="input-notification" id="cid-notification"></span>
							</p>
							<p>
								<label>选择品牌</label> <select id="bid" name="bid" class="small-input">
									<option value="0">请选择品牌</option>
									<s:iterator value="brands" status="st" id="brand">
										<option value="<s:property escape="false"  value="#brand.id"/>">
											<s:property escape="false" value="#brand.name" />
										</option>
									</s:iterator>
								</select> <span class="input-notification" id="bid-notification"></span>
							</p>
							<p>
								<label>型号</label> <input class="text-input small-input" type="text" name="model" id="model" />
								<span class="input-notification" id="model-notification"></span>
								<!-- Classes for input-notification: success, error, information, attention -->
							</p>
							<p>
								<label>标题</label> <input class="text-input medium-input" type="text" name="title" id="title" />
								<span class="input-notification" id="title-notification"></span>
								<!-- Classes for input-notification: success, error, information, attention -->
							</p>
							<p>
								<label>上传图片</label> <input class="text-input medium-input" type="text" name="iconUrl"
									id="iconUrl" readonly /> <span class="input-notification" id="iconUrl-notification"></span>
								<br /> <input type='file' id='imgBtn' /> <input type="checkbox" id="icon-default" />使用默认图片
								<span> <input type="hidden" id="iconUfid"> </span>
							<div id='fileQueue1'></div>
							<div id='imgshow'></div>
							</p>
							<p>
								<label>上传文件</label> <input class="text-input medium-input" type="text" name="insUrl"
									id="insUrl" readonly /> <span class="input-notification" id="insUrl-notification"></span>
								<br /> <span class="file-btn"><input type='file' id='fileBtn' /> <input
									type="hidden" id="insUfid"> </span>
							<div id='fileQueue2'></div>
							<div id="fileList" class="hide">
								<select id="fileListSelect" multiple></select> <input type="button" class="button"
									id="select-btn" value="选择" />
							</div>
							</p>
							<p>
								<label>说明书描述</label>
								<textarea id="description" name="description" cols="79" rows="15"></textarea>
								<span class="input-notification" id="description-notification"></span>
							</p>
							<p>
								<label>G3链接</label> <input class="text-input medium-input" type="text" name="g3Url"
									id="g3Url" /> <span class="input-notification" id="g3Url-notification"></span>
							</p>
							<p>
								<label>手机体验</label> <input id="mobileUfid" type="hidden" /> <input
									class="text-input medium-input" type="text" id="mobile" /> <span
									class="mobile-notification" id="mobile-notification"></span>
							</p>
							<p>
								<label>3D外观</label> <input id="mobile3DUfid" type="hidden" /> <input
									class="text-input medium-input" type="text" id="mobile3D" /> <span
									class="mobile3D-notification" id="mobile3D-notification"></span>
							</p>
							<p>
								<input type="checkbox" name="status" checked />是否直接审核通过
							</p>
							<p>
								<input type="button" class="button" id="sub" value="提交" />
							</p>
						</fieldset>
						<div class="clear"></div>
						<!-- End .clear -->
					</form>
				</div>
				<!-- End #tab1 -->
			</div>
			<!-- End .content-box-content -->
		</div>
		<!-- End .content-box -->

		<s:include value="../common/foot.jsp" />

	</div>
	<!-- End #main-content -->
</body>
</html>
