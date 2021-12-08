function dangNhap(){
   
    var username = $('input[name="username"]').val()
    var password = $('input[name="password"]').val()
    $.ajax({
        type: 'POST',
        url: host +endpoint.dangNhap,
        data: JSON.stringify({
          "username":username,
          "password":password       
          }),
        error: function(e) {
            document.getElementById("err").innerHTML = e.responseJSON.message;
        },
        success: function(data) {
            localStorage.clear();
            localStorage.token = data.accessToken;
            localStorage.username = data.username;
            window.location.href = "/";
        },
        dataType: "json",
        contentType: "application/json"
     });
     return false;
}