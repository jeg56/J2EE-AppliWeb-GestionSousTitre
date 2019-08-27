<%@ include file="header.jsp" %>
<body>
	<%@ include file="menu.jsp" %>
	<center><h1>Editeur de sous-titres</h1></center>
	<div class="page">
		<h4><u>Pré-requis : </u></h4>
		<ul>
			<li> Créer la base "soustitre" </li>
		    <li> Créer les objets SQL dans la base à l'aide <i>src/resources/soustitre.sql</i>  </li>
		    <li> Paramétrer si nécessaire le fichier <i>src/resources/config.properties</i> ( user/ password de la base, espace utilisé par le serveur pour le dépot des fihiers ...) </li> 	
		</ul>
		 
		<h4><u>Cette application possède 3 fonctionnalités accessible par le menu : </u></h4> 
		<ul>
			<li> Upload de nouveau fichier à traduire</li> 	
			<li> Enregistrement de la traduction en base de données </li>
		    <li> Export de la traduction en .srt </li>
		</ul>
		
		<h4><u>Comme stipulé un effort particulier a été effectué sur : </u></h4> 
		<ul>
			<li> Lisibilité générale du code par l'indentation systématique </li>
		    <li> Utilisation du Pattern DAO pointant sur les tables "fichier" & "traduction"</li>
		    <li> Présentation visuelle de l'application à l'aide de Boostrap</li> 	
		</ul>
		
		<h4><u>Pour infos : </u></h4> 
		<ul>
			<li> 2 fichiers .srt sont disponible sous <i>src/resources</i> : "petitFichier.srt" et "A.Star.Is.Born.2018.720p.WEBRip.x264-[YTS.AM].srt"</li>	
		</ul>

	</div>
	<%@ include file="footer.jsp" %>
	 
</body>
</html>