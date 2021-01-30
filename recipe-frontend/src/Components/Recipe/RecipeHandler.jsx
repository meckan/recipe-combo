import React from 'react';
import {receptService} from "../Service/recept_service";
import RecipeSim from "./Models/RecipeSim";

import Button from "react-bootstrap/Button";
import Popover from "react-bootstrap/Popover";
import Table from "react-bootstrap/Table";
import OverlayTrigger from "react-bootstrap/OverlayTrigger";
import Row from "react-bootstrap/Row";
import Container from "react-bootstrap/Container";
import Form from "react-bootstrap/Form";
import Col from "react-bootstrap/Col";

class RecipeHandler extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            recipes: []
        };

        this.getAllSim = this.getAllSim.bind(this);
        this.getRecipe = this.getRecipe.bind(this);
    }

    componentDidMount() {
        this.getAllSim()
    }


    async getAllSim() {
        try {
            let result = await receptService.getAllRecipesSim();
            if (result.ok) {
                this.setState({recipes: result.response})
            } else {
                console.log("Något gick fel");

            }
        } catch (e) {
            console.log("Emm error?")
        }
    }

    async getRecipe(id) {
        try {
            let result = await receptService.getRecipeById(id);
            if (result.ok) {
                this.setState({recipe: result.response})
                //this.forceUpdate();
            } else {
                console.log("Något gick fel");
            }
        } catch (e) {
            console.log("Emm error?")
        }
    }

    async getRecipesBySubString(event){
        if (event.target.value.length > 1 && event.target.value.trim()){
            try {
                let result = await receptService.getRecipeBySubStringSim(event.target.value);
                if (result.ok) {
                    this.setState({recipes: result.response})
                    //this.forceUpdate();
                } else {
                    console.log("Något gick fel");
                }
            } catch (e) {
                console.log("Emm error?")
            }
        }
    }

    getInformationButton = (recipeSim) => {
        return (
            <td className="text-center align-left">
                <OverlayTrigger
                    rootClose={true}
                    trigger={"click"}
                    //trigger={["click","hover", "focus"]}
                    placement="auto-start"
                    overlay={this.getOverlayRecipeSim(recipeSim)}>
                    <Button  style={{margin: 5}} variant={"info"}>{recipeSim.name}</Button>
                </OverlayTrigger>
            </td>
        )
    }

    getOverlayRecipeSim = (recipe) => {
        return (
            <Popover id="popover-basic">
                <Popover.Title as="h3">{recipe.name}</Popover.Title>
                <Popover.Content>
                    <Table striped bordered hover responsive>
                        <thead>
                        <tr>
                            <th>Namn</th>
                            <th>Type</th>
                            <th>Min</th>
                        </tr>
                        </thead>
                        <tbody>
                        <RecipeSim key={recipe.id} recipeSim={recipe} state={this.props.state}/>
                        </tbody>
                    </Table>

                    <Button style={{margin: 5}}>Lägg till inköpslista (fungerar ej än)</Button>
                    <Button style={{margin: 5}} href={"/recipe/" + recipe.id}>Laga nu</Button>
                </Popover.Content>
            </Popover>
        )
    }

    render() {
        return (
            <Container>
                <Row style={{paddingBottom: 10}}>
                    <Col xs={"auto"}>
                        <Button onClick={() => this.getAllSim()} variant="primary" type="submit">Get all</Button>{' '}
                    </Col>
                    <Form.Label column xs={"auto"}>Recept namn:</Form.Label>
                    <Col xs={"auto"}>
                        <Form.Control type="text" placeholder="Sök recept" style={{width: 150}}
                                      onChange={(event) => this.getRecipesBySubString(event)}/>
                    </Col>
                </Row>

                <Row md={4}>
                    <Table striped bordered hover responsive size="sm">
                        <thead>
                        <tr>
                            <th>Recept</th>
                        </tr>
                        </thead>
                        <tbody>
                        {this.state.recipes.map(recipe =>
                            <tr key={recipe.id}>
                                {this.getInformationButton(recipe)}
                            </tr>
                        )}
                        </tbody>
                    </Table>
                </Row>
            </Container>
        );
    }
}

export default RecipeHandler;