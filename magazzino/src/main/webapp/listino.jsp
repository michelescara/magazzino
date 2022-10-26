<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script src="jquery-3.6.1.min.js"></script>
<script src="listinoScripts.js"></script>

<meta charset="ISO-8859-1">
<title>LISTINO</title>
</head>
<body>
	<h2>LISTINO</h2>
	<div>
		<table border="1">
			<thead>
				<tr>
					<th>PRODOTTO</th>
					<th><select name='categorie' id='categorie'></select></th>
					<th>PREZZO</th>
					<th>DATA FINE VALIDITA'</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
			<tfoot>
				<tr>
				    <th><select name='prodotto' id='prodotto'></select></th>
				    <th></th>
					<th><input type='text' id='prezzo' name='prezzo'></th>
					<th><input type='date' id='dataFineValid' name='dataFineValid'></th>
				</tr>
			</tfoot>
		</table>
		<input type="button" value="Aggiorna" id="aggiorna">
		<input type="button" value="Aggiungi" id="aggiungi">
		<input type="button" value="Elimina" id="elimina">
	</div>
</body>
</html>