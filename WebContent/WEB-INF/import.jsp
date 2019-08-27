<%@ include file="header.jsp" %>
<body>
	<%@ include file="menu.jsp" %>
	<center><h1>Espace permettant d'importer les fichiers .srt </h1></center>
	<div class="page">
		 <form method="post" action="import" enctype="multipart/form-data">
			<div class="form-group">
				<label>Sélectionner votre fichier : </label>
				<input onchange="afficheButton()" type="file" name="fichier" accept=".srt">
				<button id="submitButton" style="display:none" onclick="afficheLoader()" type="submit" class="btn btn-primary active">Envoyer votre fichier</button>
			</div>
		</form>
	
		<center>
			<div id="idLoader" style="display:none" class="loader"></div>
		</center>
		<br><br><br>
		
		<c:if test="${ not empty etatImportation }" var="variable">
		  <div class="modal fade" id="etatEnreg" role="dialog">
		    <div class="modal-dialog">
		      <div class="modal-content">
		        <div class="modal-header">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		        </div>
		        <div class="modal-body">
		            <c:if test="${etatImportation == 'ECHEC'}" var="variable">
			            <div class="alert alert-danger">
			    			<strong>Erreur!</strong> Le fichier n'a pas été enregistrés ...
			 			</div>
		 			</c:if>
		 			 <c:if test="${etatImportation eq 'SUCCES'}" var="variable">
		 			  <div class="alert alert-success">
		    				<strong>Succès!</strong> Le fichier a été enregistrés !!!
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