import React from "react";
import {Route, Redirect} from "react-router-dom";

/**
 * @author Andreas Olsson (aolsson8@kth.se)
 */
const PrivateRoute = ({ component: Component,state, ...rest }) => (
    <Route {...rest} render={(props) => (
        state.loggedIn === true
            ? <Component {...props} {...rest} />
            : <Redirect to={{
                pathname: '/',
                state: { from: props.location }
            }} />
    )} />
)
export default PrivateRoute;