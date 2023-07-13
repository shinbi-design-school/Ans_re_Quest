<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="head.jsp"/>
<title>Ans re Quest</title>
</head>
<body>
<div id="canvas_wrapper">
  <canvas id="myCanvas" width="1280" height="720"></canvas>
<script type="text/javascript">
var canvas = document.getElementById("myCanvas");
var ctx = canvas.getContext("2d");

var bgImage = new function () {
  this.x = 0
  this.y = 0;

  this.width = canvas.width;
  this.height = canvas.height;

  this.loadImage = function () {
    this.image = new Image();
    this.image.src = "imgs/scenario/本屋2.jpg";
    this.image.onload = loader;

  }

  this.render = function () {
    ctx.drawImage(this.image, this.x, this.y, this.width, this.height);
  }
}

var personImage = new function () {
  this.y = 200;

  this.loadImage = function () {
    this.image = new Image();
    this.image.src = "imgs/scenario/キレルミー_通常.png";
    this.image.onload = loader;
  }

  this.render = function () {

    this.aspect = this.image.width / this.image.height;

    this.width = canvas.height * this.aspect ;
    this.height = canvas.height ;

    this.x = (canvas.width / 2 - this.width) * 2.1;

    ctx.drawImage(this.image, this.x, this.y, this.width, this.height);
  }
}


var messageBox = new function () {
  this.x = 0;

  this.loadImage = function () {
    this.image = new Image();
    this.image.src = "imgs/box_horror_red.png";
    this.image.onload = loader;
  }

  this.render = function () {

    this.aspect = this.image.width / this.image.height;

    this.width = canvas.width;
    this.height = canvas.height / this.aspect;

    this.marginBottom = 20;
    this.y = canvas.height - this.height - this.marginBottom;

    ctx.drawImage(this.image, this.x, this.y, this.width, this.height);
  }
}

var myText = new function () {

	  this.render = function () {
	    var messageBoxInnerWidth = messageBox.width - 100;
	    var messageBoxPaddingTop = 48;
	    var messageBoxPaddingLeft = 28;

	    ctx.fillStyle = "#fff";
	    ctx.font = "40px serif";
	    ctx.textAlign = "left";

	    var s = "";
	    var sentenceArray = text.split("");

	    var kaigyouHeight = 0;

	    for (var i = 0; i < sentenceArray.length; i++) {
	      s += sentenceArray[i];
	      var textWidth = ctx.measureText(s).width;

	      if(textWidth > messageBoxInnerWidth) {
	        ctx.fillText(s, messageBoxPaddingLeft, messageBox.y + kaigyouHeight + messageBoxPaddingTop);

	        kaigyouHeight += 40;
	        s = "";
	      }
	    }

	    ctx.fillText(s, messageBoxPaddingLeft, messageBox.y + kaigyouHeight + messageBoxPaddingTop);
	  }
	}

var sentences = [
"「……」",
"店番らしき亡霊が一瞥をくれると硬貨受けをこちらに差し出して、そのまま読んでいた本に目を落とした。",
"どうやら必要なものは代金を置いて勝手に持っていけという事のようだ。"
];

var Loader = function (expectedCnt, callback) {
  var cnt = 0;
  return function() {
    cnt++;
    if(cnt == expectedCnt) {
      callback();
    }
  }
}

var loader = Loader(3, function () {
  update();
});

bgImage.loadImage();
personImage.loadImage();
messageBox.loadImage();

var sentenceIndex = 0;

function update() {
  ctx.clearRect(0, 0, canvas.width, canvas.height);

  if(sentenceIndex > sentences.length - 1) {
	    bgImage.render();
	    personImage.render();
	    messageBox.render();
	    setTimeout(function() {
	    	  history.back();
	    	}, 1000);  
    } else {
    bgImage.render();
    personImage.render();
    messageBox.render();

    myText.render(text=sentences[sentenceIndex]);
    sentenceIndex++;
  }
}

canvas.addEventListener("click", function(){
  update();
});
</script>
</div>
<jsp:include page="footer.jsp"/>

</body>
</html>