<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <nav>
    	<ul class="nav">
                <li class="active">
                    <a href="${pageContext.request.contextPath}/app/shouye">
                        <dl>
                            <dt class="blead blead_01"></dt>
                            <dd>首页</dd>
                        </dl>
                    </a>
                </li>
                <li>
                    <a title="附近" href="gotoapps://gotonear?uid=37">
                        <dl>
                            <dt class="blead blead_02"></dt>
                            <dd>附近</dd>
                        </dl>
                    </a>
                </li>
                <li>
                    <a title="朋友" href="${pageContext.request.contextPath}/app/pageFriend">
                        <dl>
                            <dt class="blead blead_03"></dt>
                            <dd>朋友</dd>
                        </dl>
                    </a>
                </li>					
                <li>
                    <a title="我的" href="${pageContext.request.contextPath}/app/geren">
                        <dl>
                            <dt class="blead blead_04"></dt>
                            <dd>我的</dd>
                        </dl>
                    </a>
                </li>
            </ul>
  </nav>
