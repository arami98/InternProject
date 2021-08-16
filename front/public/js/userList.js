const xhr = new XMLHttpRequest();

userList = new Array();

xhr.open('GET', 'http://localhost:8080/api/user/list');
xhr.setRequestHeader( 'Authorization', 'Bearer '+ localStorage.getItem("jwt"));
xhr.send();

xhr.onreadystatechange = function(e){
    if(xhr.readyState == 4 && xhr.status == 200){
        
        data = xhr.responseText;

        const arr = JSON.parse(data);

        if (Array.isArray(arr) && arr.length < 1){
            notExistUser();
        } 


       for (var i = 0; i < arr.length; i++) { 	

            const addr = JSON.parse(arr[i].addr);

            const user = {
                name : arr[i].name,
                id : arr[i].loginId,
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
        
	
    }

    else if(xhr.status == 404){
        alert("페이지를 찾지 못했습니다. 관리자에게 문의해주세요.");
    }
};



function notExistUser(){
    //let user_list = document.getElementById("user_list");
    //user_list.style.display = 'none';

    let t_body = document.getElementById("t_body");
    t_body.innerHTML += "<tr> <td colspan = '4'> 회원이 존재하지 않습니다 </td> </tr>";
}



