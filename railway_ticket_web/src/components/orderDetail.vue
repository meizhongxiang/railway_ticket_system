<template>
  <div>
    <div class="tt33"><span style="padding-left: 140px;">铁路客运订单中心</span>
      <!--<Button @click="checkJump" style="margin-top:1vh;float:right;border:none;font-size:14px;background: #57a3f3;color: white;">{{this.loginMessage}}</Button>-->
    </div>
    <div class="e1">
      <div class="t3">
        <div class="t5">
          <Table  border :columns="columns3" :data="data3"></Table>
        </div>
      </div>
      <Modal
        v-model="modal2"
        value="mondal2Value"
        title="改签确认"
        ok-text="确定"
        cancel-text="取消"
        @on-ok="handleSubmit"
        @on-cancel="cancel1">
        <p style="margin-left:180px;font-size: 13pt;">车&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;次：{{mondal2Value.trainNumber}}</p>
        <p style="margin-left:180px;font-size: 13pt;">出&nbsp;&nbsp;发&nbsp;&nbsp;地：{{mondal2Value.startSite}}</p>
        <p style="margin-left:180px;font-size: 13pt;">到&nbsp;&nbsp;达&nbsp;&nbsp;地：{{mondal2Value.endSite}}</p>
        <p style="margin-left:180px;font-size: 13pt;">出发时间：{{mondal2Value.startTime}}</p>
        <p style="margin-left:180px;font-size: 13pt;">到达时间：{{mondal2Value.endTime}}</p>
        <p style="margin-left:180px;font-size: 13pt;">用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;时：{{mondal2Value.useTime}}</p>
        <p style="margin-left:180px;font-size: 13pt;">坐&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：{{mondal2Value.ticketNum}}</p>
        <p style="margin-left:180px;font-size: 13pt;">票&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价：{{mondal2Value.price}}</p>
        <p style="font-size:11pt;color:red;">*距离发车时间大于48小时的不收取改签费用，
          小于48小时大于24小时的收取5元的手续费，
          小于24小时的收取5元的手续费，
          发车前15分钟不能办理改签业务，敬请谅解！</p>

      </Modal>
      <Modal
        v-model="modal3"
        value="mondal3Value"
        title="退票确认"
        ok-text="确定"
        cancel-text="取消"
        @on-ok="handleSubmit"
        @on-cancel="cancel1">
        <p style="margin-left:180px;font-size: 13pt;">车&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;次：{{mondal3Value.trainNumber}}</p>
        <p style="margin-left:180px;font-size: 13pt;">出&nbsp;&nbsp;发&nbsp;&nbsp;地：{{mondal3Value.startSite}}</p>
        <p style="margin-left:180px;font-size: 13pt;">到&nbsp;&nbsp;达&nbsp;&nbsp;地：{{mondal3Value.endSite}}</p>
        <p style="margin-left:180px;font-size: 13pt;">出发时间：{{mondal3Value.startTime}}</p>
        <p style="margin-left:180px;font-size: 13pt;">到达时间：{{mondal3Value.endTime}}</p>
        <p style="margin-left:180px;font-size: 13pt;">用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;时：{{mondal3Value.useTime}}</p>
        <p style="margin-left:180px;font-size: 13pt;">坐&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：{{mondal3Value.ticketNum}}</p>
        <p style="margin-left:180px;font-size: 13pt;">票&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价：{{mondal3Value.price}}</p>
        <p style="font-size:11pt;color:red;">*距离发车时间大于48小时的不收取退票费用，
          小于48小时大于24小时的收取5元的手续费，
          小于24小时的收取5元的手续费， 敬请谅解！</p>

      </Modal>
    </div>
    <div class="t4">
      <Page :current.sync="currentPage" :total="dataCount" @on-change="change"/>
    </div>
    <div style="text-align: center;">
      <div class="foot">
        版权所有© 梅忠翔 王芸 周智慧
        <br />2017-2019
      </div>
    </div>
  </div>
</template>

<script>
    export default {
        data() {
            return {
                currentPage:1,
                payPwd:'',
                status:0,
                loginMessage:this.status==0?"请登录":"个人中心",
                modal2:false,
                mondal2Value:'',
                modal3:false,
                mondal3Value:'',
                data3:[],
                dataCount:8,
                columns3: [
                    {
                        title: '车次',
                        key: 'trainNumber',
                        align: 'center',
                        width:100
                    },
                    {
                        title: '出行日期',
                        key: 'startDate',
                        align: 'center',
                        width:120
                    },
                    {
                        title: '乘车人',
                        align: 'center',
                        key: 'passenger',
                        width:110
                    },
                    {
                        title: '坐席',
                        align: 'center',
                        key: 'ticketClass',
                        width:75,
                        render: (h, params) => {
                            return h('div', [
                                params.row.ticketClass==1?'一等座':'二等座'
                            ]);
                        }
                    },
                    {
                        title: '坐号',
                        align: 'center',
                        key: 'ticketNum',
                        width:100
                    },
                    {
                        title: '出发地',
                        width: 130,
                        align: 'center',
                        key: 'startSite'
                    },
                    {
                        title: '出发时间',
                        width: 100,
                        align: 'center',
                        key: 'startTime'
                    },
                    {
                        title: '到达地',
                        width: 130,
                        align: 'center',
                        key: 'endSite'
                    },
                    {
                        title: '到达时间',
                        width: 100,
                        align: 'center',
                        key: 'endTime'
                    },
                    {
                        title: '用时',
                        width: 100,
                        align: 'center',
                        key: 'useTime'
                    },
                    {
                        title: '票价',
                        align: 'center',
                        width: 80,
                        key: 'price'
                    },
                    {
                        title: '购票日期',
                        key: 'buyDate',
                        align: 'center',
                        width:120
                    },
                    {
                        title: '状态',
                        align: 'center',
                        width: 100,
                        key: 'ticketStatus',
                        render: (h, params) => {
                            return h('div', [
                                params.row.ticketStatus==1?'已出票':(params.row.ticketStatus==2?'待出票':params.row.ticketStatus==0?'退票完成':params.row.ticketStatus==4?'改签-待出票':params.row.ticketStatus==5?'改签-已出票':'订单异常')
                            ]);
                        }
                    },
                    {
                        title: '操作',
                        key: 'action',
                        width: 150,
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'primary',
                                        disabled:params.row.ticketStatus==1?true:
                                            (params.row.ticketStatus==2?false:
                                                params.row.ticketStatus==0?true:
                                                    params.row.ticketStatus==4?true:
                                                        params.row.ticketStatus==5?true:true),
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            this.mondal2Value = params.row;
                                            this.mondal2Value.desc=3;
                                            this.modal2 = true;
                                        }
                                    }
                                }, '改签'),
                                h('Button', {
                                    props: {
                                        type: 'primary',
                                        disabled:params.row.ticketStatus==1?true:
                                            (params.row.ticketStatus==2?false:
                                                params.row.ticketStatus==0?true:
                                                    params.row.ticketStatus==4?false:
                                                      params.row.ticketStatus==5?true:true),
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            this.mondal3Value = params.row;
                                            this.mondal3Value.desc=0;
                                            this.modal3 = true;
                                        }
                                    }
                                }, '退票')
                            ]);
                        }
                    }
                ]
            }
        },
        created() {
            if(localStorage.getItem("loginst") != '1'){
              this.$router.push("/index");
            }else{
                this.change();
            }
        },
        methods:{
            change(){
                let parma=[];
                parma.currentPage = this.currentPage;
                this.query(parma);
            },
            cancel1(){

            },
            handleSubmit(){
                if(this.mondal2Value.desc == 3){
                    this.$router.push({path: 'ticketSelect',
                        query:{
                            startSite: this.mondal2Value.startSite,
                            endSite: this.mondal2Value.endSite,
                            startDate: this.mondal2Value.startDate,
                            upStatus:3,
                            upTrainNumber:this.mondal2Value.trainNumber,
                            upPrice:this.mondal2Value.price,
                            upTicketClass:this.mondal2Value.ticketClass/*=='一等座'?1:2*/
                        }})
                }else if(this.mondal3Value.desc==0){
                    let url = this.$root.url + "/proxy/3000003";
                    var params = {
                        userAccountId:localStorage.getItem("aid"),
                        userId:localStorage.getItem("uid"),
                        updStartDate:this.mondal3Value.startDate,
                        updTrainNumber:this.mondal3Value.trainNumber,
                        ticketStatus:0,
                        updPrice:this.mondal3Value.price,
                        updTicketClass:this.mondal3Value.ticketClass/*=='一等座'?1:2*/
                    };
                    this.$axios({
                        url: url,
                        method: 'post',
                        data: params,
                        responseType: 'json',
                        headers: {
                            "Access-Control-Allow-Origin": this.$root.uorigin,
                            "Access-Control-Allow-Credentials": true,
                            "withCredentials": true
                        }
                    }).then(res => {
                        var data = res.data;
                        if (data.rspCode == "0000") {
                            this.$Message.success(data.rspDesc);
                            this.change();
                        } else {
                            this.$Message.error(data.rspDesc);
                        }
                    }).catch(err => {
                        this.$Message.error('网络连接失败，请稍后重试');
                    });
                }else{

                }
            },
            checkJump(){
                    this.$router.push({path: 'userDetail',
                        query:{
                            startSite: this.form.startSite,
                            endSite: this.form.endSite,
                            startTime: this.form.startTime,
                        }})
            },
            back() {
                this.$router.push({path: 'index'})
            },
            query(parma){
                let url = this.$root.url + "/proxy/3000001";
                let params = {
                    userId: localStorage.getItem("uid"),
                    userAccountId:localStorage.getItem("aid"),
                    page:parma.currentPage
                };
                this.$axios({
                    url: url,
                    method: 'post',
                    data: params,
                    responseType: 'json',
                    headers: {
                        "Access-Control-Allow-Origin": this.$root.uorigin,
                        "Access-Control-Allow-Credentials": true,
                        "withCredentials": true
                    }
                }).then(res => {
                    var data = res.data;
                    if (data.rspCode == "0000") {
                        this.data3 = [];
                        this.data3 = data.row;
                        this.dataCount = data.dataCount;
                    } else {
                        this.data3 = [];
                        this.dataCount=10;
                        this.$Modal.error({
                            title: '系统提示',
                            content: '<p>' + data.rspDesc  + '</p>',
                            okText: '确认',
                            onOk: () => {
                            }
                        })
                    }
                }).catch(err => {
                    this.data3 = [];
                    this.dataCount=10;
                    this.$Modal.error({
                        title: '系统提示',
                        content: '<p>' + "查询错误，请稍后重试！" + '</p>',
                        okText: '确认',
                        onOk: () => {
                        }
                    })
                });
            },
        }
    }
</script>

<style scoped>
  body{
  }

  .listyle .al {
    display: block;
    padding: 50px;
    color: white;
    background-color: #8ce6b0;
    border-radius: 15px;
  }
  .tt33 {
    width: 99vw;
    height: 50px;
    font-size: 23pt;
    color: white;
    display: inline-block;
    text-align: center;
    background-color:#57a3f3;
  }
  .t2{
    margin-top: 100px;
  }
  .t3{

  }
  .t4{
    margin-top:10px;
    text-align: center;
    margin-bottom: 2vh;
  }
  .t5{
    /*
      height:51vh;
    */
  }
  .t6{
    width:6vw;
    font-size: 12pt;
    color: white;
    background-color: #57a3f3;
    border: none;
    float: right;
    padding-top: 1vh;
  }

  .foot {
    padding-top: 3vh;
    color: white;
    width: 100%;
    height:13vh;
    text-align: center;
    text-decoration: none;
    background-color:#57a3f3;
  }

  .line {
    width: 90%;
    margin-left: 5%;
    text-align: center;
  }
  .tt11{
    width:360px;
  }
.e1{
  height:571px;
}

</style>
<!--
logo-yen-->
