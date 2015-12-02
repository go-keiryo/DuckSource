jQuery( document ).ready(function() {
	jQuery('.datemask').mask("99/99/9999");
	jQuery('.moneymask').priceFormat({prefix:'$', thousandsSeparator:'', clearPrefix: true});
});
