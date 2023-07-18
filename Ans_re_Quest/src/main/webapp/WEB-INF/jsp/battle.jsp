<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Boolean isUsed5050 = (Boolean) request.getAttribute("isUsed5050");
if (isUsed5050 == null) {
    isUsed5050 = false;
}

Boolean isUsedSkip = (Boolean) request.getAttribute("isUsedSkip");
if (isUsedSkip == null) {
    isUsedSkip = false;
}

Boolean isNoHint = (Boolean) request.getAttribute("isNoHint");
if (isNoHint == null) {
    isNoHint = false;
}
int limitTime = (int)request.getAttribute("limitTime");
int quantity5050 = (int)request.getAttribute("quantity5050");
int quantitySkip = (int)request.getAttribute("quantitySkip");

String enemyImage = (String) request.getAttribute("enemyImage");
String collectAnswer = (String) request.getAttribute("collectAnswer");
%>    
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="head.jsp"/>
    <title>Battle Page</title>
	<link rel="stylesheet" type="text/css" href="css/particles.css">    
	<script src="https://cdnjs.cloudflare.com/ajax/libs/three.js/110/three.min.js"></script>
	<script>
	document.addEventListener("DOMContentLoaded", function() {
	var playerImage = document.getElementById('player');
	var enemyImage = document.getElementById('enemy');
	}); 
	// アニメーションを適用する関数
		function applyAnimation(image) {
		  var container = document.createElement('div');
		  container.style.position = 'relative';
		  container.style.width = image.width*2 + 'px';
		  container.style.height = image.height*2 + 'px';
	
		  var renderer = new THREE.WebGLRenderer({ alpha: true });
		  renderer.setSize(image.width*2, image.height*2);
		  container.appendChild(renderer.domElement);
	
		  var scene = new THREE.Scene();
		  var camera = new THREE.PerspectiveCamera(75, image.width / image.height, 0.1, 1000);
		  camera.position.z = 5;
	
		  var texture = new THREE.TextureLoader().load(image.src, function () {
			    texture.needsUpdate = true;
			    texture.image.width = image.width;
			    texture.image.height = image.height;
			});
		  texture.minFilter = THREE.LinearFilter;
			
		  var geometry = new THREE.PlaneGeometry(1, 1);
		  var material = new THREE.MeshBasicMaterial({ map: texture });
	
		  var mesh = new THREE.Mesh(geometry, material);
		  scene.add(mesh);
	
		  var clock = new THREE.Clock();
	
		  function render() {
		    var delta = clock.getDelta();
		    mesh.rotation.x += 1.5 * delta;
		    mesh.rotation.y += 1.5 * delta;
	
		    renderer.render(scene, camera);
		    requestAnimationFrame(render);
		  }
	
		  render();
	
		  image.parentNode.replaceChild(container, image);
		}
	</script>
</head>
<body>
<div id="wrapper">
<div id="particles-js"></div>
<div id="totalMenu">
<div id="bgi">
    <h1><%= request.getAttribute("towerName") %>   <%= request.getAttribute("currentFloor") %>階</h1>
    <div class="enemy">
    	<p><%=request.getAttribute("enemyName") %></p>
    	<img alt="enemy" src="data:image/png;base64, <%= enemyImage %>" id="enemy">
    	<p id="enemy">HP: <progress id="file" max="<%=request.getAttribute("enemyMaxHP") %>" value="<%= request.getAttribute("enemyHP") %>"></progress><%= request.getAttribute("enemyHP") %></p>
    </div>
    <div class="player">
    	<p><%=request.getAttribute("Name") %></p>
	    <img alt="PLface" src="imgs/face.png" id="player">
	    <p id="player">HP:  <input type="range" id="inputSlider" min="0" max="<%=request.getAttribute("playerMaxHP") %>" value="<%= request.getAttribute("playerHP") %>" /><%= request.getAttribute("playerHP") %></p>
    </div>
    
<!-- 制限時間用↓ -->
<script type="text/javascript">
let limitTime = <%= limitTime %>; // 制限時間の初期値

//1秒ごとに呼び出される関数
function decreaseValue() {
if (limitTime > 0) {
 limitTime--; // 制限時間を1つ減らす
 console.log(limitTime); // 減らした値を表示する（コンソールに出力）
 document.getElementById("limitTime").textContent = limitTime; // 制限時間を表示
} else {
 clearInterval(interval); // タイマーを停止する
 // ボタンを無効化
 var buttons = document.getElementsByTagName("button");
 for (var i = 0; i < buttons.length; i++) {
   buttons[i].disabled = true;
 }
 document.getElementById("selectedChoice").value = "limitOver";

 // 1秒待機後にフォームを送信
 setTimeout(function() {
   document.getElementById("flex-questions").submit();
 }, 1000);
}
}

//1秒ごとに関数を呼び出すタイマーをセット
var interval = setInterval(decreaseValue, 1000);
</script>
<!-- 制限時間用↑ -->

    
    <div class="questions">
    	<p>第<%= request.getAttribute("currentQuizNo") %>問/全<%= request.getAttribute("totalQuizCount") %>問</p>
    	<p>問題: <%= request.getAttribute("questionText") %></p>
    	<p>制限時間:<span id="limitTime"><%=request.getAttribute("limitTime") %></span></p>
    </div>
    
<script>
  // ボタンがクリックされた時の処理仮
  function handleClick(button) {
    // ボタンを無効化
    var buttons = document.getElementsByTagName("button");
    for (var i = 0; i < buttons.length; i++) {
      buttons[i].disabled = true;
    }
	var playerImage = document.getElementById('player');
	var enemyImage = document.getElementById('enemy');
    if (button.value === "<%= collectAnswer %>") {
    	// 正解だった時の処理
    	applyAnimation(enemyImage);
    	
      } else {
    		applyAnimation(playerImage);
            
        // 不正解だった時の処理
      }
    
    // 押されたボタンの値をフォームの隠しフィールドに設定
    document.getElementById("selectedChoice").value = button.value;//

    // 1秒待機後にフォームを送信
    setTimeout(function() {
      document.getElementById("flex-questions").submit();
    }, 1000);
  }
</script>
    
<form method="post" action="battle" id="flex-questions">
  <!-- ボタンのクリック時にJavaScriptの処理を呼び出すように設定 -->
  <button type="button" name="choice" id="box" value="<%= (String)request.getAttribute("choice1") %>" onclick="handleClick(this)"><%= request.getAttribute("choice1") %></button><br>
  <button type="button" name="choice" id="box" value="<%= (String)request.getAttribute("choice2") %>" onclick="handleClick(this)"><%= request.getAttribute("choice2") %></button><br>
  <button type="button" name="choice" id="box" value="<%= (String)request.getAttribute("choice3") %>" onclick="handleClick(this)"><%= request.getAttribute("choice3") %></button><br>
  <button type="button" name="choice" id="box" value="<%= (String)request.getAttribute("choice4") %>" onclick="handleClick(this)"><%= request.getAttribute("choice4") %></button><br>
  <input type="hidden" name="isUsed5050" value="false">
  <input type="hidden" name="choice" id="selectedChoice" value="">
</form>
	
    <p></p>
    <div class="helpItems">
    <!-- AI表示script -->
<script>
    function startTypewriter() {
        var text = "<%= request.getAttribute("aiAnswer") %>"; // 表示したいテキスト
        var speed = 100; // 文字を表示する速度（ミリ秒単位）

        var i = 0;
        function typeWriter() {
            if (i < text.length) {
                document.getElementById("typewriter").textContent += text.charAt(i);
                i++;
                setTimeout(typeWriter, speed);
            }
        }

        // タイプライターエフェクトを開始する
        typeWriter();
    }
    function hideButtonText() {
        var aiButton = document.getElementById("aiButton");
        aiButton.style.display = "none";
        
        var form = document.getElementById("flex-questions");
        var isUsedAiInput = document.createElement("input");
        isUsedAiInput.type = "hidden";
        isUsedAiInput.name = "isUsedAi";
        isUsedAiInput.value = "true";
        form.appendChild(isUsedAiInput);
    }
</script>
	<button onclick="startTypewriter(); hideButtonText()" id="aiButton">AIに聞く</button>
	<div id="typewriter"></div>
	
    <!--  5050アイテム0または使用済みなら隠す -->
    <% if(isUsed5050){ %>
	<p>5050使用済み<p>
	<%} %>    
	<% if(!(isNoHint || isUsed5050 || isNoHint || (quantity5050 <= 0))){ %>
<form method="post" name="form1" action="battle">
  <input type="hidden" name="isUsed5050" value="true">
  <button type="submit" onclick="form1.submit()">
    50/50: <%= quantity5050 %>個
  </button>
</form>
	<%} %>
	<!--  SKIPアイテム0なら隠す -->
	    <% if(isUsedSkip){ %>
	<p>Skip使用した<p>
	<%} %>    
	<% if(!(isNoHint || (quantitySkip <= 0) )){ %>
<form method="post" name="form2" action="battle" class="skip">
  <input type="hidden" name="isUsedSkip" value="true">
  <button type="submit" onclick="form2.submit()">
    SKIP: <%= quantitySkip %>個
  </button>
</form>
	<%} %>	
</div>
</div>
</div>
<!--/wrapper--></div>
<jsp:include page="footer.jsp"/>
<script>
function increaseParticlesValue() {
    particlesSettings.particles.number.value = 100;
  }
</script>
<script src="http://cdn.jsdelivr.net/particles.js/2.0.0/particles.min.js"></script>
<!--自作のJS-->
<script src="js/particles.js"></script>
</body>
</html>
