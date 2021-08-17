fetch("http://193.123.254.182:8080/api/authorization/token", {
    method: 'GET',
    headers: {
        'content-type': 'application/json',
        'Access-Control-Allow-Origin' : '*',
        mode : 'no-cors'
    }
}).then(res => res.json())
    .then(res => {
        localStorage.setItem("jwt",res.accessToken);
        console.log("토큰 받아옴");
    });

                    