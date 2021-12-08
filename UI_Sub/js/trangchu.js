
function dangXuat() {
    if (confirm("Bạn có muốn đăng xuất?")) {
      localStorage.clear();
      window.location.href = "/dangnhap";
    } else {
      // false
    }
  }

function getALL() {
    {
      $.ajax({
        type: "GET",
        url: host+endpoint.user+localStorage.username,
        beforeSend: function(xhr) {
          if (localStorage.token) {
            xhr.setRequestHeader('Authorization', localStorage.token);
          }
        },
        success: function (data) {
          localStorage.id = data.user.userId;
          var username = document.getElementById("username");
          username.innerHTML = data.user.taiKhoan;
          var name = document.getElementById("name");
          name.innerHTML = data.user.ten;
          var gennder = document.getElementById("gennder");
          if ((data.user.gioiTinh == 0)) {
            gennder.innerHTML = "Nam";
          } else {
            gennder.innerHTML = "Nữ";
          }
         
          var email = document.getElementById("email");
          email.innerHTML = data.user.email;
          var carts = data.cart;
          document.getElementById('tableCartBody').innerHTML ="";
          for (var i in carts) {
          
            document.getElementById('tableCartBody').innerHTML +=
            '<tr><td style="text-align: center;vertical-align: middle;">'+carts[i].cartId+'</td>'+
            '<td style="text-align: center;vertical-align: middle;">'+carts[i].cartName+'</td>'+
            '<td style="text-align: center;vertical-align: middle;">'+carts[i].soLuong+'</td>'+
            '<td style="text-align: center;vertical-align: middle;">'+carts[i].thongTin+'</td>'+
            '</tr>';
           
          }
        
        },
        error: function (e) {
          window.location.href = "/dangnhap";
        },
      });
    }
  }
  $(function () {
    if (localStorage.username==null) window.location.href = "/dangnhap";
    getALL();
  });

function addCart(){
  /*
  {
    "cartId":3,
    "cartName":"name 3",
    "soLuong":1,
    "thongTin":"thong tin 2",
    "userId":1
}
*/
    document.getElementById("btnAdd").disabled=true;
    var cartName = $('input[name="cartName"]').val();
    var soLuong = $('input[name="soLuong"]').val();
    var thongTin = $('input[name="thongTin"]').val();
    var userId = localStorage.id;


    $.ajax({
      type: 'POST',
      url: host + endpoint.cart,
      data: JSON.stringify({
        "cartName":cartName,
        "soLuong":soLuong,
        "thongTin":thongTin,
        "userId":userId
        }),
        beforeSend: function(xhr) {
          if (localStorage.token) {
            xhr.setRequestHeader('Authorization', localStorage.token);
          }
        },
      error: function(e) {
        console.log(e);
        document.getElementById("btnAdd").removeAttribute("disabled");
      },
      success: function(data) {
        document.getElementById("btnAdd").removeAttribute("disabled");
        document.getElementById("cartName").value="";
        document.getElementById("soLuong").value="";
        document.getElementById("thongTin").value="";
        getALL();  
      },
      dataType: "json",
      contentType: "application/json"
    });

return false;
}