<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script src="jquery-3.6.1.min.js"></script>
<script src="categorieScripts.js"></script>

<meta charset="ISO-8859-1">
<title>CATEGORIE</title>
</head>
<body>
	<h2>CATEGORIE</h2>
	<div>
	<table border="1">
		<thead>
			<tr>
				<th>NOME</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
		<tfoot>
			<tr>
				<th><input type='text' id='nome' name='nome'></th>
			</tr>
		</tfoot>
	</table>
                <input type="button" value="Aggiorna" id="aggiorna">
				<input type="button" value="Aggiungi" id="aggiungi">
				<input type="button" value="Elimina" id="elimina">
	</div>
</body>
</html>