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
    this.image.src = "imgs/青203.jpg";
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
    this.image.src = "imgs/boss.png";
    this.image.onload = loader;
  }

  this.render = function () {

    this.aspect = this.image.width / this.image.height;

    this.width = canvas.height * this.aspect ;
    this.height = canvas.height ;

    this.x = (canvas.width / 2 - this.width) + 650;

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

	    this.x = (canvas.width / 2 - this.width) * 0.8;

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
	{text:"「賢しい小僧だ。此処は知識の塔ぞ、恥を知れ」", speaker: "A"},
	{text:"「ハッ、陰気なだけの爺さん婆さん集合体が偉そうに。だいたい知識が大事ならもうちょっと換気に気をつけな」", speaker: "B"},
	{text:"「どこまでも驕傲な小僧よ、口の利き方というものを一から教えてやらないといけないか？」", speaker: "A"},
	{text:"「オレだって敬える相手なら相応の態度になるさ、テメエは違うってだけ」", speaker: "B"},
	{text:"「フン、表層で態度を変えるなどとは、外界の知性はそこまで落ちたか。やはり人類というものは愚民だらけだな」", speaker: "A"},
	{text:"「井の中の蛙大海を知らずとは言うが、塔の中の引きこもりはそりゃ外の世界を知らねえよなあ」", speaker: "B"},
	{text:"「もうよい、貴様も我らが知識の贄となれ！！」", speaker: "A"},
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

	    if(sentences[sentenceIndex].speaker === "A") {
	      ctx.globalAlpha = 1.0;
	      personImage.render();
	      ctx.globalAlpha = 0.5;
	      personImage2.render();
	    } else if(sentences[sentenceIndex].speaker === "B") {
	      ctx.globalAlpha = 0.5;
	      personImage.render();
	      ctx.globalAlpha = 1.0;
	      personImage2.render();
	    } else {
	      ctx.globalAlpha = 0.5;
	      personImage.render();
	      personImage2.render();
	    }

	    ctx.globalAlpha = 1.0;
	    messageBox.render();

	    myText.render(text=sentences[sentenceIndex].text);
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