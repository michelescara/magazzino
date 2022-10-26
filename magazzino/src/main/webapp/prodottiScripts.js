//Al caricamento della pagina, riempie la tabella di dati presi tramite chiamata GET
$(document)
	.ready(function() {
		$.ajax({
			url: 'http://localhost:8080/magazzino/api/prodotti',
			type: "get",
			dataType: "json",
			success: function(data) {
				tableBody = $("table tbody")
				tableBody.empty();
				$.each(data, function(i) {
					var row = "<tr>"
					row += "<th>" + data[i].nome + "</th>"
					row += "<th>" + data[i].categoria.nomeCategoria
						+ "</th>"
					row += "<th>" + data[i].dataInizioProd
						+ "</th>"
					row += "<th>" + data[i].dataFineProd + "</th>"
					row += "<th>" + "<input type='checkbox' id=" + data[i].nome + " name=" + data[i].nome + " value=" + data[i].id + ">" + "</th>"
					row += "</tr>", tableBody.append(row);
				})
			},
			error: function(data) {
				alert(data.responseText);
			}
		})
	});


//Al click del pulsante "aggiorna", ricarica la tabella riprendendo i dati dal DB tramite chiamata GET
$(function() {
	$("#aggiorna").on(
		"click",
		function() {
			$.ajax({
				url: 'http://localhost:8080/magazzino/api/prodotti',
				type: "get",
				dataType: "json",
				success: function(data) {
					tableBody = $("table tbody")
					tableBody.empty();
					$.each(data, function(i) {
						var row = "<tr>"
						row += "<th>" + data[i].nome + "</th>"
						row += "<th>" + data[i].categoria.nomeCategoria
							+ "</th>"
						row += "<th>" + data[i].dataInizioProd
							+ "</th>"
						row += "<th>" + data[i].dataFineProd + "</th>"
						row += "<th>" + "<input type='checkbox' id=" + data[i].nome + " name=" + data[i].nome + " value=" + data[i].id + ">" + "</th>"
						row += "</tr>", tableBody.append(row);
					})
				},
				error: function(data) {
					alert(data.responseText);
				}
			})
		});
});


//Al caricamento della pagina, aggiunge le opzioni al select della categoria, tramite chiamata GET
$(document)
	.ready(
		function() {
			$.ajax({
				url: 'http://localhost:8080/magazzino/api/categorie',
				type: "get",
				dataType: "json",
				success: function(data) {
					console.log(data);
					$
						.each(
							data,
							function(i) {
								var row = "<option value=" + data[i].idCategoria + ">"
									+ data[i].nomeCategoria
									+ "</option>";
								$("#categorie")
									.append(row);
							})
				},
				error: function(data) {
					alert(data.responseText);
				}
			})
		});


$(function() {
	$("#aggiungi").on(
		"click",
		function() {
			var markers = {
				"nome": $("#nome").val(),
				"categoria":
				{
					"idCategoria": $("#categorie").val()
				},
				"dataInizioProd": $("#dataInizioProd").val(),
				"dataFineProd": $("#dataFineProd").val()
			};
			$.ajax({
				url: 'http://localhost:8080/magazzino/api/prodotti',
				type: "post",
				contentType: "application/json; charset=utf-8",
				data: JSON.stringify(markers),
				dataType: "json",
				success: function(data) {
					console.log(data);
				},
				error: function(data) {

					alert(data.responseText);
				}
			})
		});
});


$(function() {
	$("#elimina").on(
		"click", function() {

			console.log("ok");
			document.querySelectorAll('input[type=checkbox]:checked').forEach(e => {
				e.parentNode.parentNode.remove();
				var id = e.value;

				$.ajax({
					url: 'http://localhost:8080/magazzino/api/prodotti/' + id,
					type: "delete",
					contentType: "application/json; charset=utf-8",
					dataType: "json",
					success: function(data) {
						console.log(data);
					},
					error: function(data) {

						alert(data.responseText);
					}
				})


			});
		});
});

