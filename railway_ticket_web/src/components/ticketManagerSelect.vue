<template>
  <div>
      <div class="tt33"><span style="padding-left: 130px;">铁路客票管理系统</span>
      <Button @click="check" style="margin-top:1vh;float:right;border:none;font-size:14px;background: #57a3f3;color: white;">{{this.loginMessage}}</Button>
      <Button @click="checkTicketAdd" style="margin-top:1vh;float:right;border:none;font-size:14px;background: #57a3f3;color: white;"><Icon size="20" type="ios-train-outline" />添加</Button>
       </div>
      <div class="t2">
        <Form class="conjia" ref="form" :rules="ruleInline" inline :model="form">
          <FormItem label="出发地" style="width:280px;margin-left:200px;" prop="startSite">
            <Input type="text" style="width:210px;" v-model="form.startSite" placeholder="请输入出发地"/>
          </FormItem>
            <Button @click="take"><Icon size="20" type="ios-swap" /></Button>
          <FormItem label="到达地" style="width:280px;margin-left:15px;margin-right: 100px;" prop="endSite">
            <Input type="text" style="width:210px;" v-model="form.endSite" placeholder="请输入到达地"/>
          </FormItem>
          <FormItem label="出发日期：" class="ff6" prop="startDate">
            <Row >
              <Col span="12"  >
                <DatePicker class="ff5" style="width:200px;" type="date" @on-change="form.startDate=$event"  format="yyyy-MM-dd" placeholder="请选择日期"></DatePicker>
              </Col>
            </Row>
          </FormItem>
          <br />
          <FormItem label="" prop="status" style="width:500px;margin-left:200px;">
            <RadioGroup v-model="form.status">
              <Radio style="width:130px;" label='0'>到时最早</Radio>
              <Radio style="width:130px;" label='1'>发时最早</Radio>
              <Radio style="width:130px;" label='2'>低价格在前</Radio>
            </RadioGroup>
          </FormItem>
        </Form>
        <Modal v-model="modal1" :closable="false" :mask-closable="false" width="400">
          <p slot="header" style="color:#f60;">
          </p>
          <Row :gutter="32">
            <Col span="24" class="demo-tabs-style1">
              <Tabs>
                <TabPane label="登录">
                  <Form ref="login" :model="login" :rules="rulel" class="signin" style="margin-top:30px">
                    <FormItem prop="accountId">
                      <Input type="text"  class="aa11" prefix="ios-person-outline" v-model="login.accountId" placeholder="请输入登录名">
                      </Input>
                      <p class="error-text" v-show="login.error.accountId">{{login.error.accountId}}</p>
                    </FormItem>
                    <FormItem prop="userPassword">
                      <Input type="password" class="aa11" prefix="ios-lock-outline" v-model="login.userPassword" placeholder="请输入账户密码">
                      </Input>
                      <p class="error-text" v-show="login.error.userPassword">{{login.error.userPassword}}</p>
                    </FormItem>

                    <FormItem style="text-align:center"  class="aa11">
                      <p class="error-text" v-show="login.error.all">{{login.error.all}}</p>
                      <Button type="primary" @click="loginSubmit" style="width:60%;font-size:16px">登录</Button>
                    </FormItem>
                  </Form>
                </TabPane>
              </Tabs>
            </Col>
          </Row>
          <div slot="footer" style="text-align:center">
            <p></p>
          </div>
        </Modal>
        <Modal
          v-model="modal2"
          value="mondal2Value"
          title="票价修改"
          ok-text="确认"
          cancel-text="取消"
          @on-ok="modal2Ok"
          @on-cancel="modal2Cancel">
          <p style="margin-left:180px;font-size: 13pt;">车&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;次：{{mondal2Value.trainNumber}}</p><br />
          <p style="margin-left:130px;width:200px;font-size: 9pt;">一等座：<Input type="text" style="width:150px;"  v-model="mondal2Value.newPriceOne" :placeholder="mondal2Value.priceOne"></Input></p><br />
          <p style="margin-left:130px;width:200px;font-size: 9pt;">二等座：<Input type="text" style="width:150px;"  v-model="mondal2Value.newPriceTwo" :placeholder="mondal2Value.priceTwo"></Input></p>
        </Modal>
        <Modal
          v-model="modal3"
          value="mondal3Value"
          title="余量修改"
          ok-text="确认"
          cancel-text="取消"
          @on-ok="modal2Ok"
          @on-cancel="modal2Cancel">
          <p style="margin-left:180px;font-size: 13pt;">车&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;次：{{mondal3Value.trainNumber}}</p><br />
          <p style="margin-left:130px;width:200px;font-size: 9pt;">一等座：<Input type="string" style="width:150px;"  v-model="mondal3Value.newSumOne" :placeholder="mondal3Value.classOne"></Input></p><br />
          <p style="margin-left:130px;width:200px;font-size: 9pt;">二等座：<Input type="string" style="width:150px;"  v-model="mondal3Value.newSumTwo" :placeholder="mondal3Value.classTwo"></Input></p>

        </Modal>
        <div style="text-align: right;margin-right: 1%;">
          <Button type="primary" style="width:100px;" @click="change">查询</Button>
          <Button type="primary" style="width:100px;" @click="checkGo(1)">实时运营</Button>
          <Button type="primary" style="width:100px;" @click="checkGo(2)">系统运行监控</Button>
        </div><br />
      </div>
      <div class="t3">
        <div class="t5">
          <Table  border :columns="columns3" :data="data3"></Table>
          <br>
        </div>

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
                upStartDate:'',
                currentPage:1,
                status:0,
                modalStatus:0,
                loginMessage:localStorage.getItem("managertatus") == 1 ? '退出': '请登录',
                modal1: false,
                modal2:false,
                mondal2Value:{
                    newPriceOne:'',
                    newPriceTwo:'',
                },
                modal3:false,
                mondal3Value:{
                    newSumOne:'',
                    newSumTwo:'',
                },
                data3:[],
                dataCount:10,
                columns3: [
                    {
                        title: '序号',
                        type: 'index',
                        align: 'center'
                    },
                    {
                        title: '车次',
                        key: 'trainNumber',
                        align: 'center',
                    },
                    {
                        title: '出发地',
                        width: 180,
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
                        width: 180,
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
                        title: '一等座',
                        align: 'center',
                        children: [
                            {
                                title: '票价',
                                key: 'priceOne',
                                align: 'center',
                            },
                            {
                                title: '余量',
                                key: 'classOne',
                                align: 'center',
                            }
                        ]

                    },
                    {
                        title: '二等座',
                        align: 'center',
                        children: [
                            {
                                title: '票价',
                                key: 'priceTwo',
                                align: 'center',
                            },
                            {
                                title: '余量',
                                key: 'classTwo',
                                align: 'center',
                            }
                        ]
                    },
                    {
                        title: '用时',
                        width: 110,
                        align: 'center',
                        key: 'useTime'
                    },
                    {
                        title: '操作',
                        key: 'action',
                        width: 230,
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'primary',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            this.mondal2Value = params.row;
                                            this.mondalStatus = 1;
                                            this.modal2 = true;
                                        }
                                    }
                                }, '修改票价'),
                                h('Button', {
                                    props: {
                                        type: 'primary',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            this.mondal3Value = params.row;
                                            this.mondalStatus = 2;
                                            this.modal3 = true;
                                        }
                                    }
                                }, '修改余量'),
                                h('Button', {
                                    props: {
                                        type: 'error',
                                        size: 'small'
                                    },
                                    on: {
                                        click: () => {
                                            this.trainDelete(params.row);
                                        }
                                    }
                                }, '删除')
                            ]);
                        }
                    }
                ],
                form: {
                    startSite: '',
                    endSite: '',
                    startDate:'',
                    status:''
                },
                tempForm: {
                    startSite: '',
                    endSite: '',
                    startDate:'',
                    status:''
                },
                login: {
                    accountId: '',
                    userPassword: '',
                    code:'',
                    error: {
                        accountId: false,
                        userPassword: false,
                        all: false
                    }
                },
                ruleInline: {
                    startSite : [
                        {
                            required: true,
                            message: '请输入出发地',
                            trigger: 'blur'
                        }
                    ],
                    endSite : [
                        {
                            required: true,
                            message: '请输入到达地',
                            trigger: 'blur'
                        }
                    ],
                    startDate : [
                        {
                            required: true,
                            message: '请输入出发日期',
                            trigger: 'blur'
                        }
                    ]
                },
                rulel: {
                    accountId : [
                        {
                            required: true,
                            message: '请输入用户名',
                            trigger: 'blur'
                        }
                    ],
                    userPassword : [
                        {
                            required: true,
                            message: '请输入账户密码',
                            trigger: 'blur'
                        }
                    ]
                }
            }
        },
        created() {
            if(localStorage.getItem("managertatus") != '1'){
                this.modal1 = true;
                this.loginMessage = '请登录';
            }else{
                if(this.tempForm.startDate !='' && this.tempForm.startSite !='' && this.tempForm.endSite !=''){
                    let par={}
                    par.startDate=this.tempForm.startDate;
                    par.startSite=this.tempForm.startSite;
                    par.endSite=this.tempForm.endSite;
                    par.status=this.tempForm.status;
                    par.page=this.tempForm.currentPage;
                    this.createQuery(par);
                }
            }
        },
        methods:{
            checkGo(p){
                if(p == 1){
                    if(this.form.startDate != '' && this.form.startSite != '' && this.form.endSite != ''){
                        let turl= this.$router.resolve({path: 'financeMonitor',
                            query:{
                                startDate:this.form.startDate,
                                startSite : this.form.startSite,
                                endSite : this.form.endSite
                            }});
                        window.open(turl.href, '_blank');
                    }else{
                        let turl= this.$router.resolve({path: 'financeMonitor',
                            query:{
                            }});
                        window.open(turl.href, '_blank');
                    }
                }else if(p == 2){
                    let turl= this.$router.resolve({path: 'systemMonitor',
                        query:{
                        }});
                    window.open(turl.href, '_blank');

                }else{}
            },
            loginSubmit() {
                this.$refs['login'].validate((valid) => {
                    if (valid) {
                        let url = this.$root.url + "/proxy/2000006";
                        var params = this.login;
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
                                localStorage.setItem("managertatus", '1');
                                this.$Message.success(data.rspDesc);
                                this.modal1 = false;
                                this.loginMessage="退出";
                            } else {
                                this.$Message.error(data.rspDesc);
                            }
                        }).catch(err => {
                            this.$Message.error('登录失败');
                        });
                    } else {
                        this.$Message.error('登录验证未通过!')
                    }
                })
            },
            check(){
                if(localStorage.getItem("managertatus") == '1'){
                    this.data3=[];
                    this.dataCount=10;
                    this.login.accountId= '';
                    this.login.userPassword= '';
                    this.loginMessage="请登录";
                    localStorage.clear();
                    this.$Message.success('退出成功');
                    this.modal1 = true
                }else{
                    this.modal1 = true
                }
            },
            checkTicketAdd(){
                this.$router.push({path: 'ticketAdd'})
            },
            modal2Ok(){
                if(this.mondalStatus == 1/* && this.mondal2Value.newPriceOne >= 0 && this.mondal2Value.newPriceTwo >= 0*/){
                    if(/^([1-9][0-9]*)$/.test(this.mondal2Value.newPriceOne) && /^([1-9][0-9]*)$/.test(this.mondal2Value.newPriceTwo)){
                        let url = this.$root.url + "/proxy/2000003";
                        var params = {
                            userAccountId:localStorage.getItem("aid"),
                            userId:localStorage.getItem("uid"),
                            userAccountId:localStorage.getItem("aid"),
                            userId:localStorage.getItem("uid"),
                            trainNumber:this.mondal2Value.trainNumber,
                            priceOne:this.mondal2Value.newPriceOne,
                            priceTwo:this.mondal2Value.newPriceTwo,
                            startDate:this.upStartDate
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
                            } else {
                                this.$Message.error(data.rspDesc);
                            }
                        }).catch(err => {
                            this.$Message.error('网络连接失败，请稍后重试');
                        });
                    }else{
                        this.$Message.error("参数必须为整数");
                    }
                }else if(this.mondalStatus ==2/* && this.mondal3Value.newSumOne >=0 && this.mondal3Value.newSumTwo >= 0*/){
                    if(/^([1-9][0-9]*)$/.test(this.mondal3Value.newSumOne) && /^([1-9][0-9]*)$/.test(this.mondal3Value.newSumTwo)){
                        let url = this.$root.url + "/proxy/2000004";
                        var params = {
                            userAccountId:localStorage.getItem("aid"),
                            userId:localStorage.getItem("uid"),
                            trainNumber:this.mondal3Value.trainNumber,
                            classOne:this.mondal3Value.newSumOne,
                            classTwo:this.mondal3Value.newSumTwo,
                            startDate:this.upStartDate
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
                            } else {
                                this.$Message.error(data.rspDesc);
                            }
                        }).catch(err => {
                            this.$Message.error('网络连接失败，请稍后重试');
                        });
                    }else{
                        this.$Message.error("状态错误");
                    }
                }else{
                    this.$Message.error("参数不能为负数");
                }
            },
            modal2Cancel(){},
            ok() {

            },
            trainDelete(parma){
                let url = this.$root.url + "/proxy/2000002";
                var params = {
                    userAccountId:localStorage.getItem("aid"),
                    userId:localStorage.getItem("uid"),
                    startDate:this.upStartDate,
                    trainNumber:parma.trainNumber
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
                    } else {
                        this.$Message.error(data.rspDesc);
                    }
                }).catch(err => {
                    this.$Message.error('网络连接失败，请稍后重试');
                });
            },
            cancel() {
            },
            take(){
                let temp = this.form.startSite;
                this.form.startSite = this.form.endSite;
                this.form.endSite = temp;
            },
            change(){
                let parma=[];
                parma.startDate = this.form.startDate;
                parma.startSite = this.form.startSite;
                parma.endSite = this.form.endSite;
                parma.status = this.form.status;
                parma.currentPage = this.currentPage;
                this.query(parma);
            },
            query(parma){
                this.$refs['form'].validate((valid) => {
                    if (valid) {
                        let url = this.$root.url + "/proxy/2000005";
                        let params = {
                            startDate: parma.startDate,
                            startSite: parma.startSite,
                            endSite: parma.endSite,
                            status: parma.status,
                            page: parma.currentPage
                        };
                        this.tempForm.startDate=params.startDate;
                        this.tempForm.startSite= params.startSite;
                        this.tempForm.endSite= params.endSite;
                        this.tempForm.status= params.status;
                        this.tempForm.page= params.currentPage;

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
                                this.dataCount = 10;
                                this.data3 = data.row;
                                this.dataCount = data.dataCount;
                                this.upStartDate=data.startDate;
                            } else {
                                this.data3 = [];
                                this.dataCount = 10;
                                this.$Modal.error({
                                    title: '系统提示',
                                    content: '<p>' + data.rspDesc + '</p>',
                                    okText: '确认',
                                    onOk: () => {
                                    }
                                })
                            }
                        }).catch(err => {
                            this.data3 = [];
                            this.dataCount = 10;
                            this.$Modal.error({
                                title: '系统提示',
                                content: '<p>' + "查询错误，请稍后重试！" + '</p>',
                                okText: '确认',
                                onOk: () => {
                                }
                            })
                        });
                    }else{
                        this.$Message.error('参数不完整');
                    }
              })
            },
            createQuery(parma){
                        let url = this.$root.url + "/proxy/2000005";
                        let params = {
                            startDate: parma.startDate,
                            startSite: parma.startSite,
                            endSite: parma.endSite,
                            status: parma.status,
                            page: parma.currentPage
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
                                this.dataCount = 10;
                                this.data3 = data.row;
                                this.dataCount = data.dataCount;
                                this.upStartDate=data.startDate;
                            } else {
                                this.data3 = [];
                                this.dataCount = 10;
                                this.$Modal.error({
                                    title: '系统提示',
                                    content: '<p>' + data.rspDesc + '</p>',
                                    okText: '确认',
                                    onOk: () => {
                                    }
                                })
                            }
                        }).catch(err => {
                            this.data3 = [];
                            this.dataCount = 10;
                            this.$Modal.error({
                                title: '系统提示',
                                content: '<p>' + "查询错误，请稍后重试！" + '</p>',
                                okText: '确认',
                                onOk: () => {
                                }
                            })
                        });
            }
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


</style>
<!--
logo-yen-->
