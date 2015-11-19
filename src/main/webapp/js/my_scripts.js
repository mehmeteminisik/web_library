$(document).ready(function(){
	$("#table_block").hide();
	$("#findAll").click(function(){//Необходимо отправить серверу данные, введенные на форме, и как-то их сохранить
		$.fn.serializeObject = function () {
			var o = {};
			var a = this.serializeArray();
			$.each(a, function () {
				if (o[this.name] !== undefined) {
					if (!o[this.name].push) {
						o[this.name] = [o[this.name]];
					}
					o[this.name].push(this.value || '');
				} else {
					o[this.name] = this.value || '';
				}
			});
			return o;
		};

		var data=$("#allBookForm").serializeObject();//данные всех полей формы сериализуются в один объект, чтобы вызов Ajax мог передать их как единое целое.
		$.ajax({            //с помощью метода ajax можно отправить данные в формате JSON серверу
			url: "findAll",
			type: "POST", //тип запроса
			dataType: 'JSON',
			data: {bookData: JSON.stringify(data)},//send your JavaScript object as JSON string (var bookData=JSON.stringify(data))
			success: function (data) {
				console.log(data);
				$("#table_block").show();
				$("tr:has(td)").remove();                            // 1. remove all existing rows
				$.each(data, function (index, book) {     // 2. get each book(We can go over each element of the passed json)
					var td_genres = $("<td/>");                      // 2.2 Create table column for genres
					$.each(book.genres, function (i, item){          // 2.3 get each genre of this book
						var span = $("<span class='label label-info' style='margin:4px;padding:4px'/>");
						span.text(item.genre);
						td_genres.append(span);
					});

					// 2.6 Create a new row and append 4 columns
					$("#added-books").append($("<tr/>")
							.append($("<td/>").html(book.isbn))
							.append($("<td/>").html(book.title))
							.append($("<td/>").html(book.author))
							.append($("<td/>").html(book.year))
							.append(td_genres));
				});
			},
			error:function(data,status,er) {
				alert("error: "+data+" status: "+status+" er:"+er);
			}
		});
	});
});
