const baseUrl = "http://localhost:8080";
const buttonSubmit = document.getElementById("register");
const buttonCancel = document.getElementById("cancel");
const userForm = document.querySelector("form");
const passwordCheckInput = userForm.getElementsByTagName("input").passwordCheck;
const passwordInput = userForm.getElementsByTagName("input").password;

let isUniqueID = true;
let isPasswordChecked = false;


buttonSubmit.addEventListener("click",handleButtonSubmit);

buttonCancel.addEventListener("click",handleButtonCancel);
passwordCheckInput.addEventListener("change",checkPassword);
passwordInput.addEventListener("change",checkPassword);


function handleButtonSubmit(event){
    event.preventDefault();
    /*
        todo
        1. 아이디 중복 체크를 확인해야합니다.
        2. 비밀번호와 확인란의 입력이 같은지 확인해야합니다.
        3. 필수 입력값이 들어와있는지 확인해야합니다.
        4. 모든 경우가 확인되었다면 회원가입 요청을 합니다.
        5. 가입이 완료 된 후에는 주소 이외의 정보 수정을 금지합니다.
     */
    try {
        checkInput();
        checkUniqueID();
        hasCheckedPasswordStatus();
        requestUserRegister();
    } catch (error) {
        console.log(error);
    }
}

function checkUniqueID(){
    const able = "사용 가능한 id입니다.";
    const unAble = "이미 사용중인 id입니다.";
    const idCheckRes = document.getElementById("idCheckRes");

    if(idCheckRes.innerText === ""){
        alert("아이디 중복검사 해주세요.");
        throw("아이디 검별");
    }else if(idCheckRes.innerText === unAble ){
        alert("중복된 아이디입니다.");
        throw("아이디 중복");
    }
}

// 이미 패스워드 체크가 되어있는지 검사합니다.
function hasCheckedPasswordStatus(){
        if(!isPasswordChecked){
            alert("패스워드를 확인해 주세요.");
            throw("패스워드 불일치");
        }
};

function checkInput(){
        checkTextFeildInput();
        checkUserTypeInput();    
};

function checkTextFeildInput() {
    const inputs = userForm.getElementsByTagName("input");

    for (let i = 0; i < inputs.length; i++) {
        if (inputs[i].hasAttribute("required")) {
            if (inputs[i].value === "") {
                emptyFieldException("textInput");
            }
        }
    }
};

function checkUserTypeInput() {
    const userTypeRadios = document.getElementsByName("userTypeRadio");
    const inputRegNo =   document.getElementById('regNoInput'); 
    let count = 0;
    let index = 0;
    for(;index< userTypeRadios.length;index++){
        if(userTypeRadios[index].checked){
            count++;
        }
    }
    if(count < 1){
        emptyFieldException("userType");
    }

    if(userTypeRadios[0].checked && inputRegNo.value === ""){
        emptyFieldException("must fill reg no");
    }
};

function emptyFieldException(space){
    console.log(space)
    alert("모든 빈칸을 채워주세요.");
    throw(`There is empty input in ${space}`);
};


function requestUserRegister(){

    console.log("register clciked");

    const date = new Date();
    const currentTime = `${date.getFullYear()}-${date.getMonth()+1}-${date.getDate()} ${date.getHours()}:${date.getMinutes()}:${date.getSeconds()}.${date.getMilliseconds()}`;
    const updateForm = {
          name : userForm.name.value ,
          ssn1 : userForm.ssn1.value ,
          ssn2 : userForm.ssn2.value ,
          tel1 : userForm.tel1.value ,
          tel2 : userForm.tel2.value ,
          tel3 : userForm.tel3.value ,
          userId : userForm.id.value,
          password : userForm.password.value,
          address : JSON.stringify({
              city : userForm.city.value,
              country : userForm.country.value,
              dong : userForm.dong.value,
              code : userForm.code.value
          }),
          email : userForm.email.value,
          type : userForm.userTypeRadio.value,
          regNo : userForm.reg_no.value,
          regDate : currentTime,
    };

    console.log(updateForm);
    
    fetch(`${baseUrl}/api/user/register`, {
        method: 'POST',
        headers: {
            'content-type':'application/json',
            'Access-Control-Allow-Origin' : '*',
            'Authorization': 'Bearer ' + token
        },
        body : JSON.stringify(updateForm)
    }).then(res => res.json())
        .then(res => {
          console.log(res);
          switch(res.message){
            case "PHONE NUMBER IS INVALID" :
                alert("올바르지 못한 휴대폰 번호 양식입니다.");
                break;
            case "SSN NUMBER IS INVALID" :
                alert("올바르지 못한 주민번호 양식입니다.");
                break;
            case "USER ALREADY EXISTS" :
                alert("이미 존재하는 유저입니다.");
                break;
            case "REGISTER SUCCESS" :
                alert("등록 완료 되었습니다.");
                unableSubmitForm();
                break;
        }
        })
        .catch(res=>{
            if(res.message === "INVALID TOKEN" ){
                alert("미인증 사용자입니다.");
            }
            console.log(res);
        });
}


function  unableSubmitForm(){
    const emailSelector = document.getElementsByName("email_com");
    const updateButton = document.getElementById("update");

    userForm.name.readOnly  = true;
    userForm.ssn1.readOnly  = true;
    userForm.ssn2.readOnly  = true;
    userForm.tel1.readOnly  = true;
    userForm.tel2.readOnly  = true;
    userForm.tel3.readOnly  = true;
    userForm.id.readOnly  = true;
    userForm.password.readOnly  = true;
    userForm.passwordCheck.readOnly  = true;
    userForm.email.readOnly  = true;
    userForm.userTypeRadio[0].disabled  = true;
    userForm.userTypeRadio[1].disabled  = true;
    userForm.reg_no.readOnly  = true;
    emailSelector[0].disabled = true;

    userForm.name.classList.add('readOnly');
    userForm.ssn1.classList.add('readOnly');
    userForm.ssn2.classList.add('readOnly');
    userForm.tel1.classList.add('readOnly');
    userForm.tel2.classList.add('readOnly');
    userForm.tel3.classList.add('readOnly');
    userForm.id.classList.add('readOnly');
    userForm.password.classList.add('readOnly');
    userForm.passwordCheck.classList.add('readOnly');
    userForm.email.classList.add('readOnly');
    userForm.userTypeRadio[0].classList.add('readOnly');
    userForm.userTypeRadio[1].classList.add('readOnly');
    userForm.reg_no.classList.add('readOnly');
    emailSelector[0].classList.add('readOnly');

    buttonSubmit.classList.add('hide');

    updateButton.classList.remove('hide');

}
function handleButtonCancel(){
    window.history.back();
}



function checkPassword(){

    const password = userForm.password.value;
    const passwordCheck = userForm.passwordCheck.value;
    const passwordAnnount = document.getElementById("passwordAnnounce");
    
    if(password === passwordCheck){
        isPasswordChecked = true;
        passwordAnnount.classList.add("hide");
    }else{
        isPasswordChecked = false;
        passwordAnnount.classList.remove("hide");
    }

};



function selectUserType(value){
    const inputRegNo =   document.getElementById('regNoInput');
    if(value == 'agent'){
        inputRegNo.classList.remove('hide');

    }else{
        inputRegNo.classList.add('hide');
    }
}
