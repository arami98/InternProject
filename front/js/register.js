const buttonSubmit = document.querySelector("footer").querySelector(".btn#submit");
const buttonCancel = document.querySelector("footer").querySelector(".btn#cancel");
const userForm = document.querySelector("form");
const passwordCheckInput = userForm.getElementsByTagName("input").passwordCheck;
const passwordInput = userForm.getElementsByTagName("input").password;
const userTypeCheck = document.getElementById("userTypeCheck");

let isUniqueID = true;
let isPasswordChecked = false;


buttonSubmit.addEventListener("click",handleButtonSubmit);

buttonCancel.addEventListener("click",handleButtonCancel);

passwordCheckInput.addEventListener("change",checkPassword);
passwordInput.addEventListener("change",checkPassword);

userTypeCheck.addEventListener('click',checkUserTypeHandler);

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
        //checkUniqueID();
        hasCheckedPasswordStatus();
        checkInput();
        requestUserRegister();
        unableSubmitForm();
    } catch (error) {
        console.log(error);
    }
};

function checkUniqueID(){
    if(!isUniqueID){
        alert("아이디 중복검사 해주세요.");
        throw("아이디 중복");
    }
};

// 이미 패스워드 체크가 되어있는지 검사합니다.
function hasCheckedPasswordStatus(){
        if(!isPasswordChecked){
        alert("패스워드를 확인해 주세요.");
        throw("패스워드 불일치");
        }
};

function checkInput(){
        checkTextFeild();
        checkUserType();    
};

function checkTextFeild() {
    const inputs = userForm.getElementsByTagName("input");

    for (let i = 0; i < inputs.length; i++) {
        if (inputs[i].hasAttribute("required")) {
            if (inputs[i].value === "") {
                emptyFieldException();
            }
        }
    }
};

function checkUserType() {
    const userTypeRadios = document.getElementsByTagName("userTypeCheck");
    let count = 0;
    let index = 0;
    for(;index< userTypeRadios.length;index++){
        if(userTypeRadios[index].checked === ture){
            count++;
        }
    }
    if(count < 1){
        emptyFieldException();
    }
};

function emptyFieldException(){
    alert("모든 빈칸을 채워주세요.");
    throw("빈 데이터");
};


function requestUserRegister(){
    console.dir(userForm);
};


function  unableSubmitForm(){
    
}

function handleButtonCancel(){
    window.history.back();
};



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


function checkUserTypeHandler(){
    const userTypeRadios = document.getElementsByTagName("userTypeCheck");
    const registerNumber = document.getElementsByName("regNo");
    if(userTypeRadios[0].getAttribute("checked")){
        registerNumber[0].classList.remove('hide');
    }else if(userTypeRadios[1].getAttribute("checked")){
        registerNumber[0].classList.add('hide');
    }

    console.log(isPasswordChecked);
};
