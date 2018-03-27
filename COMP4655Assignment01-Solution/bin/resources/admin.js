$(function (){
    var popup_prefix = "POPUP_",
        popup_counter = 0;
    jQuery(".segment .head .popup").each(function(){
        this.id = this.id || popup_prefix + popup_counter++;
    }).hide();
        
    $(".segment .head").each(function(){
        var head = $(this),
            popup = head.find(".popup");
        head.append("<a>" + popup.attr("title") + "</a>");
        head.find("a").click(function(){
            popup.modal();
        });
        
    });
   $(".crawler .report").click(function (evt){
       var a = evt.target;
      if (a.tagName == "A") {
          evt.preventDefault();
          $.get(a.href, {}, function (data){
              $.modal(data, {containerId:"reportModalContainer"});
          });
      }; 
   });
   $(document).ajaxError(function (event, request, settings) {
       $.modal("<div class='popup error'><h3>Load Error</h3>" + 
            "<div class='content'><p>Something happened while " +
            "processing your request. Either retry your action " + 
            "or, if that hasn't worked, inform Client Support.</p></div></div>",
            {
                containerId:"errorModalContainer",
                overlayId: "errorModalOverlay"
            }
        );
   });
	$(".alert").hide().eq(0).each(function(){
		//modal() will open an empty modal even if there are NO elements 
		// in the jQuery object. We avoid the problem by using each. 
		$(this).modal();
	});
  /* 
   function showFormResult (data, statusText) {
       var opts = {
           containerId:"modalContainer",
           overlayId: "modalOverlay"
       };
       
       if (data.status === "error") {
           opts = {
                  containerId:"errorModalContainer",
                  overlayId: "errorModalOverlay"
              };
       }

       $.modal(data.message, {onClose: function(){
               window.location.reload();
           }, containerID: opts.containerId, overlayId: opts.overlayId
       });
   }
   
   $('form').ajaxForm({ success: showFormResult, dataType: 'json'});
   */
});