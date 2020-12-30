$(document).ready(function () {
    $('#loginForm').submit(function (e) {
        e.preventDefault();
    });

    $('#login').click(function (e) {
        let dataString = $('#loginForm').serialize();

        const email = $('input#email').val();
        const password = $('input#password').val();

        dataString = 'email=' + email + '&password=' + password + '&g-recaptcha-response=' + grecaptcha.getResponse();

        $.ajax({
            type : 'POST',
            url : '/Session',
            data : dataString,
            dataType : 'json',
            success : function (data) {
                if(data.success)
                    $("#ajaxResponse").html("Success");
            },
            error : function (data, status) {
                console.log("Something really bad happened " + status);
                $('#response').text(data.responseText);
            }
        });
    });
});