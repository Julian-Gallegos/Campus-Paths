/*
 * Copyright ©2019 Hal Perkins.  All rights reserved.  Permission is
 * hereby granted to students registered for University of Washington
 * CSE 331 for use solely during Spring Quarter 2019 for purposes of
 * the course.  No other use, copying, distribution, or modification
 * is permitted without prior written consent. Copyrights for
 * third-party components of this work must be honored.  Instructors
 * interested in reusing these course materials should contact the
 * author.
 */

/* A simple grid with a variable size */
/* Most of the assignment involves changes to this class */

import React, {Component} from 'react';
import Button from './Button'

class Grid extends Component {
  constructor(props) {
    super(props);
    this.canvasReference = React.createRef();
  }

  componentDidMount() {
    this.redraw();
  }

  componentDidUpdate() {
    this.redraw()
  }

  redraw = () => {
    let ctx = this.canvasReference.current.getContext('2d');
    ctx.clearRect(0, 0, this.props.width, this.props.height);
    var background = new Image();
    background.onload = () => {
      ctx.drawImage(background,0,0);
      let coordinates = this.getCoordinates();
      coordinates.forEach(coordinate => {
        this.drawCircle(ctx, coordinate);
      });
    }
    background.src = "http://localhost:3000/image.jpg"
  };

  getCoordinates = () => {

    let arr = arr[whatever];
    for (let x = (size+1)/400; x < width; x += (size+1)/400) {
      for (let y = x; y < width; y += (size+1)/400) {
        arr.add([x, y]);
      }
    }

    return arr;  //[
        // Something like (size+1) / 400
        // left side seems to be 0, and top also seems to be 0
        // So for each node, add previous node in whatever x,y combo and add (size+1) / 400

      //[100, 100], [100, 200], [100, 300],
      //[200, 100], [200, 200], [200, 300],
      //[300, 100], [300, 200], [300, 300]
    //];
  };

  drawCircle = (ctx, coordinate) => {
    ctx.beginPath();
    ctx.arc(coordinate[0], coordinate[1], 20 / this.props.size, 0, 2 * Math.PI);
    ctx.fill();
  };

  render() {
    return (
      <div id="canvas-div">
        <canvas ref={this.canvasReference} width={this.props.width} height={this.props.height} />
        <div className="center-text">Current Grid Size: {this.props.size}</div>
        <Button color="primary" onClick={() => { console.log('onClick'); }} value="Draw" />
        <Button color="secondary" onClick={() => { console.log('onClick'); }} value="Clear" />
      </div>
    );
  }
}

export default Grid;
