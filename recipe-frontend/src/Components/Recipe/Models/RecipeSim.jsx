import React from 'react';

class RecipeSim extends React.Component {

    constructor(props) {
        super(props);
        this.state = {}
    }

    render() {
        return (
            <>
                <tr>
                    <td className="text-center align-middle">{this.props.recipeSim.name}</td>
                    <td className="text-center align-middle">{this.props.recipeSim.type}</td>
                    <td className="text-center align-middle">{this.props.recipeSim.min}</td>
                </tr>
            </>
        );
    }

}

export default RecipeSim;