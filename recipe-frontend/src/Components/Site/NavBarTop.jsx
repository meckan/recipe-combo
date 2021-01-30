import React from 'react';
import Navbar from 'react-bootstrap/Navbar'
import Nav from 'react-bootstrap/Nav'
import Cookies from "universal-cookie/lib";

/**
 * @author Jonas Fredén-Lundvall (jonlundv@kth.se)
 */
class NavBarTop extends React.Component {

    constructor(props) {
        super(props);
        this.state = {};
    }

    signout = () => {
        this.setState({
            loggedIn: false,
            username: "",
            role: ""
        })
        window.location = "/"
    }


    render() {

        var cookies = new Cookies();
        let currentRecipe = cookies.get('current_recipe');

        return (
            <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
                <Navbar.Brand href={"/"}>Recept Samling</Navbar.Brand>

                <Nav className="mr-auto">
                    <Nav.Link href={"/addRecipe"}>Nytt recept</Nav.Link>
                </Nav>

                <Nav className="mr-auto">
                    <Nav.Link href={"/shoppingList"}>Inköps Lista</Nav.Link>
                </Nav>

                {currentRecipe !== undefined?
                    <Nav className="mr-auto">
                        <Nav.Link href={"/recipe/"+ currentRecipe.id}>{currentRecipe.name}</Nav.Link>
                    </Nav>: " "

                }

                {/*<Navbar.Toggle aria-controls="responsive-navbar-nav"/>
                <Navbar.Collapse id="responsive-navbar-nav">


                        <Form inline>
                            <FormControl type="text" placeholder="Search" className="mr-sm-2"/>
                            <Button variant="outline-info">Sök recept</Button>
                        </Form>

                </Navbar.Collapse>*/}
            </Navbar>
        )
    }
}

export default NavBarTop;