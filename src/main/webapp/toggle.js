var data = [];

function populate(arr){
	console.log("in populate");
	console.log(arr);
	$("#content").remove();
	var tickets = "<tbody id =\"content\">";
	$.each(arr, function(key,value){
		tickets += '<tr id = \"'+ key +'\" >';
		tickets += '<th scope="row">'+key+'</th>'
		tickets += '<td class ="route" >'+value.routeId + '</td>';
		tickets += '<td class = "fname">'+value.firstName + '</td>';
		tickets += '<td class = "lname">'+value.lastName + '</td>';
		tickets += '<td class = "seat">'+value.seat + '</td>';
		tickets += '<td class = "train">'+value.TrainId + '</td>';
		if(value.status===true){
			tickets += "<td> <button type=\"button\"  aria-label=\"Close\" id =\"c";
			tickets += key;
			tickets += "\" onClick= \" getInfo(this.id)\"";
			tickets += "> cancel </button> </td>";
			
		}else{
			tickets += "<td> <button data-toggle=\"modal\" data-target=\"#userInfo\" type=\"button\"  aria-label=\"Reserve\" id =\"r";
			tickets += key;
			tickets += "\" onClick= \" reserveUser(this.id)\"";
			tickets += "> Reserve </button> </td>";
		
		}
		tickets += "</tr>";
	});
	$('#ticket').append(tickets);
}

function get(){
		console.log("In get val");
		$.ajax  ({
			url : 'DbServlet',
			data:{
				Arrival:$('#arrival').val(),
				Date: $('#date').val(),
				Departure:$('#departure').val()
			},
			type: "GET",
			success : function(res) {
				console.log("successful In get val");
				populate(res);
			}
		});
}


function getInfo(id){
	var len = id.length;
	var action = id.slice(0, 1);
	var row = id.slice(1, len);
	console.log(action);
	console.log(row);
	var el = "tr#"+ row;
	console.log(el);
	$(el).each(function() {
        var route = $(this).find(".route").text();
        var seat = $(this).find(".seat").text();
        var trainId = $(this).find(".train").text();
        console.log(route);
        console.log(seat);
        console.log(trainId);
        sendTicket(seat, route, trainId, row);
});
	
}

function reserveUser(id){
	var len = id.length;
	var action = id.slice(0, 1);
	var row = id.slice(1, len);
	var el = "tr#"+ row;
	$(el).each(function() {
        var route = $(this).find(".route").text();
        var seat = $(this).find(".seat").text();
        var trainId = $(this).find(".train").text();
        console.log(route);
        console.log(seat);
        console.log(trainId);
        reserveTicket(seat, route, trainId, row);
});
}
function changeTicket(s,r,t,row,fname,lname, email){
	this.row =row;
	$.ajax  ({
		url : 'Reserve',
		data:{
			Seat:s,
			Route: r,
			Train: t,
			Fname: fname,
			Lname:lname,
			Email: email
		},
		type: "POST",
		success : function(res) {
			if(res===1){
				console.log("ICANSEE"+row);
				console.log("res is 1");
		        $("tr#"+row).find(".fname").text(fname);
		        $("tr#"+row).find(".lname").text(lname);
		        $("tr#"+row).find("#r"+row).text("Cancel");
		        $("tr#"+row).find("#r"+row).attr("id", "c"+row);
		        
		        $("tr#"+row).find("#c"+row).attr("onClick", "getInfo(this.id)");
		        $("tr#"+row).find("#c"+row).attr("data-toggle", "");
		        $("tr#"+row).find("#c"+row).attr("data-target", "");
		        
			}
			console.log("successful In get val"+res);
		}
	});
}

function reserveTicket(s,r,t,row){
	this.row = row;
	this.s = s;
	this.r = r;
	this.t = t;
	var fname = $("#fname").val();
	var lname = $("#lname").val();
	var email = $("#email").val();
	var button = document.querySelector('#reserveTicket');
	button.addEventListener('click', function(e) {
		console.log("I've been clicked");
		changeTicket(s,r,t,row,fname,lname, email);
		
		})
	
}


function sendTicket(s , r ,t, row){
	this.row = row;
	var name=$("tr#"+row).find(".fname").text();
    var surname=$("tr#"+row).find(".lname").text();
	var cancel = confirm("Are you sure you want to cancel "+ name+" "+ surname+ " ticket?" );
	if (cancel == false){
		
	}else{
	console.log("in send ticket")
		$.ajax  ({
			url : 'DbServlet',
			data:{
				Seat:s,
				Route: r,
				Train: t
			},
			type: "POST",
			success : function(res) {
				if(res===1){
					console.log("ICANSEE"+row);
					console.log("res is 1");
			        $("tr#"+row).find(".fname").text("Nobody");
			        $("tr#"+row).find(".lname").text("Nobody");
			        $("tr#"+row).find("#c"+row).text("Reserve");
			        $("tr#"+row).find("#c"+row).attr("id", "r"+row);
			        $("tr#"+row).find("#r"+row).attr("onClick", "reserveUser(this.id)");
			        $("tr#"+row).find("#r"+row).attr("data-toggle", "modal");
			        $("tr#"+row).find("#r"+row).attr("data-target", "#userInfo");
			        
				}
				console.log("successful In get val"+res);
			}
		});
	}
}

function historyRequest(){
	console.log("In historyRequest");
	$.ajax  ({
		url : 'Reserve',
		data:{
			User: "adilet.mukashev"
		},
		type: "GET",
		success : function(res) {
			console.log("successful In get val");
			showHistory(res);
		}
	});
	
}


function showHistory(r){
	console.log("in showHistory");
	$("#historyContent").remove();
	var history = "<tbody id = \"historyContent\">";
	$.each(r, function(key,value){
		history += "<tr>"
		history += '<th scope="row">'+key+'</th>'
		history += '<td class ="seatH" >'+value.seat + '</td>';
		history += '<td class = "dep">'+value.dep + '</td>';
		history += '<td class = "ar">'+value.ar + '</td>';
		history += '<td class = "time">'+value.time + '</td>';
		history += '<td class = "train">'+value.TrainId + '</td>';
		history += "</tr>";
		
	});
	history += "</tbody>";
	$('#history').append(history);
}








//    function productsAdd() {
//      // First check if a <tbody> tag exists, add one if not
//      if ($("#ticket tbody").length == 0) {
//        $("#ticket").append("<tbody></tbody>");
//      }
//        
//      // Append product to the table
//      $("#ticket tbody").append(
//        "<tr>" +
//          "<td>Astana</td>" +
//          "<td>nur-sultan</td>" +
//          "<td>26</td>" +
//        "</tr>"
//        );
//      $("#ticket tbody").append(
//        "<tr>" +
//        "<td>Semey</td>" +
//          "<td>Semey</td>" +
//          "<td>14</td>" +
//          "<td> <button type=\"button\" class=\"close\" aria-label=\"Close\">cancel </button> </td>"+
//        "</tr>"
//        );
//    }
$(document).ready(function() {


//  $("#datepicker").click (function () {
//    $("#datepicker").datepicker({ dateFormat: 'yy-mm-dd' });
//  });


$("#formButton").click(function() {
      $("#form1").toggle();
    });
$("#reserveTicket").click(function(){
	
});
$("#showHistory").click(function(){
	historyRequest();
});

    $("#submit").click(function() {
      $("#form1").toggle();
      console.log('deleteing');
      if ($("#ticket tbody tr").length != 0) {
    	  $("#ticket").find("tr:gt(0)").remove();
        }
      get();
      console.log($('#arrival').val());
      console.log($('#date').val());
    });

   
  });
  