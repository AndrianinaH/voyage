<%@taglib uri="/struts-tags" prefix="s" %>
<!doctype html>
<html>
<head>
  <base href="/">
  <title>Inscription Voyager Facile</title>
  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <link rel="stylesheet" href="assets/semantic.min.css" type="text/css"/>
  <link rel="stylesheet" href="assets/icon.min.css" type="text/css"/>
  <link rel="stylesheet" href="assets/login.css" type="text/css"/>
  <meta name="viewport" content="width=device-width">
</head>
<body>
<div  id="logo"> 
    <a href="index"><span id="logo_bleu">v<i class="icon world"></i>ya</span><span id="logo_jaune">ger </span><span id="logo_rouge">faci<i class="icon marker large"></i>e</span></a>
</div>
<div id="login2">
	<h1>Inscription Client</h1>
	<s:form action="inscription" method="post" class="ui form">
	    <div class="field">
	    	<input type="text" name="client_login.nom_client" placeholder="nom" required>
	    </div>
	    <div class="field">
	    	<input type="email" name="client_login.email" placeholder="email du client" required>
	   	</div>
	    <div class="field">
	    	<input type="password" name="client_login.mdp" placeholder="mot de passe" required>
	    </div>
	  	<button type="submit" class="ui fluid inverted big button">Se Connecter</button>
	  	<p>${loginError} </p>
	  	<a href="clientConnection">Vous avez déjà un compte, connectez vous ici</a>
	  	<br>
	  	<br>
	</s:form>
</div>

</body>
</html>


