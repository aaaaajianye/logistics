new Vue({
    el:"#app",
    data:{
        orders:{
            id:"",                  //主键id
            orderId:"",             //订单号
            startPoint:"",          //发货点
            endPoint:"",            //收件点
            distance:"",            //距离
            consignor:"",           //发货人
            consignorPhone:"",      //发货人电话
            addressee:"",           //收件人
            addresseePhone:"",      //收件人电话
            weight:"",              //邮件总重量
            repository_id:"",       //工厂id
            totalPrice:"",          //订单总价
            payStatus:"",           //付款状态
            orderStatus:"",         //订单配送状态
            created:""              //订单下单时间
        },
        repository_name:"",         //工厂名
        repository_id:"",           //工厂id
        ordersList:[],              //员工岗位集合
        totalPage:"",               //总页数
        currentPage:"1",             //当前页数,默认为1
        adminName:"",               //员工名
        adminId:""                  //员工id
    },
    methods:{
        //获取所有订单的方法
        getAllOrders:function () {
            var _this = this;
            axios.get('/logisticsSystem/ordersEmployeeController/getRepositoryPage',{
                params:{
                    currentPage:_this.currentPage
                }
            })
                .then(function (response) {
                    //把返回的集合赋值给employeeList
                    _this.ordersList = response.data.data.list;
                })
                .catch(function (error) {
                    console.log(error);
                })
        },
        //获取所有订单的方法
        getAllOrders2:function () {
            var _this = this;
            axios.get('/logisticsSystem/ordersEmployeeController/getRepositoryPage',{
                params:{
                    currentPage:_this.currentPage
                }
            })
                .then(function (response) {
                    //把返回的集合赋值给employeeList
                    _this.ordersList = response.data.data.list;
                })
                .catch(function (error) {
                    console.log(error);
                })
        },
        //下一页
        nextPage:function () {
            //在当前方法中定义一个变量，表明是vue对象
            var _this = this;
            //判断当前页面是否为最后一页
            if (_this.currentPage == _this.totalPage) {
                //页面跳转
                alert("已经是最后一页了噢！");
            } else {
                //页数 + 1
                _this.currentPage = Number(_this.currentPage) + 1
                //重新执行查询分页方法
                _this.getAllOrders();
            }
        },
        //上一页
        prePage:function () {
            //在当前方法中定义一个变量，表明是vue对象
            var _this = this;
            //判断当前是否为第一页
            if (_this.currentPage == 1){
                //如果是第一页的话就不用动,返回第一页
                alert("已经是第一页了噢！");
            } else {
                //如果不是第一页的话就直接currentPage - 1
                _this.currentPage = Number(_this.currentPage) - 1;
                //重新执行查询分页方法
                _this.getAllOrders();
            }
        },
        //获取订单的总页数
        getAllTotalPage:function () {
            //在当前方法中定义一个变量，表明是vue对象
            var _this = this;
            axios.get('/logisticsSystem/ordersEmployeeController/getRepositoryPage', {
                params:{
                    currentPage:_this.currentPage
                }
            })
                .then(function (response) {
                    //把返回的总页数赋值给这里的总页数
                    _this.totalPage = response.data.data.totalPage;
                })
                .catch(function (error) {
                    console.log(error);
                })
        },
        // 查询当前订单
        showOrder:function (id) {
            axios.get('/logisticsSystem/orders/setOrderSessionId', {
                params:{
                    orderSessionId:id
                }
            })
                .then(function (response) {
                    if (response.data.code == 1) {
                        // 通过第二个页面进行跳转
                        window.open("showOrderAdmin.html", "_blank");
                    }
                })
                .catch(function (error) {
                    console.log(error);
                })
        },
        // 出仓操作
        chucang:function (orderId) {
            var _this = this;
            axios.get('/logisticsSystem/repository/chucang', {
                params:{
                    orderId: orderId
                }
            })
                .then(function (response) {
                    if (response.data.code == 1) {
                        alert("出仓成功！");
                        // 刷新当前页面
                        // window.location.href = "repositoryOrder.html";
                        _this.getAllOrders2();
                    }
                })
                .catch(function (error) {
                    console.log(error);
                })
        },
        // 判断是否已经登陆的方法，已登录的话就返回登录的对象
        isLogin:function () {
            var _this = this;
            axios.get('/logisticsSystem/repository/getRepositoryBySessionId')
                .then(function (response) {
                    if (response.data.code == 1) {
                        _this.repository_name = response.data.data.repositoryName;
                        _this.repository_id = response.data.data.id;
                    }
                })
                .catch(function (error) {
                    console.log(error);
                })
        }
    },
    created:function () {
        //一刷新页面就判断该管理员是否已经登陆
        this.isLogin();
        //弹出所有的用户信息
        this.getAllOrders();
        //获取所有的用户总数,返回总页数
        this.getAllTotalPage();
    }
});