/* The main component that holds all the other elements of the React App */

import React, {Component} from 'react';
import Map from "./Map";
import Selects from "./Selects";
import "./MainContainer.css"

class MainContainer extends Component {
  constructor(props) {
    super(props);
    this.state = {
        building1: "",
        building2: "",
        buildings: "NO REQUEST RESULT"
    };
      this.fetchBuildings();
  }

  fetchBuildings() {
      fetch("http://localhost:4567/buildings").then((res) => {
          if (res.status !== 200) {
              throw Error("The server could not process the request.")
          }
          return res.json();
      }
      ).then((resJSON) => {
          this.setState({buildings: resJSON});
          }
      );
  }

  render() {
    return (
      <div>
          <div className="title"> J-Men Maps Incorporated </div>
        <Map source={this.state.building1} destination={this.state.building2} onClick={(e) => {
            this.setState({building1: ""});
            this.setState({building2: ""});
        }}/>
        <div className="Selections">
          <div className="dropdown">
            <Selects value={this.state.building1} buildings={this.state.buildings} helperText={"Start"} onChange={(e) => {
              this.setState({building1: e.target.value});
          }} />
         </div>
          <div className="dropdown">
              <Selects value={this.state.building2} buildings={this.state.buildings} helperText={"Destination"} onChange={(e) => {
                  this.setState({building2: e.target.value});
              }} />
          </div>
        </div>
      </div>
    );
  }
}

export default MainContainer;
