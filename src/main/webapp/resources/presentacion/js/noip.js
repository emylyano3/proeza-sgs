/**
 * NoIP.com
 * http://noip.com
 *
 * NoIP Web Development Team
 */
;(function ($, window, document, undefined) {
    var noip = 'noip', defaults = {
        events: {
            ajaxLogin: function (element) {
                data = $(element).serializeForm();
                var usd = $('#usd').val();
                $('.login-error').addClass('hide').fadeOut();
                $('#login-error').addClass('hide').fadeOut();
                $(element).fadeOut();

                $('#ajax-loading').fadeIn();

                $.ajax({
                    url: '/login',
                    method: 'post',
                    data: data,
                    dataType: 'json',
                    success: function (response) {
                        $(element).toggleForm();
                        if (response.success && ! response.error) {
                            $('#ajax-loading').fadeOut();

                            $('#bitcoin-usd').html(usd);
                            //$('#bitcoin-btc').html();
                            // Bitcoin specific...this needs to go away
                            $.ajax({
                                url: '/bitcoin/get-address',
                                method: 'post',
                                data: {},
                                dataType: 'json',
                                success: function (response) {
                                    if (response.payment_address) {
                                        rate = $('#usd').data('noip-convert-rate');
                                        $('#bitcoin-btc').html((usd / rate).toFixed($('#usd').data('noip-convert-decimal-places')) + $('#usd').data('noip-convert-symbol'));
                                        $('#btc_username').html(response.email);
                                        $('#btc_address').html(response.payment_address);
                                        $('#bitcoin-address').fadeIn();
                                    } else {
                                        $('#bitcoin-address').html('<p class="well">There was an error processing your request. Please try again later.</p>').fadeIn();
                                    }
                                }
                            });
                        } else {
                            $('#ajax-loading').fadeOut();
                            $(element).fadeIn();
                            $(element).toggleForm();
                            $('#login-error-' + response.error).removeClass('hide');
                            $('#login-error').fadeIn();
                        }
                    },
                    error: function (req, status, error) {
                        $(element).toggleForm();
                        $('#login-error-UNKNOWN').removeClass('hide');
                        $('#login-error').fadeIn();
                    }
                });
            },
            formatUSD: function (element) {
                formatted = parseFloat($(element).val()).toFixed(2);
                $(element).val(formatted);
            }
        }
    };

    function Noip (element, options) {
        // Sets default options
        this.settings = $.extend({}, defaults, options);
        this._defaults = defaults;
        this._name = noip;

        // Away we go!
        this.init();
    }

    Noip.prototype = {
        init: function () {
            // initiate the cart
            $.cart();

            this.registerDocumentListeners();
            this.registerEventListeners();
        },

        registerDocumentListeners: function () {
            var _this = this;
            var settings = this.settings;
            var name = this._name;

            $(document).on('submit', '[data-noip-submit-action]', function (e) {
                e.preventDefault();
                ev = $(this).data('noip-submit-action');
                element = $(this);
                _this.marshallEvent(ev, this, element);
            })
            .on('change', '[data-noip-change-action]', function (e) {
                e.preventDefault();
                ev = $(this).data('noip-change-action');
                element = $(this);
                _this.marshallEvent(ev, this, element);
            });
        },

        registerEventListeners: function () {
            var _this = this;

            $.each(_this.settings.events, function (i, e) {
                $(document).on(_this._name + '.' + i, function (e, element) {
                    _this[i](element);
                });
            });
        },

        marshallEvent: function (event, scope, element) {
            if (event in this.settings.events) {
                this.settings.events[event].apply(scope, element);
            }
        }
    };

    $.noip = function(options) {
        return new Noip(this, options);
    }

})(jQuery, window, document);