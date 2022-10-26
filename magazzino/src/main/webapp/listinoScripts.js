//Al caricamento della pagina, riempie la tabella di dati presi tramite chiamata GET
$(document)
	.ready(function() {
		$.ajax({
			url: 'http://localhost:8080/magazzino/api/listino',
			type: "get",
			dataType: "json",
			success: function(data) {
				tableBody = $("table tbody")
				tableBody.empty();
				$.each(data, function(i) {
					var row = "<tr>"
					row += "<th>" + data[i].prodotto.nome + "</th>"
					row += "<th>" + data[i].prodotto.categoria.nomeCategoria + "</th>"
					row += "<th>" + data[i].prezzo + "</th>"
					row += "<th>" + data[i].dataFineValid + "</th>"
					row += "<th>" + "<input type='checkbox' id=" + data[i].prodotto.nome + " name=" + data[i].prodotto.nome + " value=" + data[i].idListino + ">" + "</th>"
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
				url: 'http://localhost:8080/magazzino/api/listino',
				type: "get",
				dataType: "json",
				success: function(data) {
					tableBody = $("table tbody")
					tableBody.empty();
					$.each(data, function(i) {
						var row = "<tr>"
						row += "<th>" + data[i].prodotto.nome + "</th>"
						row += "<th>" + data[i].prodotto.categoria.nomeCategoria + "</th>"
						row += "<th>" + data[i].prezzo + "</th>"
						row += "<th>" + data[i].dataFineValid + "</th>"
						row += "<th>" + "<input type='checkbox' id=" + data[i].prodotto.nome + " name=" + data[i].prodotto.nome + " value=" + data[i].idListino + ">" + "</th>"
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
				url: 'http://localhost:8080/magazzino/api/prodotti',
				type: "get",
				dataType: "json",
				success: function(data) {
					console.log(data);
					$
						.each(
							data,
							function(i) {
								var row = "<option value=" + data[i].id + ">"
									+ data[i].nome
									+ "</option>";
								$("#prodotto")
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
				"prodotto":
				{
					"id": $("#prodotto").val()

				},

				"dataFineValid": $("#dataFineValid").val(),
				"prezzo": $("#prezzo").val()
			};
			$.ajax({
				url: 'http://localhost:8080/magazzino/api/listino',
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
	$("#categorie").on(
		"change",
		function() {
			$.ajax({
				url: 'http://localhost:8080/magazzino/api/listino/validiByCat' + '?idCategoria=' + $("#categorie").val(),
				type: "get",
				dataType: "json",
				success: function(data) {
					tableBody = $("table tbody")
					tableBody.empty();
					$.each(data, function(i) {
						var row = "<tr>"
						row += "<th>" + data[i].prodotto.nome + "</th>"
						row += "<th>" + data[i].prodotto.categoria.nomeCategoria + "</th>"
						row += "<th>" + data[i].prezzo + "</th>"
						row += "<th>" + data[i].dataFineValid + "</th>"
						row += "</tr>", tableBody.append(row);
					});

					$.ajax({
						url: 'http://localhost:8080/magazzino/api/listino/prezzoMedioByCat' + '?idCategoria=' + $("#categorie").val(),
						type: "get",
						dataType: "json",
						success: function(data) {
							tableBody = $("table tbody")
							console.log(data);
							var row = "<tr>"
							row += "<th></th>"
							row += "<th></th>"
							row += "<th>AVRG: " + data + "</th>"
							row += "<th></th>"
							row += "</tr>",
								tableBody.append(row);

						},
						error: function(data) {
							tableBody.empty();
						}
					})

				},
				error: function(data) {
					tableBody.empty();
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
					url: 'http://localhost:8080/magazzino/api/listino/' + id,
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
