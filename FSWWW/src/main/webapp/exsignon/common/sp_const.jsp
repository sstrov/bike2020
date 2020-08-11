<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    session="false"
    
%><%!

/*
IDP_URL : 인증서버 URL ex) https://idp.net
SP_ID : 인증서버에서 발급받은 SP ID ex) java-sp
SP_SECRET : 인증서버에서 발급받은 비밀키
TOKEN_VERIFY_FAIL_URL : 토큰 검증이 실패했을 때 이동할 URL
LOGINUSER_REDIRECT_URL : SSO 로그인 여부를 체크했을 때에 인증된 사용자가 이동할 URL
ANONYMOUS_REDIRECT_URL : SSO 로그인 여부를 체크했을 때에 인증되지 않은 사용자가 이동할 URL
*/

public final String IDP_URL = "https://sso.sejong.go.kr:442";
public final String SP_ID = "www2020";
public final String SP_SECRET = "XdzMY6AecGqgDaEXkl/QcA==";
public final String TOKEN_VERIFY_FAIL_URL = "/stat2/exsignon/sso/token_verify_fail.jsp";
public final String LOGINUSER_REDIRECT_URL = "/stat2/exsignon/sso/sso_loginuser.jsp";
public final String ANONYMOUS_REDIRECT_URL = "/stat2/exsignon/sso/sso_anonymous.jsp";

%>