$(document).ready(function () {
    $('#loginForm').submit(function (e) {
        e.preventDefault();
    });

    $('#login').click(function () {
        let dataString = $('#loginForm').serialize();

        const email = $('input#email').val();
        const password = $('input#password').val();

        dataString =
            'email=' + email +
            '&password=' + password +
            '&g-recaptcha-response=' + grecaptcha.getResponse();

        $.ajax({
            type : 'POST',
            url : '/Session',
            data : dataString,
            dataType : 'json',
            success : function (data) {
                alert(data);
            },
            error : function (data) {
                alert(data);
            }
        });
    });
});