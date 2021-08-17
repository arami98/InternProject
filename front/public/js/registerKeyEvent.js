function selectUserType(value){
    const inputRegNo =   document.getElementById('regNoInput');
    if(value == 'agent'){
        inputRegNo.classList.remove('hide');
    }else{
        inputRegNo.classList.add('hide');
    }
}
