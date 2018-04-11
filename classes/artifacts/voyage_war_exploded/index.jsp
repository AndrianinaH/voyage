<%@include file="menu.jsp" %>
    <!----------------------- list des hotels ------------------------>
<s:if test="%{hotels.size()!=0}">
    <div class="ui ten wide column">
    	<s:if test="%{find!='' && find!='Commodite'}">
    		<br>
    		<h2>Recherche des hotels ou destinations avec le mot-cle [ <i>${find }</i> ]</h2>
    	</s:if>
    	<s:elseif test="%{find=='Commodite'}">
    		<br>
    		<h2>Recherche des hotels par le filtre Commodite</h2>
    	</s:elseif>
    	<s:iterator value="hotels">
	    	<div class="ui items list_hotel">
			  <div class="item">
			    <a class="ui medium image" href="detailHotel?id_hotel=${id}&page=${page}">
			      <img src="images/${image}" style="height:200px">
			    </a>
			    <div class="content">
			      <a class="header" id="nom_hotel" href="detailHotel?id_hotel=${id}&page=${page}">${nom_hotel}</a>
			      <div class="meta">
			        <div class="extra">
				        <i class="large blue icon flag"></i><span id="_bleu">${nom_destination}</span>
				    </div>
			        <div class="extra">
				        <i class="large orange icon filter"></i><span id="_jaune">${nom_commodite}</span>
				     </div>
			      </div>
			      <div class="description">
			        <p>${description} </p>
			      </div>
			      <div class="extra">
			        <a class="ui right large floated green button" href="detailHotel?id_hotel=${id}&page=${page}">
			          Voir l'offre
			          <i class="right chevron icon"></i>
			        </a>
			      </div>
			    </div>
			  </div>
	    	</div>
	    </s:iterator>
	<br>
	</div> 
	<s:if test="%{find==''}">
		<div class="ui pagination menu">
			<input type="hidden" value="${page}" id="page">
			<s:iterator var="i" begin="1" end="(nbPage/maxResult)" >
				<a href="index?page=${i}" class="item pagination">${i}</a>
		   	</s:iterator>
		</div>
	</s:if>
</s:if>
 <s:else>
  	<div class="ui ten wide column">
	   	<div class="ui negative message">
			<i class="close icon"></i>
			<div class="header">
			  Oups ! votre recherche n'a pas abouti !
			</div>
		</div>
		<a class="ui labeled icon button"  href="index?page=${page}"><i class="icon home"></i> revenir a l'accueil</a>
  	</div>
</s:else>
</div> 
<footer>
	<div id="titre_footer">
		<span>Voyager Facile<span>
	</div>
	<div id="copyright">
		<span>Copyright Mai 2017 -Tous droits reserves</span>
		<p>creer par : Rakotoarisoa Andrianina</p>
	</div>
</footer>
</body>
</html>
<script type="text/javascript" src="assets/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="assets/semantic.min.js"></script>
<script>
$(document).ready(function() {
  $('.message .close').on('click', function() {$(this).closest('.message').transition('fade');});
  $('select.dropdown').dropdown();
  $(".browse").popup({
    popup: '.ui.popup',
    on    : 'click'
  });
  $('.ui.checkbox').checkbox();
  //----------- pagination ------------//
  $(".pagination").each(function() { 
	if($(this).text()===$("#page").val())
	{
		$(this).attr("class","item pagination active");
	}
  });
});
</script>