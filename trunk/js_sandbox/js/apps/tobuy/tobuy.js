var productsArray = [];
var productSelected;
load();

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
	});
	$('#addProduct').click(function(){
		var id = addProduct($('#productInput').val(),"test",0);
		$('#productInput').data('typeahead').source = productsArray;
		addProductToHtml(productsArray[id]);
	});
	initTypeAhead();
});

function initData(){
	addProduct("steak", "viande", 1);
	addProduct("lait", "boisson", 2);
	addProduct("eau", "boisson", 4);
	addProduct("poulet", "viande", 8);
	addProduct("céréal", "sec", 3);
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
	div.attr('pos',product.pos);
	$("#unchecked").append(div);
	
	$('div').sortElements(function(a,b){return $(a).attr('pos')>=$(b).attr('pos');});
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
function displayResult(item, val, text) {
	addProductToHtml(productsArray[val]);
}
function nextId(){
	return productsArray.length>0 ? Math.max.apply(null, productsArray.map(function(a){return a.id;})) + 1 : 0;
}
function save(){localStorage["productsArray"] = JSON.stringify(productsArray);}
function load(){
	var fromLocal = localStorage["productsArray"];
	if(fromLocal!=null ){
		productsArray=JSON.parse(fromLocal)
	}
	else{
		initData();
	}
}