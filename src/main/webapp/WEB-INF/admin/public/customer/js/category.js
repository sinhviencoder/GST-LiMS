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
                     +'<button id="delete_post" style="margin-left: 5px" class="btn btn-sm btn-danger" value="'+ data.userId + '" data-toggle="tooltip" data-original-title="delete"><i class="fa fa-trash-o"></i></button>';
                 }
             }
          ],
    });

	
	$('#profile-tab').on('click', function(e) {
		$.ajax({
			type : "GET",
			url : "/admin/user/add",
			global : false,
			success : function(data) {
				console.log(data);
				$("form#form-user" ).replaceWith(data);
			},
			error : function(error) {
				console.log(error);
			}
		});
	});
    
  	function saveUser() {
  		var data = $('form#form-user').serialize();
			console.log(data);
			$.ajax({
				type : "POST",
				url : "/admin/user/add",
				data : data,
				global : false,
				success : function(data) {
					console.log(data);
					$( "form#form-user" ).replaceWith(data);
				},
				error : function(error) {
					console.log(error);
				}
			});
		}
