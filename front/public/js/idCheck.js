const idCheck = document.getElementById("idCheck");
const idCheckRes = document.getElementById("idCheckRes");
const BaseURL = "http://localhost:8080/api/user/id-check/";
const token = localStorage.getItem("jwt");

idCheck.addEventListener("click", idDupCheck);

function idDupCheck(){
    const id = document.getElementById("id").value.trim();
    let url;

    if(!idBlankCheck(id))
        return;

    if(!idLengthCheck(id))
        return;

    if(!idFormCheck(id))
        return;
    
    url = BaseURL + id;

    fetch(url,
        {
        method: 'get',
        headers:  {
            'content-type':'application/json',
            'Access-Control-Allow-Origin' : '*',
            'Authorization': 'Bearer' + token
        } 
    })
        .then(res => res.json())
        .then(res => {
            //console.log(res);
            if(res.result == "UNUSABLE"){
                idCheckRes.innerHTML = `${res.message}`;
                idCheckRes.style.color = "red";
            }

            else if(res.result == "USABLE"){
                idCheckRes.innerHTML = `${res.message}`;
                idCheckRes.style.color = "blue";
            }
        })
        .catch(err => {
            console.log("Fetch Err.", err);
        });

}

//아이디 공백 검사
function idBlankCheck(id){
    if(id == undefined || id == ""){
        idCheckRes.innerHTML = "아이디를 입력하세요.";
        idCheckRes.style.color = "red"; 
        // alert("아이디를 입력하세요.");
        return false;
    }

    return true;
}

//아이디 길이 검사
function idLengthCheck(id){
    if(id.length > 45){
        idCheckRes.innerHTML = "아이디 길이가 너무 깁니다.";
        idCheckRes.style.color = "red"; 
        //alert("아이디 길이가 너무 깁니다!");
        return false;
    }

    return true;
}

//아이디 형식 검사
function idFormCheck(id){
    const regExp = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/; 
    if(regExp.test(id)){
        alert("한글 입력 불가");
        return false;
    }

    return true;
}