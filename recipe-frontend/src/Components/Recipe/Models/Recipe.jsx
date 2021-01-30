import React from 'react';
import {receptService} from "../../Service/recept_service";
import Table from "react-bootstrap/Table";

import "../../../Css/index.css"
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Image from "react-bootstrap/Image";
import IngredientView from "./IngredientView";
import Col from "react-bootstrap/Col";
import {Card, ListGroup, ListGroupItem} from "react-bootstrap";

import Cookies from "universal-cookie/lib";


class Recipe extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            recipe: "",
            ingredients: []
        }
    }

    componentDidMount() {
        //if(!this.checkCookie(this.props.match.params.id)){
        this.getRecipe(this.props.match.params.id)
        //}
    }

    checkCookie() {
        console.log("Checkcookie")
        let cookies = new Cookies();
        let currentRecipe = cookies.get('current_recipe');
        console.log(currentRecipe)
        if (currentRecipe !== undefined) {
            console.log("Cookie was set")
            console.log(currentRecipe.id)
            console.log(this.props.match.params.id)
            if (currentRecipe.id === Number(this.props.match.params.id)) {
                console.log("Hittade current")
                this.setState({recipe: currentRecipe})
                return true;
            }
        }

        return false;
    }

    setCookie(recipe) {
        console.log("Set cookie")
        console.log(recipe)
        const cookies = new Cookies();
        // cookies.set('access_token',access_token, {sameSite:true,path:'/'})
        recipe.picture.picArray = null;
        cookies.set('current_recipe', {id: recipe.id, name: recipe.name}, {sameSite: true, path: '/'})
        console.log(cookies.get('current_recipe'))

    }

    async getRecipe(id) {
        try {
            let result = await receptService.getRecipeById(id);
            if (result.ok) {
                this.setState({recipe: result.response})
                this.setCookie(result.response)

            } else {
                console.log("Något gick fel");
            }
        } catch (e) {
            console.log("Emm error?")
        }
    }

    render() {
        return (
            <Container>
                <Row>
                    <Container>
                        <h1>
                            {this.state.recipe.name}
                        </h1>
                    </Container>
                    <Container>
                        <Table striped bordered hover size={"sm"} cellPadding={0}>
                            <thead>
                            <tr>
                                <th>Typ</th>
                                <th>Tid min</th>
                                <th>Gjort av</th>
                                <th>Port</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr style={{width: 0}}>
                                <td>{this.state.recipe.type}</td>
                                <td>{this.state.recipe.min}</td>

                                <td>{this.state.recipe.author}</td>
                                <td>{this.state.recipe.portions}</td>
                            </tr>
                            </tbody>
                        </Table>
                    </Container>
                    <Col sm={"auto"}>
                        <Row>
                        <Container>
                            <Card bg={"secondary"} style={{width: '18rem'}}>
                                <Card.Body>
                                    <Card.Title>Ingredienser:</Card.Title>
                                    {/*<ListGroup variant={"flush"}>*/}
                                    {this.state.recipe.ingredients !== undefined ?
                                        this.state.recipe.ingredients.map(i =>
                                            <IngredientView key={i.id} grocery={i} state={this.props.state}/>
                                        )
                                        : ""
                                    }
                                </Card.Body>
                            </Card>
                        </Container>
                        </Row>

                        <Row>
                            <Container>
                                {
                                    this.state.recipe.picture !== null ?
                                        this.state.recipe.picture !== undefined ?
                                            <Image style={{maxHeight: 250, marginTop: 20}}
                                                   src={this.state.recipe.picture.picArray} rounded fluid/>
                                            : ""
                                        : ""
                                }
                            </Container>
                        </Row>
                    </Col>
                    <Col>
                        <Container>
                            <Card bg={"secondary"} style={{minWidth:300}}>
                                <Card.Body >
                                    <Card.Title>Receptet:</Card.Title>
                                    {this.state.recipe.recipeText !== undefined ?
                                        this.state.recipe.recipeText.split('\n').map(row =>
                                            <Card.Text  key={row}>



                                                {row}
                                            </Card.Text>
                                        )
                                        : ""
                                    }
                                </Card.Body>
                            </Card>
                        </Container>
                    </Col>

                    {/*<Container>
                        {
                            this.state.recipe.picture !== null ?
                                this.state.recipe.picture !== undefined ?
                                    <Image style={{maxHeight: 250, marginTop: 20}}
                                           src={this.state.recipe.picture.picArray} rounded fluid/>
                                    : ""
                                : ""
                        }
                    </Container>*/}
                </Row>
            </Container>

        );
    }
}

export default Recipe;