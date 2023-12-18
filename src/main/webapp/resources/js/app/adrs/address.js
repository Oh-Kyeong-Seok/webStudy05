0/**
 * 
 */

$(function(){
	const cPath = document.body.dataset.contextPath;
	let makeTrTag = function(adrsVO){
		let trTag = `
			<tr data-adrs-no="${adrsVO.adrsNo}">
				<td>${adrsVO.adrsName}</td>
				<td>${adrsVO.adrsHp}</td>
				<td>${adrsVO.adrsAdd}</td>
				<td><input type="button" value="삭제" class="delBtn" /></td>
				<td><input type="button" value="수정" class="updateBtn" data-bs-toggle="modal" data-bs-target="#updateModal" /></td>
			</tr>
		`;
		return trTag;
	};
	
	const baseurl = `${cPath}/adrs/address`;

	$.getJSON(baseurl, function(resp){  // method : get, dataType : json
		let adrsList = resp.adrslist;
		trTags = "";
		if(adrsList ?.length > 0){
			$.each(adrsList, function(){
				trTags += makeTrTag(this);
			});
		} else {
			trTags += `
				<tr>
					<td colspan = '3'>주소록 없음.</td>
				</tr>
			`;
		}// if~else end
		$(listBody).html(trTags);		
	});// getJSON end
	
	
	$(adrsForm).on("submit", function(event){
		event.preventDefault();
		
		let url = this.action;
		let method = this.method;
		let data = $(this).serializeJSON(); // js object
		let json = JSON.stringify(data); //마샬링의 결과물
		let settings = {
			url : baseurl,
			method : method,
			data : json,
			headers : {
				"Content-Type" : "application/json;charset=UTF-8"
			},
			dataType: "json"      
		}
		
		$.ajax(settings)
			.done(function(resp){
				if(resp.success) {
					let trTag = makeTrTag(resp.originalData);
					$(listBody).prepend(trTag);
					adrsForm.reset();
				} else {
					alert(resp.message)					
				}
			});
		return false;
		
	});	//adrsForm submit end
	
	$(listBody).on("click",".delBtn", function(){
		let flag = confirm("삭제할까요?");
		if(!flag) return false;
		
		let adrsTr = $(this).parents("tr:first");	// adrsNo를 가지고 있음
		let $adrsTr = $(adrsTr);
		let adrsNo = $adrsTr.data("adrsNo");
		let url = `${baseurl}/${adrsNo}`;
		let settings = {
			url : url,
			method : "delete",
			dataType:"json",
		};
		$.ajax(settings)
			.done(function(resp){
				if(resp.success){
					$adrsTr.remove();			
				} else {
					alert(resp.message);
				}
			});
		
	});
	
	
	$(document).on("click",".modBtn", function(){
		
		console.log(this)
		let data = $(updateForm).serializeJSON();
		let json=JSON.stringify(data); 
		
		let settings ={
			url : baseurl,
			method : "put",
			data : json,
			headers : {
				"Content-Type" : "application/json;charset=UTF-8"
			},
			dataType : "json"
		};
		
		$.ajax(settings)
			.done(function(resp){
				if(resp.success){

				}else{
					alert(resp.message);
				}
			});

			
		
	});
	
	
	
	
	
});