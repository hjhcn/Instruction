<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="/common/head.jsp" />
<div class="main">
	<div class="cat_tab">
		<div class="label">
			<ul>
				<s:iterator value="cates" id="cat" status="st">
					<s:if test="#cat.level==0">
						<s:if test="#st.index==0">
							<li><div class="left left_sel"></div>
								<div class="middle middle_sel">
									<s:property escape="false" value="#cat.name" />
								</div>
								<div class="right right_sel"></div></li>
						</s:if>
						<s:else>
							<li><div class="left left_def"></div>
								<div class="middle middle_def">
									<s:property escape="false" value="#cat.name" />
								</div>
								<div class="right right_def"></div></li>
						</s:else>
					</s:if>
				</s:iterator>
				<li id="3c"><div class="left left_def"></div>
					<div class="middle middle_def">3C资讯</div>
					<div class="right right_def"></div></li>
				<li id="3d"><div class="left left_def"></div>
					<div class="middle middle_def">三维说明书</div>
					<div class="right right_def"></div></li>
			</ul>
		</div>
		<div class="red-bar"></div>
		<div class="content">
			<ul>
				<li>
					<div>
						<a class="l1" href="list/1.html" target="_blank">热门品牌:</a> <a
							class="l2" href="list/1-7.html" target="_blank">三星</a> <a
							class="l2" href="list/1-36.html" target="_blank">HTC</a> <a
							class="l2" href="list/1-1.html" target="_blank">诺基亚</a> <a
							class="l2" href="list/1-8.html" target="_blank">索尼爱立信</a> <a
							class="l2" href="list/1-2.html" target="_blank">摩托罗拉</a> <a
							class="l2" href="list/1-29.html" target="_blank">LG</a> <a
							class="l2" href="list/1-3.html" target="_blank">多普达</a>
					</div>
					<div>
						<a class="l1" href="#">国产手机:</a> <a class="l2"
							href="list/1-55.html" target="_blank">中兴</a> <a class="l2"
							href="list/1-52.html" target="_blank">华为</a> <a class="l2"
							href="list/1-86.html" target="_blank">波导</a> <a class="l2"
							href="list/1-22.html" target="_blank">魅族</a> <a class="l2"
							href="list/1-26.html" target="_blank">联想</a> <a class="l2"
							href="list/1-183.html" target="_blank">OPPO</a>
					</div>
					<div>
						<a class="l1" href="list/11.html" target="_blank">Android手机:</a> <a
							class="l2" href="ins/9797.html" target="_blank">索尼爱立信xperiax10</a>
						<a class="l2" href="ins/8117.html" target="_blank">三星I9100</a> <a
							class="l2" href="ins/7186.html" target="_blank">HTC Sensation</a>
					</div>
					<div>
						<a class="l1" href="ins/11501.html" target="_blank">苹果:</a> <a
							class="l2" href="ins/11504.html" target="_blank">iphone 3G</a> <a
							class="l2" href="ins/11502.html" target="_blank">iphone 3GS</a> <a
							class="l2" href="ins/11503.html" target="_blank">iphone 4</a> <a
							class="l2" href="ins/11501.html" target="_blank">iphone 4S</a>
					</div>
				</li>
				<li class="hide">
					<div>
						<a class="l1" href="list/56.html" target="_blank">台式机:</a> <a
							class="l2" href="list/56.html" target="_blank">戴尔</a> <a
							class="l2" href="list/56-26.html" target="_blank">联想</a> <a
							class="l2" href="list/56.html" target="_blank">惠普</a> <a
							class="l2" href="list/56-10.html" target="_blank">苹果一体机</a> <a
							class="l2" href="list/56-515.html" target="_blank">宏基</a>
					</div>
					<div>
						<a class="l1" href="list/57.html">笔记本电脑:</a> <a class="l2"
							href="list/57.html" target="_blank">索尼</a> <a class="l2"
							href="list/57.html" target="_blank">ThinkPad</a> <a class="l2"
							href="list/57-7.html" target="_blank">三星</a> <a class="l2"
							href="list/57-10.html" target="_blank">苹果</a> <a class="l2"
							href="list/57-515.html" target="_blank">宏基</a> <a class="l2"
							href="list/57-131.html" target="_blank">东芝</a>
					</div>
					<div>
						<a class="l1" href="list/2.html" target="_blank">热门:</a> <a
							class="l2" href="ins/9797.html" target="_blank">联想</a> <a
							class="l2" href="ins/8117.html" target="_blank">三星I9100</a> <a
							class="l2" href="ins/7186.html" target="_blank">HTC Sensation</a>
					</div>
					<div>
						<a class="l1" href="#" target="_blank">周边:</a> <a class="l2"
							href="list/67.html" target="_blank">显示器</a> <a class="l2"
							href="list?search=显卡" target="_blank">显卡</a> <a class="l2"
							href="list?search=声卡" target="_blank">声卡</a> <a class="l2"
							href="list/63.html" target="_blank">PDA</a>
					</div>
				</li>
				<li class="hide">
					<div>
						<a class="l1" href="list/1.html" target="_blank">数码相机:</a> <a
							class="l2" href="list/84.html" target="_blank">单反相机</a> <a
							class="l2" href="list/85.html" target="_blank">卡片相机</a> <a
							class="l2" href="list/88.html" target="_blank">单电相机</a>
					</div>
					<div>
						<a class="l1" href="#">数码摄像机:</a> <a class="l2"
							href="list/94.html" target="_blank">高清摄像机</a> <a class="l2"
							href="list/97.html" target="_blank">专业摄像机</a> <a class="l2"
							href="list/98.html" target="_blank">闪存摄像机</a> <a class="l2"
							href="list/99.html" target="_blank">硬盘摄像机</a>
					</div>
					<div>
						<a class="l1" href="ins?id=6884" target="_blank">品牌:</a> <a
							class="l2" href="list?search=佳能" target="_blank">佳能</a> <a
							class="l2" href="list?search=尼康" target="_blank">尼康</a> <a
							class="l2" href="list?search=松下" target="_blank">松下</a> <a
							class="l2" href="list?search=奥林巴斯" target="_blank">奥林巴斯</a>
					</div>
					<div>
						<a class="l1" href="list/11.html" target="_blank">热门:</a>
					</div>
				</li>
				<li class="hide">
					<div>
						<a class="l1" href="list/1.html" target="_blank">客厅大家电:</a> <a
							class="l2" href="list/14.html" target="_blank">电视机</a> <a
							class="l2" href="list/16.html" target="_blank">冰箱</a> <a
							class="l2" href="list/17.html" target="_blank">空调</a> <a
							class="l2" href="list/27.html" target="_blank">家庭音响</a>
					</div>
					<div>
						<a class="l1" href="#">厨房大家电:</a> <a class="l2"
							href="list/26.html" target="_blank">洗碗机</a> <a class="l2"
							href="list/20.html" target="_blank">冷柜</a> <a class="l2"
							href="list/25.html" target="_blank">消毒柜</a> <a class="l2"
							href="list/21.html" target="_blank">热水器</a>
					</div>
				</li>
				<li class="hide">
					<div>
						<a class="l1" href="list/1.html" target="_blank">厨房小家电:</a> <a
							class="l2" href="list/31.html" target="_blank">电饭锅</a> <a
							class="l2" href="list/32.html" target="_blank">豆浆机</a> <a
							class="l2" href="list/33.html" target="_blank">电热水壶</a> <a
							class="l2" href="list/34.html" target="_blank">微波炉</a> <a
							class="l2" href="list/35.html" target="_blank">电压力煲</a>
					</div>
					<div>
						<a class="l1" href="#">家居小家电:</a> <a class="l2"
							href="list/43.html" target="_blank">电风扇</a> <a class="l2"
							href="list/44.html" target="_blank">吸尘器</a> <a class="l2"
							href="list/45.html" target="_blank">电暖器</a> <a class="l2"
							href="list/46.html" target="_blank">加湿器</a>
					</div>
					<div>
						<a class="l1" href="#">个人生活小家电:</a> <a class="l2"
							href="list/50.html" target="_blank">电吹风</a> <a class="l2"
							href="list/51.html" target="_blank">电动剃须刀</a> <a class="l2"
							href="list/52.html" target="_blank">电熨斗</a> <a class="l2"
							href="list/53.html" target="_blank">电动牙刷</a>
					</div>
				</li>
				<li class="hide">
					<div>
						<a class="l1" href="list/1.html" target="_blank">MP3:</a> <a
							class="l2" href="#" target="_blank">索尼</a> <a class="l2" href="#"
							target="_blank">苹果</a> <a class="l2" href="#" target="_blank">台电</a>
						<a class="l2" href="#" target="_blank">纽曼</a>
					</div>
					<div>
						<a class="l1" href="#">MP4:</a> <a class="l2" href="#"
							target="_blank">索尼</a> <a class="l2" href="#" target="_blank">苹果</a>
						<a class="l2" href="#" target="_blank">台电</a> <a class="l2"
							href="#" target="_blank">纽曼</a>
					</div>
					<div>
						<a class="l1" href="#">MP5:</a> <a class="l2" href="#"
							target="_blank">索尼</a> <a class="l2" href="#" target="_blank">苹果</a>
						<a class="l2" href="#" target="_blank">台电</a> <a class="l2"
							href="#" target="_blank">纽曼</a>
					</div>
				</li>
				<li class="hide">
					<div>
						<a class="l1" href="list/1.html" target="_blank">热门:</a> <a
							class="l2" href="#" target="_blank">索尼PS3</a> <a class="l2"
							href="#" target="_blank">微软XBOX360</a> <a class="l2" href="#"
							target="_blank">任天堂WII</a>
					</div>
					<div>
						<a class="l1" href="#">周边:</a> <a class="l2" href="list/81.html"
							target="_blank">方向盘</a> <a class="l2" href="list/82.html"
							target="_blank">游戏杆</a> <a class="l2" href="list/83.html"
							target="_blank">游戏机光枪</a>
					</div>
				</li>
				<li class="hide"><a class="l2" href="list/114.html"
					target="_blank">家庭电话</a> <a class="l2" href="list/115.html"
					target="_blank">宜居通</a> <a class="l2" href="list/116.html"
					target="_blank">可视固话</a> <a class="l2" href="list/117.html">儿童机</a>
					<a class="l2" href="list/118.html" target="_blank">老人机</a> <a
					class="l2" href="list/119.html" target="_blank">彩信相框</a> <a
					class="l2" href="list/120.html" target="_blank">家庭信息机</a> <a
					class="l2" href="list/122.html" target="_blank">家庭监控</a> <a
					class="l2" href="list/123.html" target="_blank">H-Zone</a> <a
					class="l2" href="list/124.html" target="_blank">家庭网关</a> <a
					class="l2" href="list/125.html" target="_blank">G3上网卡</a> <a
					class="l2" href="list/126.html" target="_blank">IPAD伴侣</a> <a
					class="l2" href="list/127.html" target="_blank">PAD</a> <a
					class="l2" href="list/128.html" target="_blank">家庭健康</a>
				</li>
			</ul>
		</div>
	</div>
	<div class="main-info">
		<div class="long-div">
			<div class="label">
				<div class="mainlabel">热门下载</div>
			</div>
			<div class="content show">
				<ul>
					<s:iterator value="hotInses" status="st" id="ins">
						<li>
							<div class="icon">
								<a target="_blank" href="ins/<s:property value="#ins.id"/>.html"><img
									height="100px" onerror="this.src='/css/images/default.jpg'"
									src="<s:property escape="false"  value="#ins.iconUrl"/>"
									alt="<s:property escape="false"  value="#ins.title"/>" /> </a>
							</div>
							<div class="title">
								<a target="_blank" href="ins/<s:property value="#ins.id"/>.html"
									class="ins"><s:property escape="false" value="#ins.title" />
								</a>
							</div>
						</li>
					</s:iterator>
				</ul>
			</div>
		</div>
		<div class="short-div margin-left20">
			<div class="label">
				<div class="mainlabel">3C资讯</div>
				<div class="more">
					<a href="/news/list" target="_blank">更多>></a>
				</div>
			</div>
			<div class="content new">
				<ul>
					<s:iterator value="newses" status="st" id="news">
						<li>. <a target="_blank"
							href="news/<s:property value="#news.id"/>.html" class="ins"><s:property
									escape="false" value="#news.title" /> </a></li>
					</s:iterator>
				</ul>
			</div>
			<div class="label">
				<div class="mainlabel">最新上传</div>
			</div>
			<div class="content new">
				<ul>
					<s:iterator value="newInses" status="st" id="ins">
						<li>. <a target="_blank"
							href="ins/<s:property value="#ins.id"/>.html" class="ins"><s:property
									escape="false" value="#ins.title" /> </a></li>
					</s:iterator>
				</ul>
			</div>
		</div>
		<div class="short-div clear" id="thread">
			<div class="label">
				<div class="mainlabel">网友热议</div>
			</div>
			<div class="home-page-thread content">
				<ul>
					<s:iterator value="threads" status="st" id="thread">
						<li>. <a target="_blank"
							href="<s:property value="#thread.link"/>" class="ins"><s:property
									escape="false" value="#thread.title" /> </a></li>
					</s:iterator>
				</ul>
			</div>
		</div>
		<div class="long-div margin-left20" id="headshow">
			<div class="label">
				<div class="mainlabel">说明书达人</div>
			</div>
			<div class="content show">
				<s:iterator value="darens" status="st" id="daren">
					<li>
						<div class="icon">
							<a target="_blank"
								href="http://www.139life.com/home.php?mod=space&uid=<s:property value="#daren.uid"/>"><img
								width="100px" height="100px"
								src="http://www.139life.com/uc_server/avatar.php?uid=<s:property value="#daren.uid"/>&size=middle" />
							</a>
						</div>
						<div class="title">
							<a target="_blank"
								href="http://www.139life.com/home.php?mod=space&uid=<s:property value="#daren"/>"
								class="ins"><s:property value="#daren.username" /> </a>
						</div>
					</li>
				</s:iterator>
			</div>
		</div>
	</div>

</div>
<s:include value="common/foot.jsp" />
</body>
</html>
