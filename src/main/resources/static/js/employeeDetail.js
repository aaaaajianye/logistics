new Vue({
    el:"#app",
    data:{
        employee:{
            id:"",                  //用户id
            username:"",            //用户账号
            name:"",                //用户名
            typeName:"",            //岗位
            phone:"",               //手机号
            created:"",             //入职时间
            updated:""
        },
        employee_repository:{
            id:"",                  //主键id
            repositoryId:"",        //公司id
            employeeId:"",          //员工id
            created:"",             //创建时间
            updated:""              //更新时间
        },
        repository:{
            id:"",
            repositoryName:"",      //公司名
            address:"",
            created:"",             //创建时间
            updated:""              //更新时间
        },
        adminName:"",               //员工名
        adminId:""                  //员工id
    },
    methods:{
        //判断我们的管理员是否已经登陆的方法
        isLogin:function () {
            var _this = this;
            axios.get('/logisticsSystem/employee/getEmployeeById')
                .then(function (response) {
                    if (response.data.code == 1) {
                        //如果已经登陆的话就把登录的对象返回给前端进行渲染
                        _this.adminName = response.data.data.name;
                        _this.adminId = response.data.data.id;
                    }
                })
                .catch(function (error) {
                    console.log(error);
                })
        },
        // 获取员工的信息
        getEmployeeById:function () {
            var _this = this;
            axios.get('/logisticsSystem/employee/getEmployeeById')
                .then(function (response) {
                    if (response.data.code == 1) {
                        // 将返回的数据赋值给当前的employee对象
                        _this.employee = response.data.data;
                    }
                })
                .catch(function (error) {
                    console.log(error);
                })
        },
        // 获取员工的工厂信息
        getRepositoryById:function () {
            var _this = this;
            axios.get('/logisticsSystem/employee/getRepositoryById')
                .then(function (response) {
                    if (response.data.code == 1) {
                        // 将返回的数据赋值给当前的repository对象（公司）
                        _this.repository = response.data.data;
                    }
                })
                .catch(function (error) {
                    console.log(error);
                })
        }
    },
    created:function () {
        this.isLogin();
        // 登录成功后展示员工信息
        this.getEmployeeById();
        // 登录成功后展示公司信息
        this.getRepositoryById();
    }
});