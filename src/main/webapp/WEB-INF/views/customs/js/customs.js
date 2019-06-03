$('#content').on('click', '.pagination a', function(e) {
	e.preventDefault();
	$('#content').load($(this).attr('href'));
});