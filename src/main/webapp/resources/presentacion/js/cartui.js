/**
 * NoIP.com Cart UI Implementation - v0.0.1DEV
 * http://noip.com
 *
 * NoIP Web Development Team
 */
;(function ($, window, document, undefined) {

    var cartui = 'cartui', defaults = {
        modalElement: '#cartModal',
        urls: {
            display: '/cart/display',
            numItems: '/cart/numitems'
        },
        html: {
            empty_cart: '<tr><td colspan="8" class="text-center">No more items in your cart. <a href="/">Return to our homepage</a> to read about our amazing services.</td></tr>'
        },
        zero_string: '$0.00'
    };

    function CartUI (element, options) {
        this.settings = $.extend({}, defaults, options);
        this._defaults = defaults;
        this._name = cartui;

        // Away we go!
        this.init();
    }

    CartUI.prototype = {
        init: function() {
            var _this = this;

            _this.settings.modalShown = false;

            _this.registerAjaxListeners();
            _this.registerWindowListeners();
            _this.registerEventListeners();
            _this.registerDocumentListeners();
        },

        registerAjaxListeners: function() {
            var _this = this;

            // $loader = $('<div class="hide text-center" id="cart-loading"><i class="icon-spin5 animate-spin"></i></div>');
            $(document).ajaxStart(function() {
                if (_this.settings.modalShown) {
                    _this.showLoading();
                }
            }).ajaxStop(function() {
                if (_this.settings.modalShown) {
                    _this.hideLoading();
                }
            });
        },

        showLoading: function() {
            $('#cart-loading').fadeIn('fast');
        },

        hideLoading: function() {
            $('#cart-loading').fadeOut('fast');
        },

        registerWindowListeners: function() {
            $(window).on('popstate', function() {
                if ($('.badge-cart-items').length) {
                    $.event.trigger('cart.updateItemCount');
                }
            });
        },

        addingItem: function(data, show_options) {
            this.showModal(data, show_options);
        },

        appendSuccess: function(response) {
            var _this = this;

            $('tr.js-priority-support').fadeOut('fast', function() {
                count = $('#review-order tbody tr').length;
                product = response.added;
                if (window.location.href.indexOf('cart') > -1) {
                    dropdown = '<select name="review-term[]" data-item_id="' + product.id + '" class="small">';
                    for (var i = 0; i <= product.termArray.length - 1; i++) {
                        dropdown += '<option>' + product.termArray[i] + '</option>';
                    }
                    dropdown += '</select> Years';

                    $('#review-order tbody').append('<tr data-item_id="' + product.id + '"><td data-title="#">' + count + '</td><td data-title="Domain">' + product.title + '</td><td data-title="Type">' + product.title + '</td><td data-title="Price">$' + product.price.toFixed(2) + '</td><td data-title="Term" id="review-term"><span class="quantity">' + dropdown + '</span></td><td data-title="Total">$' + product.subtotal.toFixed(2) + '</td><td><a class="remove" href="#" data-remove="item" data-item_id="' + product.id + '"><i class="icon-cancel-circle"></i>Remove</a></td></tr>');
                } else {
                     dropdown = '<select name="quant[' + product.id + ']" class="years-sel" onchange="submitQuant(this, \'' + product.id + '\', \'' + product.pid + '\', \'Priority Support\')">';

                     for (var i = 0; i <= product.termArray.length - 1; i++) {
                         dropdown += '<option>' + product.termArray[i] + '</option>';
                     }
                     dropdown += '</select> Years';

                    $('#review-order tbody').append('<tr><td scope="row" abbr="gray" class="count">' + count + '</td><td scope="row" class="domain">' + product.title + '</td><td scope="row" class="type">' + product.title + '</td><td scope="row" class="cost"><span>$' + product.price + '</span></td><td scope="row" class="cost"><span>$' + product.price + '</span></td><td scope="row" class="years">' + dropdown + '</td><td scope="row">$' + product.subtotal + '</td><td scope="row" class="actions med"><a href="reviewOrder.php?itemid=' + product.id + '&amp;remove=1" class="remove">Remove<i class="icon-cancel-circle"></i></a></td></tr>');

                }
                _this.updateTotal(response.cart_total);
            });
        },

        addSuccess: function(response) {
            if (response.append) {
                $('#cart').append(response.append);
            }

            if (response.display) {
                $('#cart').html(response.display);
            }

            this.showHiddenItems();
            this.updateTotal(response.cart_total);


            if (response.added.product_id) {
                this.hideOptions(response.added.product_id);

                if (response.added.product_id != 'support.priority') {
                    $('.js-priority-support').fadeIn();
                }

                if (response.added.product_id == 'account.enhanced') {
                    $('#cartModal a.button[data-product="account.enhanced"]').addClass('disabled');
                } else if (response.added.product_id == 'support.priority') {
                    $('.js-priority-support').fadeOut('normal', function() {
                        $(this).addClass('hide');
                    });
                }
            }

            this.removeMobileOptions();
            this.showHiddenItems();
            this.updateTotal(response.cart_total);
        },

        errorHandler: function(errors) {
            var _this = this;

            $.each(errors, function(key, error) {
                _this.showError(error);
            });
        },

        updateTotal: function(total) {
            var _this = this;

            _this.updateItemCount();

            if (total == '0.00') {
                $('#cartModal a.button.small.green').addClass('disabled').attr('disabled', 'disabled');
            } else {
                $('#cartModal a.button.small.green').removeClass('disabled').removeAttr('disabled');
            }

            $('#cart-total').html('$' + total);
            $('td.total').html('<strong>TOTAL</strong> $' + total);
        },

        updateItemCount: function() {
            var _this = this;

            $.ajax({
                url: _this.settings.urls.numItems,
                type: 'post',
                success: function(response) {
                    _this.processUpdateItemCount(response);
                },
                dataType: 'json'
            });
        },

        processUpdateItemCount: function(response) {
            $('.badge-cart-items').html(response.num_items);
            $('#cart-count').html('(' + response.num_items + ')');
        },

        showModal: function(data, with_options) {
            var _this = this;

            if (! $(_this.settings.modalElement).is(':visible') && ! _this.modalShown) {
                // load content of modal
                $.ajax({
                    url: _this.settings.urls.display,
                    type: 'post',
                    success: function(response) {
                        _this.processDisplayResponse(data, response, with_options);
                    },
                    dataType: 'json'
                });

                return;
            }

            // modal already shown, just show the options
            if (with_options) {
                _this.showOptions(data);
            }
        },

        processDisplayResponse: function(data, response, with_options) {
            var _this = this;

            $('#cart-modal').html(response.display);
            $(_this.settings.modalElement + ' form').each(function() {
                $(this).parsley();
            });

            $(_this.settings.modalElement + ' a.button.cart[data-product="' + data.product + '"]').addClass('disabled').attr('disabled', 'disabled');
            $(_this.settings.modalElement).modal('show').on('shown', function() {
                $('.tip').tooltip();
                $(this).unbind('shown');
                _this.toggleTab(data.product);

                if (with_options && ! data.product.options) {
                    _this.showOptions(data);
                }
            });
            _this.settings.modalShown = true;
        },

        toggleTab: function(product) {
            if (product) {
                product_type = product.split('.')[0];
                $('#add-service ul li a[href="#' + product_type + '-services"]').tab('show');
            }
        },

        hasOptions: function(product) {
            return ($('.product-options[data-product="' + product + '"]').length > 0);
        },

        showOptions: function(data) {
            var _this = this;

            if (! $('#add-service').is(':visible')) {
                $product = $('.add-domain-cart-wrap[data-product="' + product + '"]');
                // Mobile...copy product and options to right side for display
                $product.parent().clone().addClass('js-mobile-product').appendTo('#cart');
                $('#cart .js-mobile-product .add-product').addClass('js-mobile-add-product');
                $('#cart .js-mobile-product .product-options').addClass('js-mobile');
            }

            $(_this.settings.modalElement + ' a.button.cart[data-product="' + data.product + '"]').addClass('disabled').attr('disabled', 'disabled');

            $('.product-options[data-product="' + data.product + '"]').fadeIn('normal', function() {
                $input = $(this).find('input,select').first();
                $input.closest('.tab-content').animate({
                    scrollTop: $input.offset().top
                }, 1, function() {
                    $input.focus();
                });
            });
        },

        hideOptions: function(product) {
            var _this = this;
            $(_this.settings.modalElement + ' a.button.cart[data-product="' + product + '"]').removeClass('disabled').removeAttr('disabled');
            $('.product-options[data-product="' + product + '"]').fadeOut('normal');
        },

        showError: function(error) {
            var _this = this;

            $error = $('<div class="alert hide"><button type="button" class="close" data-dismiss="alert">&times;</button><strong>Warning!</strong>' + error + '</div>');
            $('#cart-items').append($error);

            _this.showHiddenItems();
        },

        hideErrors: function() {
            $('#cart-items .alert').each(function() {
                $(this).slideUp('fast');
            });
        },

        removeMobileOptions: function() {
            $('.js-mobile-product').slideUp('fast', function() {
                $(this).remove();
            });
        },

        showHiddenItems: function() {
            $('#cart .hide').slideDown('fast', function() {
                $('#cart-items').animate({
                    scrollTop: $('#cart-items')[0].scrollHeight
                }, 1000);

                $(this).removeClass('hide');
            });
        },

        removingItem: function(item_id) {
            $('li[data-item_id="' + item_id + '"]').slideUp('fast', function() {
                $(this).remove();
            });

            $('tr[data-item_id="' + item_id + '"]').slideUp('fast', function() {
                $(this).remove();
            });
        },

        itemRemoved: function(item_id, response) {
            var _this = this;

            if ($('#review-order').length) {
                // review order page (not modal)
                if (response.cart_total == 0) {
                    $('#review-order tbody').append(_this.settings.html.empty_cart);
                    $('.service-row, td.total').fadeOut('fast');
                }
                $('tr[data-item_id="' + item_id + '"]').slideUp('fast', function() {
                    $(this).remove();
                });
                _this.updateTotal(response.cart_total);
                return;
            }

            // modal
            this.updateTotal(response.cart_total);
            if (response.cart_total == '0.00') {
                $('.js-priority-support').fadeOut();
            }

            if (response.product == 'support.priority') {
                $('.js-priority-support').fadeIn();
            }

            if (response.product == 'account.enhanced') {
                // re-enable enhanced button
                $('#cartModal a.button.disabled[data-product="account.enhanced"]').removeClass('disabled').removeAttr('disabled');
            }


            if ($('li[data-item_id="' + item_id + '"]').length) {
                $('li[data-item_id="' + item_id + '"]').slideUp('fast', function() {
                    $(this).remove();
                });
            }
        },

        registerEventListeners: function() {
            var _this = this;

            $(document).on('cart.ready', function(e) {
                // nothing...yet
            })
            .on('cart.addingItem', function(e, data, show_options) {
                _this.addingItem(data, show_options);
            })
            .on('cart.updateItemCount', function(e) {
                _this.updateItemCount();
            })
            .on('cart.addSuccess', function(e, response) {
                _this.addSuccess(response);
            })
            .on('cart.errorHandler', function(e, errors) {
                _this.errorHandler(errors);
            })
            .on('cart.removingItem', function(e, item_id) {
                _this.hideErrors();
                _this.removingItem(item_id);
            })
            .on('cart.itemRemoved', function(e, item_id, response) {
                _this.itemRemoved(item_id, response);
            })
            .on('cart.appendFromReview', function(e, data) {
                // Nothing...yet
            })
            .on('cart.appendSuccess', function(e, response) {
                _this.appendSuccess(response);
            })
            .on('cart.addFromAnchor', function(e, $element) {
                data = {};
                data.product = $element.attr('data-product');
                data.activation = $element.attr('data-activation');

                if (! data.activation) {
                    data.activation = 'new';
                }

                if (_this.hasOptions(data.product)) {
                    if ($element.attr('data-domain')) {
                        domain = $element.attr('data-domain').split('.');
                        data.options = {
                            domain: $element.attr('data-domain'),
                            tld: domain[1]
                        };

                        _this.addingItem(data.product);
                        _this.add(data);

                        return;
                    }

                    _this.showModal(data, true);

                    return;
                }

                if (data.product.split('.')[0] == 'registration') {
                    // adding domain registration/transfer
                    domain = $element.attr('data-domain').split('.');
                    data.options = {
                        domain: $element.attr('data-domain'),
                        tld: domain[1]
                    };
                }

                _this.addingItem(data.product);
            })
            .on('cart.updateTerm', function(e, response, old_item) {
                if (response.updated) {
                    $('.price[data-item_id="' + old_item + '"]').html('$' + response.updated.subtotal.toFixed(2)).attr('data-item_id', response.updated_id);
                    $('[data-item_id="' + old_item + '"]').attr('data-item_id', response.updated.id);
                    $('[data-parent="' + old_item + '"]').attr('data-parent', response.updated.id);
                    _this.updateTotal(response.cart_total);
                    if ($('select[name="review-term[]"]').length) {
                        $line_total = $('select[data-item_id="' + old_item + '"]').parent().parent().next();
                        $total = $('td.total');

                        $line_total.html('$' + response.updated.subtotal.toFixed(2));

                        _this.updateTotal(response.cart_total);

                        if (response.child_updated) {
                            $child_total = $('tr[data-item_id="' + response.child_updated.id + '"] td:nth-child(7)');
                            $child_total.html('$' + response.child_updated.subtotal.toFixed(2));
                        }
                    }
                }
            });
        },

        registerDocumentListeners: function() {
            var _this = this;

            $('a[href="' + _this.settings.modalElement + '"]').click(function(e) {
                e.preventDefault();
                _this.showModal(false, false);
            });

            $(_this.settings.modalElement).on('hidden', function() {
                _this.modalShown = false;
            });
        }
    };

    $.cartui = function(options) {
        return new CartUI(this, options);
    }

})(jQuery, window, document);