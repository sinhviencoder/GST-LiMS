    $('#order-datatable').DataTable({
    	ajax: '/admin/order/datatable',
        serverSide: true,
        columns: [
            {
                data: 'orderId', title: '#'
            },
            {
            	data: 'book.name', title: 'Book Name'
            },
            {
            	data: 'user.lastName', title: 'User'
            },
            {
                data: 'startDate', title: 'Start Date'
            },
            {
                data: 'endDate', title: 'End Date'
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
					$( "form#form-book" ).replaceWith(data);
				},
				error : function(error) {
					console.log(error);
				}
			});
		}
