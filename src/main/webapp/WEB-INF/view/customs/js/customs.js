$('#content').on('click', '.pagination a', function(e) {
	e.preventDefault();
	$('#content').load($(this).attr('href'));
});

$('#content').on("click", "a.add-to-cart", function(){
  var bookId =  $(this).attr("data");
  console.log("a.add-to-cart", bookId);
  
  $('#modal-book-cart').load("/book/cart/add?bookId=" + bookId+ " #modal-book-cart > *", function() {
	console.log("#modal-book-cart");
	
	$('#modal-book-cart').modal('toggle');
  });
  
});

$('#modal-book-cart').on("click", "#confirm-book-order", function(){
	var bookId =  $(this).val();
	$('#modal-book-cart').load("/book/order/confirm?bookId=" + bookId+ " #modal-book-cart > *", function() {
		console.log("#confirm-book-order okoko11111111");
		
	});
	
});

