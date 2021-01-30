import React from 'react';
import "../../../Css/index.css"

class Grocery extends React.Component {

    constructor(props) {
        super(props);
        this.state = {}
    }

    componentDidMount() {
    }

    render() {
        return (
            <>
                {this.props.grocery.grocery.name}{' '}
                {/*{this.props.grocery.type}*/}
            </>
        );
    }
}

export default Grocery;