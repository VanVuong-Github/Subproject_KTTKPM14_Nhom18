function dangNhap(){
   
    var username = $('input[name="username"]').val()
    var password = $('input[name="password"]').val()
    $.ajax({
        type: 'POST',
        url: "http://localhost:9090/auth/login",
        data: JSON.stringify({
          "username":username,
          "password":password       
          }),
        error: function(e) {
            document.getElementById("err").innerHTML = e.responseJSON.message;
        },
        success: function(data) {
            localStorage.clear();
            console.log( data.accessToken)
            localStorage.token = "JWT "+ data.accessToken;
            console.log("JWT "+ data.accessToken);
            localStorage.username = data.username;
            window.location.href = "/";
        },
        dataType: "json",
        contentType: "application/json"
     });
     return false;
}