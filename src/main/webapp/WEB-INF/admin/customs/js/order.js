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
            	data: 'note', title: 'Note'
            },
            {
                data: 'status',
                render: function(data, type, full) {
                	let tagStatus = '<select id="order-status" data="'+ full.orderId+ '">'
					                    +'<option value="1" '+ ((full.status == 1) ? "selected" : "") +'>Chưa xử lý</option>'
					                    +'<option value="2" '+ ((full.status == 2) ? "selected" : "") +'>Approved</option>'
					                    +'<option value="3" '+ ((full.status == 3) ? "selected" : "") +'>Reject</option>'
					                    +'<option value="4" '+ ((full.status == 4) ? "selected" : "") +'>Return</option>'
					                    +'<option value="4" '+ ((full.status == 5) ? "selected" : "") +'>Reserve</option>'
				                    +'</select>';
                	return tagStatus;
					
				},
                title: 'Status'
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

  	$('body').on('change', '#order-status', function() {
  		let orderId = $(this).attr('data');
  		console.log(orderId);
		let orderStatus = $(this).children('option:selected').val();
		
		let order = {
				'orderId' : orderId,
				'status' : orderStatus
		};
		
		if(orderStatus == 3){
			$('#modal-confirm-order-reject').modal('toggle');
			$('body').on('click', '#btn-confirm-order-reject', function() {
				let noteOrder = $('#note-confirm-order-reject').val();
				order.note = noteOrder;
				console.log(noteOrder);
				$.ajax({
					type : "POST",
					url : "/admin/order/update-status",
					data : JSON.stringify(order),
					global : false,
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					success : function(data) {
						//alert(data.messages)
						$('#order-datatable').DataTable().ajax.reload();
						$('#modal-confirm-order-reject').modal('hide');
						console.log(data);
					},
					error : function(error) {
						console.log(error);
					}
				});
			});
			
		}else{
			$.ajax({
				type : "POST",
				url : "/admin/order/update-status",
				data : JSON.stringify(order),
				global : false,
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				success : function(data) {
					alert(data.messages)
					console.log(data);
				},
				error : function(error) {
					console.log(error);
				}
			});
		}
	});
  	
  	