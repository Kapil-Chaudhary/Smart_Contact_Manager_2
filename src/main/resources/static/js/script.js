console.log("script loaded");



// change theme work

let currentTheme = getTheme();
// console.log("currentTheme : " + currentTheme)

// initial -->
document.addEventListener('DOMContentLoaded', () => {
    changeTheme(currentTheme);
})

// TODO
function changeTheme(){
    // console.log(currentTheme);

    changePageTheme(currentTheme, currentTheme);

    // set the listener to change theme button
    const changeThemeButton = document.querySelector("#theme_change_button");

    // set to web page


    changeThemeButton.addEventListener('click', (event) => {
        let oldTheme = currentTheme
        console.log("change theme button clicked");

        // if ( currentTheme=="dark"){ // theme to light
        //     currentTheme = "light";
        // }
        // else { // theme to dark
        //     currentTheme = "dark";
        // }

        currentTheme = currentTheme=="dark" ? "light" : "dark";
        console.log(currentTheme);
        changePageTheme(currentTheme, oldTheme);
    });
}


// set theme to localstorage
function setTheme(theme){
    localStorage.setItem("theme", theme);
}


// get theme from local storage
function getTheme(){
    let theme = localStorage.getItem("theme");

    // if ( theme ) return theme;
    // return "light";

    return theme ? theme : "light";
}


// change current page theme
function changePageTheme(theme, oldTheme){


    // localstorage main update karenge
    setTheme(currentTheme);

    // remove the query selector
    document.querySelector("html").classList.remove(oldTheme);

    // set the current theme
    if ( oldTheme ) document.querySelector("html").classList.add(currentTheme);


    // change the text of button
    document
        .querySelector("#theme_change_button")
        .querySelector("span").textContent = theme == "light" ? "Dark" : "light";
}


// change page theme end