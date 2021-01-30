import React, {Component} from 'react';
import {BrowserRouter as Router} from 'react-router-dom';

import SiteRouter from "./Components/Site/SiteRouter";
import NavBarTop from "./Components/Site/NavBarTop";

import Jumbotron from 'react-bootstrap/Jumbotron'
import Container from 'react-bootstrap/Container'

/**
 * @author Andreas Olsson (aolsson8@kth.se), Jonas Fredén-Lundvall (jonlundv@kth.se)
 */
class App extends Component {

    constructor(props) {
        super(props)
        this.state = {
            username: "",
            password: "",
            role: "",
            id: "",
            logId: "",
            registerView: false,
            loggedIn: false,
        }
    }

    render() {
        return (
            <Router>
                <NavBarTop state={this.state}/>
                <Jumbotron fluid>
                    <Container>
                        {
                            //<div className="col-sm-8 col-sm-offset-2">
                            <SiteRouter state={this.state}/>
                            //</div>
                        }
                    </Container>
                </Jumbotron>
            </Router>
        );
    }
}

export default App;

/*
function App() {
  return (
    <div className="App">

      <Navbar bg="light" expand="lg">
        <Navbar.Brand href="#home">React-Bootstrap</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="mr-auto">
            <Nav.Link href="#home">Home</Nav.Link>
            <Nav.Link href="#link">Link</Nav.Link>
            <NavDropdown title="Dropdown" id="basic-nav-dropdown">
              <NavDropdown.Item href="#action/3.1">Action</NavDropdown.Item>
              <NavDropdown.Item href="#action/3.2">Another action</NavDropdown.Item>
              <NavDropdown.Item href="#action/3.3">Something</NavDropdown.Item>
              <NavDropdown.Divider />
              <NavDropdown.Item href="#action/3.4">Separated link</NavDropdown.Item>
            </NavDropdown>
          </Nav>
          <Form inline>
            <FormControl type="text" placeholder="Search" className="mr-sm-2" />
            <Button variant="outline-success">Search</Button>
          </Form>
        </Navbar.Collapse>
      </Navbar>


      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;

 */
