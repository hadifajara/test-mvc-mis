$(document).ready(function() {
	console.log('oke');
	$('#tblView').DataTable();
	$('.btn-delete').on('click', function(e){
		if(confirm('Anda Yakin?')){
			alert('oke !')
		}else{
			return false;
		}
	})
	
	$('.btn-edit').on('click', function(e){
		if(!confirm('Anda Yakin?')){
			return false;
		}
	})
})