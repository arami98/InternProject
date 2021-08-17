const menuIcon = document.querySelector('.hamburger-menu');
const navBar = document.querySelector('.navbar');

menuIcon.addEventListener('click',()=>{
        navBar.classList.toggle("change");
});

function inNumber(event){
        if(event.keyCode<48 || event.keyCode>57){
           event.returnValue=false;
        }
}