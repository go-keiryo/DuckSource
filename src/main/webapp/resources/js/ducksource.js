jQuery(document).ready(function($) {
	$('.datemask').mask("99/99/9999");
	$('.moneymask').priceFormat({prefix:'$', thousandsSeparator:'', clearPrefix: true});
});