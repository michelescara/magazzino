//Al caricamento della pagina, riempie la tabella di dati presi tramite chiamata GET
$(document)
	.ready(function() {
		$.ajax({
			url: 'http://localhost:8080/magazzino/api/categorie/perPrezzoMedio',
			type: "get",
			dataType: "json",
			success: function(data) {

				tableBody = $("table tbody")
				tableBody.empty();
				$.each(data, function(i) {
					var row = "<tr>"
					row += "<th>" + data[i].nomeCategoria + "</th>"
					row += "<th>" + "<input type='checkbox' id=" + data[i].nomeCategoria + " name=" + data[i].nomeCategoria + " value=" + data[i].idCategoria + ">" + "</th>"
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
				url: 'http://localhost:8080/magazzino/api/categorie/perPrezzoMedio',
				type: "get",
				dataType: "json",
				success: function(data) {
					tableBody = $("table tbody")
					tableBody.empty();
					$.each(data, function(i) {
						var row = "<tr>"
						row += "<th>" + data[i].nomeCategoria + "</th>"
						row += "<th>" + "<input type='checkbox' id=" + data[i].nomeCategoria + " name=" + data[i].nomeCategoria + " value=" + data[i].idCategoria + ">" + "</th>"
						row += "</tr>", tableBody.append(row);
					})
				},
				error: function(data) {
					alert(data.responseText);
				}
			})
		});
});


$(function() {
	$("#aggiungi").on(
		"click",
		function() {
			var markers = {
				"nomeCategoria": $("#nome").val(),
			};
			$.ajax({
				url: 'http://localhost:8080/magazzino/api/categorie',
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
					url: 'http://localhost:8080/magazzino/api/categorie/' + id,
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