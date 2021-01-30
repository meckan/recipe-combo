import React from "react";
import {Switch, Route} from "react-router-dom";

import LoginComponent from './Login'
//import Home from './Home'

import RecipeHandler from "../Recipe/RecipeHandler"
import Recipe from "../Recipe/Models/Recipe";
import AddRecipe from "../Recipe/AddRecipe";
import ShoppingListHandler from "../Recipe/ShoppingListHandler";

/**
 * @author Andreas Olsson (aolsson8@kth.se)
 */
export default function SiteRouter({state}) {

    return (
        <Switch>
            <Route exact path="/login">
                <LoginComponent state={state}/>
            </Route>
            <Route exact path="/">
                <RecipeHandler state={state}/>
            </Route>

            <Route path={"/addRecipe"}>
                <AddRecipe state={state}/>
            </Route>

            <Route path={"/shoppingList"}>
                <ShoppingListHandler state={state}/>
            </Route>

            <Route path="/recipe/:id" name="recipe" component={Recipe}/>

            <Route path="*" component={() => "404 NOT FOUND"}/>
        </Switch>
    );
}