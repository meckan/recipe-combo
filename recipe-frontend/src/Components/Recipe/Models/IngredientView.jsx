import React from "react"
import {withRouter} from "react-router-dom";
import {Container} from "react-bootstrap";
import Grocery from "./Grocery";

class IngredientView extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            grocery:[]
        }
    }

    componentDidMount() {
    }

    render() {
        return (
            <Container>
                <Grocery grocery={this.props.grocery} state={this.props.state}/>
                {this.props.grocery.amount > 0?
                    this.props.grocery.amount + ' ': " "
                }
                {this.props.grocery.measurement}{' '}
            </Container>
        );
    }
}

export default withRouter(IngredientView);