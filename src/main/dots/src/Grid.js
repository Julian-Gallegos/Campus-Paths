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

  drawLines() {
    let ctx = this.canvasReference.current.getContext('2d');
    let strArr = this.props.edges.split("\n");  // Split edges by newlines
    for(let i = 0; i < strArr.length; i++) {
      if (!strArr[i].includes(".")) {
        let lineArr = strArr[i].split(" ");  // Split each line by spaces

        // If the split line does not contain exactly 3 strings, do not draw it.
        if (lineArr.length === 3) {

          // Split the "x1,y1" and "x2,y2" strings to "x1" "y1" and "x2" "y2"
          let coord1 = lineArr[0].split(",");
          let coord2 = lineArr[1].split(",");
          if (coord1.length === 2 && coord2.length === 2 &&
              -1 < coord1[0] < this.props.size &&
              -1 < coord1[1] < this.props.size &&
              -1 < coord2[0] < this.props.size &&
              -1 < coord2[1] < this.props.size) {  // check if invalid input
            let scalar = (this.props.width) / (this.props.size + 1);
            ctx.beginPath();
            ctx.lineWidth = "1";
            ctx.moveTo((Number(coord1[0]) + 1) * scalar, (Number(coord1[1]) + 1) * scalar);
            ctx.lineTo((Number(coord2[0]) + 1) * scalar, (Number(coord2[1]) + 1) * scalar);
            ctx.strokeStyle = lineArr[2];
            ctx.stroke();
          }
        }
      }
    }
  };

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
    };
    background.src = "http://localhost:3000/image.jpg"
  };

  getCoordinates = () => {

    let arr = [];
    for (let x = 1; x <= this.props.size; x++) {
      for (let y = 1; y <= this.props.size; y++) {
        let y1 = y * (this.props.width)/(this.props.size+1);
        let x1 = x * (this.props.width)/(this.props.size+1);
        arr.push([x1, y1]);
      }
    }

    return arr;
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
        <Button color="primary" onClick={() => {  // draw
          this.drawLines();
        }} value="Draw" />
        <Button color="secondary" onClick={() => {  // clear
          this.redraw();
        }} value="Clear" />
      </div>
    );
  }
}

export default Grid;
