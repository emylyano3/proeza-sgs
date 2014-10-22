$(document).ready(function() {

	// load cart
	$.noip();

    // serialize a form to json
    (function($) {
        $.fn.serializeForm = function() {
            var o = {};
            var a = this.serializeArray();
           $.each(a, function() {
               if (o[this.name]) {
                   if (! o[this.name].push) {
                       o[this.name] = [o[this.name]];
                   }
                   o[this.name].push(this.value || '');
               } else {
                   o[this.name] = this.value || '';
               }
           });
           return o;
        };
    })(jQuery);

    (function($) {
        $.fn.toggleForm = function () {
            $(this).find(':input').each(function() {
                if ($(this).prop('disabled')) {
                    $(this).removeProp('disabled');
                } else {
                    $(this).prop('disabled', true);
                }
            });
            return this;
            //$(this).find(':input').prop('disabled', (!$(this).prop('disabled')));
        };
    })(jQuery);

    (function($) {
        $.fn.resetForm = function () {
            $(this).find(':input').val('');
            return this;
        };
    })(jQuery);

    //Auto Focuses Sign In Modal
	$("#loginModal").on("shown",function() {
		$(this).find("input").first().focus();
	});

	// Responsive Nav Dropdown
	$( "#nav-button" ).click(function() {
	  $('#navigation').toggle()
	});

	// disables payment form when paypal is selected
	var update_select = function () {
	    if ($('.paypal').is(':checked')) {
	    $(".paypal-disable").attr("disabled", true);
	}
	else {
	    $(".paypal-disable").removeAttr("disabled");
	}
	};
	$("input[name='paymentMethod']").change(update_select);

	// Quotes Slider
	$('.carousel').carousel({
	  interval: 5000
	})

	// SignUp page tool tips
	$('.tip').tooltip()

	// ========== MODALS =========== //

	// Downloads page modal
	$( "#open-dialog" ).click(function() {
	  $('#downloadModal').modal()
	});

	// Host Create Modal
	$('#hostcreateModal').modal('show');

	// Priority Support Modal
	$('#priority-supportModal').modal('show');

	// ========== / MODALS =========== //

	// Lazy load images
	if ($('img.lazy')) {
		$('img.lazy').lazyload({
			threshold : 0,
			effect: 'fadeIn'
		});
	}

    // Waypoint Header
    var $head = $( '#way-header-wrap' );
    $( '.waypoint' ).each( function(i) {
    	var $el = $( this ),
    		animClassDown = $el.data( 'animateDown' ),
    		animClassUp = $el.data( 'animateUp' );

    	$el.waypoint( function( direction ) {
    		if( direction === 'down' && animClassDown ) {
    			$head.attr('class', 'way-header ' + animClassDown);
    		}
    		else if( direction === 'up' && animClassUp ){
    			$head.attr('class', 'way-header ' + animClassUp);
    		}
    	}, { offset: '0' } );
    } );

    // Fade in any .messages on page load
	if ($('.message')) {
		$('.message').fadeIn();
	}

	// Manage home page text fade
	$(window).scroll(function() {
        var st = $(this).scrollTop();
        $('.fadeScroll').css({
            'opacity': 1 - st/500
        });

        if (st < 10) {
            $('.fadeup').stop(true, true).fadeIn('fast');
        } else {
            $('.fadeup').stop(true, true).fadeOut('fast');
        }

    });

    //$('input, textarea').placeholder();

	// Google keyword tracking
	if (document.referrer.match(/google\.com/gi) && document.referrer.match(/cd/gi)) {
		var referrer = document.referrer;
		var r = referrer.match(/cd=(.*?)&/);
		var rank = parseInt(r[1]);
		var kw = referrer.match(/q=(.*?)&/);

		if (kw[1].length > 0) {
			var keyword = decodeURI(kw[1]);
		} else {
			var keyword = '(not provided)';
		}
		pathname = document.location.pathname;

		_qaq.push(['_trackEvent', 'RankTracker', keyword, pathname, rank, true]);
	}

	// Submit support ticket knowledge base and faq pre-search
	var displayed_kb_articles = false;

	$(this).on('click', '#kb_submit_ticket', function() {
		$('#ticketform').submit();
	});

    $("#ticketform").submit(function(e) {
        if (!displayed_kb_articles) {
            e.preventDefault();
            if ($('input[name="subject"]').val().length > 10) {
                $.ajax({
                    url: '/support/',
                    method: 'GET',
                    data: {
                        ajax: 'on',
                        post_type: 'st_kb',
                        s: strip_common_words($('input[name="subject"]').val())
                    },
                    error: function() {
                        // failure...just submit the ticket
                        displayed_kb_articles = true;
                        $("#ticketform").submit();
                    },
                    success: function(resp) {
						if (resp.indexOf('Sorry, no posts were found') >= 0) {
							resp = '<ul id="search-result"><li class="sr-st_kb"><a href="/support/knowledgebase/basic-troubleshooting-guide/">Basic Troubleshooting Guide</a></li></ul>';
						}
						$('#ticketModal').html('<div class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button><h3 id="myModalLabel">Before You Submit</h3></div><div class="modal-body"><p><strong>We have found some light reading for you!</strong><br />Based on the information you filled in on your ticket, we found some knowledge base articles that may answer your questions. Feel free to check each one. If they don\'t answer your questions, please click the Submit Ticket button below.</p><p><input type="submit" id="kb_submit_ticket" class="button" value="Submit Ticket"></p>' + resp + '<p>&nbsp;</p>').modal('show').on('shown', function() {
	    						$('ul#search-result', resp).find('a').attr('target', '_blank');
	    						$('ul#search-results', resp).prepend('<li class="sr-st_kb"><a href="/support/knowledgebase/basic-troubleshooting-guide/">Basic Troubleshooting Guide</a></li>');
				    			displayed_kb_articles = true;
						}).on('hidden', function() {
							displayed_kb_articles = false;
						});
                    }
                });
            }
        } else {
            // else just submit the form...
            //e.preventDefault();
            //alert("submitting");
        }

	});

	function strip_common_words(text) {
        stops = ""
        +"a about above after again against all am an and any are aren't as  "
        +"at be because been before being below between both but by can't    "
        +"cannot could couldn't did didn't do does doesn't doing don't down  "
        +"during each few for from further had hadn't has hasn't have        "
        +"haven't having he he'd he'll he's her here here's hers herself     "
        +"him himself his how how's i i'd i'll i'm i've if in into is isn't  "
        +"it it's its itself let's me more most mustn't my myself no nor     "
        +"not of off on once only or other ought our ours ourselves out      "
        +"over own same shan't she she'd she'll she's should shouldn't so    "
        +"some such than that that's the their theirs them themselves then   "
        +"there there's these they they'd they'll they're they've this       "
        +"those through to too under until up very was wasn't we we'd we'll  "
        +"we're we've were weren't what what's when when's where where's     "
        +"which while who who's whom why why's with won't would wouldn't     "
        +"you you'd you'll you're you've your yours yourself yourselves      "

        // how many to replace at a time
        reSize = 20

        // build regexps
        regexes = []
        stops = stops.match(/\S+/g).sort(function(a, b) { return b.length - a.length })
        for (var n = 0; n < stops.length; n += reSize)
            regexes.push(new RegExp('\b(' + stops.slice(n, n + reSize).join("|") + ')\b', 'gi'));

        $.each(regexes, function(key, value) {
            text = text.replace(value, '');
        });

        return text;
    }

});