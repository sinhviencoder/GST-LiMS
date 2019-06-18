    $('#category-datatable').DataTable({
    	ajax: '/admin/category/datatable',
        serverSide: true,
        columns: [
            {
                data: 'categoryId', title: '#'
            },
            {
                data: 'name', title: 'Name'
            },
            {
                data: 'sortorder', title: 'SortOrder'
            },
            {
            	data: 'categoryParent.name', title: 'Parent'
            },
            {
            	data: 'status', title : 'Status'
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
                     return '<a style="margin-left: 5px; display: inline-block;" class="btn btn-sm btn-warning" href="/admin/category/edit/'+ data.categoryId +'"><i class="fa fa-pencil-square-o"></i></a>'
                     +'<button id="detail_post" style="margin-left: 5px" class="btn btn-sm btn-info" value="'+ data.categoryId + '" data-toggle="tooltip" data-original-title="detail"><i class="fa fa-align-justify"></i></button>'
                     +'<button id="delete_post" style="margin-left: 5px" class="btn btn-sm btn-danger" value="'+ data.categoryId + '" data-toggle="tooltip" data-original-title="delete"><i class="fa fa-trash-o"></i></button>';
                 }
             }
          ],
    });
	
	
	$('#category-get-form').on('click', function(e) {
		$("form#form-category").load("/admin/category/add-ajax", function(data) {
			//console.log(data);
			$('form#form-category').submit(false);
		});
	});
	
	$('form#form-category ').on('click', '#category-submit-ajax', function(e) {
		saveCategory();
	});
    // for edit
	
	$('form#form-category-edit ').on('click', '#ccategory-edit-submit', function(e) {
		updateCategory();
	});
  	function saveCategory() {
			$.ajax({
				type : "POST",
				url : "/admin/category/add-ajax",
				data : $('form#form-category').serialize(),
				global : false,
				success : function(data) {
					$('#category-datatable').DataTable().ajax.reload();
					console.log(data);
					$('form#form-category').html($(data));
				},
				error : function(error) {
					console.log(error);
				}
			});
  	}
  	function updateCategory() {
		$.ajax({
			type : "POST",
			url : "/admin/category/edit/" + categoryId,
			data : $('form#form-category-edit').serialize(),
			global : false,
			success : function(data) {
				console.log(data);
				$('form#form-category-edit').html($(data));
			},
			error : function(error) {
				console.log(error);
			}
		});
	}

  	$('.main-panel').on('click', '#edit_post', function() {
  		let categoryId = $(this).val();
  		
  		console.log(categoryId);
  		
  		$.ajax({
			type : "GET",
			url : "/admin/category/edit/" + categoryId
			
		});
  	});
  	$('.main-panel').on('click', '#delete_post', function() {
  		let categoryId = $(this).val() ;
  		
  		console.log(categoryId);
  		
  		$.ajax({
			type : "DELETE",
			url : '/api/category/' + categoryId ,
			global : false,
			contentType : "application/json; charset=utf-8",
			dataType : "json",
			success : function(data) {
				console.log(data);
				$('#category-datatable').DataTable().ajax.reload();
				toastr.success('Xóa Category', data.messages, {
					closeButton : true
				});
				console.log(data.messages);
			},
			error : function(error) {
				console.log(error);
			}
		});
  	});

  	