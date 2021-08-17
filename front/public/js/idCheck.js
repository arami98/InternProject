const idCheck = document.getElementById("idCheck");
const idCheckRes = document.getElementById("idCheckRes");
const idInput = document.getElementById("id");
const baseURL = "http://localhost:8080/api/user/id-check/";
const token = localStorage.getItem("jwt");
let check = false;
let id;

idCheck.addEventListener("click", idDupCheck);
idInput.addEventListener("keyup", idInputCheck);

function idDupCheck(){
    //console.log(token);
    let url;
    //console.log(check);
    if(!check){
        alert("아이디를 다시 확인 해주세요.");
        idInput.value = null;
        return;
    }
    
    url = baseURL + id;

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
            //console.log(data);
            if(data.result == "UNUSABLE"){
                idCheckRes.innerHTML = data.message;
                idCheckRes.style.color = "red";
            }

            else if(data.result == "USABLE"){
                idCheckRes.innerHTML = data.message;
                idCheckRes.style.color = "blue";
            }
        })
        .catch(err => {
            if(err.message == "INVALID TOKEN"){
                alert("유효하지 않은 토큰값입니다.");
                console.log("INVALID TOKEN");
            }
            console.log("Fetch Err.", err);
        });

}

function idInputCheck(){
    id = idInput.value.replace(/\s/g, "");
    if(!idBlankCheck(id))
        return;

    if(!idLengthCheck(id))
        return;

    if(!idFormCheck(id))
        return;

    check = true;
    idCheckRes.innerHTML ="";
}

//아이디 공백 검사
function idBlankCheck(id){
    if(id == undefined || id == ""){
        idCheckRes.innerHTML = "아이디를 입력하세요.";
        idCheckRes.style.color = "red"; 
        check = false;
        return false;
    }

    return true;
}

//아이디 길이 검사
function idLengthCheck(id){
    if(id.length > 45){
        idCheckRes.innerHTML = "아이디 길이가 너무 깁니다.";
        idCheckRes.style.color = "red"; 
        check = false;
        return false;
    }

    return true;
}

//아이디 형식 검사
function idFormCheck(id){
    const regExp = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/; 
    if(regExp.test(id)){
        idCheckRes.innerHTML = "**한글 입력 불가**";
        idCheckRes.style.color = "red";
        check = false;
        return false;
    }

    return true;
}