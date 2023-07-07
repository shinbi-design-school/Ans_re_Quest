/**
 * 
 */

$(function() {

  // 画像をクリックしたら発動
  $('img.character').on('click', function() {
  
  // リストアップ
  var elements = [
              ['サポートキャラの「名称未設定」だよ。アバターとして冥鳴ひまりを使っているよ。', 'imgs/homeChara/face2.png'],
              ['なんでこのアバターなのかって？<br>動画制作で使わなかった素材だからだよ。', 'imgs/homeChara/face3.png'],
              ['正直CSS担当していてもコンテンツでやりたいことはあるんだよ。でも実装どころか構成もろくにできてない有様だよ。', 'imgs/homeChara/face4.png']
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

//ホームキャラ変化用
// const image = document.getElementById('image'); // imgタグを取得
// const images = [
//	 	'imgs/homeChara/face2.jpg',
//	 	'imgs/homeChara/face3.jpg',
//	 	'imgs/homeChara/face4.jpg',
//	 	'imgs/homeChara/face5.jpg']; // 画像の配列
//
//  function OnButtonClick() { // クリックした時に実行する関数
//   const imageNo = Math.floor(Math.random() * images.length); // 0から3までのランダムな整数
//   image.src = images[imageNo]; // imgタグのsrc属性をランダムな画像に変更
//  }
//
//  image.addEventListener('click', OnButtonClick); // imgタグにイベントハンドラーを登録
//  


  
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

