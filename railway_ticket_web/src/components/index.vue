<template>
  <div>
    <div>
      <div class="aa1"><h1>铁路客票</h1></div>
      <div class="aa222"></div>
      <div class="aa222"></div>
      <div class="aa222"></div>
      <div class="aa222"></div>
      <div class="aa3">
        <Button @click="check" style="margin-bottom:1vh;font-size:14px;background: white;color: #57a3f3;"><Icon size="20" type="ios-train-outline" />订单</Button>
        <Button v-if="this.isshow" @click="checkInfo" style="margin-bottom:1vh;font-size:14px;background: white;color: #57a3f3;">{{this.infoMessage}}</Button>
        <Button @click="checkLogin" style="margin-bottom:1vh;font-size:14px;background: white;color: #57a3f3;">{{this.loginMessage}}</Button>
      </div>
    </div>

    <div style="background-color: #57a3f3;">
      <div class="aa2"></div>
      <div class="aa2"></div>
      <div class="aa2">首页</div>
      <div class="aa2">
        <router-link class="aa5" id="2" to="">车票服务</router-link>
      </div>
      <div class="aa2">
        <router-link class="aa5" id="3" to="">团购车票</router-link>
      </div>
      <div class="aa2">
        <router-link class="aa5" id="4" to="">会员服务</router-link>
      </div>
      <div class="aa2">
        <router-link class="aa5" id="5" to="">商旅服务</router-link>
      </div>
      <div class="aa2">
        <router-link class="aa5" id="6" to="">出行指南</router-link>
      </div>
      <div class="aa2">
        <router-link class="aa5" id="7" to="">信息查询</router-link>
      </div>
      <div class="aa2"></div>
      <div class="aa2"></div>
    </div>

    <div>
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
    </div>
    <div class="aa8">
        <Form ref="form" :model="form">
          <FormItem label="出 发 地 " prop="startSite" style="width:300px;">
            <Input type="text" style="width:200px;margin-left:5px;" v-model="form.startSite" placeholder="请输入出发地"/>
          </FormItem>
            <Button style="margin-top: -20px;"  @click="take"><Icon size="20" type="ios-swap" /></Button>
          <FormItem label="到 达 地 " prop="endSite" style="width:300px;">
            <Input type="text" style="width:200px;margin-left:5px;" v-model="form.endSite" placeholder="请输入到达地"/>
          </FormItem>
          <FormItem label="出发日期" style="width:300px;">
            <Row>
              <Col span="12">
                <DatePicker style="width:200px;" @on-change="form.startDate=$event"  format="yyyy-MM-dd" type="date" placeholder="请选择日期" ></DatePicker>
              </Col>
            </Row>
          </FormItem>
          <FormItem label=""  prop="status" style="width:300px;">
            <RadioGroup v-model="status" style="width:300px;">
              <RadioGroup v-model="form.status">
                <Radio  label='0'>到时最早</Radio>
                <Radio  label='1'>发时最早</Radio>
                <Radio  label='2'>低价格在前</Radio>
              </RadioGroup>
            </RadioGroup>
          </FormItem>
          <div style="text-align: center;margin-right: 1%;">
            <Button type="primary" style="width:100px;" @click="query">查询</Button>
            <br/>
            <br/>
          </div>
        </Form>
    </div>
    <div>
      <Carousel autoplay>
        <Carousel-item>
          <img class="demo-img" src="../assets/1.jpg"/>
        </Carousel-item>
        <Carousel-item>
          <img class="demo-img" src="../assets/02.jpg"/>
        </Carousel-item>
        <Carousel-item>
          <img class="demo-img" src="../assets/3.jpg"/>
        </Carousel-item>
        <Carousel-item>
          <img class="demo-img" src="../assets/4.jpg"/>
        </Carousel-item>
      </Carousel>
    </div>

    <div style="text-align: center;margin-top: 15px;">
      <div class="aa6">
        <img class="aa7" src="../assets/10.jpg"/>
      </div>
      <div class="aa6">
        <img class="aa7" src="../assets/11.jpg"/>
      </div><br/>
      <div class="aa6">
        <img class="aa7" src="../assets/8.jpg"/>
      </div>
      <div class="aa6">
        <img class="aa7" src="../assets/9.jpg"/>
      </div>
    </div>
    <div style="text-align: center;">
      <div class="foot">
        课程设计<br/>
        版权所有© 梅忠翔 王芸 周智慧
        <br />2019-11-23
      </div>
    </div>
  </div>
</template>
<script>
    export default {
        name: "normalModal",
        data() {
            return {
                isshow:false,
                infoMessage:'用户中心',
                loginMessage:'登录 | 注册',
                modal1: false,
                status:0,
                form: {
                    startSite: '',
                    endSite: '',
                    startDate: '',
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
        created(){
            if(localStorage.getItem("loginst") == 1){
                this.isshow=true;
                this.loginMessage="退出登录";
            }else{
                this.isshow=false;
            }
        },
        methods: {
            checkLogin(){
                if(localStorage.getItem("loginst") == 1){
                    localStorage.clear();
                    this.isshow=false;
                    this.loginMessage="登录 | 注册";
                    //this.cancelLogin();
                }else{
                    this.modal1 = true;
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
                            this.isshow=true;
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
                                this.isshow=true;
                                this.loginMessage="退出登录";
                                this.$Message.success('登录成功');
                                this.$router.push("/index");
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
                                  this.isshow=true;
                                  this.loginMessage="退出登录";
                                  this.$Message.success('登录成功');
                                  this.$router.push("/index");
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
                                                this.$router.push({path: '/index'});
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
                if( localStorage.getItem("loginst") == 1){
                    let turl= this.$router.resolve({path: 'orderDetail',
                        query:{
                            accountId: localStorage.getItem("aid"),
                            userId: localStorage.getItem("uid")
                        }});
                    window.open(turl.href, '_blank');
                }else{
                    this.modal1=true;
                }
            },
            checkInfo(){
                if( localStorage.getItem("loginst") == 1){
                    let turl= this.$router.resolve({path: 'userDetail',
                        query:{
                            accountId: localStorage.getItem("aid"),
                            userId: localStorage.getItem("uid")
                        }});
                    window.open(turl.href, '_blank');
                }else{
                    this.modal1=true;
                }
            },
            take(){
                let temp = this.form.startSite;
                this.form.startSite = this.form.endSite;
                this.form.endSite = temp;
            },
            query(){
                this.$router.push({path: 'ticketSelect',
                    query:{
                        startSite: this.form.startSite,
                        endSite: this.form.endSite,
                        startDate: this.form.startDate,
                        status:this.form.status
                }})
            }
        }
    }
</script>
<style scoped>
  .aa1 {
    width: 22vw;
    margin-left: 20vw;
    padding-top: 2vh;
    display: inline-block;
    height: 8vh;
    font-size: 15px;
  }

  .aa2 {
    display: inline-block;
    padding: 10px 5px 0px 0px;
    width: 10vw;
    color: white;
    font-size: 13pt;
  }
  .aa222 {
    display: inline-block;
    padding: 10px 5px 0px 0px;
    width: 9.5vw;
    color: white;
    font-size: 13pt;
  }
  .aa3 {
    display: inline-block;
    padding: 10px 5px 0px 0px;
    width: 271px;
    font-size: 13pt;
  }

  .aa5 {
    color: white;
  }
  .aa6 {
    display: inline-block;
    margin-right: 1vw;
    margin-top: 1vw;
  }
  .aa7{
    width: 29vw;
    height: 17vh;
  }
  .aa8{
    width:350px;
    float:right;
    background-color: white;
    position: absolute;
    top: 20vh;
    left: 19vw;
    z-index:10;
    padding-top: 3vh;
    padding-left: 3vh;
    border-radius: 5px;
  }
  .aa11{
    width:360px;
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
  .aa8 .ivu-form-item-content {
    width:30vw;
  }
</style>

<!--
<Button type="primary"  @click="modal1 = true">普通组件使用方法</Button>
<Modal
  v-model="modal1"
  title="普通组件使用方法"
  @on-ok="ok"
  @on-cancel="cancel">
  <h1>普通组件使用方法</h1>

</Modal>

        data() {
            return {        modal1: false,

            }
        },
        methods: {
            ok() {},
            cancel(){},




2
 //弹窗组件
            // <Button @click="handleRender">实例化使用方法</Button>
            handleRender () {
                this.$Modal.confirm({
                    render: (h) => {
                        return h('Input', {
                            props: {
                                value: this.value,
                                autofocus: true,
                                placeholder: 'Please enter your name...'
                            },
                            on: {
                                input: (val) => {
                                    this.value = val;
                        }
                    }
                })
                }
            })
            }




-->
