<%@taglib uri="/struts-tags" prefix="s" %>
<!doctype html>
<html>
<head>
  <base href="/">
  <title>BackOffice Voyager Facile</title>
  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <link rel="stylesheet" href="assets/semantic.min.css" type="text/css"/>
  <link rel="stylesheet" href="assets/icon.min.css" type="text/css"/>
  <link rel="stylesheet" href="assets/login.css" type="text/css"/>
  <meta name="viewport" content="width=device-width">
</head>
<body>
<div  id="logo"> 
   <span id="logo_bleu">v<i class="icon world"></i>ya</span><span id="logo_jaune">ger </span><span id="logo_rouge">faci<i class="icon marker large"></i>e</span>
</div>
<div id="login2">
	<h1>Connexion</h1>
	<s:form action="login" method="post" class="ui form">
	    <div class="field">
	    	<input type="text" name="user_login.nom_user" placeholder="nom d'utilisateur" required>
	    </div>
	    <div class="field">
	    	<input type="password" name="user_login.mdp_user" placeholder="mot de passe" required>
	    </div>
	  	<button type="submit" class="ui fluid inverted big button">Se Connecter</button>
	  	<p>${loginError} </p>
	  	<br>
	  	<br>
	</s:form>
</div>

</body>
</html>


