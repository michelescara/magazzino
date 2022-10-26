<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script src="jquery-3.6.1.min.js"></script>
<script src="prodottiScripts.js"></script>

<meta charset="ISO-8859-1">
<title>PRODOTTI</title>
</head>
<body>
	<h2>PRODOTTI</h2>
	<div>
		<table border="1" id="myTable">
			<thead>
				<tr>
					<th>NOME</th>
					<th>CATEGORIA</th>
					<th>DATA INIZIO PRODUZIONE</th>
					<th>DATA FINE PRODUZIONE</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
			<tfoot>
				<tr>
					<th><input type='text' id='nome' name='nome' required></th>
					<th><select name='categorie' id='categorie' required></select></th>
					<th><input type='date' id='dataInizioProd'
						name='dataInizioProd' required></th>
					<th><input type='date' id='dataFineProd' name='dataFineProd' required></th>
				</tr>
			</tfoot>
		</table>
		<input type="button" value="Aggiorna" id="aggiorna">
		<input type="button" value="Aggiungi" id="aggiungi">
		<input type="button" value="Elimina" id="elimina">
	</div>
</body>
</html>