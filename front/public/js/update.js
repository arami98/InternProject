const form = document.querySelector("form");
const buttonUpdate = document.querySelector("footer").querySelector(".btn#update");
buttonUpdate.addEventListener("click", update);



function update() {
	let uri = "http://localhost:8080/api/user/update/" + form.id.value;
	
	let data = {
		"city": form.city.value,
		"country": form.country.value,
		"dong": form.dong.value,
		"code": form.code.value
	};

	fetch(uri, {
		method: 'PUT',
		headers: {
			'content-type': 'application/json',
			'Authorization': 'Bearer ' + localStorage.getItem("jwt"),
		},
		body: JSON.stringify(data)
	}).then(res => res.json())
        .then(res => {
          console.log(res);
          switch(res.message){
            case "JSON PASING" :
                alert("올바르지 못한 주소 양식입니다.");
                break;
            case "NULL ADDRESS" :
                alert("주소를 모두 기입 해주세요.");
                break;
            case "UPUDATE SUCCESS" :
                alert("수정 완료 되었습니다.");
                unableSubmitForm();
                break;
        }
        })
        .catch(res=>{
            if(res.message === "INVALID TOKEN" ){
                alert("미인증 사용자입니다.");
            }
            console.log(res);
        });
};