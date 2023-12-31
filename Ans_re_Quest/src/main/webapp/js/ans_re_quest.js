/**
 * 
 */

$(function() {

  // 画像をクリックしたら発動
  $('img.character').on('click', function() {
  
  // リストアップ
  var elements = [
              ['君をサポートする「ノーレッジ」だよ。<br>アバターとして冥鳴ひまりを使っているよ。', 'imgs/homeChara/face2.png'],
              ['名前の由来？もちろんとある動かない大図書館からだよ。', 'imgs/homeChara/face6.png'],
              ['なんでこのアバターなのかって？<br>担当者が別の企画で使わなかった素材だからだよ。', 'imgs/homeChara/face3.png'],
              ['形はどうにかなったところは多いよ。<br>中身が伴っているわけではないよ。', 'imgs/homeChara/face4.png'],
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

//ボタンにマウスオーバーした時の処理
const scenarioArea = document.querySelector(".scenario");
var SbuttonText = ['そこからシナリオの閲覧が出来るよ。<br>因みに「最初から全部読める」よ。'];
let SbuttonImg = 'imgs/homeChara/face2.png';
scenarioArea.addEventListener('mouseover',()=>{
  $('div.character').attr('src',SbuttonImg);
  $('div.comment').html(SbuttonText).show();
});
scenarioArea.addEventListener('mouseout', () => {
  $('div.comment').hide();
});

const QuestArea = document.querySelector(".Quest");
var QbuttonText = ['そこから塔に挑むことが出来るよ。<br>頑張ってね！'];
QuestArea.addEventListener('mouseover',()=>{
  $('div.comment').html(QbuttonText).show();
});
QuestArea.addEventListener('mouseout', () => {
  $('div.comment').hide();
});

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

