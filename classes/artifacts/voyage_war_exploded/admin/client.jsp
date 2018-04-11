<%@include file="header.jsp"%>
<div class="pusher">
<div class="ui container">
  <h1>Gestions des clients</h1>
  <a class="ui circular teal labeled icon button ajoutModal"><i class="icon save"></i> Nouveau client</a>
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
            <th>Nom du client</th>
            <th>Email</th>
            <th>Mot de passe</th>
            <th>Modifier/Supprimer</th>
          </tr>
      </thead>
      <tbody>
      	<s:iterator value="allClient">
          <tr>
            <td><s:property value="id"></s:property></td>
            <td><s:property value="nom_client"></s:property></td>
            <td><s:property value="email"></s:property></td>
            <td><s:property value="mdp"></s:property></td>
            <td>
            	<s:url namespace="/" action="deleteClient" var="delete">
                    <s:param name="id_client">
                        <s:property value="id"/>
                    </s:param>
                </s:url>
                <s:url namespace="/" action="modifierClient" var="modifier">
                    <s:param name="id_client">
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
  <!------------------------------- ajouter destination ---------------------------------- -->
  <div class="ui basic modal ajoutClient">
	  <i class="close icon"></i>
	  <div class="ui huge message">
	       <div class="ui form">
	       <h1>Creer une Client</h1>
	         <s:form action="createClient" method="post" id="modal" class="ui form">
			   	<div class="field">
			    	<label>Nom du client</label>
			    	<input type="text" name="client.nom_client" placeholder="nom du client" required>
			   	</div>
			   	<div class="field">
			    	<label>Email du client</label>
			    	<input type="email" name="client.email" placeholder="email du client" required>
			   	</div>
			   	<div class="field">
			    	<label>Mot de passe</label>
			    	<input type="text" name="client.mdp" placeholder="Mot de passe" required>
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
  <!------------------------------- modifier destination ---------------------------------- -->
  <div class="ui basic modal modifClient">
	  <i class="close icon"></i>
	 <div class="ui huge message">
	       <div class="ui form">
	       <h1>Modifier une Client</h1>
	         <s:form action="updateClient" method="post" id="modal" class="ui form">
			   	<div class="field">
			    	<label>Nom du client</label>
			    	<input type="hidden" name="client_modif.id" value="${client_modif.id}" required>
			    	<input type="text" name="client_modif.nom_client" value="${client_modif.nom_client}" required>
			   	</div>
			   	<div class="field">
			    	<label>Email du client</label>
			    	<input type="email" name="client_modif.email" value="${client_modif.email}" required>
			   	</div>
			   	<div class="field">
			    	<label>Mot de passe</label>
			    	<input type="text" name="client_modif.mdp" value="${client_modif.mdp}" required>
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
		$('.ajoutClient').modal('show');					
	}); 
	/*------------- modifier tache --------------*/
	console.log($("#editMode").val());
	if($("#editMode").val()==="true"){
		$('.modifClient').modal('show');
	}				
});
</script>
