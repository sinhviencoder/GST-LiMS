    $('#user-datatable').DataTable({
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
                     return '<button id="edit_post" style="margin-left: 5px" class="btn btn-sm btn-warning" value="'+ data.userId + '" data-toggle="tooltip" data-original-title="edit"><i class="fa fa-pencil-square-o"></i></button>'
                     +'<button id="detail_post" style="margin-left: 5px" class="btn btn-sm btn-info" value="'+ data.userId + '" data-toggle="tooltip" data-original-title="detail"><i class="fa fa-align-justify"></i></button>'
                     +'<button id="delete_post" style="margin-left: 5px" class="btn btn-sm btn-danger" value=" '+ data.userId + '" data-toggle="tooltip" data-original-title="delete"><i class="fa fa-trash-o"></i></button>';
                 }
             }
          ],
    });

	$('#delete_post').on('click',function(){
		let categoryID = $(this).attr('data');
		console.log(categoryID);
	
		$.ajax({
			type : "DELETE",
			url : "/admin/category/delete/" +categoryID,
			//data : $(id :'#categoryID'),
			//global : false,
			 success: function (result) {       
		           console.log(result);                
		    },
		    error: function (e) {
		        console.log(e);
		    }
	});
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
    
  	function saveCategory() {
			$.ajax({
				type : "POST",
				url : "/admin/category/add-ajax",
				data : $('form#form-category').serialize(),
				global : false,
				success : function(data) {
					console.log(data);
					$('form#form-category').html($(data));
				},
				error : function(error) {
					console.log(error);
				}
			});
		}
