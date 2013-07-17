<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="../common/head.jsp">
 <s:param name="title">3C资讯-</s:param>
</s:include>
<script src="/js/jquery.effects.core.js"></script>
<script src="/js/jquery.effects.blind.js"></script>
   <div class="main">
     <div class="track"><a href="/index">首页</a> >3C资讯 </div>
     <div class="t-page">
       <div class="left">
          <div class="cat-block">
            <div class="label label1">全部分类</div>
            <div class="content"><div id="cid" class="hide"><s:property value="cid"/></div>
                <s:iterator value="cates" id="cat" status="st"><s:if test="#cat.level==0">
                   <s:if test="#st.index>0"></ul></div></div></s:if>
                   <div class="cat">
                     <div class="level1">
                         <div class="name"><a id="cat<s:property value="#cat.id"/>" <s:if test="#cat.id==cid">class="sel-cat"</s:if> href="/list/<s:property value="#cat.id"/>.html"><s:property value="#cat.name"/></a></div>
                         <div class="levelswitch open"></div>
                     </div>
                     <div class="level2 hide"><ul>
                 </s:if>
                 <s:else>
                   <li><a id="cat<s:property value="#cat.id"/>" <s:if test="#cat.id==cid">class="sel-cat"</s:if> href="/list/<s:property value="#cat.id"/>.html"><s:property value="#cat.name"/></a></li>
                 </s:else></s:iterator></ul></div>
                   </div>
            </div>
          </div>
          <div class="left-block" id="thread">
            <div class="label label3">网友热议</div>
            <div class="content">
               <ul>
                <s:iterator value="threads" status="st" id="thread"><li>. <a target="_blank" href="<s:property value="#thread.link"/>" class="ins"><s:property escape="false"  value="#thread.title"/></a></li></s:iterator>
               </ul>
            </div>
          </div>
       </div>
       <div class="right">
          
           <div class="right-block">
              <div class="label label2">资讯列表</div>
              <div class="content">
                <s:if test="newses.totalpage>1">
                <div class="page">
                   <s:bean name="org.apache.struts2.util.Counter" id="counter">
                       <s:param name="first" value="newses.pageindex.startindex" />
                       <s:param name="last" value="newses.pageindex.endindex" />
                       <s:if test="newses.pageindex.startindex>1"><a href="/news/list/<s:property value="search"/>-1-<s:property value="pageSize"/>.html" class="page_link">1</a><s:if test="newses.pageindex.startindex>2"><span class="omission">...</span></s:if></s:if>
                       <s:iterator>
                          <s:if test="newses.currentpage!=current-1"><a href="/news/list/<s:property value="search"/>-<s:property value="current-1"/>-<s:property value="pageSize"/>.html" class="page_link"><s:property value="current-1"/></a>
                          </s:if>
                          <s:else>
                          <span class="current_page"><s:property value="current-1"/></span>
                          </s:else>
                       </s:iterator>
                       <s:if test="newses.pageindex.endindex<newses.totalpage"><s:if test="newses.totalpage-newses.pageindex.endindex>1"><span class="omission">...</span></s:if><a href="/news/list/<s:property value="search"/>-<s:property value="newses.totalpage"/>-<s:property value="pageSize"/>.html" class="page_link"><s:property value="newses.totalpage"/></a></s:if>
                   </s:bean>
                </div>
                </s:if>
                <div class="list">
                  <ul>
                   <s:if test="newses.records.size==0">
                       暂无资讯
                   </s:if>
                   <s:iterator value="newses.records" status="st" id="news">
                    <li class="item news-item">
                        <div class="link-time">
                          <div class="link">
                          <a target="_blank" href="/news/<s:property escape="false"  value="#news.id"/>.html"><s:property escape="false"  value="#news.title"/></a>
                          </div>
                          <div class="time"><s:property escape="false"  value="#news.updateTimeFormat"/></div>
                        </div>
                        <div class="description">
                           <s:property escape="false"  value="#news.descriptionHtml"/>
                        </div>
                    </li>
                  </s:iterator>
                  </ul>
                </div>
                
                <s:if test="newses.totalpage>1">
                <div class="page">
                   <s:bean name="org.apache.struts2.util.Counter" id="counter">
                       <s:param name="first" value="newses.pageindex.startindex" />
                       <s:param name="last" value="newses.pageindex.endindex" />
                       <s:if test="newses.pageindex.startindex>1"><a href="/news/list/<s:property value="search"/>-1-<s:property value="pageSize"/>.html" class="page_link">1</a><s:if test="newses.pageindex.startindex>2"><span class="omission">...</span></s:if></s:if>
                       <s:iterator>
                          <s:if test="newses.currentpage!=current-1"><a href="/news/list/<s:property value="search"/>-<s:property value="current-1"/>-<s:property value="pageSize"/>.html" class="page_link"><s:property value="current-1"/></a>
                          </s:if>
                          <s:else>
                          <span class="current_page"><s:property value="current-1"/></span>
                          </s:else>
                       </s:iterator>
                       <s:if test="newses.pageindex.endindex<newses.totalpage"><s:if test="newses.totalpage-newses.pageindex.endindex>1"><span class="omission">...</span></s:if><a href="/news/list/<s:property value="search"/>-<s:property value="newses.totalpage"/>-<s:property value="pageSize"/>.html" class="page_link"><s:property value="newses.totalpage"/></a></s:if>
                   </s:bean>
                </div>
                </s:if>
                <div style="padding:3px; clear:both;">&nbsp;</div>
              </div>
              
           </div>
           
       </div>
     </div><!--t-page end-->
   </div>
   <s:include value="/common/foot.jsp"/>
  </body>
</html>
