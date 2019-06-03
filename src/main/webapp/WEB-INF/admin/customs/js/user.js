    $('#user-datatable').DataTable({
    	ajax: '/admin/user/datatable',
        serverSide: true,
        columns: [
            {
                data: 'userId', title: '#'
            },
            {
                data: 'firstName', title: 'First Name'
            },
            {
                data: 'lastName', title: 'Last Name'
            },
            {
            	data: 'phone', title : 'Phone'
            },
            {
            	data: 'avatar', title: 'Avatar'
            },
            {
            	data: 'username', title: 'UserName'
            },
            {
            	data: 'roles', title: 'Roles', 
            	render: function (data, type, full){
                	var s = "";
                	var size = data.length;
                    for (i = 0; i < size; i++) { 
                    	if(i != size - 1){
                    		s += data[i].name + " | "
                    	}else{
                    		s += data[i].name;
                    	}
                    }
                    return s;
                },
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
