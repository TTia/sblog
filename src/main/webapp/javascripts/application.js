// This is a manifest file that'll be compiled into application.js, which will include all the files
// listed below.
//
// Any JavaScript/Coffee file within this directory, lib/assets/javascripts, vendor/assets/javascripts,
// or vendor/assets/javascripts of plugins, if any, can be referenced here using a relative path.
//
// It's not advisable to add code directly here, but if you do, it'll appear at the bottom of the
// compiled file.
//
// Read Sprockets README (https://github.com/sstephenson/sprockets#sprockets-directives) for details
// about supported directives.
//
//= require jquery
//= require jquery_ujs
//= require jquery-ui
//= require jquery-ui/menu
//= require jquery-ui/selectmenu
//= require turbolinks
//= require_tree .

$(window).bind('page:change', autocomplete);

$(document).ready(autocomplete);

function autocomplete() {
    if ($("#search_input_text").length) {
        $("#search_input_text").autocomplete({
            source: function (request, response) {
                $.ajax({
                    url: "/posts/autocomplete_title",
                    data: {title: $("#search_input_text").val()},
                    success: function (data) {
                        console.log(data);
                        response(data);
                    },
                    failure: function () {
                        console.log("Failure");
                    }
                })
            },
            minLength: 2,
            focus: function (event, ui) {
                $("#search_input_text").val(ui.item.value);
            },
            select: function (event, ui) {
                $("#search_input_text").val(ui.item.value);
            }
        });
    }
}

function switch_easter_egg() {
    var woodstock = $('#woodstock');
    if (!woodstock.length) {
        var img = document.createElement("img");
        img.src = "/images/woodstock.png";
        img.id = "woodstock";
        img.width = "48";
        img.height = "48";

        document.getElementById("footer").appendChild(img);
    }else{
        woodstock.remove();
    }

}