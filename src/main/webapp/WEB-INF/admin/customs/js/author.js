    $('#author-datatable').DataTable({
    	ajax: '/admin/author/datatable',
        serverSide: true,
        columns: [
            {
                data: 'authorId', title: '#'
            },
            {
                data: 'name', title: 'Tên tác giả', "sClass":  "text-left"
            },
           
            {
            	data: 'description', title : 'Chi tiết'
            },
            {
            	title: 'Sửa/Xóa'
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
                	 return '<a style="margin-left: 5px; display: inline-block;" class="btn btn-sm btn-warning" href="/admin/author/edit/'+ data.authorId +'"><i class="fa fa-pencil-square-o"></i></a>'
                     +'<button id="detail-author" style="margin-left: 5px" class="btn btn-sm btn-info" value="'+ data.authorId + '" data-toggle="tooltip" data-original-title="detail"><i class="fa fa-align-justify"></i></button>'
                     +'<button id="delete-author" style="margin-left: 5px" class="btn btn-sm btn-danger" value="'+ data.authorId + '" data-toggle="tooltip" data-original-title="delete"><i class="fa fa-trash-o"></i></button>';
                    
                 }
             }
          ],
    });

	
	$('#profile-tab').on('click' , function(e) {
		$.ajax({
			type : "GET",
			url : "/admin/author/add",
			global : false,
			success : function(data) {
				console.log(data);
				$("form#form-author" ).replaceWith(data);
			},
			error : function(error) {
				console.log(error);
			}
		});
	});
    
  	function saveAuthor() {
  		var data = $('form#form-author').serialize();
			console.log(data);
			$.ajax({
				type : "POST",
				url : "/admin/author/add",
				data : data,
				global : false,
				success : function(data) {
					$('#author-datatable').DataTable().ajax.reload();
					console.log(data);
					toastr.success('Thêm thành công', data.messages, {
						closeButton : true
					});
					$("form#form-author" ).replaceWith(data);
				},
				
				error : function(error) {h
					console.log(error);
				}
			});
		}
  	
	
	$('.main-panel').on('click', '#delete-author', function() {
  		let authorId = $(this).val();
  		
  		console.log(authorId);
  		
  		$.ajax({
			type : "DELETE",
			url : "/api/author/" + authorId,
			global : false,
			contentType : "application/json; charset=utf-8",
			dataType : "json",
			success : function(data) {
				$('#author-datatable').DataTable().ajax.reload();
				console.log(data);
				toastr.success('Xóa thành công', data.messages, {
					closeButton : true
				});
				console.log(data.messages);
			},
			error : function(error) {
				console.log(error);
			}
		});
  	}
  	
  	);
