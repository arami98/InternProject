const menuIcon = document.querySelector('.hamburger-menu');
const navBar = document.querySelector('.navbar');

menuIcon.addEventListener('click',()=>{
        navBar.classList.toggle("change");
});


fetch("http://132.226.230.22:8080/api/authorization/token", {
        method: 'get',
        headers: {
                'mode' : 'no-cors',
                'content-type': 'application/json',
                'Access-Control-Allow-Origin' : '*'
        }
    }).then(res => res.json())
        .then(token => {
            localStorage.setItem("jwt",token.accessToken)
            alert("로그인 되었습니다");
        });