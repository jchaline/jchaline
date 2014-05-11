var productsArray;
load();

function displayResult(item, val, text) {
	addProduct(productsArray[val]);
	$('.alert').show().html('You selected <strong>' + val + '</strong>: <strong>' + text + '</strong>');
}

$(function () {
	$('#productInput').typeahead({
		source: productsArray,
		display: 'name',
		val: 'id',
		itemSelected: displayResult
	});
});

//add product to the checked list
function addProduct(product){
	var content = "";
	content+= '<label>'+product.name+'</label>';
	content+='<input type="checkbox" value="'+product.id+'" name="checkbox" checked="checked" />';
	content+='<button name="delete" value="'+product.id+'" type="button" class="btn btn-small btn-danger">';
	content+='<i class="icon-trash icon-white"></i>';
	content+='</button>';
	var div = $(document.createElement('div')).html(content);
	div.attr('id','product'+product.id);
	$("#checked").append(div);
}

$(document).ready(function(){
	$(document).on('click','input[name="checkbox"]',function(e){
		var checked = e.target.checked;
		var id = e.target.value;
		
		$('#product'+id).prependTo(checked?'#checked':'#unchecked');
	});
	
	$(document).on('click','[name="delete"]',function(){
		$(this).parent().remove();
	});
});

function initData(){
	var id=0;
	var products= [
		{ id: id++, name: "steak", type: "viande", pos: 1 },
		{ id: id++, name: "roti", type: "viande", pos: 3 },
		{ id: id++, name: "eau", type: "boisson", pos: 4 },
		{ id: id++, name: "lait", type: "froid", pos: 2 }
	];
	return products;
}
function nextId(){
	return Math.max.apply(null, productsArray.map(function(a){return a.id;})) + 1;
}
function save(){localStorage["productsArray"] = JSON.stringify(productsArray);}
function load(){
	var fromLocal = localStorage["productsArray"];
	productsArray = fromLocal!=null ? JSON.parse(fromLocal) : initData();
}