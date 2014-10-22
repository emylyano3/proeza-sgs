/**
 * NoIP.com Cart Implementation - v0.0.1DEV
 * http://noip.com
 *
 * NoIP Web Development Team
 */
;(function ($, window, document, undefined) {

    var cart = 'cart', defaults = {
        urls: {
            add: '/cart/add',
            legacy_append: '/members/checkout/reviewOrder',
            // FIXME: This URL has been changed to work with the OLD controller
            // works with new controller in cart-refactor topic (dmosher)
            addMultiple: '/cart/add',
            remove: '/cart/remove',
            update: '/cart/update'
        }
    };

    function Cart (element, options) {
        // Sets default options
        this.settings = $.extend({}, defaults, options);
        this._defaults = defaults;
        this._name = cart;

        // Away we go!
        this.init();
    }

    Cart.prototype = {
        init: function() {
            var _this = this;

            // initialize cartui
            $.cartui();

            _this.settings.modalShown = false;

            _this.registerDocumentListeners();

            $(document).trigger('cart.ready');
        },

        append: function(data) {
            var _this = this;
            if (window.location.href.indexOf('cart') > -1) {
                url = _this.settings.urls.add;
            } else {
                url = _this.settings.urls.legacy_append;
            }
            $.ajax({
                url: url,
                type: 'post',
                data: data,
                success: function (response) {
                    if (response.success) {
                        $.event.trigger('cart.appendSuccess', response);
                    }
                    if (response.errors) {
                        $.event.trigger('cart.errorHandler', response.errors);
                    }
                },
                dataType: 'json'
            });
        },

        add: function(data) {
            var _this = this;

            $.event.trigger('cart.addingItem', [data]);

            $.ajax({
                url: _this.settings.urls.add,
                type: 'post',
                data: data,
                success: function(response) {
                    _this.processAddResponse(response)
                },
                dataType: 'json'
            });
        },

        processAddResponse: function(response) {
            if (response.success) {
                $.event.trigger('cart.addSuccess', response);
            }
            if (response.errors) {
                $.event.trigger('cart.errorHandler', response.errors);
            }
        },

        addMultiple: function(products) {
            var _this = this;

            $.event.trigger('cart.addingItem', [{product: 'dns.plus'}]);

            $.ajax({
                url: _this.settings.urls.addMultiple,
                type: 'post',
                data: {
                    multiple: products
                },
                success: function(response) {
                    _this.processAddMultipleResponse(response);
                },
                dataType: 'json'
            });
        },

        processAddMultipleResponse: function(response) {
            if (response.success) {
                $.event.trigger('cart.addSuccess', response);
            }
            if (response.errors) {
                $.event.trigger('cart.errorHandler', response.errors);
            }
        },

        remove: function(item_id) {
            var _this = this;

            $.event.trigger('cart.removingItem', [item_id]);

            $.ajax({
                url: _this.settings.urls.remove,
                type: 'post',
                data: { item_id: item_id },
                success: function(response) {
                    _this.processRemoveResponse(item_id, response);
                }
            });
        },

        processRemoveResponse: function(item_id, response) {
            $.event.trigger('cart.itemRemoved', [item_id, response]);
        },

        registerDocumentListeners: function() {
            var _this = this;

            $(document).on('click', 'a[data-product]', function(e) {
                e.preventDefault();

                product = $(this).attr('data-product');
                data = {};

                if ($(this).hasClass('add-product')) {
                    if ($(this).closest('form').parsley('validate') || product == 'support.priority') {
                        data.product = product;
                        data.activation = $(this).attr('data-activation');

                        data.quantity = 1;
                        if ($('.product-options[data-product="' + product + '"] select').length) {
                            data.quantity = $('.product-options[data-product="' + product + '"] select').val();
                        }
                        data.options = {};
                        if ($(this).hasClass('js-mobile-add-product')) {
                            $('.product-options.js-mobile[data-product="' + product + '"] input').each(function(index, value) {
                                name = $(this).attr('name');
                                value = $(this).val();
                                data.options[name] = value;
                                $(this).val('');
                            });

                            if ($('.product-options.js-mobile[data-product="' + product + '"] select').length) {
                                data.quantity = $('.product-options.js-mobile[data-product="' + product + '"] select').val();
                            }
                        } else {
                            $('.product-options[data-product="' + product + '"] input').each(function(index, value) {
                                name = $(this).attr('name');
                                value = $(this).val();
                                data.options[name] = value;
                                $(this).val('');
                            });

                            if ($('.product-options[data-product="' + product + '"] select').length) {
                                data.quantity = $('.product-options[data-product="' + product + '"] select').val();
                            }
                        }

                        $.event.trigger('cart.addingItem', [data, false]);
                        _this.add(data);
                    }
                    return;
                }

                // adding product from page content
                data.product = product;
                data.activation = $(this).attr('data-activation');
                if (! data.activation) {
                    data.activation = 'new';
                }

                if ($(this).attr('data-domain')) {
                    domain = $(this).attr('data-domain').split('.');
                    data.options = {
                        domain: $(this).attr('data-domain'),
                        tld: domain[1]
                    };

                    $.event.trigger('cart.addingItem', [data]);
                    _this.add(data);

                    return;
                }

                if (data.product.split('.')[0] == 'registration') {
                    // adding domain registration/transfer
                    domain = $(this).attr('data-domain').split('.');
                    data.options = {
                        domain: $(this).attr('data-domain'),
                        tld: domain[1]
                    };

                    $.event.trigger('cart.addingItem', [data, false]);
                    _this.add(data);
                    return;
                }

                if ($(this).attr('data-quantity')) {
                    data.quantity = $(this).attr('data-quantity');
                    $.event.trigger('cart.addingItem', [data, false]);
                    _this.add(data);
                    return;
                }

                if ($(this).hasClass('js-cart-review-add')) {
                    $.event.trigger('cart.appendFromReview', [data]);
                    _this.append(data);
                    return;
                }

                $.event.trigger('cart.addingItem', [data, true]);
            })
            .on('click', 'input.priority-support', function(e) {
                data = {};
                data.product = 'support.priority';
                data.activation = 'new';

                $.event.trigger('cart.appendFromReview', [data]);
                _this.append(data);
            })
            .on('keypress', '.product-options input', function(e) {
                if (e.keyCode == 13) {
                    e.preventDefault();
                    if ($(this).closest('form').parsley('validate')) {
                        $button = $(this).parent().find('a.add-product');
                        product = $button.attr('data-product');

                        data.options = {};
                        $('.product-options[data-product="' + product + '"] input').each(function(index, value) {
                            name = $(this).attr('name');
                            value = $(this).val();
                            data.options[name] = value;
                            $(this).val('');
                        });
                        if ($('.product-options[data-product="' + product + '"] select').length) {
                            data.quantity = $('.product-options[data-product="' + product + '"] select').val();
                        }

                        $(this).closest('form').parsley('destroy');

                        // trigger adding event
                        _this.add(data);
                    }
                }
            })
            .on('change', 'input[name="addon-product"]', function(e) {
                if ($(this).is(':checked')) {
                    // adding addon item
                    data = {};

                    data.product = 'registration.privacy';
                    data.activation = $(this).attr('data-activation');
                    if (! data.activation) {
                        data.activation = 'new';
                    }

                    domain = $(this).attr('data-domain').split('.');
                    data.options = {
                        domain: $(this).attr('data-domain'),
                        tld: domain[1]
                    };

                    data.parent = $(this).attr('data-parent');

                    _this.add(data);

                    return;
                }

                // removing addon item
                _this.remove($(this).attr('data-item_id'));
            })
            .on('change', 'select[name="review-term[]"], select[name="term[]"]', function() {
                old_item = $(this).attr('data-item_id');
                $.ajax({
                    url: _this.settings.urls.update,
                    type: 'POST',
                    data: {
                        item_id: old_item,
                        term: $(this).val()
                    },
                    success: function(response) {
                        $.event.trigger('cart.updateTerm', [response, old_item]);
                    },
                    dataType: 'json'
                });
            })
            .on('click', '#add-domains-to-cart', function(e) {
                e.preventDefault();
                var multiple = [];
                $('#dom-available input[name="domains[]"]:checked, #dom-suggestions input[name="domains[]"]:checked').each(function() {
                    var options = {};
                    // FIXME: just post the full domain and then split it up for options?
                    domain = $(this).val().split('.');
                    options.product = 'registration.domain';
                    options.activation = 'new';
                    options.domain = $(this).val();
                    options.tld = domain[1];
                    multiple.push(options);
                });

                $('#dom-available input[name="plus-domains[]"]:checked, #dom-suggestions input[name="plus-domains[]"]:checked').each(function() {
                    var options = {};
                    // FIXME: just post the full domain and then split it up for options?
                    domain = $(this).val().split('.');
                    options.product = 'dns.plus&registration.domain';
                    options.activation = 'new';
                    options.domain = $(this).val();
                    options.tld = domain[1];
                    multiple.push(options);
                });

                _this.addMultiple(multiple);
            })
            .on('click', 'a.remove[data-item_id]', function(e) {
                e.preventDefault();
                item_id = $(this).attr('data-item_id');
                _this.remove(item_id);
            })
            .on('click', 'input[name="domains[]"]', function() {
                $label = $(this).parent().parent().find('label.with-plus');
                $plus = $label.parent().find('input[name="plus-domains[]"]');

                if ($(this).is(':checked')) {
                    $label.slideDown();
                    $plus.prop('checked', false).removeAttr('checked');
                } else {
                    $label.slideUp();
                    $plus.prop('checked', false).removeAttr('checked');
                }
            })
            .on('click', 'input[name="plus-domains[]"]', function() {
                $label = $(this).parent().parent().find('label.with-plus');
                $domains = $label.parent().find('input[name="domains[]"]');

                if ($(this).is(':checked')) {
                    $domains.prop('checked', false).removeAttr('checked');
                } else {
                    $domains.prop('checked', true);
                }
            });
        }
    };

    $.cart = function(options) {
        return new Cart(this, options);
    }

})(jQuery, window, document);