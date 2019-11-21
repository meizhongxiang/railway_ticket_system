<template>
  <div>
    <div class="t1">
      <div class="bb1">铁路客票系统</div></div>
      <div class="bb3">
        <p style="text-align: left;color:#57a3f3;font-size:13pt;">完善信息</p>
        <Form ref="form"  :rules="rule" :model="form">
          <div class="bb2">
            <FormItem label="用户姓名：" class="bb11" prop="userName">
              <Input type="text" class="bb5" v-model="form.userName" placeholder="请输入用户名"/>
            </FormItem>
            <FormItem label="用户账号：" style="width:600px;" prop="userMail">
              <Input type="text" readonly class="bb5" style="left:10px;" v-model="form.accountId" placeholder="请输入邮箱账号"/>
              <Button style="left:100px;" type="default" @click="sendMessage(form.accountId)">发送验证码</Button>
            </FormItem>
            <FormItem label="验证码：" class="bb11" prop="code">
              <Input type="text" style="width:80px;left:12px;" v-model="form.code" placeholder="邮箱验证码"/>
            </FormItem>
            <FormItem label="联系电话：" class="bb11" prop="userPhone">
              <Input type="text" class="bb5" v-model="form.userPhone" placeholder="请输入联系电话"/>
            </FormItem>
            <FormItem label="证件号码：" class="bb11" prop="cardId">
              <Input type="text" class="bb5" v-model="form.cardId" placeholder="请输入身份证号码"/>
            </FormItem>
            <FormItem label="银行卡号：" class="bb11" prop="userBankId">
              <Input type="text" class="bb5" v-model="form.userBankId" placeholder="请输入银行卡号"/>
            </FormItem>
            <FormItem label="预留手机：" class="bb11" prop="userTel">
              <Input type="text" class="bb5" v-model="form.userTel" placeholder="请输入银行卡预留手机号"/>
            </FormItem>
            <FormItem label="支付密码：" class="bb11" prop="payPassword">
              <Input type="text" class="bb5" v-model="form.payPassword" placeholder="请设置本平台支付密码，非银行卡密码"/>
            </FormItem>
          </div>

        </Form>
        <div style="text-align: center;">

          <Button style="width:100px;" type="default" @click="back"> 返回</Button>
          <Button style="width:100px;" type="primary" @click="handleSubmit">确定</Button>
           </div><br /><br />
    </div>
      <div style="text-align: center;">
        <div class="foot">
          版权所有© 梅忠翔 王芸 周智慧
          <br />2017-2019
        </div>
      </div>
  </div>
  <!--</div>-->
</template>

<script>
    export default {
        data() {
            return {
                status:1,
                form: {
                    userName: '',
                    accountId:localStorage.getItem("aid"),
                    code:'',
                    tpwd: '',
                    userPassword: '',
                    userPhone:'',
                    userBankId:'',
                    payPassword:'',
                    cardId:'',
                    userTel:''
                },
                rule: {
                    userName : [
                        { required: true, message: '请输入用户名！', trigger: 'blur' }
                    ],

                    code : [
                        {required: true,min:6, max:6, message:"请输入六位验证码", trigger:'blur'}

                    ],
                    cardId : [
                        { required: true,
                            pattern:/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,
                            type: 'string',
                            message: '证件号码为15或18位！', trigger: 'blur' }
                    ],
                    userPhone : [
                        { required: true,min:11, max:11,
                            pattern:/^1(3|4|5|6|7|8|9)\d{9}$/,
                            message: '手机号长度为11位！',
                            type: 'string',
                            trigger: 'blur' }
                            ],
                    userBankId : [
                        { required: true,min:19, max:19,type: 'string',pattern:/^6\d{18}$/, message: '银行卡号长度为19位！', trigger: 'blur' }],
                    userTel : [
                        { required: true,min:11, max:11,
                            pattern:/^1(3|4|5|6|7|8|9)\d{9}$/,
                            message: '手机号长度为11位！',
                            type: 'string', trigger: 'blur' }
                    ],
                    payPassword : [
                        { required: true,min:6, max:6, message: '支付密码长度为6位！', trigger: 'blur' },
                        { type: 'string', min: 6, message: '支付密码长度为6位', trigger: 'blur' }
                    ]
                }
            }
        },
        created() {
            if(localStorage.getItem("loginst") == 1){
                if(localStorage.getItem("status") == 1){
                    this.updateJump();
                }else if(localStorage.getItem("status") == 2){

                }else{
                    this.$router.push("/index");
                }
            }else{
              this.$router.push("/index");
            }
        },
        methods:{
            updateJump(){
                this.$Modal.error({
                    title: '系统提示',
                    content: '<p>' + "尊敬的用户，您已成功完成实名" + '</p>',
                    okText: '确认',
                    onOk: () => {
                        this.$router.push("/userDetail");
                    }
                })
            },
            back() {
                this.$router.push({path: 'index'})
            },
            handleSubmit(){
                if(this.$root.endTime >0){
                    this.$refs['form'].validate(valid => {
                        if (valid) {
                            var url=this.$root.url+'/proxy/1000004';
                            var param= this.form;
                            param.userBankId = this.form.userBankId.toString();
                            param.userId = localStorage.getItem("uid");
                                this.$axios({
                                    url: url,
                                    method: 'post',
                                    data: param,
                                    responseType:'json',
                                    withCredentials:true,
                                    headers:{
                                        "Access-Control-Allow-Origin":this.$root.uorigin,
                                        "Access-Control-Allow-Credentials":true,
                                    }
                                }).then(res => {
                                    var data = res.data;
                                    if(data.rspCode == "0000"){
                                        localStorage.setItem("status", data.userStatus);
                                        this.$Modal.success({
                                            title: '系统提示',
                                            content: '<p>' + "实名成功" +'</p>',
                                            okText: '确认',
                                            onOk: () => {
                                                this.$root.endTime=0;
                                                this.$router.push("/userDetail");
                                            }
                                        })
                                    }else{
                                        this.$Modal.error({
                                            title: '系统提示',
                                            content: '<p>' + data.rspDesc +'</p>',
                                            okText: '确认',
                                            onOk: () => {
                                            }
                                        })
                                    }
                                }).catch(err => {
                                    this.$Modal.error({
                                        title: '系统提示',
                                        content: '<p>'+ "网络错误，请稍后重试！" +'</p>',
                                        okText: '确认',
                                        onOk: () => {
                                            this.$root.endTime=0;
                                            this.$router.push("/index");
                                        }
                                    })
                                });
                        } else {
                            this.$Message.error('参数错误!');
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
            query(id,uid) {
                let url =this.$root.url+"/proxy/1000006";
                let params = {accountId:id,userId:uid};

                this.$axios({
                    url: url,
                    method: 'post',
                    data: params,
                    responseType:'json',
                    withCredentials:true,
                    headers:{
                        "Access-Control-Allow-Origin":this.$root.uorigin,
                        "Access-Control-Allow-Credentials":true,
                    }
                }).then(res => {
                    var data = res.data;
                    if(data.rspCode == "0000"){
                        this.form=data;
                    }else{
                        this.$Modal.error({
                            title: '系统提示',
                            content: '<p>' + data.rspDesc + '</p>',
                            okText: '确认',
                            onOk: () => {
                                this.$router.push("/index");
                            }
                        })
                    }
                }).catch(err => {
                    this.$Modal.error({
                        title: '系统提示',
                        content: '<p>'+ "查询错误，请稍后重试！" +'</p>',
                        okText: '确认',
                        onOk: () => {
                            this.$router.push("/index");
                        }
                    })
                });
            },
            handleReset(){
                this.$refs['form'].resetFields();
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
.bb3{
  text-align: center;
  margin-bottom: 13vh;
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

.userAdd .textare{
  width:480px;
}
.userAdd .f-single .ivu-form  .ivu-form-item  .ivu-form-item-label {
  width: 180px;
}
.userAdd .f-single .ivu-form  .ivu-form-item  .ivu-form-item-content {
  margin-left: 180px;
}

.bb1{
  width: 99vw;
  height: 50px;
  font-size: 23pt;
  color: white;
  display: inline-block;
  padding-left: 1%;
  text-align: center;
  background-color:#57a3f3;
}
.t1{
/*
  height: 100%;
*/
}
.bb2{
  margin-left: 39vw;
  margin-top: 10vh;
  width:35vw;
  text-align: left;
}
.bb5{
  width:210px;
}
  .bb11{
    width:320px;
  }
  .bb12{
    width:210px;
  }
</style>
