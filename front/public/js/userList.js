const xhr = new XMLHttpRequest();

userList = new Array();

xhr.open('GET', 'http://localhost:8080/api/test/user');
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

    }
};



function notExistUser(){
    let user_list = document.getElementById("user_list");
    user_list.style.display = 'none';

    let notExist = document.getElementById("notExist");
    notExist.innerText = "아직 사용자가 존재하지 않습니다";
}



