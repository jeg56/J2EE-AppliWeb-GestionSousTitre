<%@ include file="header.jsp" %>
<body>
<%@ include file="menu.jsp" %>
	<center><h1>Espace permettant de traduire les messages </h1></center>
	<div class="page">
		<label for="title">Liste des fichiers en base de données :</label> <br>
		 
		 <form method="post" action="liste">
			<select id="idFichier" name="idFichier" onChange="submit()">
					<option value="" selected>Sélectionner un fichier :</option>
						<c:forEach  var="ligne"  items="${ listeFichiers }" varStatus="status">
							<option value="<c:out value="${ ligne.getId() }" />" >   <c:out value="${ ligne.getNom_fichier() }" /> </option>
						</c:forEach>
			</select>
			
		</form>
		
		<br><br><br>
		 <form method="post" action="enregistrer">
		  <c:if test="${ not empty listeSequence }" var="variable">
		  
		  <c:forEach  var="ligne"  items="${ listeFichiers }" varStatus="status"> 
		  	  <c:if test="${ ligne.getId()== idFichier }" var="variable">
		  Table : <c:out value="${ idFichier }" /> - <c:out value="${ ligne.getNom_fichier() }" />
		 	<input type="hidden" name="idFichier" value = "${ ligne.getId() }"> </>
		  	</c:if>
		  </c:forEach>
		
		  <br>
		  <button onclick="afficheLoaderModal()" type="submit" class="btn btn-primary active boutonFixed">Enregistrer</button>
		  
		   <!-- Modal -->
		  <div class="modal fade" id="popLoader" role="dialog">
		    <div class="modal-dialog">
		      <!-- Modal content-->
		      <div class="modal-content">
		        <div class="modal-body">
						<center>
							<div class="loader"></div>
						</center>
		        </div>
		      </div>
		    </div>
		  </div>
		  
		 <div class="table-responsive text-nowrap tablePosition"> 
		   <table class="table table-striped table-bordered" style="width:75%">
		    <thead>
		      <tr>
		        <th style="width: 10%">Numéro de séquence</th>
		        <th style="width: 20%">Temps début</th>
		        <th style="width: 20%">Temps fin</th>
		        <th style="width: 25%">Message original</th>
		        <th style="width: 25%">Message traduit</th>
		      </tr>
		    </thead>
		    <tbody>
		    	<c:forEach  var="ligne"  items="${ listeSequence }" varStatus="status">
			    	<tr>
						<td> <c:out value="${ ligne.getNum_sequence() }" /> </td>
						<td> <c:out value="${ ligne.getTime_deb() }" /> </td>
						<td> <c:out value="${ ligne.getTime_fin() }" /> </td>
						<td> <c:out value="${ ligne.getMessage_source() }" /> </td>
						<td> <input name = "messageCible_${ligne.getNum_sequence()}" class = "inputTextTransparent" type="text" value=${ ligne.getMessage_cible() }>  </td>
					</tr>
				</c:forEach> 
		    </tbody>
		  </table>
		 </div>
		</c:if>
		</form>
		
		  <c:if test="${ not empty etatEnregistrement }" var="variable">
		  
		  <!-- Modal -->
		  <div class="modal fade" id="etatEnreg" role="dialog">
		    <div class="modal-dialog">
		    
		      <!-- Modal content-->
		      <div class="modal-content">
		        <div class="modal-header">
		        
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          
		        </div>
		        <div class="modal-body">
		            <c:if test="${etatEnregistrement == 'ECHEC'}" var="variable">
			            <div class="alert alert-danger">
			    			<strong>Erreur!</strong> Vos données n'ont pas été enregistrées ...
			 			</div>
		 			</c:if>
		 			 <c:if test="${etatEnregistrement eq 'SUCCES'}" var="variable">
		 			  <div class="alert alert-success">
		    				<strong>Succès!</strong> Vos données ont été enregistrées !!!
		  			 </div>
		          	</c:if>
		        </div>
		        <div class="modal-footer">
		          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        </div>
		      </div>
		      
		    </div>
		  </div>
		  <script type="text/javascript">$('#etatEnreg').modal('show');</script>
		
		  </c:if>
	</div>

<%@ include file="footer.jsp" %>
 
</body>
</html>