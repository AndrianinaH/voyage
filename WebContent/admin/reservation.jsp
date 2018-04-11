<%@include file="header.jsp"%>
<div class="pusher">
<div class="ui container">
  <h1>Gestions des reservations</h1>
  <a class="ui circular teal labeled icon button ajoutModal"><i class="icon save"></i> Nouvelle reservation</a>
  <input type="hidden" id="editMode" value="${editMode}">
  <s:if test="%{error!=''}">
  	 <div class="ui negative message">
	  	<i class="close icon"></i>
		<div class="header">
		  ${error}
		</div>
	 </div>
  </s:if>
  
  <table class="ui blue selectable table">
      <thead>
          <tr>
            <th>Id</th>
            <th>Chambre</th>
            <th>Client</th>
            <th>Date de debut</th>
            <th>Date de fin</th>
            <th>Modifier/Supprimer</th>
          </tr>
      </thead>
      <tbody>
      	<s:iterator value="allReservation">
          <tr>
            <td><s:property value="id"></s:property></td>
            <td><s:property value="nom_chambre"></s:property></td>
            <td><s:property value="nom_client"></s:property></td>
            <td><s:property value="date_debut"></s:property></td>
            <td><s:property value="date_fin"></s:property></td>
            <td>
            	<s:url namespace="/" action="deleteReservation" var="delete">
                    <s:param name="id_res">
                        <s:property value="id"/>
                    </s:param>
                </s:url>
                <s:url namespace="/" action="modifierReservation" var="modifier">
                    <s:param name="id_res">
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
  <!------------------------------- ajouter reservation ---------------------------------- -->
  <div class="ui basic modal ajoutReservation">
	  <i class="close icon"></i>
	  <div class="ui huge message">
	       <div class="ui form">
	       <h1>Creer une Reservation</h1>
	         <s:form action="createReservation" method="post" id="modal" class="ui form">
			   	<div class="field">
			    	<label>Debut</label>
			    	<input type="date" name="reservation.date_debut" required>
			   	</div>
			   	<div class="field">
			    	<label>Fin</label>
			    	<input type="date" name="reservation.date_fin"  required>
			   	</div>
			   	<div class="field">
			    	<label>Chambre</label>
			    	<select  name="reservation.id_chambre" required>
			    	<s:iterator value="chambres">
			    		<option value="${id}">${nom_chambre}</option>
			    	</s:iterator>
			    	</select>
			   	</div>
			   	<div class="field">
			    	<label>Client</label>
			    	<select  name="reservation.id_client" required>
			    	<s:iterator value="clients">
			    		<option value="${id}">${nom_client}</option>
			    	</s:iterator>
			    	</select>
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
  <!------------------------------- modifier reservation ---------------------------------- -->
  <div class="ui basic modal modifReservation">
	  <i class="close icon"></i>
	  <div class="ui huge message">
	       <div class="ui form">
	       <h1>Modifier une Reservation</h1>
	         <s:form action="updateReservation" method="post" id="modal" class="ui form">
			   	<div class="field">
			    	<label>Debut</label>
			    	<input type="hidden" name="reservation_modif.id" value="${reservation_modif.id}" required>
			    	<input type="date" name="reservation_modif.date_debut" value="${reservation_modif.date_debut}" required>
			   	</div>
			   	<div class="field">
			    	<label>Fin</label>
			    	<input type="date" name="reservation_modif.date_fin" value="${reservation_modif.date_fin}" required>
			   	</div>
			   	<div class="field">
			    	<label>Chambre</label>
			    	<select  name="reservation_modif.id_chambre" required>
			    	<s:iterator value="chambres">
			    		<s:if test="%{id==reservation_modif.id_chambre}">
			    			<option value="${id}" selected="selected">${nom_chambre}</option>
			    		</s:if>
			    		<s:else>
			    			<option value="${id}">${nom_chambre}</option>
			    		</s:else>
			    	</s:iterator>
			    	</select>
			   	</div>
			   	<div class="field">
			    	<label>Client</label>
			    	<select  name="reservation_modif.id_client" required>
			    	<s:iterator value="clients">
			    		<s:if test="%{id==reservation_modif.id_client}">
			    			<option value="${id}" selected="selected">${nom_client}</option>
			    		</s:if>
			    		<s:else>
			    			<option value="${id}">${nom_client}</option>
			    		</s:else>
			    	</s:iterator>
			    	</select>
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
  <br>
  <br>
</div>
</div>
<%@include file="footer.jsp"%>
<script>
$(document).ready(function() {
	/*------------- ajouter tache --------------*/
	$(".ajoutModal").on('click', function(e) {
		$('.ajoutReservation').modal('show');					
	}); 
	/*------------- modifier tache --------------*/
	console.log($("#editMode").val());
	if($("#editMode").val()==="true"){
		$('.modifReservation').modal('show');
	}				
});
</script>
