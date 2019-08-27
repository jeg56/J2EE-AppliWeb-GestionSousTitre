<%@ include file="header.jsp" %>
<body>
	<%@ include file="menu.jsp" %>
	<center><h1>Editeur de sous-titres</h1></center>
	<div class="page">
		<h4><u>Pr�-requis : </u></h4>
		<ul>
			<li> Cr�er la base "soustitre" </li>
		    <li> Cr�er les objets SQL dans la base � l'aide <i>src/resources/soustitre.sql</i>  </li>
		    <li> Param�trer si n�cessaire le fichier <i>src/resources/config.properties</i> ( user/ password de la base, espace utilis� par le serveur pour le d�pot des fihiers ...) </li> 	
		</ul>
		 
		<h4><u>Cette application poss�de 3 fonctionnalit�s accessible par le menu : </u></h4> 
		<ul>
			<li> Upload de nouveau fichier � traduire</li> 	
			<li> Enregistrement de la traduction en base de donn�es </li>
		    <li> Export de la traduction en .srt </li>
		</ul>
		
		<h4><u>Comme stipul� un effort particulier a �t� effectu� sur : </u></h4> 
		<ul>
			<li> Lisibilit� g�n�rale du code par l'indentation syst�matique </li>
		    <li> Utilisation du Pattern DAO pointant sur les tables "fichier" & "traduction"</li>
		    <li> Pr�sentation visuelle de l'application � l'aide de Boostrap</li> 	
		</ul>
		
		<h4><u>Pour infos : </u></h4> 
		<ul>
			<li> 2 fichiers .srt sont disponible sous <i>src/resources</i> : "petitFichier.srt" et "A.Star.Is.Born.2018.720p.WEBRip.x264-[YTS.AM].srt"</li>	
		</ul>

	</div>
	<%@ include file="footer.jsp" %>
	 
</body>
</html>