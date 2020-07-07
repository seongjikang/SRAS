
// main nav
$(document).ready(function(){
    $('.mainNav01 a').addClass('on');
    $('.mainNav01').addClass('on');
    
    $('.mainNav li a').click(function(){
        $('.mainNav *').removeClass('on');
        $(this).addClass('on');
        $(this).parent().addClass('on');
        
        
    });
    
});



// filter

$(document).ready(function(){
    $('.filter ul').css({
        top: 30,
        display: 'none' //tab키 처리시 리스트가 보여서 무너지기 때문
    });

    //스위치 변수 : 0 혹은 1을 담는 변수
    var isClicked = false;
    
    //버튼을 클릭했을 때
    $('.filter button').click(function(){
        
        if(isClicked==false){ //ul이 아래로 내려오며 보이게
            isClicked = true;
            
            //리스트가 다시 나타나야 함
            $(this).next().css('display','block');
            $(this).addClass('on');
            $(this).children().addClass('on');
            

            $(this).animate({
                height: 195
         
            },'fast',function(){
                $(this).next().css('display','block');
            });
            
            $(this).find('>.filterIcon').addClass('on');
            
        }else{      //ul이 위로 올라가며 안보이게
            isClicked = false;
            $(this).animate({
                height: 30
         
            },'fast',function(){
                $(this).next().css('display','none');
            });

            
            $(this).find('>.filterIcon').removeClass('on');
            $(this).removeClass('on');
            $(this).children().removeClass('on');
        }
    });
    
});


// detail page - reply panel : 여기 함수로 정리하기
$(document).ready(function(){

    var isPositive = true;
    // var confirmButtonClicked = false;

    // detial page 로드시 초기 설정
    $('.proButton').addClass('on');
    $('.replyItem01').addClass('on');
    $('.detailTagCard2').css('display','none');
    $('.detailTagCard').css('display','block');
    $('.detailReplyCardActivated').css('display','none');
    $('.buttonBack').css('display','none');
    $('.buttonSave').removeClass('on');
    
    // 긍정, 부정 버튼 클릭시
    $('.proButton').click(function(){
        $('.proConButton div').removeClass('on');
        $(this).addClass('on');
        isPositive = true;
    });

    $('.conButton').click(function(){
        $('.proConButton div').removeClass('on');
        $(this).addClass('on');
        isPositive = false;
    });

    // 확인 버튼 클릭시
    $('.confirmButton').click(function(){
        if(isPositive){
            $('.buttonSave').addClass('on');
        }else{
            $('.buttonSave').addClass('on');
            $('.buttonBack').css('display','block');
            $('.detailTagCard2').css('display','block');
            $('.detailTagCard').css('display','none');
            $('.detailReplyCardActivated').css('display','block');

        };
    });

    // 답글패널 메뉴 클릭시
    $('.detailReplyNavItem').click(function(){      
        // alert('ok');
        $('.detailReplyTemplate').removeClass('li04');
        $('.detailReplyTemplate').removeClass('li01');
        $('.detailReplyNavItem').removeClass('on');
        $(this).addClass('on');
    });

    $('.replyItem01').click(function(){
        $('.detailReplyTemplate').addClass('li01');
    });

    $('.replyItem04').click(function(){
        $('.detailReplyTemplate').addClass('li04');
    });


    // Back버튼 클릭시
    $('.buttonBack').click(function(){
        $('.detailTagCard2').css('display','none');
        $('.detailTagCard').css('display','block');
        $('.detailReplyCardActivated').css('display','none');
        $('.buttonSave').removeClass('on');
        $('.buttonBack').css('display','none');
    });
    
});