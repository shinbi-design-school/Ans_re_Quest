/**
 * 
 */

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
