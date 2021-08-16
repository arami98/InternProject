const url = "http://localhost:8080";
const userForm = document.querySelector("form");
const buttonUpdate = document.querySelector("footer").querySelector(".btn#update");
buttonUpdate.addEventListener("click", update);



function update(_event) {
	let uri = url + "/api/user/update/" + userForm.id.value;
	let data = {
		"city": userForm.city.value,
		"country": userForm.country.value,
		"dong": userForm.dong.value,
		"code": userForm.code.value
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
};
