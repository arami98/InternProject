const xhr = new XMLHttpRequest();

const token = localStorage.getItem("jwt");

const url = 'http://193.123.254.182:8080/api/user/list';

userList = new Array();

fetch(url,
    {
    method: 'get',
    headers:  {
        'content-type':'application/json',
        'Authorization': 'Bearer ' + token
    } 
    })
    .then(res => res.json())
    .then(data => {
        console.log(data.result);
        if (data.message == "Empty List"){
            notExistUser();
        }

       for (var i = 0; i < data.result.length; i++) { 	

            const addr = JSON.parse(data.result[i].addr);

            const user = {
                name : data.result[i].name,
                id : data.result[i].loginId,
                city : addr.city,
                country : addr.country,
                dong : addr.dong  
            }

            userList.push(user);
        }


        let t_body = document.getElementById("t_body");

        for(let i = 0; i < userList.length; i++){
            t_body.innerHTML += "<tr> <th scope='row'>" + 
                                    (i + 1) +"</th> <td>" + 
                                    userList[i].name + "</td> <td>" + 
                                    userList[i].id + "</td> <td>" + 
                                    userList[i].city + " " + userList[i].country + " " + userList[i].dong + "</td> </tr>";
        }
    })
    .catch(err => {
        if(err.message == "INVALID TOKEN"){
            alert("userList의 유효하지 않은 토큰값입니다.");
            console.log("INVALID TOKEN");
        }

        console.log("userList의 Fetch Err.", err);
});



function notExistUser(){
    let t_body = document.getElementById("t_body");
    t_body.innerHTML += "<tr> <td colspan = '4'> 회원이 존재하지 않습니다 </td> </tr>";
}



