import {SHOPPING_LIST_SERVER_URL} from "./base_urls";

export const shoppingListService = {
    getAllLists,
    addNewList

}

async function getAllLists(){
    let url = SHOPPING_LIST_SERVER_URL + "getAllLists"
    let response = await fetch(url, getRequestOptions())

    if (response.ok) {
        let responseJson = await response.json();
        return {ok: true, status: response.status, response: responseJson};
    } else {
        return {ok: false, status: response.status, response: null}
    }
}

async function addNewList(body){
    let url = SHOPPING_LIST_SERVER_URL + "addShoppingList"
    let response = await fetch(url,postRequestOptions(body))

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