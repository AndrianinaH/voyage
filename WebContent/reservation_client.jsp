<%@include file="menu.jsp" %>
<input type="hidden" id="editMode" value="${editMode}">
    <!----------------------- list des hotels ------------------------>
    <div class="ui ten wide column" id="detailHotel">
    <s:if test="%{reservations.size()!=0}">
	    <h1>Les reservations de : ${session.client_voyage.nom_client}</h1>
	    <a class="ui labeled icon button"  href="index"><i class="icon home"></i> revenir a l'accueil</a>
    	<table class="ui blue selectable table">
	      <thead>
			<tr>
	            <th>Chambre</th>
	            <th>Date de debut</th>
	            <th>Date de fin</th>
	            <th>Modifier/Annuler</th>
	        </tr>
	      </thead>
	      <tbody>
	      	<s:iterator value="reservations">
	          <tr>
	            <td><s:property value="nom_chambre"></s:property></td>
	            <td><s:property value="date_debut"></s:property></td>
	            <td><s:property value="date_fin"></s:property></td>
	            <td>
	            	<s:url namespace="/" action="annulerReservation" var="delete">
	                    <s:param name="id_reservation">
	                        <s:property value="id"/>
	                    </s:param>
	                </s:url>
	                <s:url namespace="/" action="modifierReservationClient" var="modifier">
	                    <s:param name="id_reservation">
	                        <s:property value="id"/>
	                    </s:param>
	                </s:url>
	                <s:a class="ui circular olive icon big button" href="%{modifier}"><i class="icon edit"></i></s:a>
		            <s:a class="ui circular red icon big button" href="%{delete}"><i class="icon trash"></i></s:a>
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
			  Vous n'avez pas encore reserver de chambre !
			</div>
		</div>
		<a class="ui labeled icon button"  href="index"><i class="icon home"></i> revenir a l'accueil</a>
		<br>
	</s:else>
	</div>  
</div> 
 <!------------------------------- Reservation Client ---------------------------------- -->
  <s:if test="%{reservations.size()!=0}">
  <div class="ui modal modifReserver">
	  <i class="close icon"></i>
	  <div class="ui huge message">
	       <div class="ui form">
	       <h1>Reservation de la chambre ${chambre.nom_chambre}</h1>
	         <s:form action="updateReservationClient" method="post" id="modal" class="ui form">
			   	<div class="field">
			    	<label>Date Debut</label>
			    	<input type="hidden" name="reservation_modif.id" value="${reservation_modif.id}" required>
			    	<input type="hidden" name="reservation_modif.id_chambre" value="${reservation_modif.id_chambre}" required>
			    	<input type="hidden" name="reservation_modif.id_client" value="${reservation_modif.id_client} " required>
			    	<div class="ui left icon input">
					  <input type="date" name="reservation_modif.date_debut" value="${reservation_modif.date_debut}" required>
					  <i class="icon calendar"></i>
					</div>
			   	</div>
			   	<div class="field">
			    	<label>Date Fin</label>
			    	<div class="ui left icon input">
					  <input type="date" name="reservation_modif.date_fin" value="${reservation_modif.date_fin}" required>
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
	$('.modifReserver').modal('show');
  }
});
</script>