import {GROCERY_SERVER_URL} from "./base_urls";


export const groceryService = {
    getBySubString,
    addNewGrocery
}

async function getBySubString(subString) {
    let url = GROCERY_SERVER_URL + "groceryBySubString?subString=" + subString
    let response = await fetch(url, getRequestOptions())

    if (response.ok) {
        let responseJson = await response.json();
        return {ok: true, status: response.status, response: responseJson};
    } else {
        return {ok: false, status: response.status, response: null}
    }
}

async function addNewGrocery(grocery){
    let url = GROCERY_SERVER_URL + "newGrocery"
    let response = await fetch(url,postRequestOptions(grocery))

    if (response.ok) {
        let responseJson = await response.json();
        return {ok: true, status: response.status, response: responseJson};
    } else {
        return {ok: false, status: response.status, response: null}
    }
}

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