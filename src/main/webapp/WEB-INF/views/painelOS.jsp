<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ include file="../cabecalho.jsp"%>
	<style>
#div1, #div2 {
	float: left;
	width: 100px;
	height: 35px;
	margin: 10px;
	padding: 10px;
	border: 1px solid black;
}
</style>
<body>
	<div class="container" ng-controller="PainelOSController"
		ng-init="init()">
		
		<h2>Drag and Drop</h2>
<p>Drag the image back and forth between the two div elements.</p>

<div id="div1" ondrop="drop(event)" ondragover="allowDrop(event)">
  <img src="img_w3slogo.gif" draggable="true" ondragstart="drag(event)" id="drag1" width="88" height="31">
</div>

<div id="div2" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
		
		
		
		
		</div>
		
		
		

	<%@ include file="../rodape.jsp"%>