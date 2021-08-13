fetch("http://localhost:8080/api/user/list", {
    method: 'POST',
    headers: {
        'content-type': 'application/json',
        'Access-Control-Allow-Origin' : '*',
        mode : 'no-cors'
    },
    body : JSON.stringify("medialogmedialogmedialogmedialogmedialogmedialogmedialog")
}).then(res => res.json())
    .then(res => {
        localStorage.setItem("jwt",res.accessToken)
        alert("인증 되었습니다");
    });

                    