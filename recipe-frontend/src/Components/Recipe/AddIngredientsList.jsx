import React from "react"
import {withRouter} from "react-router-dom";
import {Container} from "react-bootstrap";
import Popover from "react-bootstrap/Popover";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import ButtonToolbar from "react-bootstrap/ButtonToolbar";
import ButtonGroup from "react-bootstrap/ButtonGroup";
import Button from "react-bootstrap/Button";
import OverlayTrigger from "react-bootstrap/OverlayTrigger";
import {groceryService} from "../Service/grocery_service";
import Alert from "react-bootstrap/Alert";
import IngredientView from "./Models/IngredientView";

class AddIngredientsList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            ingredients: [],
            searchResults: [],

            newIngredients: {
                grocery:{
                    name:"",
                    type:""
                },
                measurement: "gram",
                amount: "",
            },
        }
    }

    componentDidMount() {
    }

    async getGrocerysSubString(subString) {
        try {
            let result = await groceryService.getBySubString(subString)
            if (result.ok) {
                this.setState({searchResults: result.response})
            }
        } catch (e) {
            return (
                <Alert className={"confirm-message"} variant={'danger'}>
                    {e + " API call failed"}
                </Alert>
            );
        }
    }

    async addNewGrocery(grocery){
        try {
            let result = await groceryService.addNewGrocery(grocery)
            if (result.ok) {
                this.setState({searchResults: result.response})
            }
        } catch (e) {
            return (
                <Alert className={"confirm-message"} variant={'danger'}>
                    {e + " API call failed"}
                </Alert>
            );
        }
    }

    getIngredientsOverLay() {
        return (
            <Popover id="add-Recipe">
                <Popover.Title as="h3">Ny ingredients</Popover.Title>
                <Popover.Content>
                    <Form>
                        <Form.Group>
                            <Row>
                                <Col xs={"auto"}>
                                    <Form.Label>Namn</Form.Label>
                                </Col>
                                <Col>
                                    <Form.Control value={this.state.newIngredients.grocery.name} onChange={(event) =>
                                        this.handleIngredientInputChange(event, "name")}/>
                                </Col>
                            </Row>
                            <Row>
                                <Col xs={"auto"}>
                                    <Form.Label>Type av ingreediens</Form.Label>
                                </Col>
                                <Col>
                                    <Form.Control value={this.state.newIngredients.grocery.type} onChange={(event) =>
                                        this.handleIngredientInputChange(event, "type")}/>
                                </Col>
                            </Row>
                        </Form.Group>
                        {this.state.searchResults.length > 0 ? [
                            <ButtonToolbar key={"emm"} className={"mb-3"} aria-label={"Toolbar with Button groups"}>
                                <ButtonGroup className={"mr-2"} aria-label={"First"}>
                                    {
                                        // TODO måste fungera så de sätter sig på ett bra sätt

                                        this.state.searchResults.map(g =>
                                            <Button key={g.id} variant={"secondary"} value={g.name}
                                                    style={{marginTop: 5}}
                                                    onClick={(event) =>
                                                        this.handleIngredientInputChange(event, "name", true)}>
                                                {g.name}
                                            </Button>
                                        )
                                    }
                                </ButtonGroup>
                            </ButtonToolbar>

                        ] : ""
                        }

                        <Form.Group as={Col}>
                            <Form.Label>Hur mycket</Form.Label>
                            <Form.Control onChange={(event) =>
                                this.handleIngredientInputChange(event, "amount")}/>
                        </Form.Group>

                        <Form.Group as={Col}>
                            <Form.Label>Enhet</Form.Label>
                            <Form.Control as={"select"} id={"selectMeasurement"}
                                          defaultValue={this.state.newIngredients.measurement}
                                          custom onChange={(event) =>
                                this.handleIngredientInputChange(event, "measurement")}>
                                <option value={"gram"}>Gram</option>
                                <option value={"dl"}>Deciliter</option>
                                <option value={"L"}>Liter</option>
                                <option value={"st"}>Stycken</option>
                                <option value={"msk"}>Matsked</option>
                                <option value={"tsk"}>Tesked</option>
                                <option value={"krm"}>Kryddmått</option>

                            </Form.Control>
                        </Form.Group>
                        <Button variant={"info"} onClick={() => this.addIngredients()}>Lägg till</Button>
                    </Form>
                </Popover.Content>
            </Popover>

        )
    }

    handleIngredientInputChange(event, variabel, alredyExsist) {
        if (variabel === "name" || variabel === "type") {
            if(variabel === "type"){
                let updateGrocery = this.state.newIngredients.grocery;
                updateGrocery["type"] = event.target.value
                this.setState({newIngredients:{grocery:updateGrocery}})
            }

            else if (alredyExsist) {
                let foundGrocery = this.state.searchResults.find(({name}) => name === event.target.value);

                let updateGrocery = this.state.newIngredients.grocery;
                updateGrocery["name"] = foundGrocery.name;
                updateGrocery["type"] = foundGrocery.type;
                this.setState({newIngredients:{grocery:updateGrocery}})

            } else {
                if (event.target.value.length > 1 && event.target.value.trim())
                    this.getGrocerysSubString(event.target.value);
                let updateGrocery = this.state.newIngredients.grocery;
                updateGrocery[variabel] = event.target.value;
                this.setState({newIngredients:{grocery:updateGrocery}})
            }
        } else {
            let updateIngredient = this.state.newIngredients;
            updateIngredient[variabel] = event.target.value;
            this.setState({updateIngredient})
        }
    }

    addIngredients() {
        let ingredients = this.state.ingredients;
        ingredients.push({
            grocery:{
                name: this.state.newIngredients.grocery.name,
                type:this.state.newIngredients.grocery.type
            },
            measurement: this.state.newIngredients.measurement,
            amount: this.state.newIngredients.amount,
            //type: this.state.newIngredients.type
        })
        this.setState({ingredients: ingredients})
        this.setState({
            newIngredients: {
                grocery:{
                    name: "",
                    type: ""
                },
                measurement: "gram",
                amount: "",
                //type: ""
            }
        })
        this.setState({searchResults: []})
        document.body.click();
    }

    removeIngredients(i) {
        let array = this.state.ingredients;
        let index = array.indexOf(i);
        if (index !== -1) {
            array.splice(index, 1);
            this.setState({ingredients: array})
        }
    }

    render() {
        return (
            <Container>
                <Container>
                    <OverlayTrigger overlay={this.getIngredientsOverLay()} trigger={"click"}
                                    placement={"auto-start"} rootClose>
                        <Button variant={"secondary"} style={{marginTop: 20, marginBottom: 20}}>Lägg
                            till
                            ingredients</Button>
                    </OverlayTrigger>
                </Container>

                <Container>
                    <b>Ingredienser:</b>
                    {
                        this.state.ingredients !== undefined ?
                            this.state.ingredients.map(i => [
                                <Container>
                                    <Row>
                                        <Col xs={"auto"}>
                                            <IngredientView key={i.id} grocery={i} state={this.props.state}/>
                                        </Col>
                                        <Col xs={"1"}>
                                            <Button size={"sm"} variant={"danger"}
                                                    onClick={() => this.removeIngredients(i)}>X</Button>
                                        </Col>
                                    </Row>
                                </Container>

                            ])
                            : ""
                    }
                </Container>
            </Container>
        );
    }
}

export default withRouter(AddIngredientsList);