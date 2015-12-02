jQuery(document).ready(function(){
	jQuery('.datemask').mask("99/99/9999");
	jQuery('.moneymask').priceFormat({prefix:'$', thousandsSeparator:'', clearPrefix: true});
	var desctext = jQuery('#desc').val();
	if (desctext) {
		jQuery('#desc').val(desctext.replace(/<br *\/?>/gi, '\n'));
	}
});
