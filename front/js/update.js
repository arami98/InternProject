const userForm = document.querySelector("form");
const buttonUpdate = document.querySelector("footer").querySelector(".btn#update");
buttonUpdate.addEventListener("click", update);



function update(_event) {
	let url = 'http://132.226.230.22:8080/api/user/update/' + userForm.id.value;
	let data = {
		"city": userForm.city.value,
		"country": userForm.country.value,
		"dong": userForm.dong.value,
		"code": userForm.code.value
	};
	
	fetch(url, {
		method: "PUT",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(data)
	}).then(res => res.json())
		.then(response => console.log('Success:', JSON.stringify(response)))
		.catch(error => console.error('Error:', error));
};