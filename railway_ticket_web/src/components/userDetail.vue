<template>
  <div>
    <div class="t1">
      <div class="cd1">用户中心&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size:10pt;"><Icon type="md-arrow-dropright" />{{this.infoTitle}}</span>
        <Button @click="check(1)"
                style="margin-top:1px;float:right;border:none;font-size:10pt;background: #57a3f3;color: white;">
          {{this.loginMessage}}
        </Button>
        <Button @click="check(2)"
                style="margin-top:1px;float:right;border:none;font-size:10pt;background: #57a3f3;color: white;">
          <Icon size="20" type="ios-train-outline" />订单出行
        </Button>
      </div>
      <div class="c3">
        <div class="detaill">
          <Form ref="form" :model="form">
            <div class="c2">
              <FormItem label="用户姓名：" class="cc6" prop="userName">
                <Input type="text" readonly class="cc5" v-model="form.userName"/>
              </FormItem>
              <FormItem label="身份证号：" class="cc6" prop="cardId">
                <Input type="text" readonly class="cc5" v-model="form.cardId"/>
              </FormItem>
              <FormItem label="用户账号：" class="cc6" prop="userMail">
                <Input type="text" readonly class="cc5" v-model="form.accountId"/>
              </FormItem>
              <FormItem label="联系电话：" class="cc6" prop="userPhone">
                <Input type="text" readonly class="cc5" v-model="form.userPhone"/>
              </FormItem>
              <FormItem label="银行卡号：" class="cc6" prop="userBankId">
                <Input type="text" readonly class="cc5" v-model="form.userBankId"/>
              </FormItem>
              <FormItem label="账户等级：" class="cc6" prop="grade">
                <Input type="text" readonly class="cc5" v-model="form.grade"/>
              </FormItem>
              <FormItem label="账户余额：" class="cc6" prop="integral">
                <Input type="text" readonly class="cc5" v-model="form.integral"/>
              </FormItem>
              <div class="c-form" style="text-align:left;">
                <Button type="primary"  style="margin-left:100px;" @click="back"> 返回</Button>
              </div>
            </div>

          </Form>
        </div>
      </div>
      <div style="text-align: center;">
        <div class="foot">
          版权所有© 梅忠翔 王芸 周智慧
          <br/>2017-2019
        </div>
      </div>
    </div>
  </div>
</template>

<script>
    export default {
        data() {
            return {
                status: 1,
                infoTitle:'',
                loginMessage: "信息修改",
                form: {
                    userName: '',
                    accountId: '',
                    userPhone: '',
                    userBankId: '',
                    grade: '',
                    cardId: '',
                    integral: '',
                    createTime: ''
                },
            }
        },
        created() {
             if(localStorage.getItem("loginst") == 1){
                 this.infoTitle="   个人详情";
                 if(localStorage.getItem("status") != 1){
                     this.updateJump();
                 }else{
                     this.query(localStorage.getItem("aid"),localStorage.getItem("uid"));
                 }
             }else{
               this.$router.push("/index");
             }
        },
        methods: {
            check(id){
                if(id == 1){
                    this.$router.push("/userUpdate");
                }else if(id == 2){
                    let turl= this.$router.resolve({path: 'orderDetail',
                        query:{
                            accountId: localStorage.getItem("aid"),
                            userId: localStorage.getItem("uid")
                        }});
                    window.open(turl.href, '_blank');
                }else{}
            },
            updateJump(){
                this.$Modal.error({
                    title: '系统提示',
                    content: '<p>' + "尊敬的用户，系统为未实名用户开通了部分功能，为了您的出行顺利，请提前进行实名" + '</p>',
                    okText: '确认',
                    onOk: () => {
                        this.$router.push("/userRealName");
                    }
                })
            },
            back() {
                this.$router.push({path: 'index'})
            },
            query(id, uid) {
                let url = this.$root.url + "/proxy/1000006";
                let params = {accountId: id, userId: uid};
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
                        this.form = data;
                    } else {
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
                        content: '<p>' + "查询错误，请稍后重试！" + '</p>',
                        okText: '确认',
                        onOk: () => {
                            this.$router.push("/index");
                        }
                    })
                });
            }
        }
    }
</script>

<style scoped>
  .detaill form .ivu-input {
    border: 0px;
    box-shadow: none;
    font-size: 12px;
  }

  .c2 {
    margin-left: 39vw;
    margin-top: 10vh;
    width: 35vw;
    text-align: left;
  }

  .c5 {
    width: 15vw;
  }

  .cd1 {
    box-shadow: none;
    width: 99vw;
    height: 50px;
    font-size: 15pt;
    color: white;
    display: inline-block;
    padding-top: 7px;
    padding-left: 1%;
    text-align: left;
    background-color: #57a3f3;
  }

  .foot {
    padding-top: 3vh;
    color: white;
    width: 100%;
    height: 13vh;
    text-align: center;
    text-decoration: none;
    background-color: #57a3f3;
  }

  .c3 {
    text-align: center;
    margin-bottom: 19vh;
  }
  .cc5{
    width:210px;
  }
  .cc6{
    width:320px;
  }
</style>
