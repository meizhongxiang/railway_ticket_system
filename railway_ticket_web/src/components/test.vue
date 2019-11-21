<template>
  <div style="background-color: #8ce6b0">
    <Button @click="modal1 = true" style="font-size:14px;background: #393D49;color:black;">登录 注册</Button>
    <Modal v-model="modal1" width="400">
      <p slot="header" style="color:#f60;text-align:center">
      </p>
      <Row :gutter="32">
        <Col span="24" class="demo-tabs-style1">
          <Tabs>
            <TabPane label="登录">
              <Form ref="Login" :model="Login" :rules="ruleInline" class="signin" style="margin-top:30px">
                <FormItem prop="mobile">
                  <Input type="text" v-model="Login.mobile" placeholder="输入手机号" > <!--@focus="errorUnshow"-->
                    <Icon type="ios-person-outline" slot="prepend"></Icon>
                  </Input>
                  <p class="error-text" v-show="Login.error.mobile">{{Login.error.mobile}}</p>
                </FormItem>
                <FormItem prop="password">
                  <Input type="password" v-model="Login.password" placeholder="输入密码" > <!--@focus="errorUnshow"-->
                    <Icon type="ios-locked-outline" slot="prepend"></Icon>
                  </Input>
                  <p class="error-text" v-show="Login.error.password">{{Login.error.password}}</p>
                </FormItem>
                <FormItem style="text-align:center">
                  <p class="error-text" v-show="Login.error.all">{{Login.error.all}}</p>
                  <Button type="primary" @click="loginSubmit('Login')" style="width:60%;font-size:16px">登录</Button>
                </FormItem>
              </Form>

              <!--<ul class="form other-form flexa">
                <li>
                  <h5>使用第三方帐号登录</h5>
                </li>
                <li class="other-login">
                  <a class="qq" href="http://shop.1234.com:8001/login/qq/"><img src="../assets/5.jpg" alt="" class="qqwximg"></a>
                  <a class="sina" href="http://shop.1234.com:8001/login/weibo/"><img src="../assets/5.jpg" alt="" class="qqwximg"></a>
                  <a class="weixin" href="http://shop.1234.com:8001/login/weixin/"><img src="../assets/5.jpg" alt="" class="qqwximg"></a>
                </li>
              </ul>-->
            </TabPane>
            <TabPane label="注册">
              <Form ref="Register" :model="Register" :rules="ruleInline" class="signup" style="margin-top:30px">
                <FormItem prop="mobile">
                  <Input type="text" v-model="Register.mobile" placeholder="输入手机号" style="text-align:center">
                    <Icon type="ios-person-outline" slot="prepend"></Icon>
                  </Input>
                  <p class="error-text marb8" v-show="Register.error.mobile">{{Register.error.mobile}}</p>
                </FormItem>
                <FormItem prop="code">
                  <div class="flex">
                    <Input type="text" v-model="Register.code" placeholder="输入获取的验证码" style="text-align:center"></Input>

                    <Button type="ghost" @click="seedMessage" style="background:#ff6060;color:#fff">{{Register.getMessageText}}</Button>
                  </div>
                  <p class="error-text marb8" v-show="Register.error.code">{{Register.error.code}}</p>
                </FormItem>
                <FormItem prop="password">
                  <Input type="password" v-model="Register.password" placeholder="输入密码">
                    <Icon type="ios-locked-outline" slot="prepend"></Icon>
                  </Input>
                  <p class="error-text marb8" v-show="Register.error.password">{{Register.error.password}}</p>
                </FormItem>
                <FormItem style="text-align:center">
                  <p class="error-text marb8" v-show="Register.error.error">{{Register.error.error}}</p>
                  <Button type="primary" @click="registerSubmit('Register')" style="width:60%;font-size:16px">注册</Button>
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
</template>
<script>
    export default {
        data() {
            return {
                modal1:false,
                Login:{
                    mobile:'',
                    password:'',
                    error:{
                        mobile:false,
                        password:false,
                        all:false
                    }
                },
                Register:{
                    mobile:'',
                    password:'',
                    code:'',
                    getMessageText:'',
                    error:{
                        mobile:false,
                        password:false,
                        code:false,
                        error:false
                    }
                },
                ruleInline:{

                }
            }
        },
        methods: {
            loginSubmit(){

            },
            seedMessage(){},
            registerSubmit(){}
        },

    }
</script>
<style>
  /这里加上scoped就不起作用/
  .ivu-tabs-nav {
    left: 50%;
    transform: translateX(-50%);
    font-size: 20px;
  }
  /手机端模态框/
  .qqwximg {
    height: 38px;
  }
  .error-text {
    color: #fa8341;
  }
</style>
