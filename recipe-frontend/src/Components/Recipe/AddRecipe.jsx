import React from 'react';

import {receptService} from "../Service/recept_service";
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Container from "react-bootstrap/Container";
import {Formik} from "formik";
import * as Yup from "yup";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import Image from "react-bootstrap/Image";
import Alert from "react-bootstrap/Alert";

import LoadingSpinner from "../Site/HelpFulComponents/LoadingSpinner";
import AddIngredientsList from "./AddIngredientsList";

const minMessage = "Too Short!";
const maxMessage = "Too Long!";
const requiredMessage = "Required";
const validationSchema = Yup.object().shape({
    name: Yup.string()
        .min(2, minMessage)
        .max(50, maxMessage)
        .required(requiredMessage),
});

class AddRecipe extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            recipe: {
                name: null,
                type: "Middag",
                recipeText: "",
                author: null,
                portions: 4,
                min: 60,

                picture: {
                    picArray: null,
                    pictureType: null,
                }
            },
            foodPic: null,
            foodPicType: null,
            foodPicData: null,
            hidePic: false,
            loading: false,

            showAlert:false,
            alertMessage:""
        };

        this.handleEventPicture = this.handleEventPicture.bind(this);
        this.getRecipeText = this.getRecipeText.bind(this);
    }

    getRecipeText(screenOrientation) {
        try {
            this.setState({loading: true}, async () => {
                let result = await receptService.getPicText(this.state.foodPicData, screenOrientation);
                if (result.ok) {
                    if (this.state.recipe.recipeText !== null) {
                        //this.setState({recipeText: this.state.recipe.recipeText + result.response})
                        let updateRecipe = this.state.recipe;
                        updateRecipe.recipeText = this.state.recipe.recipeText + result.response;
                        this.setState({updateRecipe})
                    } else {
                        //this.setState({recipeText: result.response})
                        let updateRecipe = this.state.recipe;
                        updateRecipe.recipeText = result.response;
                        this.setState({updateRecipe})
                    }
                    //this.forceUpdate();
                } else {
                    console.log("Något gick fel");
                }
                this.setState({loading: false})
            })
        } catch (e) {
            console.log("Emm error?")
        }
    }

    async addRecipe() {
        try {
            let result = await receptService.addRecipe(this.state.recipe, this.state.test)
            if (result.ok) {

                this.setState({showAlert:true,alertMessage:"Detta är ett test"})

                /*
                return (
                    <Alert className={"confirm-message"} variant={'success'}>
                        Successfully added recipe {this.state.recipe.name}!
                    </Alert>)

                 */
            }
        } catch (e) {
            return (
                <Alert className={"confirm-message"} variant={'danger'}>
                    {e + " API call failed"}
                </Alert>
            );
        }
    }

    handleEventPicture = (event) => {
        //this.setScreenOrientation();
        let file = event.target.files[0];
        this.setState({foodPicData: file})
        let reader = new FileReader();
        // Urlen är bara för att kunna skriva ut de. Men reader... behövs
        //let url = reader.readAsDataURL(file);
        reader.readAsDataURL(file);

        reader.onloadend = function (e) {
            this.setState({foodPic: [reader.result], foodPicType: file.type})
        }.bind(this)
    }

    handleEvent = (event, variabel) => {
        if (variabel === "picture") {
            let file = event.target.files[0];
            let reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onloadend = async function (e) {
                let updateRecipe = this.state.recipe;
                updateRecipe[variabel] = {
                    picArray: reader.result,
                    pictureType: file.type
                }
                this.setState({updateRecipe})
            }.bind(this)
        } else {
            let updateRecipe = this.state.recipe;
            updateRecipe[variabel] = event.target.value;
            this.setState({updateRecipe})
        }
    }

    getPictureCode() {
        return (
            <Container>
                {
                    this.state.foodPic !== null ? [
                            <Button key={"imageHideButton"} variant={"primary"} style={{marginTop: 20}}
                                    onClick={() => this.setState({hidePic: !this.state.hidePic})}>Hide / show pic</Button>,
                            window.screen.width >= 1280 ?
                                <Button key={"PcButton"} onClick={() => this.getRecipeText("pc")}
                                        style={{marginTop: 20, marginLeft: 20}}>Pc</Button>
                                : [
                                    <Button key={"PortButton"} onClick={() => this.getRecipeText("portrait")}
                                            style={{marginTop: 20, marginLeft: 20}}>Portrait</Button>,
                                    <Button key={"LandButton"} onClick={() => this.getRecipeText("landScape")}
                                            style={{marginTop: 20, marginLeft: 20}}>LandsScape</Button>
                                ],

                            this.state.loading ?
                                <LoadingSpinner/>
                                :
                                "",
                            this.state.hidePic ?
                                <Image key={"foodPic"} src={this.state.foodPic} rounded fluid/>
                                :
                                "",

                        ]
                        :
                        ""
                }
            </Container>
        )
    }

    getDropDowns() {
        return (
            <Row>
                <Form inline>

                    <Col xs={"auto"}>
                        <Form.Label>Portioner:</Form.Label>
                    </Col>
                    <Col xs={"auto"}>
                        <Form.Control as="select"
                                      custom
                                      defaultValue={4}
                                      onChange={(event) => this.handleEvent(event, "portions")}>
                            <option value={1}>1</option>
                            <option value={2}>2</option>
                            <option value={3}>3</option>
                            <option value={4}>4</option>

                            <option value={5}>5</option>
                            <option value={6}>6</option>
                            <option value={7}>7</option>
                            <option value={8}>8</option>

                        </Form.Control>
                    </Col>
                </Form>

                <Form inline>
                    <Col xs={"auto"}>
                        <Form.Label>Typ:</Form.Label>
                    </Col>
                    <Col xs={"auto"}>
                        <Form.Control as="select"
                                      custom
                                      defaultValue={"Middag"}
                                      onChange={(event) => this.handleEvent(event, "type")}>
                            <option value={"Middag"}>Middag</option>
                            <option value={"Lunch"}>Lunch</option>
                            <option value={"Frukost"}>Frukost</option>
                            <option value={"Dessert"}>Dessert</option>

                            <option value={"Snacks"}>Snacks</option>
                            <option value={"Dryck"}>Dryck</option>

                        </Form.Control>
                    </Col>
                </Form>


                <Form inline>
                    <Col xs={"auto"}>
                        <Form.Label>Tid:</Form.Label>
                    </Col>
                    <Col xs={"auto"}>
                        <Form.Control as="select"
                                      custom
                                      defaultValue={60}
                                      onChange={(event) => this.handleEvent(event, "min")}>
                            <option value={15}>15 min</option>
                            <option value={30}>30 min</option>
                            <option value={45}>45 min</option>
                            <option value={60}>60 min</option>
                            <option value={90}>90 min</option>

                        </Form.Control>
                    </Col>
                </Form>
            </Row>
        )
    }

    render() {
        return (
            <Formik
                initialValues={{
                    name: "",
                    type: "",
                    undecodedRecipeText: "",
                    recipeText: "",
                    author: "",
                    portions: "",
                    min: ""
                }}
                validationSchema={validationSchema}>
                {({
                      values, errors, touched,
                      handleChange, handleBlur
                  }) => (
                    <Container>
                        <h1>Nytt recept</h1>

                        {this.state.showAlert ? [
                                <Alert key={"alert"} className={"confirm-message"} variant={'success'} //trigger={["hover","focus","click"]}
                                //onChange={this.setState({showAlert:!this.state.showAlert})}
                                    onClick={this.setState({showAlert:!this.state.showAlert})}
                                >
                                    {this.state.alertMessage}
                                </Alert>
                            ] :
                            ""
                        }

                        <Container>
                            <Form.Group>
                                <Form.Row>
                                    <Form.Label column xs={"auto"}>Namn:</Form.Label>
                                    <Col >
                                        <Form.Control type="text" placeholder="name" style={{width: 150}}
                                            //onChange={(event) => this.setState({name:event.target.value})}
                                                      onChange={(event) => this.handleEvent(event, "name")}
                                        />
                                    </Col>
                                </Form.Row>
                                <Form.Row style={{marginTop: 5}}>
                                    <Form.Label column xs={"auto"}>Författare:
                                    </Form.Label>
                                    <Col >
                                        <Form.Control type="text" placeholder="name" style={{width: 150}}
                                            //onChange={(event) => this.setState({name:event.target.value})}
                                                      onChange={(event) => this.handleEvent(event, "author")}
                                        />
                                    </Col>
                                </Form.Row>
                            </Form.Group>

                        </Container>
                        <Container>
                            {this.getDropDowns()}
                        </Container>

                        <AddIngredientsList/>
                        <Form.Group>
                            <Container style={{paddingTop: 20}}>
                                <Form.File id={"upload"} label={"Bild på receptet"}
                                           accept={"image/*"}
                                           onChange={(event) => this.handleEventPicture(event)}/>
                            </Container>

                            {this.getPictureCode()}

                            <Container>
                                {this.state.recipeText !== null ? [
                                        <Form.Control key={"changeText"} as={"textarea"} style={{marginTop: 5}}
                                                      onChange={(event) => this.handleEvent(event, "recipeText")}
                                                      value={this.state.recipe.recipeText} rows={10}/>
                                    ]
                                    :
                                    ""
                                }
                            </Container>

                            <Container style={{paddingTop: 20}}>
                                <Form.File id={"upload"} label={"Bild på maten"}
                                           accept={"image/*"}
                                           onChange={(event) => this.handleEvent(event, "picture")}/>
                            </Container>

                        </Form.Group>
                        <Container>
                            <Button variant={"success"} type={"submit"} onClick={() => this.addRecipe()}>Spara
                                recept</Button>
                        </Container>
                    </Container>
                )}
            </Formik>
        );
    }
}

export default AddRecipe;