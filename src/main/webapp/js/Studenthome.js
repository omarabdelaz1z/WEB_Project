//Update Profile Effects
$(document).ready(function(){
    $('#edit').click(function(){
        $('.data').hide('slow');
        $('.input').show('slow');
        $('#edit').hide(1000);
        $('#cancel').show('slow');
        $('#save').show('slow');
    });

    $('#cancel').click(function(){
        $('.data').show('slow');
        $('.input').hide('slow');
        $('#edit').show(1000);
        $('#save').hide('slow');
        $('#cancel').hide('slow');
    });
});

//Update Profile
$(document).ready(function () {
    $('#Profile').submit(function (e) {
        e.preventDefault();
    });

    $('#save').click(function () {
        $("#save").prop('disabled', true).prop('value', 'Saving...').show("slow");
        let dataString = $('#Profile').serialize();

        const name = $('#name').val();
        const password = $('#password').val();

        dataString =
            'name=' + name +
            '&password=' + password;

        $.ajax({
            type : 'POST',
            url : '/UpdateUserData',
            data : dataString,
            dataType : 'json',
            success : function (data) {
                $("#save").prop('disabled', false).prop('value', 'Save').show("slow");
                alert(data);
                window.location.reload();
            },
            error : function (data) {
                $("#save").prop('disabled', false).prop('value', 'Save').show("slow");
                alert(data);
                window.location.reload();
            }
        });
    });
});

//Contact
$(document).ready(function () {
    $('#Contact').submit(function (e) {
        e.preventDefault();
    });

    $('#SendMessage').click(function () {
        $("#SendMessage").prop('disabled', true).prop('value', 'Sending...').show("slow");
        let dataString = $('#Contact').serialize();

        const email = $('#email').val();
        const subject = $('#subject').val();
        const message = $('#message').val();

        dataString =
            'email=' + email +
            '&subject=' + subject +
            '&message=' + message;

        $.ajax({
            type : 'POST',
            url : '/SendEmail',
            data : dataString,
            dataType : 'json',
            success : function (data) {
                $("#SendMessage").prop('disabled', false).prop('value', 'Send').show("slow");
                alert(data);
                window.location.reload();
            },
            error : function (data) {
                $("#SendMessage").prop('disabled', false).prop('value', 'Send').show("slow");
                alert(data);
                window.location.reload();
            }
        });
    });
});

//Search logic
(function (document) {
    'use strict';
    var TableFilter = (function(Arr){
        var input;

        function onInputEvent(e) {
            input = e.target;
            var tables = document.getElementsByClassName(input.getAttribute('data-table'));
            Arr.forEach.call(tables, function(table){
                Arr.forEach.call(table.tBodies, function (tbody) {
                    Arr.forEach.call(tbody.rows, filter);
                });
            });
        }

        function filter(row) {
            var text = row.textContent.toLowerCase(), val = input.value.toLowerCase();
            row.style.display = text.indexOf(val) === -1 ? 'none' : 'table-row';
        }

        return{
            init: function () {
                var inputs = document.getElementsByClassName('search');
                Arr.forEach.call(inputs, function (input) {
                    input.oninput = onInputEvent;
                });
            }
        };
    })(Array.prototype);

    document.addEventListener('readystatechange', function(){
        if(document.readyState === 'complete'){
            TableFilter.init();
        }
    });
})(document);

function searchBySubject() {
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById('search');
    filter = input.value.toUpperCase();
    table = document.getElementById('staffData');
    tr = table.getElementsByTagName('tr');

    for(i = 0; i < tr.length; i++){
        td = tr[i].getElementsByTagName('td')[1];
        if(td){
            txtValue = td.textContent || td.innerText;
            if(txtValue.toUpperCase().indexOf(filter) > -1){
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}