<%@ include file="header.jsp" %>
<body>
	<%@ include file="menu.jsp" %>
	<center><h1>Espace permettant d'exporter les messages traduits</h1></center>
	<div class="page">
		<label for="title">Liste des fichiers en base de donn�es : </label> <br>
		 
		<form method="post" action="export">
			<select id="idFichier" name="idFichier" >
				<option value="" selected>S�lectionner un fichier :</option>
					<c:forEach  var="ligne"  items="${ listeFichiers }" varStatus="status">
						<option value="<c:out value="${ ligne.getId() }" />" >   <c:out value="${ ligne.getNom_fichier() }" /> </option>
				</c:forEach>
			</select>
			<button onclick="afficheLoader()" type="submit" class="btn btn-primary active">G�n�rer le fichier</button>
		</form>
		
		<center>
			<div id="idLoader" style="display:none" class="loader"></div>
		</center>
		
		<br>
		<c:if test="${ not empty sessionScope.dataSequence }" var="variable">
			<form method="get" action="exportCSV">
			<center>
				<button type="submit" class="btn btn-primary active">T�l�charger le fichier</button>
			</center>	
			</form>	
		</c:if>	
	</div>
	<%@ include file="footer.jsp" %>
 
</body>
</html>