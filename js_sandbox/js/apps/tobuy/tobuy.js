var productsArray;
var productSelected;
load();

function displayResult(item, val, text) {
	addProductToHtml(productsArray[val]);
}

//add product to the checked list
function addProductToHtml(product){
	var content = "";
	content+= '<label>'+product.name+'</label>';
	content+='<input type="checkbox" value="'+product.id+'" name="checkbox" />';
	content+='<button name="delete" value="'+product.id+'" type="button" class="btn btn-small btn-danger">';
	content+='<i class="icon-trash icon-white"></i>';
	content+='</button>';
	var div = $(document.createElement('div')).html(content);
	div.attr('id','product'+product.id);
	$("#unchecked").append(div);
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
	$('#save').click(function(){
		save();
	});
	$('#load').click(function(){
		load();
		refreshData();
	});
	$('#addProduct').click(function(){
		var id = addProduct($('#productInput').val(),"test",0);
		$('#productInput').data('typeahead').source = productsArray;
		addProductToHtml(productsArray[id]);
	});
	initTypeAhead();
});

function refreshData(){
	
}
function initTypeAhead() {
	$('#productInput').typeahead({
		source: productsArray,
		display: 'name',
		val: 'id',
		itemSelected: displayResult
	});
}
function addProduct(name,type,pos){
	var matches = $(productsArray).filter(function(i,n){return n.name == name;});
	var newId = 0;
	if(matches.length==0){
		newId = nextId();
		productsArray[newId]={id:newId, name:name, type:type, pos:pos};
	}
	else{
		newId = matches[0].id;
	}
	return newId;
}
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