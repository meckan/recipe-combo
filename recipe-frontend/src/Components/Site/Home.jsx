import React from 'react';

/**
 * @author Andreas Olsson (aolsson8@kth.se)
 */
class Home extends React.Component {
    
    constructor(props) {
        super(props);
        this.state = this.props.state
    }
    
    render() {
        return (
            <h1>
                {this.props.state.username}
                {this.props.state.loggedIn ? (" är inloggad"):(" Du är inte inloggad")}
            </h1>
        );
    }
}

export default Home;
