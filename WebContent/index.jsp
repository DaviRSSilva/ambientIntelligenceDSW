<%@page import="br.edu.ifsp.hto.AmbientInteligence"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="ai" uri="WEB-INF/ambient_inteligence.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%
	String lumnValue = request.getParameter(AmbientInteligence.ATTR_NAME);
	if (lumnValue != null) {
		session.setAttribute(AmbientInteligence.ATTR_NAME, lumnValue);
	}

	Object currLumensSessionAttribute = session.getAttribute(AmbientInteligence.ATTR_NAME);
	if (currLumensSessionAttribute == null) {
		session.setAttribute(AmbientInteligence.ATTR_NAME, 0);
	}

	String currLumens = session.getAttribute(AmbientInteligence.ATTR_NAME).toString();
%>
<link href="https://fonts.googleapis.com/css?family=Roboto"
	rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<ai:ambInt />

<style type="text/css">
body {
	font-family: 'Roboto', sans-serif;
}

h1 {
	text-align: center
}

h2 {
	text-align: center
}

form {
	magin: 0 auto;
    text-align: center;
}
</style>
<script type="text/javascript">
	function rangeKeyUp() {
		document.getElementById('form').submit();
	}
</script>

<title>Insert title here</title>
</head>
<body>
	<h1>Olá! Para alterar o valor de lumens da sessão utilize o slider
		abaixo.</h1>
	<h2>
		Valor atual de lumens:
		<%=currLumens%></h2>
	<div>
		<form id="form"
			oninput="result.value=parseInt(<%=AmbientInteligence.ATTR_NAME%>.value)">
			<input type="range" min="0" max="100" step="1"
				name="<%=AmbientInteligence.ATTR_NAME%>" value="<%=currLumens%>"
				onmouseup="rangeKeyUp()" /> <br />
			<p>
				O valor será alterado para:
				<output name="result" for="<%=AmbientInteligence.ATTR_NAME%>"
					value="1"><%=currLumens != null ? currLumens : 0%></output>
			</p>
		</form>
	</div>

</body>

</html>