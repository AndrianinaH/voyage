<%@taglib uri="/struts-tags" prefix="s" %>
<!doctype html>
<html>
<head>
  <base href="/">
  <title>Voyager Facile</title>
  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <link rel="stylesheet" href="assets/semantic.min.css" type="text/css"/>
  <link rel="stylesheet" href="assets/icon.min.css" type="text/css"/>
  <link rel="stylesheet" href="assets/style.css" type="text/css"/>
  <meta name="viewport" content="width=device-width">
</head>
<body>
<!----------------------- Menu Principal  ------------------------>
<div class="ui stackable menu">
   <a class="item" href="index">
     <span id="logo_bleu">v<i class="icon world"></i>ya</span><span id="logo_jaune">ger </span><span id="logo_rouge">faci<i class="icon marker large"></i>e</span>
   </a>
   <div class="ui stackable right menu">
     <s:if test="%{checkSessionClient()}">
	    <a class="active grey item" href="reservationClient">
	       <strong>Réservation</strong>
	       <div class="ui red left pointing label">${nbr_reservation}</div>
	    </a>
		<div class="ui browse active grey item">
	       <strong>${session.client_voyage.nom_client}</strong><i class="icon dropdown"></i>
	       <div class="ui menu popup">
	         <a class="active grey item" href="logoutClient">
	           <i class="icon sign out large"></i><strong>Déconnexion</strong>
	         </a>
	       </div>
	    </div>
	 </s:if>
	 <s:else>
		<div class="ui browse active grey item">
	       <strong>Connexion</strong><i class="icon dropdown"></i>
	       <div class="ui menu popup">
	         <a class="active grey item" href="inscriptionClient">
	           <i class="icon user large"></i><strong>Inscription</strong>
	         </a>
	         <a class="active grey item" href="clientConnection">
	           <i class="icon sign in large"></i><strong>Connexion</strong>
	         </a>
	       </div>
	     </div>
	 </s:else>
     
   </div>
</div>
<!----------------------- bar de recherche  ------------------------>
<div id="search_bar">
   <div class="ui form">
   <s:form action="find" method="post">
     <div class="inline fields">
       <div class="sixteen wide field">
         <div class="ui large action input">
           <input type="text" name="find" placeholder="Votre Destination ou Hotel">
           <button class="ui blue button">Rechercher</button>
         </div>
       </div>
     </div>
   </s:form>
   </div>
</div>
<div class="ui fluid container">
<div class="ui large stackable grid">
 <!----------------------- Filtre ------------------------>
   <div class="ui four wide stretched column" id="menu_filtre">
   <s:form action="findByCommodite" method="post">
     <div class="ui fluid vertical menu">
       <a class="item">
         <h3 class="ui grey header">Filtre</h3>
       </a>
       <a class="item">
         <h4 class="ui blue header">Commodité</h4>
         <div class="ui form">
	         	${allCommodite}
         </div>
       </a>
    </div>
    <button type="submit" class="ui fluid inverted large orange icon labeled button"><i class="icon filter"></i> Filtrer</button>
  </s:form>
  </div>
   