import React from 'react';
import '../../Css/App.css';
import {BrowserRouter as Router, Link, withRouter} from "react-router-dom";

import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form"
import Nav from "react-bootstrap/Nav"

/**
 * @author Gustav Kavtaradze (guek@kth.se), Andreas Olsson (aolsson8@kth.se), Björn Andsten (bandsten@kth.se), Jonas Fredén-Lundvall (jonlundv@kth.se), Erik Wikzén (wikzen@kth.se)
 */
class LoginComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {}

        this.logIn = this.logIn.bind(this);
        this.usernameInput = React.createRef()
        this.passwordInput = React.createRef()

    }

    async logIn(e) {
        e.preventDefault()

        //let username = this.usernameInput.current.value
        //let password = this.passwordInput.current.value


    }

    render() {

        if (this.props.state.loggedIn) {
            var renderMenu = ""
            console.log(this.props.state.role)
            switch (this.props.state.role) {
                case "USER":
                    renderMenu = <>
                        <Nav.Item>
                            <Nav.Link as={Link} to="/rooms">Book a room</Nav.Link>
                        </Nav.Item>
                    </>
                    break;
                default:
                    renderMenu = ""

            }
            return (
                <Router>
                    <h2>Welcome!</h2>
                    <Form>
                        <Nav variant="tabs">
                            {renderMenu}
                        </Nav>
                    </Form>
                </Router>
            );
        } else {
            return (
                <>
                    <Form onSubmit={this.logIn.bind(this)}>
                        <h2>Sign in</h2>
                        <Form.Group>
                            <Form.Label>Username</Form.Label>
                            <Form.Control type="text" ref={this.usernameInput} name="username"
                                          placeholder="Enter username"/>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Password</Form.Label>
                            <Form.Control type="password" ref={this.passwordInput} name="password"
                                          placeholder="Enter password"/>
                        </Form.Group>
                        <Form.Group>
                            <Button variant="primary" type="submit">
                                Submit
                            </Button>
                        </Form.Group>
                    </Form>
                </>
            );
        }
    }
}

export default withRouter(LoginComponent);

/*
export async function refreshAccessToken(state) {
    let refresh_token = state.refresh_token
    let result = await userService.refreshUserAccessToken(refresh_token)
    if (result.ok) {
        state.access_token = result.response.access_token;
        state.refresh_token = result.response.refresh_token;
        return true;
    } else {
        // TODO: denna ska redirecta till login typ
        console.log("Emm nope")
        return(
            <Router>
                <LoginComponent/>
            </Router>
        )
    }
}
 */