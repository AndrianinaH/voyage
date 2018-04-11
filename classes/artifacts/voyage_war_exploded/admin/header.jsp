<%@taglib uri="/struts-tags" prefix="s" %>
<!doctype html>
<html>
<head>
  <base href="/">
  <title>BackOffice Voyager Facile</title>
  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <link rel="stylesheet" href="assets/semantic.min.css" type="text/css"/>
  <link rel="stylesheet" href="assets/icon.min.css" type="text/css"/>
  <link rel="stylesheet" href="assets/admin.css" type="text/css"/>
  <meta name="viewport" content="width=device-width">
</head>
<body>
<div class="ui fixed stackable menu">
   <a class="item hamburger">
      <section id="nav-icon">
	      <span></span>
	      <span></span>
	      <span></span>
      </section>
    </a>
    <a class="item">
       <span id="logo_bleu">v<i class="icon world"></i>ya</span><span id="logo_jaune">ger </span><span id="logo_rouge">faci<i class="icon marker large"></i>e</span>
    </a>
    <div class="ui right stackable menu">
      <a class="item" id="menu">
        <i class="icon user large"></i><strong>${session.admin_voyage.nom_user} </strong>
      </a>
      <a class="item" id="menu" href="logout">
        <i class="icon sign out large"></i><strong>Deconnexion</strong>
      </a>
    </div>
</div>
<div class="ui sidebar labeled icon vertical menu">
    <a class="item" id="menu" href="destination">
      <i class="icon plane"></i>
      <strong>Gestion des destinations</strong>
    </a>
    <a class="item" id="menu" href="hotel">
     <i class="icon building"></i>
      <strong>Gestion des hotels</strong>
    </a>
    <a class="item" id="menu" href="chambre">
      <i class="icon hotel"></i>
      <strong>Gestion des chambres</strong>
    </a>
    <a class="item" id="menu" href="commodite">
      <i class="icon filter"></i>
      <strong>Gestion des Commodités</strong>
    </a>
    <a class="item" id="menu" href="client">
      <i class="icon users"></i>
      <strong>Gestion des clients</strong>
    </a>
    <a class="item" id="menu" href="reservation">
      <i class="icon calendar"></i>
      <strong>Gestion des reservations</strong>
    </a>
</div>
