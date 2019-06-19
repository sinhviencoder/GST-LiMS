    $('#book-datatable').DataTable({
    	ajax: '/admin/book/datatable',
        serverSide: true,
        columns: [
            {
                data: 'bookId', title: '#'
            },
            {
                data: 'name', title: 'Name', "sClass":  "text-left"
            },
            {
                data: 'quantity', title: 'Quantity'
            },
            {
            	data: 'quantityActual', title: 'Quantity Actual'
            },
            {
            	data: 'description', title : 'Description'
            },
            {
            	data: 'category.name', title: 'Category'
            },
            {
            	data: 'author.name', title: 'Author'
            },
            {
                data: 'status', title: 'Status'
            },
            {
            	title: 'Action'
            }
        ],
	    "aoColumnDefs": [
			 { 	"mData": null,
				"sDefaultContent": '"none"',
				"aTargets": [ "_all" ]
			 },
        	 {"sClass":  "text-center", "aTargets" : ["_all"]},
        	 { "sTitle" : "Action",
        		 "orderable": false,
        		 "bSearchable": false,
                "aTargets": [-1],
                "mRender": function (data, type, full) {
                     return '<a id="edit-book" style="margin-left: 5px;  display: inline-block;" class="btn btn-sm btn-warning" href="/admin/book/edit/'+ data.bookId + '" data-toggle="tooltip" data-original-title="edit"><i class="fa fa-pencil-square-o"></i></a>'
                     +'<button id="detail-book" style="margin-left: 5px" class="btn btn-sm btn-info" value="'+ data.bookId + '" data-toggle="tooltip" data-original-title="detail"><i class="fa fa-align-justify"></i></button>'
                     +'<button id="delete-book" style="margin-left: 5px" class="btn btn-sm btn-danger" value="'+ data.bookId + '" data-toggle="tooltip" data-original-title="delete"><i class="fa fa-trash-o"></i></button>';
                 }
             }
          ],
    });

	
	$('#profile-tab').on('click', function(e) {
		$.ajax({
			type : "GET",
			url : "/admin/book/add",
			global : false,
			success : function(data) {
				console.log(data);
				$("form#form-book" ).replaceWith(data);
			},
			error : function(error) {
				console.log(error);
			}
		});
	});
    
  	function saveBook() {
  		var data = $('form#form-book').serialize();
			console.log(data);
			$.ajax({
				type : "POST",
				url : "/admin/book/add",
				data : data,
				global : false,
				success : function(data) {
					console.log(data);
					$("form#form-book" ).replaceWith(data);
				},
				error : function(error) {
					console.log(error);
				}
			});
		}
  	
  	$('.main-panel').on('click', '#delete-book', function() {
  		let bookId = $(this).val();
  		
  		console.log(bookId);
  		
  		$.ajax({
			type : "DELETE",
			url : "/api/book/" + bookId,
			global : false,
			contentType : "application/json; charset=utf-8",
			dataType : "json",
			success : function(data) {
				console.log(data);
				toastr.success('Xóa Book', data.messages, {
					closeButton : true
				});
				console.log(data.messages);
			},
			error : function(error) {
				console.log(error);
			}
		});
  	});
