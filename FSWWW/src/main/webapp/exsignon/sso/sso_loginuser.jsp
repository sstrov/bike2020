<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    session="false"

%><%

HttpSession session = request.getSession();
String eXSignOnUserId = (String) session.getAttribute("eXSignOn.session.userid");

out.print("연결체크 : " + eXSignOnUserId);

/*
SSO 로그인이 되어 사용자 인증정보가 존재할 때에 이 화면으로 넘어온다.
session.getAttribute("eXSignOn.session.userid") 에는 인증서버의 설정에 따라
사용자의 인증정보가 단일 String 혹은 JSONString 형태로 들어오게 되는데, 넘어온 정보를 핸들링하여 연계시스템에 로그인시키면 된다.
*/

// TO DO...

%>

<%=eXSignOnUserId%>