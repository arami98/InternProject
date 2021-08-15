const buttonSubmit = document.querySelector("footer").querySelector(".btn#submit");
const buttonCancel = document.querySelector("footer").querySelector(".btn#cancel");
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
        unableSubmitForm();
    } catch (error) {
        console.log(error);
    }
};

function checkUniqueID(){
    const able = "사용 가능한 id입니다.";
    const unAble = "이미 사용중인 id입니다.";
    const idCheckRes = document.getElementById("idCheckRes");

    if(idCheckRes.innerText === ""){
        alert("아이디를 입력 해주세요.");
        throw("아이디 미입력");
    }else if(idCheckRes.innerText === unAble ){
        alert("아이디 중복검사 해주세요.");
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
    //TODO REGISTER USER 
}


function  unableSubmitForm(){
    console.log("unable form");
    //TODO UNABLE FORM EXCEPT ADDRESS FORM
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



function selectUserType(value){
    const inputRegNo =   document.getElementById('regNoInput');
    if(value == 'agent'){
        inputRegNo.classList.remove('hide');
    }else{
        inputRegNo.classList.add('hide');
    }
}
