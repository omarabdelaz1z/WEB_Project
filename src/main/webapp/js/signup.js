//Decides whether reveal the subjects if the registering is Staff Member
function reveal(){
    if($('.type:checked').val() === 'staffMember'){
        $('#reveal').show('slow');
    }
    else {
        $('#reveal').hide('slow');
    }
}

//Validating data with the Servlet
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
        const subject = $('.subject:checked').val();

        if(type === 'staffMember'){
            dataString =
                'name=' + name +
                '&type=' + type +
                '&email=' + email +
                '&subject=' + subject +
                '&g-recaptcha-response=' + grecaptcha.getResponse();
        } else {
            dataString =
                'name=' + name +
                '&type=' + type +
                '&email=' + email +
                '&g-recaptcha-response=' + grecaptcha.getResponse();
        }

        $.ajax({
            type : 'POST',
            url : '/SignUp',
            data : dataString,
            dataType : 'json',
            success : function (data) {
                $("#signup").prop('disabled', false).prop('value', 'SignUp').show("slow");
                if(data === "SUCCESS"){
                    alert("Registered Successfully");
                    $('#login').show("slow");
                } else {
                    alert("You missed the captcha");
                    $('#login').hide();
                }
            },
            error : function (data) {
                alert(data);
                $('#login').hide();
            }
        });
    });
});