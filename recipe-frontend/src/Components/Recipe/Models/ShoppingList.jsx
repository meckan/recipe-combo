import React from "react"
import {withRouter} from "react-router-dom";
import {Container, FormCheck} from "react-bootstrap";
import IngredientView from "./IngredientView";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import AddIngredientsList from "../AddIngredientsList";

class ShoppingList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            shoppingList:{
                name:"",
                ingredientDTOS: []
            },
            ingredientsInList: {
                taken: Boolean,
                ingredient: []
            }
        }
    }

    componentDidMount() {
        let shoppingList = this.props.shoppingList;
        for (let i = 0; i < shoppingList.ingredientDTOS.length; i++) {
            shoppingList[i] = {taken: false, ingredient: shoppingList[i]}
        }
        this.setState({shoppingList: shoppingList})
    }

    updateValueInArrayBoolean(id, input, event) {
        let answerList = this.state.shoppingList.ingredientDTOS
        let index = answerList.findIndex(x => x.id === id)
        let answer = answerList[index]
        answer[input] = event.target.checked
        answerList[index] = answer;
        this.setState({shoppingList: {ingredientDTOS:answerList}})
    }

    renderList(ingredient) {
        return (
            <Container key={ingredient.id + "Container"} style={{marginBottom: '0.8rem'}}>
                <Row>
                    <Col xs={1}>
                        <FormCheck type={"switch"} id={ingredient.id} label={" "} checked={ingredient.taken}
                                   onChange={this.updateValueInArrayBoolean.bind(this, ingredient.id, "taken")}
                                   key={ingredient.id + "Switch"}
                                   size={"lg"}
                        />
                    </Col>
                    <Col xs={"auto"}>
                        <IngredientView key={ingredient.id} grocery={ingredient} state={this.props.state}/>
                    </Col>
                </Row>
            </Container>
        )
    }

    render() {
        return (
            <Container key={"shoppingList"}>
                <h2>{this.props.shoppingList.name}</h2>
                {this.state.shoppingList.ingredientDTOS.map(grocery =>
                    this.renderList(grocery)
                )}


                <AddIngredientsList/>
            </Container>
        );
    }
}

export default withRouter(ShoppingList);