const form = document.querySelector("form");
const buttonUpdate = document.querySelector("footer").querySelector(".btn#update");
buttonUpdate.addEventListener("click", update);



function update(_event) {
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
		.then(json =>console.log(json));
		
    alert("수정 완료 되었습니다.");	
};
