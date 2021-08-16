fetch("http://localhost:8080/api/authorization/token", {
    method: 'GET',
    headers: {
        'content-type': 'application/json',
        'Access-Control-Allow-Origin' : '*',
        mode : 'no-cors'
    }
}).then(res => res.json())
    .then(res => {
        localStorage.setItem("jwt",res.accessToken)
        alert("인증 되었습니다");
    });

                    