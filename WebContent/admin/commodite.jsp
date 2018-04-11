<%@include file="header.jsp"%>
<div class="pusher">
<div class="ui container">
  <h1>Gestions des commodites</h1>
  <a class="ui circular teal labeled icon button ajoutModal"><i class="icon save"></i> Nouvelle commodite</a>
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
            <th>Nom de la commodite</th>
            <th>Modifier/Supprimer</th>
          </tr>
      </thead>
      <tbody>
      	<s:iterator value="allCommodite">
          <tr>
            <td><s:property value="id"></s:property></td>
            <td><s:property value="nom_commodite"></s:property></td>
            <td>
            	<s:url namespace="/" action="deleteCommodite" var="delete">
                    <s:param name="id_com">
                        <s:property value="id"/>
                    </s:param>
                </s:url>
                <s:url namespace="/" action="modifierCommodite" var="modifier">
                    <s:param name="id_com">
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
  <div class="ui basic modal ajoutCommodite">
	  <i class="close icon"></i>
	  <div class="ui huge message">
	       <div class="ui form">
	       <h1>Creer une Commodite</h1>
	         <s:form action="createCommodite" method="post" id="modal" class="ui form">
			   	<div class="field">
			    	<label>Nom de la commodite</label>
			    	<input type="text" name="commodite.nom_commodite" placeholder="nom de la Commodite" required>
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
  <div class="ui basic modal modifCommodite">
	  <i class="close icon"></i>
	  <div class="ui huge message">
	       <div class="ui form">
	       <h1>Modifier une commodite</h1>
	         <s:form action="updateCommodite" method="post" id="modal" class="ui form">
			   	<div class="field">
			    	<label>Nom de la destination</label>
			    	<input type=hidden name="commodite_modif.id" value="${commodite_modif.id}" required>
			    	<input type="text" name="commodite_modif.nom_commodite" value="${commodite_modif.nom_commodite}" required>
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
		$('.ajoutCommodite').modal('show');					
	}); 
	/*------------- modifier tache --------------*/
	console.log($("#editMode").val());
	if($("#editMode").val()==="true"){
		$('.modifCommodite').modal('show');
	}				
});
</script>
