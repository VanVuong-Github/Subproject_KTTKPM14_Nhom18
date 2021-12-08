function dangKy(){
        document.getElementById("btndangky").disabled=true;
        var username = $('input[name="username"]').val();
        var password = $('input[name="password"]').val();
        var email = $('input[name="email"]').val();
        var name = $('input[name="name"]').val();
        
        var gennder =  parseInt($('select[name="gennder"]').val().toString());
        var deviceToken="";
       
        $.ajax({
          type: 'POST',
          url: host + endpoint.dangKy,
          data: JSON.stringify({
            "username":username,
            "password":password
            }),
          error: function(e) {
            document.getElementById("err").innerHTML = e.responseJSON.message;
            document.getElementById("btndangky").removeAttribute("disabled");
          },
          success: function(data) {
            
              $.ajax({
                type: 'POST',
                url: host + endpoint.user + 'saveUser',

                data: JSON.stringify({
                  "taiKhoan":username,
                  "matKhau":password,
                  "email":email,
                  "ten":name,
                  "gioitinh":gennder,
                  }),
                error: function(e) {
                  document.getElementById("err").innerHTML = e.responseJSON;
                  document.getElementById("btndangky").removeAttribute("disabled");
                },
                success: function(data) {
                  alert("Đăng ký thành công")
                    window.location.href = "/dangnhap";
                  
        },
        dataType: "json",
        contentType: "application/json"
    });
     
            
          },
          dataType: "json",
          contentType: "application/json"
       });
      
       return false;
}

