<%@include file="header.jsp"%>
<div class="pusher">
<div class="ui container">
  <h1>Gestions des hotels</h1>
  <a class="ui circular teal labeled icon button ajoutModal"><i class="icon save"></i> Nouvelle hotel</a>
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
            <th>Nom de l'hotel</th>
            <th>Destination</th>
            <th>Commodite</th>
            <th>Description</th>
            <th>Image</th>
            <th>Modifier/Supprimer</th>
          </tr>
      </thead>
      <tbody>
      	<s:iterator value="allHotel">
          <tr>
            <td><s:property value="id"></s:property></td>
            <td><s:property value="nom_hotel"></s:property></td>
            <td><s:property value="nom_destination"></s:property></td>
            <td><s:property value="nom_commodite"></s:property></td>
            <td><s:property value="description"></s:property></td>
            <td><img class="ui small rounded image" src="images/${image}" style="height:90px"></td>
            <td>
            	<s:url namespace="/" action="deleteHotel" var="delete">
                    <s:param name="id_hotel">
                        <s:property value="id"/>
                    </s:param>
                </s:url>
                <s:url namespace="/" action="modifierHotel" var="modifier">
                    <s:param name="id_hotel">
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
  <!------------------------------- ajouter hotel ---------------------------------- -->
  <div class="ui basic modal ajoutHotel">
	  <i class="close icon"></i>
	  <div class="ui huge message">
	       <div class="ui form">
	       <h1>Creer un hotel</h1>
	         <s:form action="createHotel" method="post" enctype="multipart/form-data" id="modal" class="ui form">
			   	<div class="field">
			    	<label>Nom</label>
			    	<input type="text" name="hotel.nom_hotel" placeholder="nom de l'hotel" required>
			   	</div>
			   	<div class="field">
			    	<label>Description</label>
			    	<textarea rows="2" name="hotel.description" placeholder="description de l'hotel" onkeyup="auto_grow(this)" required></textarea>
			   	</div>
			   	<div class="field">
			    	<label>Destination</label>
			    	<select  name="hotel.id_destination" required>
			    	<s:iterator value="destinations">
			    		<option value="${id}">${nom_destination}</option>
			    	</s:iterator>
			    	</select>
			   	</div>
			   	<div class="field">
			    	<label>Commodite</label>
			    	<select  name="hotel.id_commodite" required>
			    	<s:iterator value="commodites">
			    		<option value="${id}">${nom_commodite}</option>
			    	</s:iterator>
			    	</select>
			   	</div>
			   	<div class="field">
			   		<label>Image</label>
					<div class="input-file-container">
					  <input class="input-file" id="my-file" type="file" name="imageHotel" style="display:none" required>
					  <label for="my-file" class="input-file-trigger ui labeled icon button"><i class="image icon"></i>choisir une image ...</label>
					</div>
					<p class="file-return"></p>
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
  <!------------------------------- modifier hotel ---------------------------------- -->
  <div class="ui basic modal modifHotel">
	  <i class="close icon"></i>
	  <div class="ui huge message">
	       <div class="ui form">
	       <h1>Modifier un hotel</h1>
	         <s:form action="updateHotel" method="post" enctype="multipart/form-data" id="modal" class="ui form">
			   	<div class="field">
			    	<label>Nom</label>
			    	<input type="hidden" name="hotel_modif.id" value="${hotel_modif.id}" required>
			    	<input type="text" name="hotel_modif.nom_hotel" value="${hotel_modif.nom_hotel}"  required>
			   	</div>
			   	<div class="field">
			    	<label>Description</label>
			    	<textarea rows="2" name="hotel_modif.description" onkeyup="auto_grow(this)" required>${hotel_modif.description}</textarea>
			   	</div>
			   	<div class="field">
			    	<label>Destination</label>
			    	<select  name="hotel_modif.id_destination" required>
			    	<s:iterator value="destinations">
  						<s:if test="%{id==hotel_modif.id_destination}">
  							<option value="${id}" selected="selected">${nom_destination}</option>
  						</s:if>
  						<s:else>
  							<option value="${id}">${nom_destination}</option>
  						</s:else>
			    	</s:iterator>
			    	</select>
			   	</div>
			   	<div class="field">
			    	<label>Commodite</label>
			    	<select  name="hotel_modif.id_commodite" required>
			    	<s:iterator value="commodites">
			    		<s:if test="%{id==hotel_modif.id_commodite}">
			    			<option value="${id}" selected="selected">${nom_commodite}</option>
			    		</s:if>
			    		<s:else>
			    			<option value="${id}">${nom_commodite}</option>
			    		</s:else>
			    	</s:iterator>
			    	</select>
			   	</div>
			   	<div class="field">
			   		<label>Image</label>
			   		<input type="hidden" name="hotel_modif.image" value="${hotel_modif.image}" required>
					<div class="input-file-container2">
					  <input class="input-file2" id="my-file2" type="file" name="imageHotel" style="display:none">
					  <label for="my-file2" class="input-file-trigger2 ui labeled icon button"><i class="image icon"></i>choisir une image ...</label>
					</div>
					<p class="file-return2">${hotel_modif.image}</p>
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
		$('.ajoutHotel').modal('show');					
	}); 
	/*------------- modifier tache --------------*/
	console.log($("#editMode").val());
	if($("#editMode").val()==="true"){
		$('.modifHotel').modal('show');
	}				
});
</script>
<script>
//--------------------------------- personalisation de l'input file1 ------------------------------//
// ajout de la classe JS a HTML
document.querySelector("html").classList.add('js');
// initialisation des variables
var fileInput  = document.querySelector( ".input-file" ),  
    button     = document.querySelector( ".input-file-trigger" ),
    the_return = document.querySelector(".file-return");
// action lorsque le label est clique
button.addEventListener( "click", function( event ) {
   fileInput.focus();
   return false;
});
// affiche un retour visuel des que input:file change
fileInput.addEventListener( "change", function( event ) {  
    the_return.innerHTML = this.value;  
});

//--------------------------------- personalisation de l'input file2 ------------------------------//
//ajout de la classe JS a HTML
document.querySelector("html").classList.add('js');
//initialisation des variables
var fileInput2  = document.querySelector( ".input-file2" ),  
 button2     = document.querySelector( ".input-file-trigger2" ),
 the_return2 = document.querySelector(".file-return2");
//action lorsque le label est clique
button2.addEventListener( "click", function( event ) {
fileInput2.focus();
return false;
});
//affiche un retour visuel des que input:file change
fileInput2.addEventListener( "change", function( event ) {  
 the_return2.innerHTML = this.value;  
});
</script>