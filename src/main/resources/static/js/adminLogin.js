new Vue({
    el:"#app",
    data:{
        login_user:{
            id:"",           // 管理员id
            username:"",    // 登录账号
            password:""    // 登录密码
        }
    },
    methods:{
        // 登录方法
        login:function (username,password) {
            //在当前方法中定义一个变量，表明是vue对象
            var _this = this;
            axios.get('/logisticsSystem/admin/loginAdmin',{
                params:{
                    username:username,      //账号
                    password:password       //密码
                }
            })
                .then(function (response) {
                    if (response.data.data.id == null || response.data.data.id == 0) {
                        alert("账号或密码有误！请重新输入！");
                        //清空username和password重新登录
                        _this.login_user.username = "";
                        _this.login_user.password = "";
                    } else {
                        alert("登录成功(●'◡'●)！欢迎您，尊敬的：" + response.data.data.name);
                        //登录成功后跳转到管理端页面
                        window.location.href = "employeeAdmin.html";
                    }
                })
                .catch(function (error) {
                    console.log(error);
                })
        }
    }
});