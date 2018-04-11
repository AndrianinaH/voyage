<%@include file="menu.jsp" %>
<input type="hidden" id="editMode" value="${editMode}">
    <!----------------------- list des hotels ------------------------>
    <div class="ui ten wide column" id="detailHotel">
    <s:if test="%{chambres.size()!=0}">
	    <h1>Les chambres de l'hotel : ${chambres.get(0).getNom_hotel()}</h1>
	    <a class="ui labeled icon button"  href="index?page=${page}"><i class="icon home"></i> revenir a l'accueil</a>
    	<table class="ui blue selectable table">
	      <thead>
	          <tr>
	            <th>Id</th>
	            <th>Nom de la chambre</th>
	            <th>nombre petit lit</th>
	            <th>nombre grand lit</th>
	            <th>prix en $</th>
	             <th>Reserver</th>
	          </tr>
	      </thead>
	      <tbody>
	      	<s:iterator value="chambres">
	          <tr>
	            <td><s:property value="id"></s:property></td>
	            <td><s:property value="nom_chambre"></s:property></td>
	            <td><s:property value="nbr_petit_lit"></s:property></td>
	            <td><s:property value="nbr_grand_lit"></s:property></td>
	            <td><s:property value="prix"></s:property>$</td>
	            <td>
	                <s:url namespace="/" action="reserverModal" var="reserver">
	                    <s:param name="id_chambre">
	                        <s:property value="id"/>
	                    </s:param>
	                    <s:param name="id_hotel">
	                        <s:property value="id_hotel"/>
	                    </s:param>
	                </s:url>
	                <s:a class="ui circular teal icon big button" href="%{reserver}"><i class="icon calendar"></i></s:a>
	            </td>
	          </tr>
	        </s:iterator>
	      </tbody>
	   </table>
	   <br>
	</s:if>
	<s:else>
		<div class="ui negative message">
			<i class="close icon"></i>
			<div class="header">
			  Oups ! il n'y a pas encore de chambre disponible dans cette hotel !
			</div>
		</div>
		<a class="ui labeled icon button"  href="index?page=${page}"><i class="icon home"></i> revenir a l'accueil</a>
		<br>
	</s:else>
	</div>  
</div> 
 <!------------------------------- Reserver Chambre ---------------------------------- -->
  <s:if test="%{chambres.size()!=0}">
  <div class="ui modal reserverChambre">
	  <i class="close icon"></i>
	  <div class="ui huge message">
	       <div class="ui form">
	       <h1>Reservation de la chambre ${chambre.nom_chambre}</h1>
	         <s:form action="reserver" method="post" id="modal" class="ui form">
			   	<div class="field">
			    	<label>Date Debut</label>
			    	<input type="hidden" name="reservation.id_chambre" value="${chambre.id}" required>
			    	<input type="hidden" name="reservation.id_client" value="${session.client_voyage.id} " required>
			    	<div class="ui left icon input">
					  <input type="date" name="reservation.date_debut" required>
					  <i class="icon calendar"></i>
					</div>
			    	
			   	</div>
			   	<div class="field">
			    	<label>Date Fin</label>
			    	<div class="ui left icon input">
					  <input type="date" name="reservation.date_fin" required>
					  <i class="icon calendar"></i>
					</div>
			    	
			   	</div>
			   	<div class="actions">
				   <div class="ui black deny button">
				      annuler
				   </div>
				   <button type="submit" class="ui inverted green icon button">Enregistrer</button>
			  	</div>
			  	<br>
			  	<br>
			 </s:form>
	       </div>
	  </div>
  </div>
  </s:if>
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
  //---------- modal reserver --------//
  console.log($("#editMode").val());
  if($("#editMode").val()==="true"){
	$('.reserverChambre').modal('show');
  }
});
</script>