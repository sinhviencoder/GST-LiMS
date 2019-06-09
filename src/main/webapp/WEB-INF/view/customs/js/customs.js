$('#content').on('click', '.pagination a', function(e) {
	e.preventDefault();
	$('#content').load($(this).attr('href'));
});

$('#content').on("click", "a.add-to-cart", function(){
  var bookId =  $(this).attr("data");
  console.log("ok", bookId);
  
  $('#modal-book-cart').load("/book/cart/add?bookId=1 #modal-book-cart > *", function() {
	console.log("ok");
	
	$('#modal-book-cart').modal('toggle');
//	$('#modal-cart').modal('show');
//	$('#modal-cart').modal('hide');
	
  });
  
})

