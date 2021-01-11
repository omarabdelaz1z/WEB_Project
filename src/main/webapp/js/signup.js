$(document).ready(function () {
    $('#registerForm').submit(function (e) {
        e.preventDefault();
    });

    $('#signup').click(function () {
        $("#signup").prop('disabled', true).prop('value', 'Signing Up...').show("slow");
        let dataString = $('#registerForm').serialize();

        const name = $('input#name').val();
        const email = $('input#email').val();
        const type = $('.type:checked').val();

        dataString =
            'name=' + name +
            '&type=' + type +
            '&email=' + email +
            '&g-recaptcha-response=' + grecaptcha.getResponse();

        $.ajax({
            type : 'POST',
            url : '/SignUp',
            data : dataString,
            dataType : 'json',
            success : function (data) {
                $("#signup").prop('disabled', false).prop('value', 'SignUp').show("slow");
                alert(data);
                $('#login').show("slow");
            },
            error : function (data) {
                alert(data);
            }
        });
    });
});