<%@include file="header.jsp"%>
<div class="pusher">
<div class="ui container">
  <h1>Gestions des chambres</h1>
  <a class="ui circular teal labeled icon button ajoutModal"><i class="icon save"></i> Nouvelle Chambre</a>
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
            <th>Nom de la chambre</th>
            <th>Hotel</th>
            <th>nombre petit lit</th>
            <th>nombre grand lit</th>
            <th>prix en $</th>
            <th>Modifier/Supprimer</th>
          </tr>
      </thead>
      <tbody>
      	<s:iterator value="allChambre">
          <tr>
            <td><s:property value="id"></s:property></td>
            <td><s:property value="nom_chambre"></s:property></td>
            <td><s:property value="nom_hotel"></s:property></td>
            <td><s:property value="nbr_petit_lit"></s:property></td>
            <td><s:property value="nbr_grand_lit"></s:property></td>
            <td><s:property value="prix"></s:property>$</td>
            <td>
            	<s:url namespace="/" action="deleteChambre" var="delete">
                    <s:param name="id_chambre">
                        <s:property value="id"/>
                    </s:param>
                </s:url>
                <s:url namespace="/" action="modifierChambre" var="modifier">
                    <s:param name="id_chambre">
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
  <!------------------------------- ajouter chambre ---------------------------------- -->
  <div class="ui basic modal ajoutChambre">
	  <i class="close icon"></i>
	  <div class="ui huge message">
	       <div class="ui form">
	       <h1>Creer une Chambre</h1>
	         <s:form action="createChambre" method="post" id="modal" class="ui form">
			   	<div class="field">
			    	<label>Nom</label>
			    	<input type="text" name="chambre.nom_chambre" placeholder="nom de la chambre" required>
			   	</div>
			   	<div class="field">
			    	<label>Nombre de Petit Lit</label>
			    	<input type="number" name="chambre.nbr_petit_lit" placeholder="nombre de petit lit" min="0" required>
			   	</div>
			   	<div class="field">
			    	<label>Nombre de Grand Lit</label>
			    	<input type="number" name="chambre.nbr_grand_lit" placeholder="nombre de grand lit" min="0" required>
			   	</div>
			   	<div class="field">
			    	<label>Prix</label>
			    	<input type="number" name="chambre.prix" placeholder="prix" min="0" required>
			   	</div>
			   	<div class="field">
			    	<label>Hotel</label>
			    	<select  name="chambre.id_hotel" required>
			    	<s:iterator value="hotels">
			    		<option value="${id}">${nom_hotel}</option>
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
  <!------------------------------- modifier chambre ---------------------------------- -->
  <div class="ui basic modal modifChambre">
	  <i class="close icon"></i>
	  <div class="ui huge message">
	       <div class="ui form">
	       <h1>Modifier une Chambre</h1>
	         <s:form action="updateChambre" method="post" id="modal" class="ui form">
			   	<div class="field">
			    	<label>Nom</label>
			    	<input type="hidden" name="chambre_modif.id" value="${chambre_modif.id}" required>
			    	<input type="text" name="chambre_modif.nom_chambre" value="${chambre_modif.nom_chambre}" required>
			   	</div>
			   	<div class="field">
			    	<label>Nombre de Petit Lit</label>
			    	<input type="number" name="chambre_modif.nbr_petit_lit" min="0" value="${chambre_modif.nbr_petit_lit}" required>
			   	</div>
			   	<div class="field">
			    	<label>Nombre de Grand Lit</label>
			    	<input type="number" name="chambre_modif.nbr_grand_lit" min="0" value="${chambre_modif.nbr_grand_lit}" required>
			   	</div>
			   	<div class="field">
			    	<label>Prix</label>
			    	<input type="number" name="chambre_modif.prix" placeholder="prix" min="0" value="${chambre_modif.prix}" required>
			   	</div>
			   	<div class="field">
			    	<label>Hotel</label>
			    	<select  name="chambre_modif.id_hotel" required>
			    	<s:iterator value="hotels">
			    		<s:if test="%{id==chambre_modif.id_hotel}">
  							<option value="${id}" selected="selected">${nom_hotel}</option>
  						</s:if>
  						<s:else>
  							<option value="${id}">${nom_hotel}</option>
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
		$('.ajoutChambre').modal('show');					
	}); 
	/*------------- modifier tache --------------*/
	console.log($("#editMode").val());
	if($("#editMode").val()==="true"){
		$('.modifChambre').modal('show');
	}				
});
</script>
