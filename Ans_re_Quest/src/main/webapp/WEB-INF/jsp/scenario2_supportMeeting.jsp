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
    this.image.src = "imgs/background.jpg";
    this.image.onload = loader;

  }

  this.render = function () {
    ctx.drawImage(this.image, this.x, this.y, this.width, this.height);
  }
}

var personImage = new function () {
  this.y = 100;

  this.loadImage = function () {
    this.image = new Image();
    this.image.src = "imgs/homeChara/face6.png";
    this.image.onload = loader;
  }

  this.render = function () {

    this.aspect = this.image.width / this.image.height;

    this.width = canvas.height * this.aspect ;
    this.height = canvas.height ;

    this.x = (canvas.width / 2 - this.width) * 8;

    ctx.drawImage(this.image, this.x, this.y, this.width, this.height);
  }
}

var personImage2 = new function () {
	  this.y = 200;

	  this.loadImage = function () {
	    this.image = new Image();
	    this.image.src = "imgs/man.png";
	    this.image.onload = loader;
	  }

	  this.render = function () {

	    this.aspect = this.image.width / this.image.height;

	    this.width = canvas.height * this.aspect ;
	    this.height = canvas.height ;

	    this.x = (canvas.width / 2 - this.width) * 1;

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
"「やあやあやあ、ちょっと解像度が荒い君が新たな挑戦者かい？」",
"「なんだアンタ？」",
"「僕たちは…そうだね、統合思念体とでも言っておこうか。取り敢えず君の敵ではないよ」",
"「つまり味方でもないってことだな」",
"「これは手厳しい。ま、否定はしないよ。結局此処にいる存在だからね」",
"「オレはここの『誰も知り得ない知識』ってのを狙いに来ているワケだが、それに手を貸すのか？」",
"「いやまあ、僕たちと似て非なるやつが上層階で出張っていてね。それがちょっと邪魔だと思っているんだよ」",
"「ソレを倒したらアンタらが窮地（きゅうち）に陥ったりする可能性はあるんじゃないか？」",
"「僕らは常に求知（きゅうち）であるから問題はないね」",
"「言葉遊びだな」",
"取り敢えず本気で敵意は持っていないようだ。使えるなら使ってやろう。"
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

var loader = Loader(4, function () {
  update();
});

bgImage.loadImage();
personImage.loadImage();
personImage2.loadImage();
messageBox.loadImage();

var sentenceIndex = 0;

function update() {
  ctx.clearRect(0, 0, canvas.width, canvas.height);

  if(sentenceIndex > sentences.length - 1) {
	    bgImage.render();
	    personImage.render();
	    personImage2.render();
	    messageBox.render();
	    setTimeout(function() {
	    	  history.back();
	    	}, 1000);  
    } else {
    bgImage.render();
    personImage.render();
    personImage2.render();
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