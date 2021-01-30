import React from "react"
import {withRouter} from "react-router-dom";
import {Button, Container, FormControl} from "react-bootstrap";
import {shoppingListService} from "../Service/shoppingList_service";
import ShoppingList from "./Models/ShoppingList";
import Form from "react-bootstrap/Form";
import Col from "react-bootstrap/Col";
import Row from "react-bootstrap/Row";

class ShoppingListHandler extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            allFoundLists: [],
            name: null,
            shoppingListShow: undefined
        }
    }

    async componentDidMount() {
        await this.getAllLists()
    }

    async getAllLists() {
        try {
            let result = await shoppingListService.getAllLists();
            if (result.ok) {
                this.setState({allFoundLists: result.response})
            } else {
                console.log("Något gick fel");
            }
        } catch (e) {
            console.log("Emm error?")
        }
    }

    async addNewList() {
        let body = {
            name: this.state.name,
            ingredientDTOS: []
        }
        try {
            let result = await shoppingListService.addNewList(body);
            if (result.ok) {
                await this.getAllLists();
            } else {
                console.log("Något gick fel");
            }
        } catch (e) {
            console.log("Emm error?")
        }
    }

    updateValue(event) {
        this.setState({name: event.target.value})
    }
    updateShowList(list){
        this.setState({shoppingListShow:list})
    }

    render() {
        return (
            <Container>
                <h2>Inköpslistor</h2>
                <Form>
                    <Row>
                        <Col>
                            <FormControl type={"text"} placeholder={"name"}
                                         onChange={this.updateValue.bind(this)}/>
                        </Col>
                        <Col>
                            <Button onClick={this.addNewList.bind(this)}>Ny lista</Button>
                        </Col>
                    </Row>
                </Form>

                {this.state.allFoundLists.map(list =>
                    <Container key={list.id}>
                        <Button style={{marginTop:20}}
                            onClick={this.updateShowList.bind(this,list)}>{list.name}</Button>
                        {/*<ShoppingList key={list.id} shoppingList={list} state={this.props.state}/>*/}
                    </Container>
                )}

                {this.state.shoppingListShow !== undefined ?
                    <ShoppingList key={this.state.shoppingListShow.id} shoppingList={this.state.shoppingListShow}
                                  state={this.props.state}/>
                    : ""
                }

            </Container>
        );
    }
}

export default withRouter(ShoppingListHandler);