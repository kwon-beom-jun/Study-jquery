<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="my" uri="http://kitri.com/my" %>    
<%@taglib prefix="tf" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>customtag.jsp</title>
</head>
<body>
<my:welcome/>
<hr>
<tf:now/>
<hr>
<tf:pageGroup current="3" start="1" end="5" url="boardlist"></tf:pageGroup>
</body>
</html>