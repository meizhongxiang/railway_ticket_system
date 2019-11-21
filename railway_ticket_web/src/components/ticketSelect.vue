<template>
  <div>
    <div class="tt33"><span style="padding-left: 280px;">铁路客运售票中心</span>
      <Button @click="check" style="margin-top:1vh;float:right;border:none;font-size:14px;background: #57a3f3;color: white;">{{this.loginMessage}}</Button>
      <Button @click="checkOrder" style="margin-top:1vh;float:right;border:none;font-size:14px;background: #57a3f3;color: white;"><Icon size="20" type="ios-train-outline" />订单</Button>
      <Button @click="checkInfo" style="margin-top:1vh;float:right;border:none;font-size:14px;background: #57a3f3;color: white;">个人中心</Button>
       </div>
      <div class="t2">
        <Form class="conjia" ref="form" :rules="selectrule" inline :model="form">
          <FormItem label="出发地" style="width:280px;margin-left:200px;" prop="startSite">
            <Input type="text" style="width:210px;" v-model="form.startSite" placeholder="请输入出发地"/>
          </FormItem>
            <Button @click="take"><Icon size="20" type="ios-swap" /></Button>
          <FormItem label="到达地" style="width:280px;margin-left:15px;margin-right: 100px;" prop="endSite">
            <Input type="text" style="width:210px;" v-model="form.endSite" placeholder="请输入到达地"/>
          </FormItem>
          <FormItem label="出发日期" class="ff6" prop="startDate">
            <Row >
              <Col span="12"  >
                <DatePicker class="ff5" style="width:200px;" v-model="form.datet" type="date" @on-change="form.startDate=$event"  format="yyyy-MM-dd" placeholder="请选择日期"></DatePicker>
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
        <Modal v-model="modal1" width="400">
          <p slot="header" style="color:#f60;">
          </p>
          <Row :gutter="32">
            <Col span="24" class="demo-tabs-style1">
              <Tabs>
                <TabPane label="登录">
                  <Form ref="login" :model="login" :rules="ruleInline" class="signin" style="margin-top:30px">
                    <FormItem prop="accountId">
                      <Input type="email"  class="aa11" prefix="ios-person-outline" v-model="login.accountId" placeholder="输入邮箱">
                      </Input>
                      <p class="error-text" v-show="login.error.accountId">{{login.error.accountId}}</p>
                    </FormItem>
                    <FormItem prop="userPassword">
                      <Input type="password" class="aa11" prefix="ios-lock-outline" v-model="login.userPassword" placeholder="输入密码">
                      </Input>
                      <p class="error-text" v-show="login.error.userPassword">{{login.error.userPassword}}</p>
                    </FormItem>
                    <router-link id="12" to="userForgetPwd"><p style="text-align: right;">忘记密码？</p></router-link>

                    <FormItem style="text-align:center"  class="aa11">
                      <p class="error-text" v-show="login.error.all">{{login.error.all}}</p>
                      <Button type="primary" @click="loginSubmit" style="width:60%;font-size:16px">登录</Button>
                    </FormItem>
                  </Form>
                </TabPane>
                <TabPane label="注册">
                  <Form ref="register" :model="register" :rules="ruleInline" class="signup" style="margin-top:30px">
                    <FormItem prop="accountId">
                      <Input type="text" prefix="ios-person-outline" class="aa11"  v-model="register.accountId" placeholder="输入邮箱"
                             style="text-align:center">
                      </Input>
                      <p class="error-text marb8" v-show="register.error.accountId">{{register.error.accountId}}</p>
                    </FormItem>
                    <FormItem prop="code">
                      <div class="flex">

                        <Input type="text" prefix="md-mail"  class="aa11" v-model="register.code" placeholder="邮箱验证码"
                               style="text-align:center;">
                        </Input>
                        <Button type="primary" @click="sendMessage(register.accountId)">
                          {{register.getMessageText}}
                        </Button>
                      </div>
                      <p class="error-text marb8" v-show="register.error.code">{{register.error.code}}</p>
                    </FormItem>
                    <FormItem prop="userPassword">
                      <Input type="password" prefix="ios-lock-outline" class="aa11" v-model="register.userPassword" placeholder="输入密码">
                      </Input>
                      <p class="error-text marb8" v-show="register.error.userPassword">{{register.error.userPassword}}</p>
                    </FormItem>
                    <FormItem style="text-align:center"  class="aa11">
                      <p class="error-text marb8" v-show="register.error.error">{{register.error.error}}</p>
                      <Button type="primary" @click="registerSubmit" style="width:60%;font-size:16px">注册
                      </Button>
                    </FormItem>
                  </Form>
                </TabPane>

                <TabPane label="快捷登录">
                  <Form ref="fastLogin" :model="fastLogin" :rules="ruleInline" class="signin" style="margin-top:30px">
                    <FormItem prop="accountId">
                      <Input type="email" prefix="ios-person-outline" class="aa11" v-model="fastLogin.accountId" placeholder="输入邮箱">
                      </Input>
                      <p class="error-text" v-show="fastLogin.error.accountId">{{fastLogin.error.accountId}}</p>
                    </FormItem>
                    <FormItem prop="code">
                      <div class="flex">

                        <Input type="text" prefix="md-mail" class="aa11" v-model="fastLogin.code" placeholder="邮箱验证码"
                               style="text-align:center;">
                        </Input>
                        <Button type="primary" @click="sendMessage(fastLogin.accountId)">
                          {{fastLogin.getMessageText}}
                        </Button>
                      </div>
                      <p class="error-text marb8" v-show="fastLogin.error.code">{{fastLogin.error.code}}</p>
                    </FormItem>
                    <FormItem style="text-align:center"  class="aa11">
                      <p class="error-text" v-show="fastLogin.error.all">{{fastLogin.error.all}}</p>
                      <Button type="primary" @click="fastLogins" style="width:60%;font-size:16px">快捷登录</Button>
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
            title="订单确认"
            ok-text="支付"
            cancel-text="取消"
            @on-ok="modal2Ok()"
            @on-cancel="modal2Cancel">
            <p style="margin-left:180px;font-size: 13pt;">车&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;次：{{mondal2Value.trainNumber}}</p>
            <p style="margin-left:180px;font-size: 13pt;">出&nbsp;&nbsp;发&nbsp;&nbsp;地：{{mondal2Value.startSite}}</p>
            <p style="margin-left:180px;font-size: 13pt;">到&nbsp;&nbsp;达&nbsp;&nbsp;地：{{mondal2Value.endSite}}</p>
            <p style="margin-left:180px;font-size: 13pt;">出发时间：{{mondal2Value.startTime}}</p>
            <p style="margin-left:180px;font-size: 13pt;">到达时间：{{mondal2Value.endTime}}</p>
            <p style="margin-left:180px;font-size: 13pt;">用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;时：{{mondal2Value.useTime}}</p>
            <p style="margin-left:180px;font-size: 13pt;">坐&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;席：{{mondal2Value.cla}}</p>
            <p style="margin-left:180px;font-size: 13pt;">票&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价：{{mondal2Value.val}}</p>
            <Input type="password" style="margin-left:180px;width:150px;" prefix="ios-lock-outline" v-model="mondal2Value.payPwd" placeholder="请输入支付密码"></Input>

          </Modal>
        <div style="text-align: right;margin-right: 1%;">
          <Button type="primary" style="width:100px;" @click="query">查询</Button>
          <Button type="primary" style="width:100px;" @click="back">返回</Button>
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
                currentPage:1,
                status:0,
                loginMessage:"请登录",
                infoMessage:'',
                modal1: false,
                modal2:false,
                mondal2Value:'',
                data3:[
                ],
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
                        align: 'center'
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
                        title: '交易',
                        key: 'action',
                        width: 230,
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'primary',
                                        size: 'small',
                                        //disabled:params.row.trainNumber == this.upform.upTrainNumber ?true:false
                                        disabled:params.row.trainNumber == this.upform.upTrainNumber ?true:(params.row.classOne==0?true:false)
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            if(localStorage.getItem("loginst") != 1){
                                                this.modal1=true;
                                            }
                                            if(localStorage.getItem("status") != 1){
                                                this.checkInfo();
                                            }
                                            if(localStorage.getItem("loginst") == 1 && localStorage.getItem("status") == 1){
                                                this.mondal2Value = params.row;
                                                this.mondal2Value.cla = '一等座';
                                                this.mondal2Value.val = params.row.priceOne;
                                                this.modal2 = true;
                                            }
                                        }
                                    }
                                }, '一等座'),
                                h('Button', {
                                    props: {
                                        type: 'primary',
                                        size: 'small',
                                        //disabled:params.row.trainNumber == this.upform.upTrainNumber ?true:false
                                        disabled:params.row.trainNumber == this.upform.upTrainNumber ?true:(params.row.classTwo==0?true:false)
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            if(localStorage.getItem("loginst") != 1){
                                                this.modal1=true;
                                            }
                                            if(localStorage.getItem("status") != 1){
                                                this.checkInfo();
                                            }
                                            if(localStorage.getItem("loginst") == 1 && localStorage.getItem("status") == 1){
                                                this.mondal2Value = params.row;
                                                this.mondal2Value.cla = '二等座';
                                                this.mondal2Value.val = params.row.priceTwo;
                                                this.modal2 = true;
                                            }
                                        }
                                    }
                                }, '二等座')
                            ]);
                        }
                    }
                ],
                form: {
                    startSite: '',
                    endSite: '',
                    startDate:'',
                    status:'',
                    datet:''
                },
                upform: {
                    upStartDate:'',
                    upStatus:'',
                    upTrainNumber:'',
                    upTicketClass:''
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
                register: {
                    accountId: '',
                    userPassword: '',
                    code: '',
                    getMessageText: '发送验证码',
                    error: {
                        accountId: false,
                        userPassword: false,
                        code: false,
                        error: false
                    }
                },
                fastLogin:{
                    accountId: '',
                    code: '',
                    getMessageText: '发送验证码',
                    error: {
                        accountId: false,
                        code: false,
                        error: false
                    }
                },
                selectrule:{
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
                            message: '请选择日期',
                            trigger: 'blur'
                        }
                    ]
                },
                ruleInline: {
                    accountId : [
                        {
                            required: true,
                            pattern:/^\w+@[a-z0-9]+\.[a-z]{2,4}$/,
                            message: '请输入正确邮箱',
                            trigger: 'blur'
                        }
                    ],
                    code : [
                        {
                            required: true,
                            message: '请输入验证码',
                            trigger: 'blur'
                        }
                    ],
                    userPassword : [
                        {
                            required: true,
                            message: '请输入密码',
                            trigger: 'blur'
                        }
                    ]
                }
            }
        },
        created() {
            if(localStorage.getItem("loginst") == 1){
                if(this.$route.query.upStatus == 3){
                    this.upform.upStartDate=this.$route.query.startDate;
                    this.upform.upTrainNumber=this.$route.query.upTrainNumber;
                    this.upform.upStatus=this.$route.query.upStatus;
                    this.upform.upPrice=this.$route.query.upPrice;
                    this.upform.upTicketClass=this.$route.query.upTicketClass;
                }
                this.loginMessage="退出登录";
            }
            let par={};
            par.startSite=this.$route.query.startSite;
            par.endSite=this.$route.query.endSite;
            par.startDate=this.$route.query.startDate;
            par.status=this.$route.query.status;
            par.datet=this.$route.query.startDate;
            if(par.startSite != '' && par.endSite!='' && par.startDate!=''){
                this.form.startSite=this.$route.query.startSite;
                this.form.endSite=this.$route.query.endSite;
                this.form.startDate=this.$route.query.startDate;
                this.form.status=this.$route.query.status;
                this.form.datet=this.$route.query.startDate;
                this.createQuery(par);
            }
        },
        methods:{
            loginSubmit() {
                this.$refs['login'].validate((valid) => {
                    if (valid) {
                        let url = this.$root.url + "/proxy/1000001";
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
                                localStorage.setItem("aid", data.accountId);
                                localStorage.setItem("uid", data.userId);
                                localStorage.setItem("status", data.userStatus);
                                localStorage.setItem("loginst", data.loginst);
                                this.modal1 = false;
                                this.loginMessage='退出';
                                this.$Message.success('登录成功');

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
            fastLogins(){
                if(this.$root.endTime >0){
                    this.$refs['fastLogin'].validate((valid) => {
                        if (valid) {
                            let url = this.$root.url + "/proxy/1000002";
                            var params = this.fastLogin;
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
                                    localStorage.setItem("aid", data.accountId);
                                    localStorage.setItem("uid", data.userId);
                                    localStorage.setItem("status", data.userStatus);
                                    localStorage.setItem("loginst", data.loginst);
                                    this.modal1 = false;
                                    this.$router.push("/ticketSelect");
                                    this.$Message.success('登录成功');
                                } else {
                                    this.$Message.error(data.rspDesc);
                                }
                            }).catch(err => {
                                this.$root.endTime=0;
                                this.$Message.error('登录失败');
                            });
                        } else {
                            this.$Message.error('登录验证未通过!')
                        }
                    })
                }else{
                    this.$Modal.error({
                        title: '系统提示',
                        content: '<p>'+ "请先验证邮箱" + '</p>',
                        okText: '确认',
                        onOk: () => {
                        }
                    })
                }
            },
            registerSubmit() {
                if(this.$root.endTime >0){
                    this.$refs['register'].validate(valid => {
                        if (valid) {
                            var url=this.$root.url+'/proxy/1000003';
                            var param = this.register;
                            this.$http.post(url, param,{emulateJSON : false}).then(function(res){
                                var data = res.body;
                                if(data.rspCode == "0000"){
                                    this.$Modal.success({
                                        title: '系统提示',
                                        content: '<p>注册成功</p>',
                                        okText: '确认',
                                        onOk: () => {
                                            this.$root.endTime=0;
                                            this.modal1 = false;
                                            this.$router.push({path: '/ticketSelect'});
                                        }
                                    })
                                }else{
                                    this.$Modal.error({
                                        title: '系统提示',
                                        content: '<p>'+data.rspDesc+'</p>',
                                        okText: '确认',
                                        onOk: () => {
                                            this.$root.endTime=0;
                                            this.modal1 = false;
                                        }
                                    })
                                }
                            },function(res){
                                this.$Message.error('注册失败，请稍后重试！');
                                this.$root.endTime=0;
                                this.modal1 = false;
                            });
                        }
                    });
                }else{
                    this.$Modal.error({
                        title: '系统提示',
                        content: '<p>'+ "请先验证邮箱" + '</p>',
                        okText: '确认',
                        onOk: () => {
                        }
                    })
                }
            },
            sendMessage(id) {
                if(id !=''){
                    this.$root.uMail=id;
                    this.$root.etime =new Date();
                    if(this.$root.etime-this.$root.nowTime>60000 || this.$root.endTime==0){
                        this.$root.endTime=this.$root.endTime+1;
                        this.tim();
                        var url=this.$root.url+'/proxy/1000008';
                        var param = {accountId:id};
                        this.$http.post(url, param, {emulateJSON: false}).then(function (res) {
                            var data = res.body;
                            if (data.rspCode == "0000") {
                                this.$Modal.success({
                                    title: '系统提示',
                                    content: '<p>' +'验证码发送成功，请前往邮箱查看'+ '</p>',
                                    okText: '确认',
                                    onOk: () => {
                                    }
                                })
                            } else {
                                this.$Modal.error({
                                    title: '系统提示',
                                    content: '<p>' + data.rspDesc+ '</p>',
                                    okText: '确认',
                                    onOk: () => {
                                    }
                                })
                            }
                        }, function (res) {
                            this.$Message.error('发送失败，请稍后重试！');
                        });
                    }else{
                        this.$Modal.error({
                            title: '系统提示',
                            content: '<p>'+ "请在60秒后重试" + '</p>',
                            okText: '确认',
                        })
                    }
                }else{
                    this.$Modal.error({
                        title: '系统提示',
                        content: '<p>'+ "请先验证邮箱" + '</p>',
                        okText: '确认',
                    })
                }
            },
            tim(){
                this.$root.nowTime = new Date();
            },
            check(){
                if(localStorage.getItem("loginst") == 1){
                    this.loginMessage = '请登录';
                    localStorage.clear();
                }else{
                    this.modal1 = true;
                }
            },
            checkOrder(){
                if(localStorage.getItem("loginst") == 1){
                    let turl =this.$router.resolve({path: 'orderDetail',
                        query:{
                            accountId: localStorage.getItem("aid"),
                            userId: localStorage.getItem("uid")
                        }});
                    window.open(turl.href, '_blank');
                }else{
                    this.modal1 = true
                }
            },
            checkInfo(){
                if(localStorage.getItem("loginst") == 1){
                    let turl = this.$router.resolve({path: 'userDetail',
                        query:{
                            accountId: localStorage.getItem("aid"),
                            userId: localStorage.getItem("uid")
                        }});
                    window.open(turl.href, '_blank');
                }else{
                    this.modal1 = true
                }
            },
            cancelLogin(){
                var url=this.$root.url+'/proxy/1000009';
                var param = {accountId:localStorage.getItem("aid"),userId:localStorage.getItem("uid")};
                this.$http.post(url, param, {emulateJSON: false}).then(function (res) {
                    var data = res.body;
                    if (data.rspCode == "0000") {
                        localStorage.clear();
                        this.loginMessage="登录 | 注册";
                        this.$router.push("/index");
                    } else {
                        this.$Modal.error({
                            title: '系统提示',
                            content: '<p>' + data.rspDesc  + '</p>',
                            okText: '确认',
                            onOk: () => {
                            }
                        })
                    }
                }, function (res) {
                    this.$Message.error('退出失败，请稍后重试！');
                });
            },
            modal2Ok(){
                if(localStorage.getItem("loginst") != 1){
                    this.modal1=true;
                }
                if(localStorage.getItem("status") != 1){
                    this.checkInfo();
                }
                if(localStorage.getItem("loginst") == 1 && localStorage.getItem("status") == 1){
                    let url = this.$root.url + "/proxy/3000002";
                    var params = {
                        trainNumber:this.mondal2Value.trainNumber,
                        startDate:this.form.startDate,
                        startSite:this.mondal2Value.startSite,
                        endSite:this.mondal2Value.endSite,
                        startTime:this.mondal2Value.startTime,
                        endTime:this.mondal2Value.endTime,
                        useTime:this.mondal2Value.useTime,
                        ticketClass:(this.mondal2Value.cla == '一等座')?1:2,
                        price:this.mondal2Value.val,
                        payPassword:this.mondal2Value.payPwd,
                        ticketStatus:2,
                        userAccountId:localStorage.getItem("aid"),
                        userId:localStorage.getItem("uid")
                    };

                    if(this.upform.upStatus == 3){
                        params.updStartDate=this.upform.upStartDate;
                        params.updTrainNumber=this.upform.upTrainNumber;
                        params.ticketStatus=4;
                        params.updPrice=this.upform.upPrice;
                        params.updTicketClass=this.upform.upTicketClass;
                    }
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
                            let turl= this.$router.resolve({path: 'orderDetail',
                                query:{
                                    accountId: localStorage.getItem("aid"),
                                    userId: localStorage.getItem("uid")
                                },meta: {allowBack: false}});
                            if(this.upform.upStatus == 3){
                                window.open(turl.href, '_self');
                            }else{
                                window.open(turl.href, '_blank');
                            }

                        } else {
                            this.$Message.error(data.rspDesc);
                        }
                    }).catch(err => {
                        this.$Message.error('网络连接失败，请稍后重试');
                    });
                }
            },
            modal2Cancel(){},
            ok() {
            },
            cancel() {
            },
            back() {
                this.$router.push({path: 'index'})
            },
            flushThis(startTime){
                this.form.startTime = new Date(startTime.toString());
            },
            take(){
                let temp = this.form.startSite;
                this.form.startSite = this.form.endSite;
                this.form.endSite = temp;
            },
            change(){
                this.query();
            },
            query(){
                this.$refs['form'].validate((valid) => {
                    if(valid){
                        let url = this.$root.url + "/proxy/2000005";
                        this.form.page=this.currentPage;
                        this.$axios({
                            url: url,
                            method: 'post',
                            data: this.form,
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
                                this.form.startDate = data.startDate;
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
                    }else{
                        this.$Message.error('参数错误');
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
