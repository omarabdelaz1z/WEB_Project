$(document).ready(function () {
    $('#ReplyForm').submit(function (e) {
        e.preventDefault();
    });

    $('#Reply').click(function () {
        $("#Reply").prop('disabled', true).prop('value', 'Sending...').show("slow");
        let dataString = $('#ReplyForm').serialize();

        const email = $('.email').val();
        const subject = $('.subject').val();
        const message = $('.message').val();

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
                $("#Reply").prop('disabled', false).prop('value', 'Send').show("slow");
                alert(data);
                window.location.reload();
            },
            error : function (data) {
                $("#Reply").prop('disabled', false).prop('value', 'Send').show("slow");
                alert(data);
                window.location.reload();
            }
        });
    });
});