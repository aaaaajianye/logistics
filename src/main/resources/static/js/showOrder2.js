new Vue({
    el:"#app",
    data:{
        orders:{
            id:"",                  //订单id
            orderId:"",             //订单号
            startPoint:"",          //发货点
            endPoint:"",            //收货点
            distance:"",            //总距离
            consignor:"",           //发货人姓名
            consignorPhone:"",      //发货人电话
            addressee:"",           //收件人姓名
            addresseePhone:"",      //收件人电话
            weight:"",              //寄件规格/kg
            totalPrice:"",          //寄件总价
            payStatus:"",           //付款状态
            notes:"",               //寄件备注信息
            orderStatus:"",         //配送状态
            idCardOne:"",           //身份证正面
            idCardTwo:"",           //身份证反面
            created:"",             //创建时间
            updated:""              //更新时间
        },
        idCardOne:"",               //身份证正面
        idCardTwo:"",               //身份证反面
        ordersList:[]               //订单集合
    },
    methods:{
        // 根据存在session域中的订单号查询订单 selectBySessionId
        showOrders:function () {
            var _this = this;
            axios.get('/logisticsSystem/orders/selectBySessionId')
                .then(function (response) {
                    //把返回的user对象的id赋值给这里的id
                    if (response.data != null) {
                        _this.orders = response.data.data;
                        _this.getPayStatus(response.data.data.payStatus);
                        _this.getOrderStatus(response.data.data.orderStatus);
                        // alert("欢迎来到付款页面！");
                        //刷新页面
                        // window.location.href = "index.html";
                    } else {
                        alert("操作异常！请联系客服！");
                    }
                })
                .catch(function (error) {
                    console.log(error);
                })
        },
        // 根据付款状态获取状态信息 付款状态：[0]未付款 [1]已付款 [2]已退款
        getPayStatus:function(current_payStatus) {
            var _this = this;
            if (current_payStatus == 0) {
                _this.orders.payStatus = "未付款";
            } else if (current_payStatus == 1) {
                _this.orders.payStatus = "已付款";
            } else if (current_payStatus == 2){
                _this.orders.payStatus = "已退款";
            } else {
                _this.orders.payStatus = "异常状态";
            }
        },
        // 订单状态：[0]未收件 [1]已收件 [2]配送中 [3]已收货
        getOrderStatus:function(current_orderStatus) {
            var _this = this;
            if (current_orderStatus == 0) {
                _this.orders.orderStatus = "未收件";
            } else if (current_orderStatus == 1) {
                _this.orders.orderStatus = "已收件";
            } else if (current_orderStatus == 2){
                _this.orders.orderStatus = "配送中";
            } else if (current_orderStatus == 3){
                _this.orders.orderStatus = "已收货";
            } else {
                _this.orders.orderStatus = "异常状态";
            }
        },
        // 付款操作
        payOrders:function (orderId, price) {
            if (confirm("总计费用：[" + price + "元 ]是否确定付款？")) {
                //在当前方法中定义一个变量，表明是vue对象
                var _this = this;
                axios.get('/logisticsSystem/orders/updateOrderPayStatus', {
                    params:{
                        orderId:orderId
                    }
                })
                    .then(function (response) {
                        //把返回的user对象的id赋值给这里的id
                        if (response.data.code == 1) {
                            alert("订单付款成功！请关注订单的物流信息！");
                        } else {
                            alert("操作异常！请联系客服！");
                        }
                    })
                    .catch(function (error) {
                        console.log(error);
                    })
            }
        }
    },
    created:function () {
        //创建的时候先执行查询订单的方法
        this.showOrders();
    }
});