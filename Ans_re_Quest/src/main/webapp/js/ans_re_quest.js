/**
 * 
 */

$(function() {

  // 画像をクリックしたら発動
  $('img.character').on('click', function() {
  
  // リストアップ
  var elements = [
              ['サポートキャラの「名称未設定」だよ。<br>アバターとして冥鳴ひまりを使っているよ。', 'imgs/homeChara/face2.png'],
              ['なんでこのアバターなのかって？<br>動画制作で使わなかった素材だからだよ。', 'imgs/homeChara/face3.png'],
              ['正直CSS担当していてもコンテンツでやりたいことはあるんだよ。でも実装どころか構成も構想もろくにできてない有様だよ。', 'imgs/homeChara/face4.png'],
              ['フフフフ、分からない。分からないんだよ。どうすればいいのか分からないんだよ！', 'imgs/homeChara/face5.png']
//              ['', 'imgs/homeChara/'],
             ];

  // リストからランダムに取得
  var randElm = elements[Math.floor(Math.random() * elements.length)];

  // 表示エリアにコメントを挿入して時間経過で非表示
  $('div.comment').html(randElm[0]).show(); // show()で表示する

  // 表示エリアに切り替え用画像を挿入
  var img = document.getElementById('change_image');
  img.src = randElm[1];
  
  // 最初の画像をフェードアウトさせる
  $('div.character').fadeOut(500, function() {
    // テキストを表示
    $('div.text').fadeIn(500, function() {
      // 時間経過でテキストを非表示
      $(this).delay(4000).fadeOut(500, function() { // コールバック関数の中に書く
        // 最初の画像をフェードインさせる
        $('div.character').fadeIn(500);
      });
    });
    // 吹き出しを表示
    $('div.word').animate({opacity: 1}, {duration: 500, easing: 'linear'}, function() { // opacityを1にするアニメーションを追加
      // 時間経過で吹き出しを非表示
      $(this).delay(4000).animate({opacity: 0}, {duration: 500, easing: 'linear'}); // opacityを0にするアニメーションを追加
    });
    
   });
  
 });
});



//logoの表示
$(window).on('load',function(){
    $("#splash").delay(1800).fadeOut('slow');//ローディング画面を1.8秒待機してからフェードアウト
    $("#splash_logo").delay(5000).fadeOut('slow');//ロゴを5秒待機してからフェードアウト
  });
  var bar = new ProgressBar.Line(containe, {
    strokeWidth: 4,
    easing: 'easeInOut',
    duration: 1400,
    color: '#FFEA82',
    trailColor: '#eee',
    trailWidth: 1,
    svgStyle: {width: '100%', height: '100%'}
  });
  
  bar.animate(1.0); 

//音楽再生用モーダルウインドウ、処理確定が出来ていないのでコメントアウト中
//const audio = $("#audio")[0]
//
//$(document).on('click', '#can-play-button', function (e) {
//  $("#button-wrap").hide();
//  //audioを読み込み
//  audio.load()
//  //３秒後audioを再生
//  setTimeout(function()
//    {play(audio)}, 2000
//  );
//});
//
//function play(audio){
//  audio.play();
//}


//カーソル追従エフェクト 対応するJS入れると背景変えられるしでよくわからん
//  // パーティクルを生成する
//  var particles = bubbly({
//    colorStart: "#fff4e6",
//    colorStop: "#ffe9e4",
//    blur: 1,
//    compose: "source-over",
//    bubbleFunc: () => `hsla(${Math.random() * 50}, 100%, 50%, .3)`,
//      bubbles: 500,  selector: "#totalMenu" 
//  }).firstChild;
//
//  // #totalMenu要素にmousemoveイベントを設定する
//  var totalMenu = document.getElementById("totalMenu");
//  totalMenu.addEventListener("mousemove", function (e) {
//    // マウスの座標を取得する
//    var x = e.clientX;
//    var y = e.clientY;
//
//    // #totalMenu要素の範囲内であるかどうかを判定する
//    var rect = totalMenu.getBoundingClientRect();
//    if (
//      x >= rect.left &&
//      x <= rect.right &&
//      y >= rect.top &&
//      y <= rect.bottom
//    ) {
//      // パーティクルの位置をマウスの座標に合わせる
//      particles.style.left = x + "px";
//      particles.style.top = y + "px";
//    }
//  });