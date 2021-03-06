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
            notes:"",               //寄件备注信息
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
        // //根据产品名进行模糊查询
        // getProsByName:function () {
        //     //在当前方法中定义一个变量，表明是vue对象
        //     var _this = this;
        //     //跳转到列表展示页面
        //     window.location.href = "liebiao.html?pName=" + _this.pro.pName + "&currentPage=1";
        // },
        // //获取已经登陆的user的方法
        // getLoginUser:function () {
        //     //在当前方法中定义一个变量，表明是vue对象
        //     var _this = this;
        //     axios.get('/Mishop/user/getUser')
        //         .then(function (response) {
        //             //把返回的user对象的id赋值给这里的id
        //             _this.user.id = response.data.id;
        //             _this.user.name = response.data.name;
        //         })
        //         .catch(function (error) {
        //             console.log(error);
        //         })
        // },
        // //退出当前账号
        // loginOut:function () {
        //     if (confirm("是否确定要退出账号？")) {
        //         axios.get('/Mishop/user/loginOut')
        //             .then(function (response) {
        //                 //把返回的user对象的id赋值给这里的id
        //                 if (response.data == "OK") {
        //                     alert("退出成功！");
        //                     //刷新页面
        //                     window.location.href = "index.html";
        //                 } else {
        //                     alert("操作异常！请联系客服！");
        //                 }
        //             })
        //             .catch(function (error) {
        //                 console.log(error);
        //             })
        //     }
        // },

        uploadCardOne:function(event){
            //获取图片
            var _this = this;
            _this.idCardOne = event.target.files[0];

            //上传图片
            event.preventDefault();

            var formData = new FormData();
            formData.append('upload', _this.idCardOne);

            var config = {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            }
            axios.post('/logisticsSystem/upload/uploadFile',formData,config)
                .then(function (response) {
                    if (response.data.code == 1) {
                        alert("身份证正面照上传" + response.data.msg);
                        _this.idCardOne = response.data.data;
                        alert("idCardOne：" + _this.idCardOne);
                    }
                })
                .catch(function (error) {
                    console.log(error);
                })
        },
        uploadCardTwo:function(event){
            //获取图片
            var _this = this;
            _this.idCardTwo = event.target.files[0];

            //上传图片
            event.preventDefault();

            var formData = new FormData();
            formData.append('upload', _this.idCardTwo);

            var config = {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            }
            axios.post('/logisticsSystem/upload/uploadFile',formData,config)
                .then(function (response) {
                    if (response.data.code == 1) {
                        alert("身份证反面照上传" + response.data.msg);
                        _this.idCardTwo = response.data.data;
                        alert("idCardTwo：" + _this.idCardTwo);
                    }
                })
                .catch(function (error) {
                    console.log(error);
                })
        },
        // 提交订单
        submitOrder:function(){
            var _this = this;
            // alert(_this.orders.startPoint + "-" + _this.orders.endPoint + "-" + _this.orders.consignor + "-" + _this.orders.consignorPhone
            //     + _this.idCardTwo + "-" + _this.idCardOne + "-" + _this.orders.weight + "-" + _this.orders.notes + "-"
            //     + "-" + _this.orders.addresseePhone + "-" + _this.orders.addressee);
            axios.get('/logisticsSystem/orders/insertOrders', {
                params:{
                    startPoint:_this.orders.startPoint,
                    endPoint:_this.orders.endPoint,
                    consignor:_this.orders.consignor,
                    consignorPhone:_this.orders.consignorPhone,
                    addressee:_this.orders.addressee,
                    addresseePhone:_this.orders.addresseePhone,
                    weight:_this.orders.weight,
                    notes:_this.orders.notes,
                    idCardOne:_this.idCardOne,
                    idCardTwo:_this.idCardTwo
                }
            })
                .then(function (response) {
                    //把返回的user对象的id赋值给这里的id
                    if (response.data.code == 1) {
                        alert("订单生成成功！请立即付款！");
                        // 页面跳转
                        window.location.href = "showOrder.html";
                    } else {
                        alert("操作异常！请联系客服！");
                    }
                })
                .catch(function (error) {
                    console.log(error);
                })
        },
        // 订单的模糊查询
        search:function (id) {
            // 订单id
            // alert(id);
            axios.get('/logisticsSystem/orders/setOrderSessionOrderId', {
                params:{
                    orderSessionOrderId:id
                }
            })
                .then(function (response) {
                    //把返回的user对象的id赋值给这里的id
                    if (response.data.code == 1) {
                        alert("查询成功！");
                        // 页面跳转
                        window.location.href = "showOrder2.html";
                    } else {
                        alert("订单不存在！");
                    }
                })
                .catch(function (error) {
                    console.log(error);
                })

        }
    },
    created:function () {
        //创建的时候先执行判断是否登录的方法
        // this.getLoginUser();
    }
});