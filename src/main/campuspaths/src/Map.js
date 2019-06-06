import React, {Component} from 'react';
import "./Map.css";
import Button from "./Button";

class Map extends Component {

  // NOTE:
  // This component is a suggestion for you to use, if you would like to.
  // It has some skeleton code that helps set up some of the more difficult parts
  // of getting <canvas> elements to display nicely.
  //
  // If you don't want to use this component, you're free to delete it.

  constructor(props) {
    super(props);
    this.canvasReference = React.createRef();
    this.backgroundImage = new Image();
    this.backgroundImage.onload = () => {
      this.drawBackgroundImage();
    };
    this.backgroundImage.src = "./campus_map.jpg";
  }

  drawBackgroundImage() {
    let canvas = this.canvasReference.current;
    let ctx = canvas.getContext("2d");
    if (this.backgroundImage.complete) { // This means the image has been loaded.
      canvas.width = this.backgroundImage.width;
      canvas.height = this.backgroundImage.height;
      ctx.drawImage(this.backgroundImage, 0, 0);
    }
  }

  drawLines(path) {
    let canvas = this.canvasReference.current;
    let ctx = canvas.getContext("2d");
    let pathCollection = path.path;
    this.drawBackgroundImage();

    pathCollection.forEach((element) => {
      let x1 = element.start.x;
      let y1 = element.start.y;
      let x2 = element.end.x;
      let y2 = element.end.y;
      ctx.beginPath();
      ctx.lineWidth = 25;
      ctx.moveTo(x1, y1);
      ctx.lineTo(x2, y2);
      ctx.strokeStyle = "red";
      ctx.lineCap = "round";
      ctx.stroke();
    });
  }

  findPathData() {
    if(this.props.source === "" || this.props.destination === "") {
      alert("Must Select a building for both start and destination");
    } else {
      fetch("http://localhost:4567/find-path?start=" + this.props.source + "&end=" + this.props.destination).then(
          (res) => {
            if (res.status !== 200) {
              throw Error("Response doesn't have 200 - OK status.")
            }
            return res.json();
          }
      ).then((resText) => {
            this.drawLines(resText);
          }
      );
    }
  }

  render() {
    // TODO: You should put a <canvas> inside the <div>. It has a className
    // that's set up to center the canvas for you. See Map.css for more details.
    // Make sure you set up the React references for the canvas correctly so you
    // can get the canvas object and call methods on it.
    return (
      <div className="canvasHolder">
        <canvas ref={this.canvasReference} />

        <div className="buttons">
          <div className="innerButton">
          <Button color="primary" onClick={() => {  // Search path finder
            this.findPathData();
          }} value="Search" />
          </div>
          <div className="innerButton">
          <Button color="secondary" onClick={() => {  // clear
            this.drawBackgroundImage();
            this.props.onClick();
          }} value="Clear" />
          </div>
        </div>

      </div>
    )
  }
}

export default Map;