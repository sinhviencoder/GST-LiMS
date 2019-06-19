$('#request-buy-book-datatable').DataTable({
    	ajax: '/admin-request-buy-book/datatable',
        serverSide: true,
        columns: [
            {
                data: 'requestBuyBookId', title: '#'
            },
            {
                data: 'bookName', title: 'Book Name'
            },
            {
                data: 'user.firstName', title: 'User Name'
            },
            {
            	data: 'authorName', title : 'Author Name'
            },
            {
            	data: 'publisher', title: 'Publisher'
            },
            {
            	data: 'categoryName', title: 'Category Name'
            },
            {
                data: 'quantity', title: 'Quantity'
            },
            {
                data: 'note', title: 'Note '
            },
            {
            	data: 'status',
            	render: function (data, type, full){
            		//console.log(full);
            		if(full.status==1){
                    return '<select data="' + full.requestBuyBookId + '" class="custom-select" id="status-rbb"> <option value="1" selected >Chờ xác nhận</option> <option value="2">Từ chối</option> <option value="3">Đã duyệt</option> </select>';
            		}
            		if(full.status==2){
                        return '<select data="' + full.requestBuyBookId + '" class="custom-select" id="status-rbb"> <option value="1">Chờ xác nhận</option> <option value="2" selected>Từ chối</option> <option value="3">Đã duyệt</option> </select>';
                		}
                        return '<select data="' + full.requestBuyBookId + '" class="custom-select" id="status-rbb"> <option value="1">Chờ xác nhận</option> <option value="2">Từ chối</option> <option value="3">Đã duyệt</option> </select>';
                		
            		},
            	title: 'Status'
            },
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
					//console.log(data);
					$( "form#form-book" ).replaceWith(data);
				},
				error : function(error) {
					//console.log(error);
				}
			});
		}
  	
  	$('.content-wrapper').on('change', '#status-rbb', function() {
  		
  		let rbbId = $(this).attr('data');
  		
  		console.log(rbbId);
  		
  		let status = $(this).children("option:selected").val();
  		
  		console.log(status);
  		
  		let rbb = {
  					"requestBuyBookId": rbbId,
  					"status": status,
  		};
  		//console.log(rbb);
  		
  		if(status==2){
  			$('#modal-rbb').modal('toggle');
  			$('.content-wrapper').on('click', '#btn1', function() {
  				$.ajax({
  					type : "POST",
  					url : "/admin-request-buy-book/update-status",
  					data : JSON.stringify(rbb),
  					global : false,
  					contentType : "application/json; charset=utf-8",
  					dataType : "json",
  					success : function(data) {
  						toastr.success(data.messages, "Update status", {
  							closeButton : true
  						});
  						$('#modal-rbb').modal('hide');
  						console.log(data);
  					},
  					error : function(error) {
  						console.log(error);
  					}
  				});
  			});
  		}
  		else{
  		$.ajax({
  			type : "POST",
  			url : "/admin-request-buy-book/update-status",
  			data : JSON.stringify(rbb),
  			global : false,
  			contentType : "application/json; charset=utf-8",
  			dataType : "json",
  			success : function(data) {
  				toastr.success(data.messages, "Update status", {
						closeButton : true
					});
  			},
  			error : function(error) {
  				//	console.log(error);
  			}
  		});
  		}
  	});
  		
  		
  	
  	
  	
  	