import React, {Component} from 'react';

import MenuItem from '@material-ui/core/MenuItem';
import Select from '@material-ui/core/Select';
import FormHelperText from "@material-ui/core/FormHelperText";

class Selects extends Component {

  constructor(props) {
    super(props);

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
          <MenuItem value={10}>Ten</MenuItem>
          <MenuItem value={20}>Twenty</MenuItem>
          <MenuItem value={30}>Thirty</MenuItem>
        </Select>
        <FormHelperText>{this.props.helperText}</FormHelperText>
      </div>
    )
  }
}

export default Selects;