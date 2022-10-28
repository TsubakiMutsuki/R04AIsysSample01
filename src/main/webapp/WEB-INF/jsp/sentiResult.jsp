<%@page import="java.util.Optional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	Optional<String> positive = 
		Optional.ofNullable((String) request.getAttribute("positive"));
	Optional<String> neutral = 
		Optional.ofNullable((String) request.getAttribute("neutral"));
	Optional<String> negative = 
			Optional.ofNullable((String) request.getAttribute("negative"));

%>

<body>
<H1>Sentiment</H1>
<H3>ポジティブ：<%= positive.orElse("ERROR") %></H3>
<H3>ニュートラル：<%= neutral.orElse("ERROR") %></H3>
<H3>ネガティブ：<%= negative.orElse("ERROR") %></H3>
</body>
</html>