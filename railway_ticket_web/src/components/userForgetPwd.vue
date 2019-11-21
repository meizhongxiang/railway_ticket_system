<template>
  <div>
    <div class="t1">
      <div class="d1">铁路客运售票中心
      </div>
      <div class="d3">
        <p style="text-align: left;color:#57a3f3;font-size:13pt;">找回密码</p>
        <Form ref="form"  :rules="rule" :model="form">
          <div class="d2">
            <FormItem label="用户账号：" style="width:500px;" prop="accountId">
              <Input type="text"  class="dd7"  v-model="form.accountId" placeholder="请输入邮箱账号"/>
              <Button  type="default" @click="sendMessage(form.accountId)">发送验证码</Button>
            </FormItem>
            <FormItem label="验证码：" class="dd6" prop="code">
              <Input type="text" style="width:80px;left:11px;" v-model="form.code" placeholder="邮箱验证码"/>
            </FormItem>
            <FormItem label="证件号码：" class="dd6" prop="cardId">
              <Input type="text" class="dd7" v-model="form.cardId" placeholder="请输入身份证号码"/>
            </FormItem>
            <FormItem label="新密码：" class="dd6" prop="userPassword">
              <Input type="password" style="left:12px;" class="dd7" v-model="form.userPassword" placeholder="请输入密码"/>
            </FormItem>
            <FormItem label="确认密码：" class="dd6" prop="newPassword">
              <Input type="password"  class="dd7" v-model="form.newPassword" placeholder="请重新输入密码"/>
            </FormItem>
          </div>
        </Form>
        <div style="text-align: center;">
          <Modal
            v-model="modal2"
            title="找回密码确认"
            ok-text="确定"
            cancel-text="点错了"
            @on-ok="handleSubmit"
            @on-cancel="cancel1">
            <p style="font-size:17pt;color:red;">修改密码后请用新密码登录，确定要修改密码吗？</p>

          </Modal>
          <Button style="width:100px;" type="default" @click="back"> 返回</Button>

          <Button style="width:100px;" type="primary" @click="modal2 = true">确定</Button>
           </div>
    </div>
      <div style="text-align: center;">
        <div class="foot">
          版权所有© 梅忠翔 王芸 周智慧
          <br />2017-2019
        </div>
      </div>
  </div>
  </div>
</template>

<script>
    export default {
        data() {
            return {
                modal2:false,
                status:1,
                loginMessage:"个人中心",
                form: {
                    accountId:'',
                    code:'',
                    userPassword: '',
                    cardId:'',
                    newPassword:''
                },
                rule: {
                    accountId : [
                        {
                            required: true,
                            pattern:/^\w+@[a-z0-9]+\.[a-z]{2,4}$/,
                            message: '请输入正确邮箱',
                            trigger: 'blur'
                        }

                    ],
                    code : [
                        {required: true,min:6, max:6, message:"请输入六位验证码", trigger:'blur'}

                    ],
                    cardId : [
                        { required: true, min:18, max:18,message: '请输入身份证号码', trigger: 'blur' },
                        { type: 'string', min: 18, message: '证件号码为18位', trigger: 'blur' }
                    ],
                    userPassword : [
                        { required: true, min:6, max:8,message: '请输入密码！', trigger: 'blur' },
                        { type: 'string', min: 6, message: '密码长度不能小于6位', trigger: 'blur' }
                    ],
                    newPassword : [
                        { required: true, min:6, max:8,message: '请输入密码！', trigger: 'blur' },
                        { type: 'string', min: 6, message: '密码长度不能小于6位', trigger: 'blur' }
            ]
                }
            }
        },
        created() {},
        methods:{
            ok1() {},
            cancel1(){},
            back() {
                this.$router.push({path: 'index'})
            },
            handleSubmit(){
                if(this.$root.endTime >0){
                    this.$refs['form'].validate(valid => {
                        if (valid) {
                            var url=this.$root.url+'/proxy/1000007';
                            var param= this.form;
                            if (param.userPassword==param.newPassword) {
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
                                        this.$Modal.success({
                                            title: '系统提示',
                                            content: '<p>' + "密码找回成功" +'</p>',
                                            okText: '确认',
                                            onOk: () => {
                                                this.$root.endTime=0;
                                                this.$router.push("/index");
                                            }
                                        })
                                    }else{
                                        this.$Modal.error({
                                            title: '系统提示',
                                            content: '<p>' + data.rspDesc+ '</p>',
                                            okText: '确认',
                                            onOk: () => {
                                            }
                                        })
                                    }
                                }).catch(err => {
                                    this.$Modal.error({
                                        title: '系统提示',
                                        content: '<p>'+ "网络错误，请联系管理员！" +'</p>',
                                        okText: '确认',
                                        onOk: () => {
                                            this.$root.endTime=0;
                                            this.$router.push("/index");
                                        }
                                    })
                                });
                            }else{
                                this.$Message.error('两次密码输入不一致!');
                            }
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
.d3{
  text-align: center;
  margin-bottom: 44vh;
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
.d1{
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
.d2{
  margin-left: 39vw;
  margin-top: 10vh;
  width:35vw;
  text-align: left;
}
.d5{
  width:15vw;
}
.dd7{
  width:210px;
}
.dd6{
  width:320px;
}
</style>
