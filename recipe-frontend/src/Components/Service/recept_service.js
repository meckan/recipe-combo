import {RECIPE_SERVER_URL} from "./base_urls";

export const receptService = {
    getAllRecipesSim,
    getRecipeById,
    getPicText,
    addRecipe,
    getRecipeBySubStringSim
}

async function getAllRecipesSim() {
    let url = RECIPE_SERVER_URL + "getAllRecipesSim"
    let response = await fetch(url, getRequestOptions())

    if (response.ok) {
        let responseJson = await response.json();
        return {ok: true, status: response.status, response: responseJson};
    } else {
        return {ok: false, status: response.status, response: null}
    }
}

async function getRecipeById(id) {
    let url = RECIPE_SERVER_URL + "getRecipeById?id=" + id

    let response = await fetch(url, getRequestOptions())
    //console.log(response);
    if (response.ok) {
        let responseJson = await response.json();
        //console.log(responseJson)
        return {ok: true, status: response.status, response: responseJson};
    } else {
        return {ok: false, status: response.status, response: null}
    }
}

async function getPicText(file, orientation) {

    let formdata = new FormData();
    formdata.append("file", file);

    let requestOptions = {
        method: 'POST',
        body: formdata,
        redirect: 'follow'
    };

    let url = RECIPE_SERVER_URL + "testPic?orientation=" + orientation;

    let response = await fetch(url, requestOptions)

    console.log(response);
    if (response.ok) {
        let responseJson = await response.json();
        return {ok: true, status: response.status, response: responseJson.recipeText};
    } else {
        console.log("Else")
        return {ok: false, status: response.status, response: null}
    }
}

async function addRecipe(recipe) {
    let url = RECIPE_SERVER_URL + "/addRecipe";
    let response = await fetch(url, postRequestOptions(recipe));

    if (response.ok) {
        let responseJson = await response.json();
        return {ok: true, status: response.status, response: responseJson};
    } else {
        return {ok: false, status: response.status, response: null}
    }
}

async function getRecipeBySubStringSim(subString) {
    let url = RECIPE_SERVER_URL + "getRecipeBySubStringSim?subString=" + subString

    let response = await fetch(url, getRequestOptions())
    if (response.ok) {
        let responseJson = await response.json();
        return {ok: true, status: response.status, response: responseJson};
    } else {
        return {ok: false, status: response.status, response: null}
    }
}


//@formatter:off
function getRequestOptions(){
    return {
        method: 'GET',
        redirect: 'follow'
    };
}
function postRequestOptions(body){
    let myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    return {
        method: 'POST',
        headers: myHeaders,
        body: JSON.stringify(body),
        redirect: 'follow'
    };
}
//@formatter:on