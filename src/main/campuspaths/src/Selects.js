import React, {Component} from 'react';

import MenuItem from '@material-ui/core/MenuItem';
import Select from '@material-ui/core/Select';
import FormHelperText from "@material-ui/core/FormHelperText";

class Selects extends Component {

  createTable(){
      let table = [];
      let buildings = this.props.buildings;
      for (let key in buildings){

          if (buildings.hasOwnProperty(key)) {
            table.push(<MenuItem value={key}>{buildings[key]}</MenuItem>);
          }
      }
      return table;
  }

  render() {
    return (
      <div>
        <Select
          onChange={this.props.onChange}
          value={this.props.value}
          name="building"
          displayEmpty={true}
          autoWidth={true}
        >
          <MenuItem value="">
              <em>None</em>
          </MenuItem>
            {this.createTable()}
        </Select>
        <FormHelperText>{this.props.helperText}</FormHelperText>
      </div>
    )
  }
}

export default Selects;