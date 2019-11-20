var data = [];

var ar = [];
var ar_pos = [];
var max = 0;


function populate(arr){
	console.log("in populate");
	console.log(arr);
	$("#content").remove();
	var tickets = "<tbody id =\"content\">";
	$.each(arr, function(key,value){
		tickets += '<tr id = \"'+ key +'\" >';
		tickets += '<th scope="row">'+key+'</th>'
		tickets += '<td class ="dat" >'+value.dat + '</td>';
		tickets += '<td class = "train">'+value.train + '</td>';
		tickets += '<td class = "leg">'+value.leg + '</td>';
		tickets += '<td class = "route">'+value.route + '</td>';
		tickets += '<td class = "time">'+value.time + '</td>';
		tickets += '<td class = "dep">'+value.dep + '</td>';
		tickets += '<td class = "arr">'+value.arr + '</td>';
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






// -------------------- FROM SOME.JS ----------------------
function findMax(){
    console.log("in max find");
    console.log(ar);
    for(var i = 0; i < ar.length; i+=1){
        var n = getNumberPart(ar[i].id);
        var x = parseInt(n);
        if(x>max){
            max = x;
        }
    }

}

function prepareData(){
    ar.length = 0;
    ar_pos.length = 0;
    var table = document.getElementById('ticket');
    var rowLength = table.rows.length;
    for(var i=1; i<rowLength; i+=1){
        var row = table.rows[i];
        ar.push(row)
    }

    var temp = 0;
    var counter = 0;
    for (var j=1; j<10; j+=1){
        counter = 0;
        for(var k =0; k<ar.length; k+=1){
            temp = getNumberPart(ar[k].id)
            temp = temp.slice(0,1);
            var x  = parseInt(temp);
            if(x ===j){
                counter+=1
            }
        }
        ar_pos.push(counter);

    }

    findMax();

    console.log(ar);
    console.log(ar_pos);
    console.log(max);
}

function isNumberKey(evt){
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    return true;
}

function getNumberPart(s){
    var end = s.length
    var ans = s.slice(1,end)
    return ans
}
function getNumber(s){

    return s.cells[4].innerText;
}
function compare(a,b){
    var num = returnNumber();
    if(num === 0){
        return 0;
    }
    var len = num.length;
    console.log(num);
    var num2 = num.slice(0, len-1);
    console.log(num2);
    var t1 = getNumber(a);
    var t2 = getNumber(b);
    if(len<=t1.length&&len<=t2){
        t1 = t1.slice(0,len);
        t2 = t2.slice(0,len);

        c1 = t1.slice(0,len-1);
        c2 = t2.slice(0,len-1);
    }
    if (t1 == num && t2==num){
        return -1;
    }else if(t1 == num && t2!=num){
        return -1;
    }else if(t1 != num && t2==num){
        console.log("here");
        return 1;
    }else if(t1 == num && c2==num2){
        return -1;
    }else if(t1 == num && c2!=num2){
        return -1;
    }
    else if(t1 != num && c2==num2){
        return 1;
    }else if(c1 == num2 && t2==num){
        return 1;
    }else if(c1 == num2 && t2!=num){
        return -1;
    }
    else{
        return 1;
    }
}

function compare2(a,b){
    var n1 = getNumberPart(a.id);
    var n2 = getNumberPart(b.id);
    var num1 = parseInt(n1,10)
    var num2 = parseInt(n2,10)
    if(num1<num2){
        return -1;
    }else{
        return 1;
    }
}

function returnNumber(){

    if($("#number").val()===""){
        return 0;

    }
    return $("#number").val();
}


function rearrange(evt){
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode > 31 && (charCode < 48 || charCode > 57)){
        return 0
    }



    window.ar.sort(compare);
    var num = returnNumber();
    var n = parseInt(num);
    if(num===0){
        return 0;
    }
    if(n>max||n<0){

    }
    num = num.slice(0,1);
    var x = parseInt(num);
    console.log(ar);
    console.log("here the number of times: "+ar_pos[x-1]);

    // ar = ar.slice(0, ar_pos[x-1]).sort(compare2).concat(ar.slice(ar_pos[x-1], ar.len));
    console.log(ar);
    for (var j = ar.length; j>=0;j-=1){
        $(ar[j]).insertAfter("#content");
    }
}



//--------------------- END SOME.JS ------------------------

$(document).ready(function() {



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
      window.setTimeout(prepareData,1000);
    });



//------------------------ FROM SOME.JS ------------------------------
    $("#number").on('keyup',function(event){
        rearrange(event);
        console.log("rearranged");
    });

// ------------------------ END SOME.JS ------------------------------
   
  });
  