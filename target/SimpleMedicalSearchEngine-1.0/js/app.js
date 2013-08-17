$(document).ready(
    function() {
        relButtonDisble = true;
        automatic = true;
        
        function autoButton() {
            if(automatic) {
                $("#automatic").addClass('active');
                $("#rel").removeClass('active');
            } else {
                $("#rel").addClass('active');
                $("#automatic").removeClass('active');
            }
        }
        
        $("#rel").click(function() {
            $("#relatedterm > li").remove();    
            $.ajax({
                url: 'related.do',
                type: 'GET',
                data: 'q=' + $("#query").val(),
                dataType: 'xml', 
                success: function(xml) { 
                    $(xml).find('term').each(function(){
                        var term = $(this).text();
                        $('#relatedterm').append('<li><a class="term" href="#">' + term + '</a></li>');
                    });
                    $(".term").click(function () {
                        text = $(this).text();
                        $("#query").val( $("#query").val( ) + ',' + text);
                        automatic = false;
                        autoButton();
                    });
                    if ( $(".term").size() == 0 ) {
                        $('#relatedterm').append('<li><a class="term" href="#"> empty query </a></li>');
                    }
                }
            }); 
        });
        
        $('#myModal').on('hidden', function () {
            // do something…
            automatic = true;
            autoButton();
        });
        
        function loading_show()
        {
            $('#loading').html("<img src='img/loading.gif'/>").fadeIn('fast');
        }

        function loading_hide()
        {
            $('#loading').fadeOut();
        } 

        function pagination(page, maxpage) 
        {
        
            $('#main').append('<div class="pagination pagination-centered"></div>');
        
            $('.pagination').append('<ul></ul>');
        
            var prev = (page == 1) ? 1 : page-1;
        
            $('.pagination ul').append('<li p="' + prev + '" class="pageno btn"><i class="icon-arrow-left icon-white"></i></li>');
                        
            var start;
            if( page < 3 ) {
                start = 1;
            } else if ( page > maxpage - 4 ) {
                start = maxpage - 4;
            } else {
                start = page;
            }
                
            for (var i=start;i<=start+4;i++)
            { 
                if(page != i) {
                    $('.pagination ul').append('<li p="'+ i +'" class="pageno btn">'+i +'</li>');
                } else {
                    $('.pagination ul').append('<li p="'+ i +'" class="active pageno btn">'+i +'</li>');
                }
            }
        
            var back = (page == maxpage) ? page : page + 1;
            $('.pagination ul').append('<li p="'+ back +'" class="pageno btn"><i class="icon-arrow-right icon-white"></i></li>');
        
            $(".pageno").click(function(){
                var page = parseInt($(this).attr('p'));
                $('.result').remove();
                loadData(page);
            });
        } 

        function loadData(page)
        {
            loading_show();
            $.ajax
            ({
                type: "GET",
                url: 'search.do',
                data: 'q=' + $("#query").val() + '&k=' + $("#k").val() + '&page=' + page +'&autoselect=' + automatic,
                success: function(xml)
                {
                    $('.pagination').remove();
                    $('#res').remove();
                    $('#resinfo p').remove();
                
                
                    $('#main').append('<ol class="result"></ol>');
                
                    $(xml).find('document').each(function(){
                        var term = $(this).text();
                    
                        var firstLine = term.split('.')[0];
                    
                        $('.result').append('<li><div class="row"><h3><a class="span12 rurl" href="document.jsp?title='+ encodeURIComponent(firstLine) +'&content='+ encodeURIComponent(term) +'">'+firstLine+'</a></h3></div><div class="row rcontent"><p>' + term +'</p></div></li>');
                    });
                
                    var maxpage = parseInt($(xml).find("documentcollection").attr('maxpage'));
                    var size = parseInt($(xml).find("documentcollection").attr('size'));
                
                    $('#resinfo').append('<p>Retrieved ' + size + ' document</p>');
                    pagination(page, maxpage);
                }
            });
        }
        $("#button").click(function(event){
            event.preventDefault(event);
            $('.result').remove();
            $('#resinfo p').remove();
            loadData(1);
        });
        
        autoButton();
    
    });


