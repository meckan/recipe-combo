import React from 'react';
import Spinner from "react-bootstrap/Spinner";

const LoadingSpinner = () => (
    <Spinner animation="border" role="status"  style={{padding:15}}>
        <span className="sr-only">Loading...</span>
    </Spinner>
);

export default LoadingSpinner;