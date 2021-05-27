'use strict';

CKEDITOR.plugins.add( 'contentlink', {

  requires : ['richcombo'],

  init: function( editor ) {

    console.log('In CKEDITOR.plugins init()');
    var items = [];
    
             $.ajax({
		  url: select_content_url,
		  context: document.body
		}).done(function(data,status) {
			var elements = data;
			for(var p in elements){
			var el =elements[p];
			var tag = '{{ content:'+el.id+' }}';
			items.push(el);
		   }	
                		
		});
		
		
		
    var config = editor.config;
    editor.ui.addRichCombo('contentlink', {
      label : "Pagine", // label displayed in toolbar
      title : 'Link a contenuto', // tooltip text when hovering over the dropdown
      multiSelect : false,

      // use the same style as the font/style dropdowns
      panel : {
        css: [ CKEDITOR.skin.getPath( 'editor' ) ].concat( config.contentsCss ),
      },

      init : function() {
      
	      for(var item in items){
	      var el = items[item];
	      var tag =  '<a href="';
	      tag +=  '{{ content:'+el.id+' }}">Visita pagina '+el.title;
		 tag +=  '</a>';
      		 this.add( tag, el.title ,tag  );
	      }
      
	
		
        // dropdown options:
        // this.add( VALUE, HTML, TEXT );
        // VALUE - The value we get when the row is clicked
        // HTML - html/plain text that should be displayed in the dropdown
        // TEXT - displayed in popup when hovered over the row.
        //this.add( "<p>Widget 1</p>", "Widget 1", "Widget 1" );
//        this.add( "<p>Widget 2</p>", "Widget 2", "Widget 2" );
      },

      // this function is called when a row is clicked
      onClick: function(value) {
        editor.insertHtml(value);
        console.log(value);
      },
    });
  }
});